package com.kilomind.screenrecorder;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kilomind.screenrecorder.settings.Settings;

public class NoRootModeActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Settings.initialize(this);
        super.onCreate(savedInstanceState);
        Settings.getInstance().setRootEnabled(false);
        Intent intent = new Intent(this, RecorderService.class);
        intent.setAction(RecorderService.LOUNCHER_ACTION);
        startService(intent);
        finish();
    }
}
