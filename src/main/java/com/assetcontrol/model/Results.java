package com.assetcontrol.model;

import org.springframework.stereotype.Component;

@Component
public class Results {

    private int errorCount;
    private int warningCount;
    private int infoCount;

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public int getWarningCount() {
        return warningCount;
    }

    public void setWarningCount(int warningCount) {
        this.warningCount = warningCount;
    }

    public int getInfoCount() {
        return infoCount;
    }

    public void setInfoCount(int infoCount) {
        this.infoCount = infoCount;
    }

    @Override
    public String toString() {
        return "Results{" +
                "errorCount=" + errorCount +
                ", warningCount=" + warningCount +
                ", infoCount=" + infoCount +
                '}';
    }
}