package com.kilomind.screenrecorder.audio;

import android.content.Context;

import com.kilomind.screenrecorder.settings.Settings;

import static com.kilomind.screenrecorder.audio.InstallationStatus.CHECKING;
import static com.kilomind.screenrecorder.audio.InstallationStatus.NOT_INSTALLED;

public class CheckInstallationAsyncTask extends InstallationAsyncTask {
    public CheckInstallationAsyncTask(Context context, AudioDriver audioDriver) {
        super(context, audioDriver, null, CHECKING);
    }

    @Override
    protected InstallationStatus doInBackground(Void... params) {
        if (!Settings.getInstance().isRootFlavor()) {
            return NOT_INSTALLED;
        }
        return getInstaller().checkStatus();
    }
}
