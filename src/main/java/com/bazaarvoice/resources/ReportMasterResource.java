package com.bazaarvoice.resources;

import com.bazaarvoice.db.ReportMasterDao;
import com.bazaarvoice.models.ReportMaster;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/report-master")
@Api("/report-master")
@Produces(MediaType.APPLICATION_JSON)
public class ReportMasterResource {

    private final ReportMasterDao reportMasterDao;

    public ReportMasterResource(ReportMasterDao reportMasterDao) {
        this.reportMasterDao = reportMasterDao;
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    @Timed
    @ApiOperation("Get report master")
    public ReportMaster findReportMaster(@PathParam("id") String id){
        return this.reportMasterDao.findById(id);
    }

    @GET
    @UnitOfWork
    @Timed
    @ApiOperation("Get all report masters")
    @Path("/all")
    public List<ReportMaster> getReportMaster(){
        return this.reportMasterDao.getReportMasters();
    }

}
