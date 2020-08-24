package com.wufan.web.utils;

import java.util.UUID;

/**
 * @author wufan
 * @date 2020/4/11 0011 3:37
 */
public class MyStringUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
