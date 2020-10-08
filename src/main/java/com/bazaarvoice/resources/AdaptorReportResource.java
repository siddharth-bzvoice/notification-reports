package com.bazaarvoice.resources;

import com.bazaarvoice.db.AdaptorReportDao;
import com.bazaarvoice.models.AdaptorReport;
import com.bazaarvoice.service.ExcelManager;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.List;

@Path("/adaptor-report")
@Api("/adapter-report")
public class AdaptorReportResource {

    private final AdaptorReportDao adaptorReportDao;

    public AdaptorReportResource(AdaptorReportDao adaptorReportDao) {
        this.adaptorReportDao = adaptorReportDao;
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    @Timed
    @ApiOperation("Get adaptor report")
    @Produces(MediaType.APPLICATION_JSON)
    public AdaptorReport findAdaptorReport(@PathParam("id") String id){
        return this.adaptorReportDao.findById(id);
    }

    @GET
    @UnitOfWork
    @Timed
    @ApiOperation("Get all adaptor reports")
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AdaptorReport> getAdaptorAllReports(){
        return adaptorReportDao.getAdaptorReports();
    }

}
