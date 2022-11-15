package com.alidaodao.web;


import java.util.ArrayList;
import java.util.List;

public class ListCopyTool {

    public static List copyTo(List fromList, Class toClass) {
        try {
            List toList = new ArrayList();
            Object tempObj;
            for (Object aFromList : fromList) {
                tempObj = toClass.newInstance();
                toList.add(tempObj);
            }
        } catch (Exception e) {
            throw new RuntimeException("");
        }
        return new ArrayList<>();
    }
}