package com.kilomind.screenrecorder;

public interface IRecorderService {
    void startRecording();
    void stopRecording();
    void close();

    void showSettings();
}
