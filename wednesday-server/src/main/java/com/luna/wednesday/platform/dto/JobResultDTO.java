package com.luna.wednesday.platform.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;

/**
 * @author Tony
 */
public class JobResultDTO {
    /** 类型，见{@link } */
    @NotEmpty
    private String      type;

    private Long        projectId;

    private Set<String> resultSet;

    private Set<Long>   taskResultIdSet;
    /** 运行时长 */
    private int         runningSecond;

    public Set<Long> getTaskResultIdSet() {
        return taskResultIdSet;
    }

    public void setTaskResultIdSet(Set<Long> taskResultIdSet) {
        this.taskResultIdSet = taskResultIdSet;
    }

    public Set<String> getResultSet() {
        return resultSet;
    }

    public void setResultSet(Set<String> resultSet) {
        this.resultSet = resultSet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getProjectId() {
        return projectId;
    }

    public JobResultDTO setProjectId(Long projectId) {
        this.projectId = projectId;
        return this;
    }

    public int getRunningSecond() {
        return runningSecond;
    }

    public void setRunningSecond(int runningSecond) {
        this.runningSecond = runningSecond;
    }
}
