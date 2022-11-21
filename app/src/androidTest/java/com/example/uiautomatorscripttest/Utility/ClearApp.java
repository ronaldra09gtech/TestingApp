package com.example.uiautomatorscripttest.Utility;
import android.util.Log;
import com.example.uiautomatorscripttest.Interfaces.GlobalVariables;
import java.io.DataOutputStream;

public class ClearApp {

    public static void clearCache(String packageName) {
        try {
            Process install = Runtime.getRuntime().exec("su\n");
            DataOutputStream os = new DataOutputStream(install.getOutputStream());
            os.writeBytes("adb shell\n");
            os.writeBytes("su\n");
            os.writeBytes(" rm -rf /data/data/" + packageName + "/cache/* \n");
            os.writeBytes("exit\n");
            os.flush();
            os.close();
            Log.d(GlobalVariables.Tag_Name, "Cleared Cache Successfully");

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Clearing Cache");
            e.printStackTrace();
        }
    }

    // Clear Data
    public static void clearData(String packageName) {
        try {
            Process install = Runtime.getRuntime().exec("su\n");
            DataOutputStream os = new DataOutputStream(install.getOutputStream());
            os.writeBytes("adb shell\n");
            os.writeBytes("su\n");
            os.writeBytes("pm clear " + packageName + "\n");
            os.writeBytes("exit\n");
            os.flush();
            os.close();
            Log.d(GlobalVariables.Tag_Name, "Successfully cleared the data and cache");
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Clearing data ");
            e.printStackTrace();
        }
    }
}

