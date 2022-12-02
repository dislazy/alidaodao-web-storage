package com.alidaodao.web.utlis;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class StringTool {

    /**
     * 驼峰字符串改为_分割
     *
     * @return
     */
    public static String camel2underscore(String camelName) {
        //先把第一个字母大写
        camelName = capitalize(camelName);
        String regex = "([A-Z][a-z]+)";
        String replacement = "$1_";
        String underscoreName = camelName.replaceAll(regex, replacement);
        //output: Pur_Order_Id_ 接下来把最后一个_去掉，然后全部改小写
        underscoreName = underscoreName.toLowerCase().substring(0, underscoreName.length() - 1);
        return underscoreName;
    }

    /**
     * _分割转为驼峰字符串
     *
     * @param underscoreName
     * @return
     */
    public static String underscore2camel(String underscoreName) {
        String[] sections = underscoreName.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sections.length; i++) {
            String s = sections[i];
            if (i == 0) {
                sb.append(s);
            } else {
                sb.append(capitalize(s));
            }
        }
        return sb.toString();
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return new StringBuilder(str.length())
                .append(Character.toUpperCase(str.charAt(0)))
                .append(str.substring(1))
                .toString();
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String lowerCase(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return new StringBuilder(str.length())
                .append(Character.toLowerCase(str.charAt(0)))
                .append(str.substring(1))
                .toString();
    }

    /**
     * 判断对象是否为Null或空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

    /**
     * 判断字符串是否不为Null或空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

    /**
     * 字符串数组转成字符串
     *
     * @param strs [a,b,c]
     * @return a, b, c
     */
    public static String toString(String[] strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str).append(",");
        }
        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }

    /**
     *  作者 史东晗
     *  时间 2018/1/15 下午4:10
     *  方法名 subFileName
     *  参数 [fileName]
     *  返回值 java.lang.String
     *  描述 获取文件名
     */
    public static String subFileName(String fileName){
        return fileName.substring(0,fileName.lastIndexOf(""));
    }

    public static String subFileType(String fileName) {
        return fileName.substring(fileName.lastIndexOf("")+1);

    }

    /**
     * 返回百分比数据
     *
     * @param str
     * @return
     */
    public static String percentData(String str){
        if (StringUtils.isEmpty(str)){
            return null;
        }
        return str.equals("nan") || str.equals("inf")  ? null :str+"%";
    }

    /**
     * 返回百分比数据
     *
     * @param str
     * @return
     */
    public static String percentDataDouble(Double str){
        if (str == null){
            return null;
        }
        return str.toString()+"%";
    }

    /**
     * 转Integer
     *
     * @param obj
     * @return
     */
    public static Object objToInteger(Object obj) {
        if (obj != null){
            if (obj instanceof ArrayList){
                List<Integer>  list = new ArrayList<>();
                ((List<String>) obj).forEach(d->{
                    list.add(Integer.valueOf(d));
                });
                return list;
            }else if(obj instanceof String){
                return Integer.valueOf(obj.toString());
            }
        }
        return null;
    }

    /**
     *
     * @param categoryId  完整的CID
     * @return [0]  [1]
     */
    public static Integer[] pickCategory(String categoryId) {
        if (org.apache.commons.lang3.StringUtils.isBlank(categoryId)) {

        }
        String[] c = categoryId.split("-");
        if (c.length <= 2) {

        }
        //1级品类
        if ("C".equals(c[1]) && "C".equals(c[2])) {
            return new Integer[]{1, Integer.parseInt(c[0])};
        }
        //二级品类
        if (!"C".equals(c[1]) && "C".equals(c[2])) {
            return new Integer[]{2, Integer.parseInt(c[1])};
        }
        //三级品类
        if (!"C".equals(c[1]) && !"C".equals(c[2])) {
            return new Integer[]{3, Integer.parseInt(c[2])};
        }
        return new Integer[]{0, 0};
    }

    /**
     * 数据null转横杠-
     *
     * @param obj
     * @return
     */
    public static Object objNullToBar(Object obj){
        if (obj == null){
            return "-";
        }
        return obj;
    }

}