package com.alidaodao.web;

import com.alidaodao.web.utlis.NumberTool;
import junit.framework.TestCase;

/**
 * <p>
 *
 * </p>
 *
 * @author songbo
 * @date 2022-12-02 15:26
 * @since
 */
public class NumberToolTest extends TestCase {

    public void testRandomEmailCode() {
        NumberTool.randomEmailCode(10);
    }

    public void testRandDom() {
        NumberTool.randDom(1,10);
    }

    public void testStrToBigDecimal() {
        try {
            NumberTool.strToBigDecimal("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}