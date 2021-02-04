package com.luna.wednesday.platform.entity;

import java.util.Date;

/**
 * (tb_speed#)
 *
 * @author luna
 * @since 2021/02/03 19:57:21
 */
public class SpeedDO{

    private static final long serialVersionUID = 1L;

   /**  (Not Null) */
    private Long id;
   /**  (Not Null) */
    private Date createTime;
   /**  (Not Null) */
    private Date modifiedTime;
   /**  (Not Null) */
    private Integer version;
   /** 项目id (Not Null) */
    private Long projectId;
   /** 设备id (Not Null) */
    private Long agentId;
   /**  (Not Null) */
    private Integer taskSize;
   /**  (Not Null) */
    private Integer runningSecond;
   /**  */
    private String indicators;

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

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Integer getTaskSize() {
        return taskSize;
    }

    public void setTaskSize(Integer taskSize) {
        this.taskSize = taskSize;
    }

    public Integer getRunningSecond() {
        return runningSecond;
    }

    public void setRunningSecond(Integer runningSecond) {
        this.runningSecond = runningSecond;
    }

    public String getIndicators() {
        return indicators;
    }

    public void setIndicators(String indicators) {
        this.indicators = indicators == null ? null : indicators.trim();
    }


}
