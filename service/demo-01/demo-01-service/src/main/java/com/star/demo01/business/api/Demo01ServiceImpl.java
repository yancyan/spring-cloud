package com.star.demo01.business.api;

import com.star.demo01.dto.TestDemoDTO;
import com.star.demo02.business.api.Demo02Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ZhuYX
 * @date 2021/07/29
 */
@Slf4j
@Service
public class Demo01ServiceImpl implements Demo01Service {


    @Resource
    private Demo02Service demo02Service;

    @Override
    public String test(String name) {
        log.info("Demo01ServiceImpl exec. name {}", name);
        return demo02Service.test(name);
    }

    @Override
    public TestDemoDTO testDto(String name) {
        var dto = demo02Service.testDto(name);
        dto.setName(dto.getName() + " -> Demo01");
        return dto;
    }

}
