package com.softpager.cms.utils;

public enum UserTitle {
    DR("Dr."),
    PROF("Prof."),
    MR("Mr."),
    MISS("Miss."),
    MRS("Mrs."),
    REV("Rev"),
    OTHERS("Others");

    private final String selectedValue;
     
    private UserTitle(String selectedValue) {
        this.selectedValue = selectedValue;
    }

    public String getSelectedValue() {
        return selectedValue;
    }
}