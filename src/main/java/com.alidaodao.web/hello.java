package com.alidaodao.web;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bosong
 * @since 2021-09-07
 */
public class hello {
    /**
     * hello-world 初始化项目文件
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("hello world");
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ints.add(i);
        }
        StreamUtils.distinctByKeys(e->e.equals(1));
    }
}
