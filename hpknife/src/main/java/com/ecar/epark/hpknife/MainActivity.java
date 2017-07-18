package com.ecar.epark.hpknife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.happy.HelloWord;
import com.happy.annotaion.DIView;
import com.happy.base.RoadKnife;


public class MainActivity extends AppCompatActivity {

    @DIView(R.id.tx_content)
    TextView tx_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RoadKnife.bindView(this);
        tx_content.setText("显示文字");
        startActivity(new Intent(this,MainActivity2.class));
    }
}
