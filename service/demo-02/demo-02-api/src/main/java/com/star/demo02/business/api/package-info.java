/**
 * @author ZhuYX
 * @date 2021/07/30
 */
package com.star.demo02.business.api;


class Info {
    public static final String NAME = "demo-02-service";
    public static final String URL = "${boss.url." + NAME + ":" + NAME + "}";
    public static final String JAXRS_PATH = NAME;
}