package com.kilomind.screenrecorder.audio;

import android.content.Context;

import static com.kilomind.screenrecorder.audio.InstallationStatus.INSTALLATION_FAILURE;
import static com.kilomind.screenrecorder.audio.InstallationStatus.INSTALLED;
import static com.kilomind.screenrecorder.audio.InstallationStatus.INSTALLING;

public class InstallAsyncTask extends InstallationAsyncTask {

    public InstallAsyncTask(Context context, AudioDriver audioDriver, long installId) {
        super(context, audioDriver, installId, INSTALLING);
    }

    @Override
    protected InstallationStatus doInBackground(Void... params) {
        return getInstaller().install() ? INSTALLED : INSTALLATION_FAILURE;
    }
}
