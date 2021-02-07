package com.luna.wednesday.hashcatcore.entity;

import java.util.Date;

/**
 * (tb_hashcat_project#)
 *
 * @author luna
 * @since 2021/02/06 16:54:26
 */
public class HashcatProjectDO {

    private static final long serialVersionUID = 1L;

    /** hashcat 项目编号 (Not Null) */
    private Long              id;
    /** 平台项目编号 (Not Null) */
    private Long              projectId;
    /** 创建时间 (Not Null) */
    private Date              createTime;
    /** 修改时间 (Not Null) */
    private Date              modifiedTime;
    /** 版本 (Not Null) */
    private Integer           version;
    /** 掩码 (Not Null) */
    private String            mask;
    /**  */
    private Long              keyspace;
    /** hash类型 (Not Null) */
    private Integer           hashMode;
    /** 计算模式 (Not Null) */
    private Integer           attackMode;
    /** hashId (Not Null) */
    private Long              hashId;
    /** (Not Null) */
    private Long              lines;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask == null ? null : mask.trim();
    }

    public Long getKeyspace() {
        return keyspace;
    }

    public void setKeyspace(Long keyspace) {
        this.keyspace = keyspace;
    }

    public Integer getHashMode() {
        return hashMode;
    }

    public void setHashMode(Integer hashMode) {
        this.hashMode = hashMode;
    }

    public Integer getAttackMode() {
        return attackMode;
    }

    public void setAttackMode(Integer attackMode) {
        this.attackMode = attackMode;
    }

    public Long getHashId() {
        return hashId;
    }

    public void setHashId(Long hashId) {
        this.hashId = hashId;
    }

    public Long getLines() {
        return lines;
    }

    public void setLines(Long lines) {
        this.lines = lines;
    }

}
