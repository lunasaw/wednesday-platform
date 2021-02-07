package com.luna.wednesday.hashcatcore.entity;

import java.util.Date;

/**
 * (tb_hashcat_task#)
 *
 * @author luna
 * @since 2021/02/06 16:55:24
 */
public class HashcatTaskDO {

    private static final long serialVersionUID = 1L;

    /** hashcat 任务编号 (Not Null) */
    private Long              id;
    /** 平台任务编号 (Not Null) */
    private Long              taskId;
    /** 创建时间 (Not Null) */
    private Date              createTime;
    /** 修改时间 (Not Null) */
    private Date              modifiedTime;
    /** 版本 (Not Null) */
    private Integer           version;
    /** (Not Null) */
    private Long              skip;
    /** (Not Null) */
    private Long              limit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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

    public Long getSkip() {
        return skip;
    }

    public void setSkip(Long skip) {
        this.skip = skip;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

}
