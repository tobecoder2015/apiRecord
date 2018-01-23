package com.wing.apirecord.controller;

import lombok.Data;

@Data
public class ErrorMsg {
    int status=0;
    String msg="";

    public ErrorMsg(String msg, int status){
        this.status=status;
        this.msg=msg;
    }
}
