package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Random;

public class MainActivity extends Activity {


    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =1 ;
    Button sendBtn;
    EditText txtphoneNo;
    String phoneNo;
    String message;
    Random Number;
    int Rnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = (Button) findViewById(R.id.btnSendSMS);
        txtphoneNo = (EditText) findViewById(R.id.editText);
        Number = new Random();
        Rnumber = Number.nextInt(10000);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SendSms();
            }
        });
    }

    private void SendSms() {
        phoneNo = txtphoneNo.getText().toString();
        message = String.valueOf(Rnumber);

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
            else
                {
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(phoneNo, null, message, null, null);
                }
        }

    }

