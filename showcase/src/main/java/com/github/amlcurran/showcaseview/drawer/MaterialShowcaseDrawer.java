package com.github.amlcurran.showcaseview.drawer;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.View;

import com.github.amlcurran.showcaseview.R;
import com.github.amlcurran.showcaseview.ShowcaseDrawer;
import com.github.amlcurran.showcaseview.targets.Target;

public class MaterialShowcaseDrawer implements ShowcaseDrawer {

  private float borderWidth;
  private float radius;
  private Paint basicPaint;
  private Paint eraserPaint;
  private boolean calculateRadius;
  private int backgroundColor;
  private Target target;
  private int borderColor;

  public MaterialShowcaseDrawer(Resources resources, boolean calculateRadius) {
    this(resources);
    this.calculateRadius = calculateRadius;
  }

  public MaterialShowcaseDrawer(Resources resources) {
    this.radius = resources.getDimension(R.dimen.showcase_radius_material);
    this.borderWidth = resources.getDimension(R.dimen.showcase_radius_material_outer_width);
    this.eraserPaint = new Paint();
    this.eraserPaint.setColor(0xFFFFFF);
   // this.eraserPaint.setAlpha(0);
    this.eraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
    this.eraserPaint.setAntiAlias(true);
    this.basicPaint = new Paint();
  }

  @Override
  public void setShowcaseColour(int color) {
    // no-op
  }

  @Override
  public void drawShowcase(Bitmap buffer, float x, float y, float scaleMultiplier) {
    Canvas bufferCanvas = new Canvas(buffer);
    this.eraserPaint.setColor(borderColor);
    //this.eraserPaint.setAlpha(0);


    bufferCanvas.drawCircle(x, y, radius+borderWidth, eraserPaint);

    eraserPaint.setAlpha(0);
    bufferCanvas.drawCircle(x, y, radius, eraserPaint);
  }

  @Override
  public int getShowcaseWidth() {
    return (int) ((radius + borderWidth) * 2);
  }

  @Override
  public int getShowcaseHeight() {
    return (int) ((radius + borderWidth) * 2);
  }

  @Override
  public void setBorderWidth(float borderWidth) {
    this.borderWidth = borderWidth;
  }

  @Override
  public float getBlockedRadius() {
    return radius + borderWidth;
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
    if (calculateRadius && target != null && target.getView() != null) {
      View view = target.getView();
      radius = Math.max(view.getMeasuredHeight(), view.getMeasuredWidth()) / 2 + borderWidth;
    }
  }
}
