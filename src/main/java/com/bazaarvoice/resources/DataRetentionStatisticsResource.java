package com.bazaarvoice.resources;

import com.bazaarvoice.db.AdaptorReportDao;
import com.bazaarvoice.db.DataRetentionStatisticsDao;
import com.bazaarvoice.models.AdaptorReport;
import com.bazaarvoice.models.DataRetentionStatistics;
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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    @GET
    @UnitOfWork
    @Timed
    @ApiOperation("Get all data retention statistics")
    @Path("/all/{clientname}")
    public List<DataRetentionStatistics> getDataRetentionStatisticsFiltered(@PathParam("clientname") String clientname){
        return dataRetentionStatisticsDao.getDataRetentionStatisticsByClientName(clientname);
    }

    @POST
    @UnitOfWork
    @Timed
    @ApiOperation("Create data retention statistics")
    public void createDataRetentionStatistics(@Valid @NotNull DataRetentionStatistics dataRetentionStatistics){
        dataRetentionStatisticsDao.create(dataRetentionStatistics);
    }

    @GET
    @UnitOfWork
    @Timed
    @Path("/downloadxls")
    @Produces("application/vnd.ms-excel")
    @ApiOperation("Download adaptor reports as excel")
    public Response downloadXLS() throws IOException {
        List<DataRetentionStatistics> dataRetentionStatisticsList =  dataRetentionStatisticsDao.getDataRetentionStatistics();
        ByteArrayOutputStream out = ExcelManager.writeToExcel("Data Retention Statistics", dataRetentionStatisticsList);
        Response.ResponseBuilder response = Response.ok(out.toByteArray());
        response.header("Content-Disposition", "attachment; filename=adapter_reports.xlsx");
        response.header("Content-Type","application/vnd.ms-excel");
        return response.build();
    }

    @GET
    @UnitOfWork
    @Timed
    @Path("/downloadxls/{clientname}")
    @Produces("application/vnd.ms-excel")
    @ApiOperation("Download adaptor reports as excel")
    public Response downloadXLSFiltered() throws IOException {
        List<DataRetentionStatistics> dataRetentionStatisticsList =  dataRetentionStatisticsDao.getDataRetentionStatistics();
        ByteArrayOutputStream out = ExcelManager.writeToExcel("Data Retention Statistics", dataRetentionStatisticsList);
        Response.ResponseBuilder response = Response.ok(out.toByteArray());
        response.header("Content-Disposition", "attachment; filename=data_retention_statistics.xlsx");
        response.header("Content-Type","application/vnd.ms-excel");
        return response.build();
    }
}
