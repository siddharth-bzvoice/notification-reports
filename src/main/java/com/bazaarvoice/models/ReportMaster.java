package com.bazaarvoice.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ReportMaster {

    @Id
    private String reportId;
    private String reportType;
    private String reportDesc;
    private Date createdDateTime;

    public ReportMaster(){}

    public ReportMaster(String reportId, String reportType, String reportDesc, Date createdDateTime) {
        this.reportId = reportId;
        this.reportType = reportType;
        this.reportDesc = reportDesc;
        this.createdDateTime = createdDateTime;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }
}
