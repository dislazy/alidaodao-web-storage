package com.alidaodao.web;
import java.util.Objects;

public class CookieTool {


    /**
     *设置cookie
     *
     * @param response
     * @param cookieName
     * @param value
     * @param cookieMaxAge
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String value, int cookieMaxAge){
        Cookie cookie = new Cookie(cookieName,value);
        cookie.setPath("/");
        cookie.setMaxAge(cookieMaxAge);
        cookie.setHttpOnly(false);
//        cookie.setSecure(true);
        response.addCookie(cookie);
        response.setHeader(cookieName,value);
    }

    /**
     * 删除cookie
     *
     * @param response
     * @param cookieName
     */
    public static void deleteCookie(HttpServletResponse response, String cookieName){
        setCookie(response,cookieName,null,0);
    }

    /**
     * 保存登录的cookie
     *
     * @param response
     * @param token
     */
    public static void saveAuthCookie(HttpServletResponse response,String token){
        //默认设置一天
        setCookie(response, BusinessConstants.AUTHORIZATION, token, 86400);
    }



}
