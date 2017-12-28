package com.github.amlcurran.showcaseview.drawer;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.View;

import com.github.amlcurran.showcaseview.R;
import com.github.amlcurran.showcaseview.ShowcaseDrawer;
import com.github.amlcurran.showcaseview.targets.Target;

public class RectangleShowcaseDrawer implements ShowcaseDrawer {

  private int margin;
  private Paint eraserPaint;
  private Paint basicPaint;
  private RectF renderRect;
  private int backgroundColor;
  private int height;
  private int width;
  private Target target;
  private int borderColor;
  private float borderWidth;

  public RectangleShowcaseDrawer(Resources resources) {
    this(resources, 10);
  }

  public RectangleShowcaseDrawer(Resources resources, int margin) {
    this.borderWidth = resources.getDimension(R.dimen.showcase_radius_material_outer_width);
    this.margin = margin;
    this.eraserPaint = new Paint();
    this.eraserPaint.setColor(0xFFFFFF);
    //this.eraserPaint.setAlpha(0);
    this.eraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
    this.eraserPaint.setAntiAlias(true);
    this.basicPaint = new Paint();
    renderRect = new RectF();
  }

  @Override
  public void setShowcaseColour(int color) {

  }

  @Override
  public void drawShowcase(Bitmap buffer, float x, float y, float scaleMultiplier) {
    Canvas bufferCanvas = new Canvas(buffer);
    renderRect.left = x - width / 2f - borderWidth;
    renderRect.right = x + width / 2f + borderWidth;
    renderRect.top = y - height / 2f - borderWidth;
    renderRect.bottom = y + height / 2f + borderWidth;
    this.eraserPaint.setColor(borderColor);
    bufferCanvas.drawRect(renderRect, eraserPaint);

    eraserPaint.setAlpha(0);
    renderRect.left = x - width / 2f;
    renderRect.right = x + width / 2f;
    renderRect.top = y - height / 2f;
    renderRect.bottom = y + height / 2f;
    bufferCanvas.drawRect(renderRect, eraserPaint);
  }

  @Override
  public int getShowcaseWidth() {
    return (int) (width + 2 * borderWidth);
  }

  @Override
  public int getShowcaseHeight() {
    return (int) (height + borderWidth * 2);
  }

  @Override
  public float getBlockedRadius() {
    return width + borderWidth;
  }

  @Override
  public void setBackgroundColour(int backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  @Override
  public void setBorderColour(int borderColor) {
    this.borderColor = borderColor;
  }

  @Override
  public void setBorderWidth(float borderWidth) {
    this.borderWidth = borderWidth;
  }

  @Override
  public void erase(Bitmap bitmapBuffer) {
    bitmapBuffer.eraseColor(backgroundColor);
  }

  @Override
  public void drawToCanvas(Canvas canvas, Bitmap bitmapBuffer) {
    canvas.drawBitmap(bitmapBuffer, 0, 0, basicPaint);
  }

  @Override
  public void setTarget(Target target) {
    this.target = target;
    height = target.getView().getMeasuredHeight() + margin * 2;
    width = target.getView().getMeasuredWidth() + margin * 2;
  }
}