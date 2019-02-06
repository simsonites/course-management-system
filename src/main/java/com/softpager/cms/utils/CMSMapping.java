package com.softpager.cms.utils;

public final class CMSMapping {
    public static final String STUDENTS = "/students";
    public static final String ALL = STUDENTS;
    public static final String SHOW_FORM = STUDENTS+"/new";
    public static final String CREATE = STUDENTS+"/create";
    public static final String REDIRECT = "redirect:/"+ ALL;


    public static final String GET_ONE = "/{id}";
    public static final String UPDATE = "/{id}";
    public static final String DELETE = "/{id}";
}
