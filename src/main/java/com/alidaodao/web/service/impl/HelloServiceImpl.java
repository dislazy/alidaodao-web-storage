package com.alidaodao.web.service.impl;

import com.alidaodao.web.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author jack
 * @date 2022-12-02 16:46
 * @since
 */
@Service
public class HelloServiceImpl implements HelloService {
    /**
     * hello
     *
     * @return
     */
    @Override
    public String hello() {
        return "hello";
    }
}
