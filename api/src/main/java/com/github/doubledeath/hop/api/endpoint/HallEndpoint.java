package com.github.doubledeath.hop.api.endpoint;

import com.github.doubledeath.hop.api.db.repo.TagRepo;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by doubledeath on 2/27/17.
 */
@Path("/hall")
public class HallEndpoint {

    @EJB
    private TagRepo tagRepo;

    @GET
    @Path("/test")
    public void test() {
        tagRepo.update();
    }

}
