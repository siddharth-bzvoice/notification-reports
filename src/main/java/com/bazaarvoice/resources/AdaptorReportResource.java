package com.bazaarvoice.resources;

import com.bazaarvoice.db.AdaptorReportDao;
import com.bazaarvoice.models.AdaptorReport;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

    @POST
    @UnitOfWork
    @Timed
    @ApiOperation("Create adaptor report")
    @Produces(MediaType.APPLICATION_JSON)
    public void createAdaptorReport(@Valid @NotNull AdaptorReport adaptorReport){
        adaptorReportDao.create(adaptorReport);
    }

    @GET
    @Path("/downloadxls")
    @Produces("application/vnd.ms-excel")
    @UnitOfWork
    public Response downloadXLS() throws IOException {

        // This code need to be refactored to add column header
        // Cell no's are hardcoded here which will be a loop
        // This code we will put in service utils/package
        // The download resource need to be applied to other models

        List<AdaptorReport> adaptorReportList =  adaptorReportDao.getAdaptorReports();

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Sheet sheet = workbook.createSheet("Adapter Reports");

        int rowIndex = 0;

        for (AdaptorReport adaptorReport : adaptorReportList) {

            Row row = sheet.createRow(rowIndex++);

            Cell arId = row.createCell(0);
            arId.setCellValue(adaptorReport.getArId());
            Cell feedType = row.createCell(1);
            feedType.setCellValue(adaptorReport.getFeedType());
            Cell reportType = row.createCell(2);
            reportType.setCellValue(adaptorReport.getReportType());
            Cell createdDateTime = row.createCell(3);
            createdDateTime.setCellValue(adaptorReport.getCreatedDateTime());
        }

        workbook.write(out);

        Response.ResponseBuilder response = Response.ok(out.toByteArray());
        response.header("Content-Disposition", "attachment; filename=adapter_reports.xlsx");
        response.header("Content-Type","application/vnd.ms-excel");
        return response.build();

    }
}
