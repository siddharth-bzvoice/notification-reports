package com.bazaarvoice.models;

import javax.persistence.*;

@Entity
@Table
public class DataRetentionStatistics {

    @Id
    private String jobId;
    private String jobStatus;
    private String runningTime;
    private long purgedEventCount;
    private long purgedCorrespondencesCount;
    private String clientName;
    private String feedType;
    private String runningDate;


    public DataRetentionStatistics() {}

    public DataRetentionStatistics(
            String jobId,
            String jobStatus,
            String runningTime,
            long purgedEventCount,
            long purgedCorrespondencesCount,
            String clientName,
            String feedType,
            String runningDate) {
        this.jobId = jobId;
        this.jobStatus = jobStatus;
        this.runningTime = runningTime;
        this.purgedEventCount = purgedEventCount;
        this.purgedCorrespondencesCount = purgedCorrespondencesCount;
        this.clientName = clientName;
        this.feedType = feedType;
        this.runningDate = runningDate;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public long getPurgedEventCount() {
        return purgedEventCount;
    }

    public void setPurgedEventCount(long purgedEventCount) {
        this.purgedEventCount = purgedEventCount;
    }

    public long getPurgedCorrespondencesCount() {
        return purgedCorrespondencesCount;
    }

    public void setPurgedCorrespondencesCount(long purgedCorrespondencesCount) {
        this.purgedCorrespondencesCount = purgedCorrespondencesCount;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getRunningDate() {
        return runningDate;
    }

    public void setRunningDate(String runningDate) {
        this.runningDate = runningDate;
    }

}
