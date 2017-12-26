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

  private float radius;
  private Paint basicPaint;
  private Paint eraserPaint;
  private boolean calculateRadius;
  private int backgroundColor;
  private Target target;

  public MaterialShowcaseDrawer(Resources resources, boolean calculateRadius) {
    this(resources);
    this.calculateRadius = calculateRadius;
  }

  public MaterialShowcaseDrawer(Resources resources) {
    this.radius = resources.getDimension(R.dimen.showcase_radius_material);
    this.eraserPaint = new Paint();
    this.eraserPaint.setColor(0xFFFFFF);
    this.eraserPaint.setAlpha(0);
    this.eraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
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
    bufferCanvas.drawCircle(x, y, radius, eraserPaint);
  }

  @Override
  public int getShowcaseWidth() {
    return (int) (radius * 2);
  }

  @Override
  public int getShowcaseHeight() {
    return (int) (radius * 2);
  }

  @Override
  public float getBlockedRadius() {
    return radius;
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
    if (calculateRadius && target != null && target.getView() != null) {
      View view = target.getView();
      radius = Math.max(view.getMeasuredHeight(),view.getMeasuredWidth())/2;
    }
  }
}
