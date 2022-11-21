package com.example.uiautomatorscripttest;

import android.content.Context;
import android.content.Intent;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Until;
import android.util.Log;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.uiautomatorscripttest.Apps.MicrosoftTeam;
import com.example.uiautomatorscripttest.Apps.NewGlobeOne;
import com.example.uiautomatorscripttest.Apps.Skype;
import com.example.uiautomatorscripttest.Utility.ClearApp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Timestamp;
import java.util.Date;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private UiDevice device;
    public static Timestamp alt;

    Context context = ApplicationProvider.getApplicationContext();


    @Before
    public void launchApp() {
        try {
            if (GlobalVariables.ScriptName.equalsIgnoreCase("NewGlobeOne")) {
                startAppActivity(GlobalVariables.NewGlobeOne_Package,true,false);
            }
            if (GlobalVariables.ScriptName.equalsIgnoreCase("MicrosoftTeams")) {
                startAppActivity(GlobalVariables.MicrosoftTeams_Package,true,false);
            }
            if (GlobalVariables.ScriptName.equalsIgnoreCase("Skype")) {
                startAppActivity(GlobalVariables.Skype_Package,true,false);
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Launching App");
            e.printStackTrace();
        }
    }

    @Test
    public void runApp() {
        try {
            if(GlobalVariables.ScriptName.equalsIgnoreCase("NewGlobeOne")){
                NewGlobeOne newglobeone = new NewGlobeOne();
                newglobeone.testRun(device);
            }
            if(GlobalVariables.ScriptName.equalsIgnoreCase("MicrosoftTeams")){
                MicrosoftTeam microsoftteam = new MicrosoftTeam();
                microsoftteam.testRun(device);
            }
            if(GlobalVariables.ScriptName.equalsIgnoreCase("Skype")){
                Skype skype = new Skype();
                skype.testRun(device);
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Running App");
            e.printStackTrace();
        }
    }

    @After
    public void closeApp() {
        try {
            Thread.sleep(GlobalVariables.Timeout);
            if (GlobalVariables.ScriptName.equalsIgnoreCase("NewGlobeOne")) {
                device.executeShellCommand(getCommand(GlobalVariables.NewGlobeOne_Package));
            }
            if (GlobalVariables.ScriptName.equalsIgnoreCase("MicrosoftTeams")) {
                device.executeShellCommand(getCommand(GlobalVariables.MicrosoftTeams_Package));
            }
            if (GlobalVariables.ScriptName.equalsIgnoreCase("Skype")) {
                device.executeShellCommand(getCommand(GlobalVariables.Skype_Package));
            }
            Log.d(GlobalVariables.Tag_Name,"Application Closed Successfully");
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Closing App");
            e.printStackTrace();
        }
    }

    private String getCommand(String appPackage) {
        return "am force-stop " + appPackage;
    }

    private void startAppActivity(String appPackage,boolean clearCache, boolean clearData) {
        try {
            Log.d(GlobalVariables.Tag_Name, "Launching the App Activity");

            if (clearCache) {
                ClearApp.clearCache(appPackage);
            }

            if (clearData) {
                ClearApp.clearData(appPackage);
            }

            device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
            final String launcherPackage = device.getLauncherPackageName();
            device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), 0);

            final Intent intent =
                    context.getPackageManager().getLaunchIntentForPackage(appPackage);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
            alt = new Timestamp(new Date().getTime());
            Log.d(GlobalVariables.Tag_Name, GlobalVariables.App_Launch_Time + alt);
            device.hasObject(By.pkg(appPackage));

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Launching the App Activity");
            e.printStackTrace();
        }
    }
}
