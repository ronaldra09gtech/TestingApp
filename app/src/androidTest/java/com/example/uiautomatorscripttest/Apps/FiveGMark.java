package com.example.uiautomatorscripttest.Apps;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import com.example.uiautomatorscripttest.Interfaces.GlobalVariables;
import com.example.uiautomatorscripttest.Utility.DataHolder;

import org.apache.commons.lang3.time.StopWatch;

import java.sql.Timestamp;
import java.util.Date;

public class FiveGMark {

    private UiDevice device;
    long counter = 0;
    Timestamp homeElementsAppearTime,AquimarkWebTime,LaunchtestTime,gForestDisTime,gLifeDisTime,moneySentTime,sendMoneyTime;



    public int testRun(UiDevice device) {
        boolean runTest = true;
        this.device = device;
        try {
            Log.d(GlobalVariables.Tag_Name, "Application Launched");
            runTest = homePage();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to display Home element");
                return 1;
            }
            runTest = gotoAquimarkWeb();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Aqiamark - WEB");
                return 1;
            }
            runTest = clickLaunchtest();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Launch test");
                return 1;
            }
            runTest = Aqiamarktesting();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to load Web Test 1");
                return 1;
            }
            runTest = checkloading();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Check Progress");
                return 1;
            }

            tearDown();
            return 0;

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name, "Error in Running App");
        }
        return 0;

    }// End testRun


    private String getCommand(String appPackage) { return "am force-stop " + appPackage; }
    public void tearDown() {
        try {
            device.executeShellCommand(getCommand(GlobalVariables.FiveGMark_Package));
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in closing the app");
        }
    }

    public boolean homePage() {
        boolean value = false;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
//                UiSelector selector = new UiSelector().textContains("Aqiamark - WEB");
                UiSelector selector = new UiSelector().resourceId("qosi.fr.pro5gmark:id/logo");
                UiObject object = device.findObject(selector);

                if (object.exists()) {
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

    public boolean gotoAquimarkWeb() {
        long addCounter = 20000;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                Log.d(GlobalVariables.Tag_Name, "internal Counter " + stopWatch.getTime());
                Log.d(GlobalVariables.Tag_Name, "global Counter " + counter);
                //get adds element in home page
//                UiSelector addsSelector = new UiSelector().className("android.widget.ImageView").index(9);
                UiSelector addsSelector = new UiSelector().resourceId("qosi.fr.pro5gmark:id/embedded_layout_for_test");
                UiObject addsObject = device.findObject(addsSelector);

//                UiSelector adds2Selector = new UiSelector().textContains("O1CN01wDnGi51lWoUAxpKMR_!!6000000004827-2-tps-32-33");
//                UiObject adds2Object = device.findObject(adds2Selector);
                if(addsObject.exists()){
                    //remove adds in home page
                    counter = stopWatch.getTime();
                    AquimarkWebTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "First Home adds detected");
//                    addsObject.click();
                    checkloading();
                    //device.pressBack();
                    break;
                }
//                else if (adds2Object.exists())
//                {
//                    //remove adds in home page
//                    counter = stopWatch.getTime();
//                    AquimarkWebTime = new Timestamp(new Date().getTime());
//                    Log.d(GlobalVariables.Tag_Name, "Second Home adds detected");
//
//                    device.pressBack();
//                    break;
//                }
                else if(stopWatch.getTime() >= (addCounter-counter))
                {
                    Log.d(GlobalVariables.Tag_Name, String.valueOf((addCounter-counter)));
                    AquimarkWebTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name, "no Home adds detected");
                    break;
                }
            }
            return true;
        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in Home Page");
            return false;
        }
    }


    public boolean clickLaunchtest() {
        boolean value = false;
        try{
            UiSelector selector = new UiSelector().textContains("Launch test");
            UiObject object = device.findObject(selector);

            if(object.waitForExists(GlobalVariables.OneMin_Timeout)){
                object.click();
                LaunchtestTime = new Timestamp(new Date().getTime());
                Log.d(GlobalVariables.Tag_Name,"Clicked on Launch test Successfully Time:" + LaunchtestTime);
                final long waitingTime = 5000L;
                Thread.sleep(waitingTime);
                value = true;

            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on clicking Launch test");
        }
        return value;
    }



    public boolean Aqiamarktesting(){
        boolean value = false;
        try{
            Thread.sleep(2000);
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name,"Loading Web Test");

                UiSelector selectorone = new UiSelector().resourceId("qosi.fr.pro5gmark:id/embedded_layout_for_test");
                UiObject objectone = device.findObject(selectorone);


//                UiSelector selector1 = new UiSelector().textContains("Aqiamark - WEB (7/20)");
//                UiObject object1 = device.findObject(selector1);
//
//                UiSelector selectorprogress = new UiSelector().textContains("qosi.fr.pro5gmark:id/testProgress");
//                UiObject objectprogress = device.findObject(selectorprogress);
//
//                UiSelector selectortimeline = new UiSelector().textContains("qosi.fr.pro5gmark:id/timeline");
//                UiObject objecttimeline = device.findObject(selectortimeline);


                if(objectone.exists()){
//                    Timestamp WebTest1 = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Web Test Successfully Time 1");
                    value = true;
                    checkloading();
//                    break;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in loading Web Test 1");
        }
        return value;
    }




    public boolean checkloading(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {

                UiSelector selector = new UiSelector().resourceId("qosi.fr.pro5gmark:id/timeline");
                UiObject object = device.findObject(selector);

                UiSelector selectorelapse = new UiSelector().resourceId("qosi.fr.pro5gmark:id/elapsed_time");
                UiObject objectelapse = device.findObject(selectorelapse);

                UiSelector selectoreprogress = new UiSelector().resourceId("qosi.fr.pro5gmark:id/testProgress");
                UiObject objectprogress = device.findObject(selectoreprogress);

//                UiSelector mediaSelector = new UiSelector().textContains("Media");
//                UiObject mediaObject = device.findObject(mediaSelector);

                if(object.exists()){
                    //object.click();
                    Timestamp ClickAttachTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"View timeline Successfully Time:" + ClickAttachTime);
                    value = true;
//                    break;

//                    if (mediaObject.exists())
//                    {
//                        break;
//                    }
                }
                if(objectelapse.exists()){
                    //object.click();
                    Timestamp ClickAttachTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"View Elapse Successfully Time:" + ClickAttachTime);
                    value = true;
//                    break;
                }
                if(objectprogress.exists()){
                    //object.click();
                    Timestamp ClickAttachTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"View Progress Successfully Time:" + ClickAttachTime);
                    value = true;
//                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error in View timeline");
        }
        return  value;
    }



//    public boolean Aqiamarktesting() {
//        boolean value = false;
//        try{
//            UiSelector selector = new UiSelector().textContains("Aqiamark - WEB (1/20)");
//            UiObject object = device.findObject(selector);
//
//            UiSelector selectorprogress = new UiSelector().textContains("qosi.fr.pro5gmark:id/testProgress");
//            UiObject objectprogress = device.findObject(selectorprogress);
//
//
//            if(object.exists() || objectprogress.exists()){
//                Timestamp WebTest1 = new Timestamp(new Date().getTime());
//                Log.d(GlobalVariables.Tag_Name,"Web Test 1/20 Successfully Time:" + WebTest1);
//                final long waitingTime = 5000L;
//                Thread.sleep(waitingTime);
//                value = true;
//
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//            Log.d(GlobalVariables.Tag_Name,"Error on Loading Web test 1");
//        }
//        return value;
//    }



//    public boolean AquimarkWebTesting(){
//        boolean value = false;
//        try{
//            StopWatch stopWatch = new StopWatch();
//            stopWatch.start();
//
//            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
//                UiSelector selectorLive = new UiSelector().className("android.widget.FrameLayout").index(3);
//                UiObject objectLive = device.findObject(selectorLive);
//
//                UiSelector selector = new UiSelector().resourceId("videoplayer0");
//                UiObject object = device.findObject(selector);
//
//                if(object.exists()){
//                    object.click();
//                    clickLiveTime = new Timestamp(new Date().getTime());
//                    Log.d(GlobalVariables.Tag_Name,"Clicked on Live successfully Time:" + clickLiveTime);
//                    value = true;
//                    break;
//                }
//                else if(objectLive.exists())
//                {
//                    objectLive.click();
//                    clickLiveTime = new Timestamp(new Date().getTime());
//                    Log.d(GlobalVariables.Tag_Name,"Clicked on Live successfully Time:" + clickLiveTime);
//                    value = true;
//                    break;
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            Log.d(GlobalVariables.Tag_Name,"Error in clicking on Live");
//        }
//        return value;
//    }




}
