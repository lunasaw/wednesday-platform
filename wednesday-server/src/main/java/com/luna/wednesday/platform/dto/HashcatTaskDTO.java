package com.luna.wednesday.platform.dto;

import java.util.Date;

/**
 * @author luna@mac
 * 2021年03月31日 08:53:00
 */
public class HashcatTaskDTO {

    private long    skip;

    private long    limit;

    /** id (Not Null) */
    private Long    id;
    /** 创建时间 (Not Null) */
    private Date    createTime;
    /** 修改时间 (Not Null) */
    private Date    modifiedTime;
    /** 版本 (Not Null) */
    private Integer version;
    /** 项目id (Not Null) */
    private Long    projectId;
    /** task状态 (Not Null) */
    private String  status;

    public long getSkip() {
        return skip;
    }

    public void setSkip(long skip) {
        this.skip = skip;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
