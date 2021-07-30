package com.star.demo02.business.api;

import com.star.demo01.dto.TestDemoDTO;
import org.springframework.cloud.openfeign.FeignClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author ZhuYX
 * @date 2021/07/30
 */
@FeignClient(
        name = Info.NAME,
        // path = Info.JAXRS_PATH,
        // url = Info.URL,
        primary = false,
        contextId = "jax-rs"
)
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/demo-02")
public interface Demo02Service {

    @GET
    @Path("test")
    String test(@QueryParam("name") String name);


    @GET
    @Path("test/dto")
    TestDemoDTO testDto(@QueryParam("name") String name);
}
