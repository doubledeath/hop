package com.github.doubledeath.hop.api.endpoint;

import com.github.doubledeath.hop.api.database.repo.HallRepo;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by doubledeath on 2/17/17.
 */
@Path(EndpointInfo.HALL)
public class HallEndpoint {

    @Inject
    private HallRepo hallRepo;

    @GET
    @Path("/test")
    public void test() {
        hallRepo.test();
    }

}
