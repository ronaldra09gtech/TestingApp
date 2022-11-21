package com.example.uiautomatorscripttest.Utility;


import android.util.Log;

import com.example.uiautomatorscripttest.Interfaces.GlobalVariables;

public class DataHolder {
    private static DataHolder dataObject = null;

    private DataHolder() {
    }

    public static DataHolder getInstance() {
        if (dataObject == null)
            dataObject = new DataHolder();
        return dataObject;
    }
    private String failureReason = "";


    public static DataHolder getDataObject() {
        return dataObject;
    }

    public static void setDataObject(DataHolder dataObject) {
        DataHolder.dataObject = dataObject;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
        Log.d(GlobalVariables.Tag_Name, this.failureReason);
    }


    public void clear(){
        this.failureReason = "";
    }
}