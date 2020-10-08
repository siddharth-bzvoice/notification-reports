package com.bazaarvoice.resources;

import com.bazaarvoice.db.DataRetentionStatisticsDao;
import com.bazaarvoice.models.DataRetentionStatistics;
import com.bazaarvoice.services.ExcelManager;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

@Path("/data-retention")
@Api("/data-retention")
public class DataRetentionStatisticsResource {

    private final DataRetentionStatisticsDao dataRetentionStatisticsDao;

    public DataRetentionStatisticsResource(DataRetentionStatisticsDao dataRetentionStatisticsDao) {
        this.dataRetentionStatisticsDao = dataRetentionStatisticsDao;
    }

    @POST
    @UnitOfWork
    @Timed
    @ApiOperation("Create data retention statistics")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createDataRetentionStatistics(@Valid @NotNull DataRetentionStatistics dataRetentionStatistics){
        dataRetentionStatisticsDao.create(dataRetentionStatistics);
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    @Timed
    @ApiOperation("Get data retention statistics report")
    @Produces(MediaType.APPLICATION_JSON)
    public DataRetentionStatistics findDataRetentionStatistics(@PathParam("id") String id){
        return this.dataRetentionStatisticsDao.findById(id);
    }

    @GET
    @UnitOfWork
    @Timed
    @ApiOperation("Get all data retention statistics")
    @Path("/statistics")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DataRetentionStatistics> getDataRetentionStatisticsFiltered(@QueryParam("clientname") Optional<String> clientname){
        List<DataRetentionStatistics> dataRetentionStatisticsList = null;
        if(!clientname.isPresent()){
            dataRetentionStatisticsList =  dataRetentionStatisticsDao.getDataRetentionStatistics();
        }
        else {
            dataRetentionStatisticsList = dataRetentionStatisticsDao.getDataRetentionStatisticsByClientName(clientname.get());
        }
        return dataRetentionStatisticsList;
    }

    @GET
    @UnitOfWork
    @Timed
    @Path("/statistics/downloadxls")
    @Produces("application/vnd.ms-excel")
    @ApiOperation("Download data retention statistics reports as excel")
    public Response downloadXLSFiltered(@QueryParam("clientname") Optional<String> clientname) {
        List<DataRetentionStatistics> dataRetentionStatisticsList = null;
        if(!clientname.isPresent()){
            dataRetentionStatisticsList =  dataRetentionStatisticsDao.getDataRetentionStatistics();
        }
        else {
            dataRetentionStatisticsList = dataRetentionStatisticsDao.getDataRetentionStatisticsByClientName(clientname.get());
        }
        ByteArrayOutputStream out = ExcelManager.writeToExcel("Data Retention Statistics", dataRetentionStatisticsList);
        Response.ResponseBuilder response = Response.ok(out.toByteArray());
        response.header("Content-Disposition", "attachment; filename=data_retention_statistics.xlsx");
        response.header("Content-Type","application/vnd.ms-excel");
        return response.build();
    }
}
