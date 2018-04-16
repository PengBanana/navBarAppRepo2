package com.example.hp.navbarapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class hello extends AppCompatActivity {
    EditText et_hello;
    TextView tv_hello;
    private CheckBox cb_update;
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        et_hello = (EditText) findViewById(R.id.et_hello);
        tv_hello = (TextView) findViewById(R.id.tv_hello);
        cb_update = (CheckBox) findViewById(R.id.cb_autoupdate);
        btn_send = (Button) findViewById(R.id.btn_change);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_hello.setText(et_hello.getText().toString());
            }
        });
        et_hello.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv_hello.setText("Output goes here");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(cb_update.isChecked()){
                    tv_hello.setText(et_hello.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
