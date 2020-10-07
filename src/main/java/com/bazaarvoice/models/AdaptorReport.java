package com.bazaarvoice.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@NamedQueries({@NamedQuery(name="get_adapter_reports", query="select e from AdaptorReport e")})
public class AdaptorReport {

    @Id
    private String arId;
    private String feedType;
    private String reportType;
    private Date createdDateTime;

    public AdaptorReport() {}

    public AdaptorReport(String arId, String feedType, String reportType, Date createdDateTime) {
        this.arId = arId;
        this.feedType = feedType;
        this.reportType = reportType;
        this.createdDateTime = createdDateTime;
    }

    public String getArId() {
        return arId;
    }

    public void setArId(String arId) {
        this.arId = arId;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        return "AdaptorReport{" +
                "arId='" + arId + '\'' +
                ", feedType='" + feedType + '\'' +
                ", reportType='" + reportType + '\'' +
                ", createdDateTime=" + createdDateTime +
                '}';
    }
}

