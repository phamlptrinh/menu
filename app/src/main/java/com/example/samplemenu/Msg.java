package com.example.samplemenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Msg extends AppCompatActivity {
    TextView txMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        txMsg = (TextView) findViewById(R.id.txMsg);
        Intent intent = getIntent();
        String msg = intent.getStringExtra("msg");
        txMsg.setText(msg);
    }
}