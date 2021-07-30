/**
 * PackageInfo
 *
 * @author ZhuYX
 * @date 2021/07/30
 */
package com.star.demo01.business.api;

class Info {
    public static final String NAME = "demo-01-service";
    public static final String URL = "${boss.url." + NAME + ":" + NAME + "}";
    public static final String JAXRS_PATH = NAME;
    public static final String WEB_PATH = "";
}