package com.alidaodao.web.utlis;


import java.lang.reflect.Field;

public class ReflectUtil {

    /**
     * 根据属性名获取属性值
     * 不考虑从祖先类继承的属性，只获取当前类属性，包括四类访问权限，private，protect，default，public
     *
     * @param fieldName
     * @param object
     * @return
     */
    public static String getFieldValueFromCurrentClassByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            // 设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            return (String) field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据属性名获取属性元素，包括各种安全范围和所有父类
     * 考虑父类继承过来的属性，包括四类访问权限，private，protect，default，public
     *
     * @param fieldName
     * @param object
     * @return
     */
    public static String getFieldByClasss(String fieldName, Object object) {
        Field field = null;
        Class<?> clazz = object.getClass();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                // 设置对象的访问权限，保证对private的属性的访问
                field.setAccessible(true);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        try {
            return (String) field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param object
     * @return
     */
    public static Object getFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
