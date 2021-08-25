package com.kilomind.screenrecorder.settings;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings.System;
import android.widget.Toast;

public class ShowTouchesController {
    private final String SHOW_TOUCHES_SETTING = "show_touches";

    private Context context;
    private ContentResolver contentResolver;

    public ShowTouchesController(Context context) {
        this.context = context;
        contentResolver = context.getContentResolver();
    }

    public boolean getShowTouches() {
        int setting = System.getInt(contentResolver, SHOW_TOUCHES_SETTING, 0);
        return setting == 1;
    }

    public void setShowTouches(boolean show) {
        if (getShowTouches() != show) {
            try {
                System.putInt(contentResolver, SHOW_TOUCHES_SETTING, show ? 1 : 0);
            }catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
