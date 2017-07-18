package com.ecar.epark.hpknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.happy.annotaion.DIView;
import com.happy.base.RoadKnife;


public class MainActivity2 extends AppCompatActivity {

    @DIView(R.id.tx_content)
    TextView tx_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RoadKnife.bindView(this);
        tx_content.setText("显示2文字");

    }
}
