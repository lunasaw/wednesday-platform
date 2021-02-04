package com.luna.wednesday.platform.entity;

import java.util.Date;

/**
 * (tb_task_result#)
 *
 * @author luna
 * @since 2021/02/03 19:57:21
 */
public class TaskResultDO{

    private static final long serialVersionUID = 1L;

   /** id (Not Null) */
    private Long id;
   /** 创建时间 (Not Null) */
    private Date createTime;
   /** 修改时间 (Not Null) */
    private Date modifiedTime;
   /** 版本 (Not Null) */
    private Integer version;
   /** task_id (Not Null) */
    private Long taskId;
   /** agent_id (Not Null) */
    private Long agentId;
   /** 状态 (Not Null) */
    private String status;
   /** 单个task结果 */
    private String result;

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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }


}
