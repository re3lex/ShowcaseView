package com.github.amlcurran.showcaseview.drawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.View;

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

  public RectangleShowcaseDrawer() {
    this(10);
  }

  public RectangleShowcaseDrawer(int margin) {
    this.margin = margin;
    this.eraserPaint = new Paint();
    this.eraserPaint.setColor(0xFFFFFF);
    this.eraserPaint.setAlpha(0);
    this.eraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
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
    renderRect.left = x - width / 2f;
    renderRect.right = x + width / 2f;
    renderRect.top = y - height / 2f;
    renderRect.bottom = y + height / 2f;
    bufferCanvas.drawRect(renderRect, eraserPaint);
  }

  @Override
  public int getShowcaseWidth() {
    return (int) width;
  }

  @Override
  public int getShowcaseHeight() {
    return (int) height;
  }

  @Override
  public float getBlockedRadius() {
    return width;
  }

  @Override
  public void setBackgroundColour(int backgroundColor) {
    this.backgroundColor = backgroundColor;
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