package com.github.amlcurran.showcaseview.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.espian.showcaseview.sample.R;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.drawer.MaterialShowcaseDrawer;
import com.github.amlcurran.showcaseview.drawer.RectangleShowcaseDrawer;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

public class BottomButtonActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bottom_button);

    ViewTarget target = new ViewTarget(R.id.editText, this);

    ShowcaseView showcaseView = new ShowcaseView.Builder(this)
      .withMaterialShowcase()
      .setTarget(target)
      .setContentTitle(R.string.showcase_main_title)
      .setContentText(R.string.showcase_main_message)
      .setStyle(R.style.CustomShowcaseTheme4)
      //.setShowcaseDrawer(new RectangleShowcaseDrawer(getResources()))
      .setShowcaseDrawer(new MaterialShowcaseDrawer(getResources(), true))
      //.replaceEndButton(R.layout.view_custom_button)
      .setButtonAutoPositioning(true)
      .build();
/*
    RelativeLayout.LayoutParams lps = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    lps.addRule(RelativeLayout.ALIGN_PARENT_TOP);
    lps.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
    int margin = ((Number) (getResources().getDisplayMetrics().density * 12)).intValue();
    lps.setMargins(margin, margin, margin, margin);

    showcaseView.setButtonPosition(lps);
    */
  }
}
