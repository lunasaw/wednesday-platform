package com.luna.wednesday.platform.entity;

import java.util.Date;

/**
 * (tb_status#)
 *
 * @author luna
 * @since 2021/02/04 15:35:58
 */
public class StatusDO {

    private static final long serialVersionUID = 1L;

    /** (Not Null) */
    private Long              id;
    /** (Not Null) */
    private Date              createTime;
    /** (Not Null) */
    private Date              modifiedTime;
    /** (Not Null) */
    private Integer           version;
    /** (Not Null) */
    private Long              agentId;
    /** (Not Null) */
    private String            content;

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

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

}
