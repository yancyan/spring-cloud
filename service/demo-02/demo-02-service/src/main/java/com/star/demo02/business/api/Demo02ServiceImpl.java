package com.star.demo02.business.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ZhuYX
 * @date 2021/07/30
 */
@Slf4j
@Service
public class Demo02ServiceImpl implements Demo02Service {


    @Override
    public String test(String name) {
        log.info("Demo02ServiceImpl test exec. name {}", name);
        return name;
    }
}
