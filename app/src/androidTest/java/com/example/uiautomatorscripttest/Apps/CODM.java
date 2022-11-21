package com.example.uiautomatorscripttest.Apps;

import android.support.test.uiautomator.UiDevice;
import android.util.Log;

import com.example.uiautomatorscripttest.ExampleInstrumentedTest;
import com.example.uiautomatorscripttest.Interfaces.GlobalVariables;
import com.example.uiautomatorscripttest.Utility.DataHolder;

import java.sql.Timestamp;
import java.util.Date;

public class CODM {
    public static UiDevice device;
    public int testRun(UiDevice device) {
        boolean runTest = true;
        try {
            this.device = device;

            Log.d(GlobalVariables.Tag_Name, "Before Sleeping");
            Thread.sleep(20000);
            runTest = newUpdateAdvisory();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Advisory Button");
                return 1;
            }
            Thread.sleep(30000);
            Log.d(GlobalVariables.Tag_Name, "After Sleeping");

            runTest = removeAdds();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Classic Button");
                return 1;
            }

            Thread.sleep(10000);

            runTest = clickBRButton();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Classic Button");
                return 1;
            }

            Thread.sleep(10000);

            runTest = clickStartButton();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Find Start Game Button");
                return 1;
            }

            Thread.sleep(10000);

            runTest = playGame();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Play Game");
                return 1;
            }

            Thread.sleep(2000);

            runTest = clickSetting();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Click Setting");
                return 1;
            }

            Thread.sleep(2000);

            runTest = exitBRGame();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Click Exit BR");
                return 1;
            }

            Thread.sleep(2000);

            runTest = exitBRGameYes();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Click Yes");
                return 1;
            }

            Thread.sleep(20000);

            runTest = backToLobby();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Click Back");
                return 1;
            }

            runTest = kpiCalculation();
            if (!runTest) {
                DataHolder.getInstance().setFailureReason("Unable To Calculate KPI's");
                return 1;
            }

            tearDown();

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Test Run");
        }
        return 0;
    }


    private String getCommand(String appPackage) {
        return "am force-stop " + appPackage;
    }

    public void tearDown() {
        try {
            Thread.sleep(GlobalVariables.Timeout);
            device.executeShellCommand(getCommand(GlobalVariables.CODM_Package));
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in closing the app");
        }
    }

    private boolean newUpdateAdvisory() {
        try {
            boolean classicButton = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to find Advisory Button with Attempt no." + (i + 1));

                    int x = device.getDisplayWidth();
                    int y = device.getDisplayHeight();
                    Log.d(GlobalVariables.Tag_Name, "Total Width of the Device Screen:- " + x);
                    Log.d(GlobalVariables.Tag_Name, "Total Height of the Device Screen:- " + y);
                    double width = x / 100.00;
                    double height = y / 100.00;
                    int w = (int) (width * 55.50);
                    int h = (int) (height * 87.50);

                    Log.d(GlobalVariables.Tag_Name, "Value of Width after 53.76% is " + w);
                    Log.d(GlobalVariables.Tag_Name, "Value of Height after 76.38% is " + h);

                    device.click(w, h);
                    Log.d(GlobalVariables.Tag_Name, "Clicked on Advisory Button Time:" + new Timestamp(new Date().getTime()));
                    classicButton = true;
                    break;

                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Error in Click Classic");
                }
            }
            return classicButton;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding Classic Button Method");
            return false;
        }
    }

    private boolean removeAdds() {
        try {
            boolean classicButton = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to find X button with Attempt no." + (i + 1));

                    int x = device.getDisplayWidth();
                    int y = device.getDisplayHeight();
                    Log.d(GlobalVariables.Tag_Name, "Total Width of the Device Screen:- " + x);
                    Log.d(GlobalVariables.Tag_Name, "Total Height of the Device Screen:- " + y);
                    double width = x / 100.00;
                    double height = y / 100.00;
                    //small ads
                    int w = (int) (width * 89.00);
                    int h = (int) (height * 18.70);
                    //large ads
                    int w1 = (int) (width * 92.50);
                    int h1 = (int) (height * 10.00);

                    device.click(w1, h1);
                    Thread.sleep(2000);
                    device.click(w1, h1);
                    Thread.sleep(2000);
                    device.click(w1, h1);
                    Thread.sleep(2000);

                    device.click(w, h);
                    Thread.sleep(2000);

                    device.click(w1, h1);
                    Thread.sleep(2000);
                    device.click(w1, h1);
                    Thread.sleep(2000);
                    device.click(w1, h1);
                    Thread.sleep(2000);

                    device.click(w, h);
                    Thread.sleep(2000);

                    device.click(w1, h1);
                    Thread.sleep(2000);
                    device.click(w1, h1);
                    Thread.sleep(2000);
                    device.click(w1, h1);
                    Thread.sleep(2000);

                    device.click(w, h);
                    Thread.sleep(2000);

                    device.click(w1, h1);
                    Thread.sleep(2000);
                    device.click(w1, h1);
                    Thread.sleep(2000);
                    device.click(w1, h1);
                    Thread.sleep(2000);

                    device.click(w, h);
                    Log.d(GlobalVariables.Tag_Name, "Remove Adds Time: " + new Timestamp(new Date().getTime()));
                    classicButton = true;
                    break;

                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Error in Click Classic");
                }
            }
            return classicButton;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding Classic Button Method");
            return false;
        }
    }

    private boolean clickBRButton() {
        try {
            boolean classicButton = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to find BR button with Attempt no." + (i + 1));

                    int x = device.getDisplayWidth();
                    int y = device.getDisplayHeight();
                    Log.d(GlobalVariables.Tag_Name, "Total Width of the Device Screen:- " + x);
                    Log.d(GlobalVariables.Tag_Name, "Total Height of the Device Screen:- " + y);
                    double width = x / 100.00;
                    double height = y / 100.00;
                    int w = (int) (width * 97.50);
                    int h = (int) (height * 53.00);

                    Log.d(GlobalVariables.Tag_Name, "Value of Width after 53.76% is " + w);
                    Log.d(GlobalVariables.Tag_Name, "Value of Height after 76.38% is " + h);

                    device.click(w, h);
                    Log.d(GlobalVariables.Tag_Name, "Clicked on BR Button Time:" + new Timestamp(new Date().getTime()));
                    classicButton = true;
                    break;

                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Error in Click Classic");
                }
            }
            return classicButton;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding Classic Button Method");
            return false;
        }
    }

    private boolean clickStartButton() {
        try {
            boolean startGameButton = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to find Start Game button with Attempt no." + (i + 1));

                    int x = device.getDisplayWidth();
                    int y = device.getDisplayHeight();
                    Log.d(GlobalVariables.Tag_Name, "Total Width of the Device Screen:- " + x);
                    Log.d(GlobalVariables.Tag_Name, "Total Height of the Device Screen:- " + y);
                    double width = x / 100.00;
                    double height = y / 100.00;
                    int w = (int) (width * 99.89);
                    int h = (int) (height * 90.00);

                    Log.d(GlobalVariables.Tag_Name, "Value of Width after 46.24% is " + w);
                    Log.d(GlobalVariables.Tag_Name, "Value of Height after 84.44% is " + h);

                    device.click(w, h);
                    Log.d(GlobalVariables.Tag_Name, "Clicked on Start Game Button Time:" + new Timestamp(new Date().getTime()));
                    startGameButton = true;
                    break;

                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Error in Click Start Game");
                }
            }
            return startGameButton;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding Start Game Button Method");
            return false;
        }
    }

    private boolean playGame() {
        try {
            boolean play = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(GlobalVariables.Tag_Name,"Game Started");

                    Log.d(GlobalVariables.Tag_Name,"Trying to Move and Hit Enemy with Attempt no." + (i + 1));

                    int x = device.getDisplayWidth();
                    int y = device.getDisplayHeight();
                    Log.d(GlobalVariables.Tag_Name, "Total Width of the Device Screen:- " + x);
                    Log.d(GlobalVariables.Tag_Name, "Total Height of the Device Screen:- " + y);
                    double width = x / 100.00;
                    double height = y / 100.00;

                    // Move Button Co-Ordinates
                    int w1 = (int) (width * 14.00);
                    int h1 = (int) (height * 75.00);

                    Log.d(GlobalVariables.Tag_Name, "Value of Width after 21.08% is " + w1);
                    Log.d(GlobalVariables.Tag_Name, "Value of Height after 83.33% is " + h1);

                    // Hit Button Co-Ordinates
                    int w2 = (int) (width * 97.00);
                    int h2 = (int) (height * 75.00);

                    Log.d(GlobalVariables.Tag_Name, "Value of Width after 98.73% is " + w2);
                    Log.d(GlobalVariables.Tag_Name, "Value of Height after 92.36% is " + h2);

                    // Moves Right
                    device.drag(w1, h1, w1 + 100, h1 - 40,5);
                    device.click(w2, h2);

                    // Moves Top
                    device.drag(w1, h1, w1 - 20, h1 - 200, 5);
                    device.click(w2, h2);

                    // Moves Left
                    device.drag(w1, h1, w1 - 190, h1 - 60, 5);
                    device.click(w2, h2);

                    // Moves Bottom
                    device.drag(w1, h1, w1, h1 + 100, 5);
                    device.click(w2, h2);

                    Log.d(GlobalVariables.Tag_Name, "Hero Moved and Clicked on Hit Button Time:" + new Timestamp(new Date().getTime()));
                    play = true;

                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Error in Play Game");
                }
            }
            return play;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Playing Game Method");
            return false;
        }
    }
    private boolean clickSetting() {
        try {
            boolean value = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to find Setting button with Attempt no." + (i + 1));

                    int x = device.getDisplayWidth();
                    int y = device.getDisplayHeight();
                    Log.d(GlobalVariables.Tag_Name, "Total Width of the Device Screen:- " + x);
                    Log.d(GlobalVariables.Tag_Name, "Total Height of the Device Screen:- " + y);
                    double width = x / 100.00;
                    double height = y / 100.00;
                    int w = (int) (width * 89.00);
                    int h = (int) (height * 06.00);

                    Log.d(GlobalVariables.Tag_Name, "Value of Width after 46.24% is " + w);
                    Log.d(GlobalVariables.Tag_Name, "Value of Height after 84.44% is " + h);

                    device.click(w, h);
                    Log.d(GlobalVariables.Tag_Name, "Clicked on Setting Game Button Time:" + new Timestamp(new Date().getTime()));
                    value = true;
                    break;

                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Error in Click Start Game");
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding Start Game Button Method");
            return false;
        }
    }
    private boolean exitBRGame() {
        try {
            boolean value = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to find Exit button with Attempt no." + (i + 1));

                    int x = device.getDisplayWidth();
                    int y = device.getDisplayHeight();
                    Log.d(GlobalVariables.Tag_Name, "Total Width of the Device Screen:- " + x);
                    Log.d(GlobalVariables.Tag_Name, "Total Height of the Device Screen:- " + y);
                    double width = x / 100.00;
                    double height = y / 100.00;
                    int w = (int) (width * 55.00);
                    int h = (int) (height * 50.00);

                    Log.d(GlobalVariables.Tag_Name, "Value of Width after 46.24% is " + w);
                    Log.d(GlobalVariables.Tag_Name, "Value of Height after 84.44% is " + h);

                    device.click(w, h);
                    Log.d(GlobalVariables.Tag_Name, "Clicked on Exit Game Button Time:" + new Timestamp(new Date().getTime()));
                    value = true;
                    break;

                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Error in Click Start Game");
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding Start Game Button Method");
            return false;
        }
    }
    private boolean exitBRGameYes() {
        try {
            boolean value = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to find Yes button with Attempt no." + (i + 1));

                    int x = device.getDisplayWidth();
                    int y = device.getDisplayHeight();
                    Log.d(GlobalVariables.Tag_Name, "Total Width of the Device Screen:- " + x);
                    Log.d(GlobalVariables.Tag_Name, "Total Height of the Device Screen:- " + y);
                    double width = x / 100.00;
                    double height = y / 100.00;
                    int w = (int) (width * 68.00);
                    int h = (int) (height * 90.00);

                    Log.d(GlobalVariables.Tag_Name, "Value of Width after 46.24% is " + w);
                    Log.d(GlobalVariables.Tag_Name, "Value of Height after 84.44% is " + h);

                    device.click(w, h);
                    Log.d(GlobalVariables.Tag_Name, "Clicked on Yes Button Time:" + new Timestamp(new Date().getTime()));
                    value = true;
                    break;

                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Error in Click Start Game");
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding Start Game Button Method");
            return false;
        }
    }
    private boolean backToLobby() {
        try {
            boolean value = false;
            for (int i = 0; i < 5; i++) {
                try {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Trying to find Back button with Attempt no." + (i + 1));

                    int x = device.getDisplayWidth();
                    int y = device.getDisplayHeight();
                    Log.d(GlobalVariables.Tag_Name, "Total Width of the Device Screen:- " + x);
                    Log.d(GlobalVariables.Tag_Name, "Total Height of the Device Screen:- " + y);
                    double width = x / 100.00;
                    double height = y / 100.00;
                    int w = (int) (width * 05.00);
                    int h = (int) (height * 04.00);

                    Log.d(GlobalVariables.Tag_Name, "Value of Width after 46.24% is " + w);
                    Log.d(GlobalVariables.Tag_Name, "Value of Height after 84.44% is " + h);

                    device.click(w, h);
                    Log.d(GlobalVariables.Tag_Name, "Clicked on Back Button Time:" + new Timestamp(new Date().getTime()));
                    value = true;
                    break;

                } catch (Exception e) {
                    Log.d(
                            GlobalVariables.Tag_Name,
                            "Error in Click Start Game");
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Finding Start Game Button Method");
            return false;
        }
    }
    private boolean kpiCalculation() {
        try {
//            Thread.sleep(10000);

            Log.d(GlobalVariables.Tag_Name, "Time To Load Home Page = ");
            Log.d(GlobalVariables.Tag_Name, "Game Load Time = ");
            Log.d(GlobalVariables.Tag_Name, "FPS Median = ");
            Log.d(GlobalVariables.Tag_Name, "FPS Stability = ");
            return true;

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in KPI Calculation");
            e.printStackTrace();
            return false;
        }
    }
}
