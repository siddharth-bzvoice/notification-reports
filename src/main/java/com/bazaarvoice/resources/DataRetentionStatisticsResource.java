package com.bazaarvoice.resources;

import com.bazaarvoice.db.AdaptorReportDao;
import com.bazaarvoice.db.DataRetentionStatisticsDao;
import com.bazaarvoice.models.AdaptorReport;
import com.bazaarvoice.models.DataRetentionStatistics;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/data-retention")
@Api("/data-retention")
@Produces(MediaType.APPLICATION_JSON)
public class DataRetentionStatisticsResource {

    private final DataRetentionStatisticsDao dataRetentionStatisticsDao;

    public DataRetentionStatisticsResource(DataRetentionStatisticsDao dataRetentionStatisticsDao) {
        this.dataRetentionStatisticsDao = dataRetentionStatisticsDao;
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    @Timed
    @ApiOperation("Get data retention statistics report")
    public DataRetentionStatistics findDataRetentionStatistics(@PathParam("id") String id){
        return this.dataRetentionStatisticsDao.findById(id);
    }

    @GET
    @UnitOfWork
    @Timed
    @ApiOperation("Get all data retention statistics")
    @Path("/all")
    public List<DataRetentionStatistics> getDataRetentionStatistics(){
        return dataRetentionStatisticsDao.getDataRetentionStatistics();
    }

    @POST
    @UnitOfWork
    @Timed
    @ApiOperation("Create data retention statistics")
    public void createDataRetentionStatistics(@Valid @NotNull DataRetentionStatistics dataRetentionStatistics){
        dataRetentionStatisticsDao.create(dataRetentionStatistics);
    }
}
