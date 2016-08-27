package com.cpb.katerynalevytska.sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by katerynalevytska on 8/13/16.
 */
public class About extends Activity {

    TextView aboutInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        aboutInfo = (TextView) findViewById(R.id.aboutTV);

    }
}
