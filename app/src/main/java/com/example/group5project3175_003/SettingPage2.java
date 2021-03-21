package com.example.group5project3175_003;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class SettingPage2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page2);

        UserDatabase  userdatabase = new UserDatabase(this);
        SharedPreferences loginId = getSharedPreferences("loginId",MODE_PRIVATE);
        String loginID = loginId.getString("loginId","");
        ImageView btnTracker_set1 = findViewById(R.id.btnTracker_set1);
        ImageView btnBig_set1 = findViewById(R.id.btnBig_set1);
        ImageView btnReport_set1 = findViewById(R.id.btnReport_set1);
        ImageView btnSetting_set1 = findViewById(R.id.btnSetting_set1);
        Button button = findViewById(R.id.btnSettingChange);
        int positionId = Integer.parseInt(loginID)-1;
        TextView setFName = findViewById(R.id.setFName);
        TextView setLName = findViewById(R.id.setLName);
        TextView setEmail = findViewById(R.id.setEmail);
        TextView setPsw = findViewById(R.id.setPsw);
        TextView setAcName = findViewById(R.id.setAcName);
        Switch setAlarm = findViewById(R.id.setAlarm);
        Cursor c = userdatabase.viewData();
        c.moveToPosition(positionId);
         String alarm = c.getString(6);


        setAlarm.setChecked(alarm.equals("On"));
//        setAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                if (isChecked){
//                    Alarm = "On";
//                }
//                else{
//                    Alarm = "Off";
//                }
//            }
//        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sFName = setFName.getText().toString();
                String sLName = setLName.getText().toString();
                String sEmail = setEmail.getText().toString();
                String sPsw = setPsw.getText().toString();
                String sAcName = setAcName.getText().toString();

                SQLiteDatabase db = userdatabase.getWritableDatabase();
                ContentValues values = new ContentValues();
                if (!sAcName.isEmpty()) {
                    values.put("Account_Name", sAcName);
                }
                if (!sFName.isEmpty()) {
                    values.put("FName", sFName);
                }
                if (!sLName.isEmpty()) {
                    values.put("LName", sLName);
                }
                if (!sEmail.isEmpty()) {
                    values.put("Email", sEmail);
                }
                if (!sPsw.isEmpty()) {
                    values.put("Password", sPsw);
                }
                if (setAlarm.isChecked()) {
                    values.put("Alarm","On");
                }
                else{
                    values.put("Alarm","Off");
                }
                db.update("Userinf",values,"Id=?",new String[]{loginID});
            }
        });





        btnTracker_set1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingPage2.this, MainActivity.class));
            }
        });

        btnBig_set1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingPage2.this, bigExpensePage1.class));
            }
        });

        btnReport_set1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingPage2.this, reportMain.class));
            }
        });

        btnSetting_set1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingPage2.this, SettingPage1.class));
            }
        });
    }
}