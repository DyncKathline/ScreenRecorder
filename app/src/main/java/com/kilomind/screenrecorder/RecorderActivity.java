package com.kilomind.screenrecorder;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kilomind.screenrecorder.settings.Settings;

import java.util.List;

public class RecorderActivity extends AppCompatActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Settings.initialize(this);
        super.onCreate(savedInstanceState);
        PermissionUtil.getInstance().with(this).requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, new PermissionUtil.PermissionListener() {
            @Override
            public void onGranted() {

            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                PermissionUtil.getInstance().showDialogTips(getBaseContext(), deniedPermission, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
            }

            @Override
            public void onShouldShowRationale(List<String> deniedPermission) {

            }
        });
        if (savedInstanceState == null && GusherDialogFragment.shouldShow(this)) {
            new GusherDialogFragment().show(getFragmentManager(), GusherDialogFragment.FRAGMENT_TAG);
        } else {
            Intent intent = new Intent(this, RecorderService.class);
            intent.setAction(RecorderService.LOUNCHER_ACTION);
            startService(intent);
            finish();
        }
    }
}
