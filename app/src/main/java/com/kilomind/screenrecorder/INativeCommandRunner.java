package com.kilomind.screenrecorder;

public interface INativeCommandRunner {
    String getSuVersion();
    boolean isExecBlocked();
    String getExecutable();
    boolean runCommand(String command, int requestId, String args);
}
