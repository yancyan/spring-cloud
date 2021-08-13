package com.star.test.message.binder;

import com.star.demo01.Demo01Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.test.context.ActiveProfiles;

import java.io.Serializable;

/**
 * @author ZhuYX
 * @date 2021/08/02
 */

@SpringBootTest(classes = {Demo01Application.class})
@ActiveProfiles(profiles = {"dev-f1"})
public class SampleStreamTests {

    @Autowired
    StreamBridge streamBridge;

    @Test
    public void testEmptyConfiguration() {
        var person = new Person();

        person.setName("testName");
        streamBridge.send("bind-test-01", person);

    }


    class Person implements Serializable {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
