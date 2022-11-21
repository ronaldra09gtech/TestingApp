package com.example.uiautomatorscripttest.Apps;


import android.graphics.Point;
import android.graphics.Rect;
import android.os.Environment;
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

import java.io.File;
import java.io.FilenameFilter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static org.junit.Assert.assertEquals;

public class GDrive {

    private UiDevice device;
    Timestamp homeElementsAppearTime,uploadFileTime,fileUploadedTime,downloadDocsTime,docsDownloadedTime,imageDownloadedTime,videoDownloadedTime,downloadImageTime,downloadVideoTime;
    int loopCount = 1;

    public int testRun(UiDevice device) {
        boolean runTest = true;
        this.device = device;
        try {
            runTest = homePage();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to display Home element");
                return 1;
            }
            runTest = gotoFiles();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to go to Files");
                return 1;
            }
            runTest = clickCreate();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Create");
                return 1;
            }
            runTest = clickUpload();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click upload");
                return 1;
            }
            runTest = clickSearch();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Search");
                return 1;
            }
            runTest = clickFolder();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Folder");
                return 1;
            }
            runTest = clickImage();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click Image");
                return 1;
            }
            runTest = uploadStatus();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Check Upload Status");
                return 1;
            }
            runTest = clickMoreInUploaded();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click More");
                return 1;
            }
            runTest = firstClear();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Drag First Clear");
                return 1;
            }
            runTest = clickMoreDownload();
            if(!runTest){
                DataHolder.getInstance().setFailureReason("Unable to Click More in Download");
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
            device.executeShellCommand(getCommand(GlobalVariables.GCash_Package));
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
                File downloadFolder = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)));

                File[] files = downloadFolder.listFiles((d,s)-> s.contains("DL_"));
                for(int i=0; i<files.length; i++){
                    files[i].delete();
                    Log.d(GlobalVariables.Tag_Name, "Files " + files[i]);
                }
//                File[] files2 = downloadFolder.listFiles((d,s)-> s.equalsIgnoreCase("DL_docs.pdf"));
//                for(int i=0; i<files2.length; i++){
//                    files2[i].delete();
//                    Log.d(GlobalVariables.Tag_Name, "Files " + files2[i]);
//                }
//                File[] files3 = downloadFolder.listFiles((d,s)-> s.equalsIgnoreCase("DL_image.jpg"));
//                for(int i=0; i<files3.length; i++){
//                    files3[i].delete();
//                    Log.d(GlobalVariables.Tag_Name, "Files " + files3[i]);
//                }


                UiSelector secElementSelector = new UiSelector().resourceId("com.google.android.apps.docs:id/file_title");
                UiObject secElementObject = device.findObject(secElementSelector);

                UiSelector thirdElementSelector = new UiSelector().resourceId("com.google.android.apps.docs:id/entry_thumbnail");
                UiObject thirdElementObject = device.findObject(thirdElementSelector);

                if (secElementObject.exists() || thirdElementObject.exists())
                {
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

    public boolean gotoFiles(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().descriptionContains("File");
                UiObject object = device.findObject(selector);

                if (object.exists())
                {
                    object.click();
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Files Successfully Time:" + unUsableTime);
                    value = true;
                    clickMoreInUploaded();
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking Files");
        }
        return value;
    }

    public boolean clickCreate(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().resourceId("com.google.android.apps.docs:id/entry_label");
                UiObject object = device.findObject(selector);

                UiSelector uploadBtn = new UiSelector().resourceId("com.google.android.apps.docs:id/extended_fab");
                UiObject uploadBtnObj = device.findObject(uploadBtn);

                if (object.exists())
                {
                    uploadBtnObj.click();
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Create Successfully Time:" + unUsableTime);
                    value = true;
                    break;
                }
                if (uploadBtnObj.exists())
                {
                    uploadBtnObj.click();
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Create Successfully Time:" + unUsableTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking Create");
        }
        return value;
    }

    public boolean clickUpload(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector firstElementSelector = new UiSelector().textContains("Upload");
                UiObject firstElementObject = device.findObject(firstElementSelector);
                Log.d(GlobalVariables.Tag_Name,"Trying to Upload Button");

                if(firstElementObject.exists()){
                    firstElementObject.click();
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Click Upload Successfully Time:" + unUsableTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking Upload");
        }
        return value;
    }

    public boolean clickSearch(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector selector = new UiSelector().descriptionContains("Search");
                UiObject object = device.findObject(selector);

                UiSelector selectorSearchTab = new UiSelector().className("android.widget.EditText");
                UiObject objectSearchTab = device.findObject(selectorSearchTab);

                if(object.exists())
                {
                    object.click();
                    objectSearchTab.setText("image5mb");
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Click and input message Successfully Time:" + unUsableTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking Search");
        }
        return value;
    }

    public boolean clickFolder(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Thread.sleep(2000);
                UiSelector firstSelector = new UiSelector().textContains("image5mb").resourceId("android:id/title");
                UiObject firstElementObject = device.findObject(firstSelector);
                if(firstElementObject.exists())
                {
                    firstElementObject.click();
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Click Folder Successfully Time:" + unUsableTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking Folder");
        }
        return value;
    }

    public boolean clickImage(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector firstSelector = new UiSelector().resourceId("com.google.android.documentsui:id/icon_thumb");
                UiObject firstElementObject = device.findObject(firstSelector);

                if(firstElementObject.exists())
                {
                    firstElementObject.click();
                    uploadFileTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Clicked Image Successfully" + uploadFileTime);
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error clicking Image");
        }
        return value;
    }

    public boolean uploadStatus(){
        boolean value = false;
        boolean checker = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= 120000) {
                UiSelector firstSelector = new UiSelector().textContains("Uploading");
                UiObject firstElementObject = device.findObject(firstSelector);

                if(firstElementObject.exists())
                {
                    Log.d(GlobalVariables.Tag_Name,"Uploading...");
                    checker = true;
                }
                else
                {
                    if (checker) {
                        fileUploadedTime = new Timestamp(new Date().getTime());
                        Log.d(GlobalVariables.Tag_Name, "Uploaded Successfully" + fileUploadedTime);
                        value = true;
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Uploading 5mb");
        }
        return value;
    }
    public boolean clickMoreInUploaded(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name,"Entered in function");

                Thread.sleep(6000);
                UiSelector firstSelector = new UiSelector().descriptionContains("More actions for Snake");
                UiObject firstElementObject = device.findObject(firstSelector);

                UiSelector removeSel = new UiSelector().textContains("Remove").resourceId("com.google.android.apps.docs:id/label");
                UiObject remove = device.findObject(removeSel);
                Log.d(GlobalVariables.Tag_Name, String.valueOf(firstElementObject.exists()));
                Log.d(GlobalVariables.Tag_Name, String.valueOf(remove.exists()));

                if(firstElementObject.exists() || remove.exists())
                {
                    if (firstElementObject.exists())
                    {
                        firstElementObject.click();
                    }
                    Log.d(GlobalVariables.Tag_Name,"Clicked first element");

                    Thread.sleep(2000);
                    UiSelector removeSelector = new UiSelector().textContains("Remove");
                    UiObject removeObject = device.findObject(removeSelector);
                    if (removeObject.exists())
                    {
                        removeObject.click();
                        Log.d(GlobalVariables.Tag_Name,"Clicked second element");

                        Thread.sleep(2000);
                        UiSelector moveSelector = new UiSelector().textContains("Move to").className("android.widget.Button");
                        UiObject moveObject = device.findObject(moveSelector);
                        if (moveObject.exists())
                        {
                            moveObject.click();
                            Timestamp unUsableTime = new Timestamp(new Date().getTime());
                            Log.d(GlobalVariables.Tag_Name,"Click Remove Successfully" + unUsableTime);
                            value = true;
                            clickMoreInUploaded();
                            break;
                        }
                    }
                    else
                    {
                        Log.d(GlobalVariables.Tag_Name,"Scroll Down");
                        UiScrollable scroll = new UiScrollable(new UiSelector().resourceId("com.google.android.apps.docs:id/menu_recycler_view"));
                        scroll.scrollForward(5);
                    }
                }
                else
                {
                    Log.d(GlobalVariables.Tag_Name,"no more item to delete");
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name, String.valueOf(e));
        }
        return value;
    }

    public boolean firstClear(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            UiSelector uploadBtn = new UiSelector().resourceId("com.google.android.apps.docs:id/extended_fab");
            UiObject uploadBtnObj = device.findObject(uploadBtn);

            UiSelector clearButton = new UiSelector().descriptionContains("Clear");
            UiObject clear = device.findObject(clearButton);

            UiSelector clearButton2 = new UiSelector().textContains("Clear");
            UiObject clear2 = device.findObject(clearButton2);

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout)
            {
                int x = device.getDisplayWidth();
                int y = device.getDisplayHeight();
                double width = x / 100.00;
                double height = y / 100.00;
                int w = (int) (width * 30.00);
                int h = (int) (height * 01.00);
                device.drag(w,h,w,h + 200, 6);

                if (uploadBtnObj.exists() == false)
                {
                    if (clear.exists())
                    {
                        clear.click();
                    }
                    if (clear2.exists())
                    {
                        clear2.click();
                    }
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on Drag down First Clear");
        }
        return value;
    }

    public boolean clickMoreDownload(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                Log.d(GlobalVariables.Tag_Name,"Loop Count : " + loopCount);
                UiSelector firstSelector = new UiSelector().descriptionContains("More actions for DL_docs");
                UiObject firstElementObject = device.findObject(firstSelector);

                UiSelector secSelector = new UiSelector().descriptionContains("More actions for DL_image");
                UiObject secElementObject = device.findObject(secSelector);

                UiSelector thirdSelector = new UiSelector().descriptionContains("More actions for DL_video");
                UiObject thirdElementObject = device.findObject(thirdSelector);

                if(firstElementObject.exists() && loopCount == 1)
                {
                    firstElementObject.click();
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"More on docs Clicked Successfully" + unUsableTime);
                    value = true;
                    download();
                    break;
                }
                else if(secElementObject.exists() && loopCount == 2)
                {
                    secElementObject.click();
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"More on image Clicked Successfully" + unUsableTime);
                    value = true;
                    download();
                    break;
                }
                else if(thirdElementObject.exists() && loopCount == 3)
                {
                    thirdElementObject.click();
                    Timestamp unUsableTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"More on video Clicked Successfully" + unUsableTime);
                    value = true;
                    download();
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Uploading 5mb");
        }
        return value;
    }

    public boolean download(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector firstSelector = new UiSelector().textContains("Download");
                UiObject firstElementObject = device.findObject(firstSelector);

                if(firstElementObject.exists() && loopCount == 1)
                {
                    firstElementObject.click();
                    downloadDocsTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Download Clicked for docs Successfully" + downloadDocsTime);
                    value = true;
                    dragDown();
                    break;
                }
                if(firstElementObject.exists() && loopCount == 2)
                {
                    firstElementObject.click();
                    downloadImageTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Download Clicked for image Successfully" + downloadImageTime);
                    value = true;
                    dragDown();
                    break;
                }
                if(firstElementObject.exists() && loopCount == 3)
                {
                    firstElementObject.click();
                    downloadVideoTime = new Timestamp(new Date().getTime());
                    Log.d(GlobalVariables.Tag_Name,"Download Clicked for video Successfully" + downloadVideoTime);
                    value = true;
                    dragDown();
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Clicking Download");
        }
        return value;
    }

    public boolean dragDown(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            UiSelector uploadBtn = new UiSelector().resourceId("com.google.android.apps.docs:id/extended_fab");
            UiObject uploadBtnObj = device.findObject(uploadBtn);

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout)
            {
                int x = device.getDisplayWidth();
                int y = device.getDisplayHeight();
                double width = x / 100.00;
                double height = y / 100.00;
                int w = (int) (width * 30.00);
                int h = (int) (height * 01.00);
                device.drag(w,h,w,h + 200, 6);

                if (uploadBtnObj.exists() == false)
                {
                    checkIfDownloaded();
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error on Drag down");
        }
        return value;
    }

    public boolean checkIfDownloaded(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= 120000 && loopCount < 4) {
                UiSelector firstSelector = new UiSelector().textContains("Downloaded");
                UiObject firstElementObject = device.findObject(firstSelector);

                switch (loopCount)
                {
                    case 1:
                        if(firstElementObject.exists())
                        {
                            docsDownloadedTime = new Timestamp(new Date().getTime());
                            Log.d(GlobalVariables.Tag_Name,"Docs Downloaded Successfully" + docsDownloadedTime);
                            value = true;
                            loopCount = 2;
                            clearStatus();
                            break;
                        }
                        else
                        {
                            Log.d(GlobalVariables.Tag_Name,"Downloading Docs...");
                        }
                        break;
                    case 2:
                        if(firstElementObject.exists())
                        {
                            imageDownloadedTime = new Timestamp(new Date().getTime());
                            Log.d(GlobalVariables.Tag_Name,"Image Downloaded Successfully" + imageDownloadedTime);
                            value = true;
                            loopCount = 3;
                            clearStatus();
                            break;
                        }
                        else
                        {
                            Log.d(GlobalVariables.Tag_Name,"Downloading Image...");
                        }
                        break;
                    case 3:
                        if(firstElementObject.exists())
                        {
                            videoDownloadedTime = new Timestamp(new Date().getTime());
                            Log.d(GlobalVariables.Tag_Name,"Video Downloaded Successfully" + videoDownloadedTime);
                            value = true;
                            loopCount = 4;
                            clearStatus();
                            break;
                        }
                        else
                        {
                            Log.d(GlobalVariables.Tag_Name,"Downloading Video...");
                        }
                        break;
                    default:
                        Log.d(GlobalVariables.Tag_Name,"Waiting to response!");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Checking if Downloaded");
        }
        return value;
    }

    public boolean clearStatus(){
        boolean value = false;
        try{
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            while (stopWatch.getTime() <= GlobalVariables.OneMin_Timeout) {
                UiSelector clearButton = new UiSelector().descriptionContains("Clear");
                UiObject clear = device.findObject(clearButton);

                UiSelector clearButton2 = new UiSelector().textContains("Clear");
                UiObject clear2 = device.findObject(clearButton2);

                if (clear.exists())
                {
                    clear.click();
                }
                if (clear2.exists())
                {
                    clear2.click();
                }
                if (loopCount <= 3)
                {
                    Log.d(GlobalVariables.Tag_Name,"Cleared");
                    value = true;
                    clickMoreDownload();
                    break;
                }
                else
                {
                    Log.d(GlobalVariables.Tag_Name,"Cleared");
                    value = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.Tag_Name,"Error Uploading 5mb");
        }
        return value;
    }

    private boolean kpiCalculation() {
        try {
            Thread.sleep(5000);
            double TTLH = (homeElementsAppearTime.getTime() - ExampleInstrumentedTest.alt.getTime()) / 1000.0;
            double TTUF = (fileUploadedTime.getTime() - uploadFileTime.getTime()) / 1000.0;
            double TTDD = (docsDownloadedTime.getTime() - downloadDocsTime.getTime()) / 1000.0;
            double TTDI = (imageDownloadedTime.getTime() - downloadImageTime.getTime()) / 1000.0;
            double TTDV = (videoDownloadedTime.getTime() - downloadVideoTime.getTime()) / 1000.0;

            Log.d(GlobalVariables.Tag_Name, "Time to Load Home Page = " + TTLH);
            Log.d(GlobalVariables.Tag_Name, "Time to Send 5MB File = " + TTUF);
            Log.d(GlobalVariables.Tag_Name, "Time to Download 5MB Document = " + TTDD);
            Log.d(GlobalVariables.Tag_Name, "Time to Download 10MB Image = " + TTDI);
            Log.d(GlobalVariables.Tag_Name, "Time to Download 30MB Video = " + TTDV);
            return true;

        } catch (Exception e) {
            Log.d(GlobalVariables.Tag_Name, "Error in KPI Calculation");
            e.printStackTrace();
            return false;
        }
    }
}

