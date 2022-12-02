package com.alidaodao.web;

import com.alidaodao.web.utlis.PasswordUtil;
import junit.framework.TestCase;

/**
 * <p>
 *
 * </p>
 *
 * @author songbo
 * @date 2022-11-29 15:18
 * @since
 */
public class PasswordUtilTest extends TestCase {

    public void testCheckPassword() {
        PasswordUtil.checkPassword("1123434");
    }

    public void testGetSalt() {
        try {
            PasswordUtil.getSalt();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void testGetStaticSalt() {
    }

    public void testEncrypt() {
    }
}