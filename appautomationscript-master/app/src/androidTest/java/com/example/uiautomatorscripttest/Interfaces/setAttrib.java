package com.example.uiautomatorscripttest.Interfaces;

public class setAttrib {
    String script_name;
    String package_name;

    public setAttrib(String script_name,String package_name){
        this.script_name = script_name;
        this.package_name = package_name;
    }
    public String getName(){
        return script_name;
    }
    public String getPackage(){
        return  package_name;
    }
}
