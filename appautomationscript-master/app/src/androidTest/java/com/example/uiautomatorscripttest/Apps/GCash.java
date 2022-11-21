package com.example.uiautomatorscripttest.Apps;


import android.graphics.Point;
import android.graphics.Rect;
import android.os.health.TimerStat;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;
import android.util.Log;

import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;


import com.example.uiautomatorscripttest.ExampleInstrumentedTest;
import com.example.uiautomatorscripttest.Interfaces.GlobalVariables;
import com.example.uiautomatorscripttest.Utility.DataHolder;

import org.apache.commons.lang3.time.StopWatch;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static org.junit.Assert.assertEquals;

public class GCash {

    private UiDevice device;
    Timestamp homeElementsAppearTime,lastPINTime,gForestTime,gLifeTime,gForestDisTime,gLifeDisTime,moneySentTime,sendMoneyTime;

    public int testRun(UiDevice device) {
        boolean runTest = true;
        this.device = device;
        try {
            Log.d(GlobalVariables.Tag_Name, "Application Launched");
            runTest = PINDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to display PIN element");
                return 1;
            }
            runTest = enterPIN();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click PIN element");
                return 1;
            }
            runTest = homePage();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to display Home element");
                return 1;
            }
            runTest = checkAddsOnHome();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Remove Ads");
                return 1;
            }
            runTest = gotoGForest();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click GForest");
                return 1;
            }
            runTest = gForestDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display GForest");
                return 1;
            }
            runTest = gotoGLife();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click GLife");
                return 1;
            }
            runTest = gLifeDisplayed();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display GLife");
                return 1;
            }
            runTest = gotoSendMoney();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Go To Send Money");
                return 1;
            }
            runTest = gotoExpressSend();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Go to Express Send");
                return 1;
            }
            runTest = enterNumber();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Enter Number");
                return 1;
            }
            runTest = sendPHP();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to go to Long Click Message");
                return 1;
            }
            runTest = successfullySent();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Display Successfully Sent");
                return 1;
            }
            runTest = logOut();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Logout");
                return 1;
            }
            runTest = kpiCalculation();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Calculate KPI's");
                return 1;
            }

            tearDown();
            return 0;

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name, "Error in Running App");
        }
        return 0;

    }

    private String getCommand(String appPackage) {
        return "am force-stop " + appPackage;
    }

    public void tearDown() {
        try {
            device.executeShellCommand(getCommand(GlobalVariables.Telegram_Package));
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in closing the app");
        }
    }
    public boolean PINDisplayed() {
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().resourceId("com.globe.gcash.android:id/cl_mpin_container");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Displayed PIN Successfully Time:" + unUsableTime);
                value = true;
                Thread.sleep(5000);
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on Displaying PIN");
        }
        return value;
    }
    public boolean enterPIN() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                for(int ab = 0;ab <= 3;ab++){
                    //first PIN
                    UiSelector firstSelector = new UiSelector().resourceId("com.globe.gcash.android:id/btn_number6");
                    UiObject firstObject = device.findObject(firstSelector);

                    //second PIN
                    UiSelector secondSelector = new UiSelector().resourceId("com.globe.gcash.android:id/btn_number2");
                    UiObject secondObject = device.findObject(secondSelector);

                    //third PIN
                    UiSelector thirdSelector = new UiSelector().resourceId("com.globe.gcash.android:id/btn_number7");
                    UiObject thirdObject = device.findObject(thirdSelector);

                    //fourth PIN
                    UiSelector fourthSelector = new UiSelector().resourceId("com.globe.gcash.android:id/btn_number5");
                    UiObject fourthObject = device.findObject(fourthSelector);

                    if (firstObject.exists() && ab == 0) {
                        firstObject.click();
                        Timestamp onePINTime = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Click 1st successfully: " + onePINTime);
                        Log.d(GlobalVariables.Tag_Name, String.valueOf(ab));
                        Thread.sleep(2000);
                    }
                    else if(secondObject.exists() && ab == 1)
                    {
                        secondObject.click();
                        Timestamp onePINTime = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Click 2nd successfully: " + onePINTime);
                        Log.d(GlobalVariables.Tag_Name, String.valueOf(ab));
                        Thread.sleep(2000);
                    }
                    else if(thirdObject.exists() && ab == 2)
                    {
                        thirdObject.click();
                        Timestamp onePINTime = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Click 3rd successfully: " + onePINTime);
                        Log.d(GlobalVariables.Tag_Name, String.valueOf(ab));
                        Thread.sleep(2000);
                    }
                    else if(fourthObject.exists() && ab == 3)
                    {
                        fourthObject.click();
                        lastPINTime = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Click 4th successfully: " + lastPINTime);
                        Log.d(GlobalVariables.Tag_Name, String.valueOf(ab));
                        value = true;
                    }
                    else {
                        Log.d(GlobalVariables.Tag_Name, "Loading PIN Elements");
                        Thread.sleep(200);
                    }

                }
                break;
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Home Page");
            return value;
        }
    }
    public boolean homePage() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().textContains("PHP");
                UiObject object = device.findObject(selector);

                UiSelector remindSelector = new UiSelector().resourceId("com.globe.gcash.android:id/btn_remind_me_later");
                UiObject remindObject = device.findObject(remindSelector);

                if (object.exists() || remindObject.exists()) {
                    homeElementsAppearTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "Home Elements Appear Time:" + homeElementsAppearTime);
                    value = true;
                    break;
                } else {
                    Log.d(GlobalVariables.Tag_Name, "Loading Home Page Elements");
                    Thread.sleep(200);
                }
            }
            return value;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Home Page");
            return false;
        }
    }
    public boolean checkAddsOnHome() {
        boolean value = false;
        try {
            //get adds element in home page
            UiSelector addsSelector = new UiSelector().resourceId("com.globe.gcash.android:id/btn_remind_me_later");
            UiObject addsObject = device.findObject(addsSelector);
            if(addsObject.waitForExists(5000)){
                //remove adds in home page
                Log.d(GlobalVariables.Tag_Name, "Home adds detected");
                addsObject.click();
                value = true;
            } else {
                Log.d(GlobalVariables.Tag_Name, "no Home adds detected");
                value = true;
            }
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Home Page");
        }
        return value;
    }

    public boolean gotoGForest() {
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("GForest");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                gForestTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked on GForest Successfully Time:" + gForestTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on clicking GForest");
        }
        return value;
    }

    public boolean gForestDisplayed() {
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().textContains("My energy");
                UiObject object = device.findObject(selector);

                UiSelector getStartedSelector = new UiSelector().textContains("GET STARTED");
                UiObject getStartedObject = device.findObject(getStartedSelector);
                if(object.exists() || getStartedObject.exists())
                {
                    gForestDisTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"GForest Displayed Successfully Time:" + gForestDisTime);
                    value = true;
                    Thread.sleep(3000);
                    device.pressBack();
                    checkAddsOnHome();
                    break;
                }
                else
                {
                    Log.d(GlobalVariables.Tag_Name,"Loading..");
                    Thread.sleep(200);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on Displaying GForest");
        }
        return value;
    }

    public boolean gotoGLife() {
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("GLife");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                gLifeTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked on GLife Successfully Time:" + gLifeTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on clicking GLife");
        }
        return value;
    }

    public boolean gLifeDisplayed() {
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().textContains("Favorites");
                UiObject object = device.findObject(selector);

                UiSelector selectorSec = new UiSelector().className("android.widget.EditText");
                UiObject objectSec = device.findObject(selectorSec);

                UiSelector selectorThird = new UiSelector().className("android.widget.EditText");
                UiObject objectThird = device.findObject(selectorThird);

                if(object.exists() || objectSec.exists() || objectThird.exists()){
                    gLifeDisTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"GLife Displayed Successfully Time:" + gLifeDisTime);
                    value = true;
                    Thread.sleep(3000);
                    device.pressBack();
                    checkAddsOnHome();
                    break;
                }
                else
                {
                    Log.d(GlobalVariables.Tag_Name,"Loading..");
                    Thread.sleep(200);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on Displaying GLife");
        }
        return value;
    }

    public boolean gotoSendMoney(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().textContains("Send Money");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked Send Money Successfully" + unUsableTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking Send Money");
        }
        return value;
    }

    public boolean gotoExpressSend(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().textContains("Express Send");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked Express Send Successfully" + unUsableTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking Express Send");
        }
        return value;
    }

    public boolean enterNumber(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                //contact number
                UiSelector contactSelector = new UiSelector().resourceId("com.globe.gcash.android:id/txt_number");
                UiObject contactObject = device.findObject(contactSelector);

                UiSelector removeAutoSelector = new UiSelector().resourceId("android:id/text1");
                UiObject removeAutoObject = device.findObject(removeAutoSelector);
                if (removeAutoObject.exists())
                {
                    device.pressBack();
                }
                else if(contactObject.exists()){
                    contactObject.setText("09175881114");
                    Thread.sleep(2000);
                    Log.d(GlobalVariables.Tag_Name,"Input Contact Successfully");

                    //amount
                    UiSelector amountSelector = new UiSelector().resourceId("com.globe.gcash.android:id/txt_amount");
                    UiObject amountObject = device.findObject(amountSelector);
                    if(amountObject.exists())
                    {
                        amountObject.setText("10");
                        device.pressBack();
                        Thread.sleep(2000);
                        Log.d(GlobalVariables.Tag_Name,"Input Amount Successfully");

                        UiSelector nextSelector = new UiSelector().textContains("NEXT");
                        UiObject nextObject = device.findObject(nextSelector);
                        if(amountObject.exists())
                        {
                            nextObject.click();
                            Timestamp unUsableTime = new Timestamp(new Date().getTime());
                            Log.d(GlobalVariables.Tag_Name,"Clicked NEXT button Successfully: " + unUsableTime);
                            value = true;
                            break;
                        }
                        else
                        {
                            Log.d(GlobalVariables.Tag_Name,"Loading Clicking NEXT Button");
                            Thread.sleep(200);
                        }
                    }
                    else
                    {
                        Log.d(GlobalVariables.Tag_Name,"Loading Amount Input");
                        Thread.sleep(200);
                    }
                }
                else
                {
                    Log.d(GlobalVariables.Tag_Name,"Loading Contact Input");
                    Thread.sleep(200);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking Express Send");
        }
        return value;
    }

    public boolean sendPHP(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().resourceId("com.globe.gcash.android:id/btn_next");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                sendMoneyTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked SEND Successfully: " + sendMoneyTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking SEND");
        }
        return value;
    }
    public boolean successfullySent(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().textContains("Successfully sent to");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                moneySentTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Sent Money Successfully: " + moneySentTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Sending Money");
        }
        return value;
    }

    public boolean logOut(){
        boolean value = false;
        try{

            UiSelector selector = new UiSelector().descriptionContains("Navigate up");
            UiObject object = device.findObject(selector);

            UiSelector selectorOk = new UiSelector().textContains("Ok").resourceId("android:id/button1");
            UiObject objectOk = device.findObject(selectorOk);

            UiSelector selectorOK = new UiSelector().textContains("OK").resourceId("android:id/button1");
            UiObject objectOK = device.findObject(selectorOK);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                Thread.sleep(2000);
                if (objectOk.exists())
                {
                    objectOk.click();
                }
                Timestamp unUsableTime = new Timestamp(new Date().getTime());
                Thread.sleep(2000);
                device.pressBack();
                if (objectOK.exists())
                {
                    objectOK.click();
                }
                Log.d(GlobalVariables.Tag_Name,"Logout successfully: " + unUsableTime);
                value = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Sending Money");
        }
        return value;
    }

    private boolean kpiCalculation() {
        try {
            Thread.sleep(10000);
            double TTLH = (homeElementsAppearTime.getTime() - lastPINTime.getTime()) / 1000.0;
            double TTLGf = (gForestDisTime.getTime() - gForestTime.getTime()) / 1000.0;
            double TTLGl = (gLifeDisTime.getTime() - gLifeTime.getTime()) / 1000.0;
            double TTSM = (moneySentTime.getTime() - sendMoneyTime.getTime()) / 1000.0;

            Log.d(GlobalVariables.Tag_Name, "Time To Load Home Page = " + TTLH);
            Log.d(GlobalVariables.Tag_Name, "Time to Load Feature Page = " + TTLGf);
            Log.d(GlobalVariables.Tag_Name, "Time to Load Product Page = " + TTLGl);
            Log.d(GlobalVariables.Tag_Name, "Time to Send Money = " + TTSM);
            return true;

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in KPI Calculation");
            e.printStackTrace();
            return false;
        }
    }
}

