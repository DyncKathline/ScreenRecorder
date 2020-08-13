package com.kilomind.screenrecorder;

public interface IScreenOverlay {
    void show();
    void hide();
    void onDestroy();
    boolean isVisible();
}
