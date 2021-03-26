package com.luna.wednesday.platform.entity;

import java.util.Date;

/**
 * (tb_project#)
 *
 * @author luna
 * @since 2021/02/23 22:37:35
 */
public class ProjectDO {

    private static final long serialVersionUID = 1L;

    /** id (Not Null) */
    private Long              id;
    /** 创建时间 (Not Null) */
    private Date              createTime;
    /** 修改时间 (Not Null) */
    private Date              modifiedTime;
    /** 版本 (Not Null) */
    private Integer           version;
    /** 项目名 (Not Null) */
    private String            name;
    /** 状态 (Not Null) */
    private String            status;
    /** 扩展 (Not Null) */
    private String            content;
    /**  */
    private String            remarks;

    public String getRemarks() {
        return remarks;
    }

    public ProjectDO setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "ProjectDO{" +
            "id=" + id +
            ", createTime=" + createTime +
            ", modifiedTime=" + modifiedTime +
            ", version=" + version +
            ", name='" + name + '\'' +
            ", status='" + status + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
