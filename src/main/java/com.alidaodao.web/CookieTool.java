package com.alidaodao.web;

import com.bigonelab.dop.common.constants.BusinessConstants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * <p>
 * cookie操作类
 * </p>
 *
 * @author songbo
 * @date 2022-08-22 15:47
 * @since
 */
public class CookieTool {

    /**
     * 获取cookie
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookie(HttpServletRequest request, String cookieName){
        if (Objects.isNull(request.getCookies())){
            return null;
        }
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(cookieName)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

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
