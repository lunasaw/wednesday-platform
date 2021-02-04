package com.luna.wednesday.platform.entity;

import java.util.Date;

/**
 * (tb_project#)
 *
 * @author luna
 * @since 2021/02/04 15:01:59
 */
public class ProjectDO {

    private static final long serialVersionUID = 1L;

   /** id (Not Null) */
    private Long id;
   /** 创建时间 (Not Null) */
    private Date createTime;
   /** 修改时间 (Not Null) */
    private Date modifiedTime;
   /** 版本 (Not Null) */
    private Integer version;
   /** 项目名 (Not Null) */
    private String projectName;
   /** 状态 (Not Null) */
    private String projectStatus;
   /** 计算行数 (Not Null) */
    private Long projectLines;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus == null ? null : projectStatus.trim();
    }

    public Long getProjectLines() {
        return projectLines;
    }

    public void setProjectLines(Long projectLines) {
        this.projectLines = projectLines;
    }


}
