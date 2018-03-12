//***********************************************************************************************
// File Name: SplashScreen
// Authors: Christopher Farfan (101067074) & Esaac Ahn (100836038)
// Date: March 11th, 2018
// Assignment 1 submission.
//
// Description:
// Splash screen that loads the Play Screen after 4 seconds
//
//  Revision History:
//  Can be found on https://github.com/bluCraze/SlotMachine/commits/master
//***********************************************************************************************
package com.example.esaacahn.slotmachine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class SplashScreen extends Activity {

    private static int TIME_OUT = 4000; //Time to launch the another activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, PlayScreen.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}
