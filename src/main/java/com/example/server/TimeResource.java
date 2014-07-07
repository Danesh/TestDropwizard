package com.example.server;

import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/time")
@Produces(MediaType.APPLICATION_JSON)
public class TimeResource {

    public TimeResource() {
    }

    @GET
    @Timed
    public long getTime() {
        return System.currentTimeMillis();
    }
}