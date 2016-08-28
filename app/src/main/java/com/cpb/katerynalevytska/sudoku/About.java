package com.cpb.katerynalevytska.sudoku;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by katerynalevytska on 8/13/16.
 */
public class About extends Activity {

    TextView aboutInfo;
    TextView aboutTitile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        aboutInfo = (TextView) findViewById(R.id.aboutTV);
        aboutTitile = (TextView) findViewById(R.id.aboutTitle);

        Typeface type = Typeface.createFromAsset(getAssets(), "my-font.ttf");
        aboutInfo.setTypeface(type);
        aboutTitile.setTypeface(type);

    }
}
