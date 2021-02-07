package com.luna.wednesday.hashcatcore.entity;

import java.util.Date;

/**
 * @author Tony
 */
public class HashcatStatusDO {
    /** 主键id */
    private long   id;
    /** 创建时间 */
    private Date   createTime;
    /** 修改时间 */
    private Date   modifiedTime;
    /** 锁 */
    private int    version;

    private long   agentId;

    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
