package com.example.hp.navbarapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class LifeCycleActivity extends AppCompatActivity {

    private TextView onCreateNum;
    private TextView onStartNum;
    private TextView onResumeNum;
    private TextView onPauseNum;
    private TextView onStopNum;
    private TextView onDestroyNum;

    private TextView onCreateTime;
    private TextView onStartTime;
    private TextView onResumeTime;
    private TextView onPauseTime;
    private TextView onStopTime;
    private TextView onDestroyTime;

    private int createNum=0;
    private int startNum=0;
    private int resumeNum=0;
    private int pauseNum=0;
    private int stopNum=0;
    private int destroyNum=0;
    private String createTime="No Time Set";
    private String startTime="No Time Set";
    private String resumeTime="No Time Set";
    private String pauseTime="No Time Set";
    private String stopTime="No Time Set";
    private String destroyTime="No Time Set";
    String timeFormat = "HH:mm";
    SimpleDateFormat tf = new SimpleDateFormat(timeFormat);
    private long longTime;
    String currentTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        createTime = getTime();
        createNum++;
        assignUI();
        SharedPreferences prefs = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        if (prefs.contains("prefSave")) {
            assignMyPreferences();
        }
        setUI();
    }

    private void assignMyPreferences() {
        SharedPreferences prefs = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        createNum=prefs.getInt("createNum", 0);
        startNum=prefs.getInt("startNum", 0);
        resumeNum=prefs.getInt("resumeNum", 0);
        pauseNum=prefs.getInt("pauseNum", 0);
        stopNum=prefs.getInt("stopNum", 0);
        destroyNum=prefs.getInt("destroyNum", 0);
        createTime=prefs.getString("createTime", "No Time Set");
        startTime=prefs.getString("startTime", "No Time Set");
        resumeTime=prefs.getString("resumeTime", "No Time Set");
        pauseTime=prefs.getString("pauseTime", "No Time Set");
        stopTime=prefs.getString("stopTime", "No Time Set");
        destroyTime=prefs.getString("destroyTime", "No Time Set");
    }

    @Override
    protected void onStart() {
        super.onStart();
        startNum++;
        startTime=getTime();
        setUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeNum++;
        resumeTime=getTime();
        setUI();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseNum++;
        pauseTime=getTime();
        setUI();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopNum++;
        stopTime=getTime();
        setUI();
        savePreferences();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyNum++;
        destroyTime=getTime();
        savePreferences();
    }

    private void assignUI() {
        onCreateNum = (TextView) findViewById(R.id.onCreateNum);
        onStartNum = (TextView) findViewById(R.id.onStartNum);
        onResumeNum = (TextView) findViewById(R.id.onResumeNum);
        onPauseNum = (TextView) findViewById(R.id.onPauseNum);
        onStopNum = (TextView) findViewById(R.id.onStopNum);
        onDestroyNum = (TextView) findViewById(R.id.onDestroyNum);

        onCreateTime = (TextView) findViewById(R.id.onCreateTime);
        onStartTime = (TextView) findViewById(R.id.onStartTime);
        onResumeTime = (TextView) findViewById(R.id.onResumeTime);
        onPauseTime = (TextView) findViewById(R.id.onPauseTime);
        onStopTime = (TextView) findViewById(R.id.onStopTime);
        onDestroyTime = (TextView) findViewById(R.id.onDestroyTime);
    }

    private void setUI() {
        onCreateNum.setText(createNum+"");
        onCreateTime.setText(createTime);
        onStartNum.setText(startNum+"");
        onStartTime.setText(startTime);
        onResumeNum.setText(resumeNum+"");
        onResumeTime.setText(resumeTime);
        onPauseNum.setText(pauseNum+"");
        onPauseTime.setText(pauseTime);
        onStopNum.setText(stopNum+"");
        onStopTime.setText(stopTime);
        onDestroyNum.setText(destroyNum+"");
        onDestroyTime.setText(destroyTime);
    }

    private String getTime(){
        longTime=System.currentTimeMillis();
        currentTime=tf.format(longTime);
        return currentTime;
    }
    /*
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("createNum", createNum);
        outState.putInt("startNum", startNum);
        outState.putInt("resumeNume", resumeNum);
        outState.putInt("pauseNum", pauseNum);
        outState.putInt("stopNum", stopNum);
        outState.putInt("destroyNum", destroyNum);

        outState.putString("createTime", createTime);
        outState.putString("startTime", startTime);
        outState.putString("resumeTime", resumeTime);
        outState.putString("pauseTime", pauseTime);
        outState.putString("stopTime", stopTime);
        outState.putString("destroyTime", destroyTime);
    }
    */
    private void savePreferences(){
        SharedPreferences prefs = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("prefSave", 1);
        editor.putInt("createNum", createNum);
        editor.putInt("startNum", startNum);
        editor.putInt("resumeNume", resumeNum);
        editor.putInt("pauseNum", pauseNum);
        editor.putInt("stopNum", stopNum);
        editor.putInt("destroyNum", destroyNum);

        editor.putString("createTime", createTime);
        editor.putString("startTime", startTime);
        editor.putString("resumeTime", resumeTime);
        editor.putString("pauseTime", pauseTime);
        editor.putString("stopTime", stopTime);
        editor.putString("destroyTime", destroyTime);
        editor.commit();
    }
}
