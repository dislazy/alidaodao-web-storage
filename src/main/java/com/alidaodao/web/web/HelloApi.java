package com.alidaodao.web.web;

import com.alidaodao.web.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 *
 * @author jack
 * @date 2022-12-02 16:48
 * @since
 */
@RestController
@RequestMapping("/api/")
public class HelloApi {

    @Resource
    private HelloService helloService;
    @GetMapping("hello")
    public String hello(){
        return helloService.hello();
    }
}
