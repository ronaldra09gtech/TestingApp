package com.example.uiautomatorscripttest.Interfaces;

public interface GlobalVariables {
    // Mention/Update the script name for which script you want to run
    String ScriptName = "Skype";

    String Tag_Name = "Aquamark";
    long Timeout = 3000;
    long OneMin_Timeout = 60000;
    long HalfMin_Timeout = 30000;
    int Wait_Timeout = 12000;

    // create different package name variables for different apps
    
    String NewGlobeOne_Package = "ph.com.globe.globeonesuperapp";
    String MicrosoftTeams_Package = "com.microsoft.teams";
    String Skype_Package = "com.skype.raider";



    String App_Launch_Time = "App Launch Time:";


}
