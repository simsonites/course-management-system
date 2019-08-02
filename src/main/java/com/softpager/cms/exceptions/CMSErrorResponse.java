package com.softpager.cms.exceptions;

import lombok.Data;

@Data
public class CMSErrorResponse {
    private  int status;
    private String message;
    private long timeStamp;
}
