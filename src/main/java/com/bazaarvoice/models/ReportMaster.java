package com.bazaarvoice.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@NamedQueries({@NamedQuery(name="com.bazaarvoice.models.ReportMaster.findAll", query="select e from ReportMaster e")})
public class ReportMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reportId;
    private String reportType;
    private String reportDesc;
    private Date createdDateTime;

    public ReportMaster(){}

    public ReportMaster(long reportId, String reportType, String reportDesc, Date createdDateTime) {
        this.reportId = reportId;
        this.reportType = reportType;
        this.reportDesc = reportDesc;
        this.createdDateTime = createdDateTime;
    }

    public long getReportId() {
        return reportId;
    }

    public void setReportId(long reportId) {
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
