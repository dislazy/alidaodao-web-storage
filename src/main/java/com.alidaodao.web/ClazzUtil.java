package com.alidaodao.web;


public class ClazzUtil {

    // 判断一个类是否基本类型的包装类型
    public static boolean isWrapClass(Class clz) {

        try {
            boolean bool = clz.getName().equals("java.lang.String");
            if (bool) {
                return true;
            }
            return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }
}
