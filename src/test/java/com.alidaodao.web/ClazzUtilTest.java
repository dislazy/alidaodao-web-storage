package com.alidaodao.web;

import junit.framework.TestCase;

/**
 * <p>
 *
 * </p>
 *
 * @author songbo
 * @date 2022-11-15 15:17
 * @since
 */
public class ClazzUtilTest extends TestCase {

    public void testIsWrapClass() {
        ClazzUtil.isWrapClass(ClazzUtilTest.class);
    }
}