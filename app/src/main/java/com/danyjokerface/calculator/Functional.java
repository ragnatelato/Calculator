package com.danyjokerface.calculator;

public class Functional extends HomeScreen {
    static boolean checkclick = false;

    public void setToolbar() {
    }

    public String setTextViewToolbar(String greeting, String appname) {
        if (!checkclick) {
            checkclick = true;
            return (greeting + " " + new String(Character.toChars(0x1F609)));
        } else {
            checkclick = false;
            return (appname);
        }

    }

    public void setOperations() {
    }

}