package com.luna.wednesday.platform.entity;

import java.util.Date;

/**
 * (tb_hash#)
 *
 * @author luna
 * @since 2021/02/23 22:51:06
 */
public class HashDO {

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
    private String            name;
    /**  */
    private String            left;
    /**  */
    private String            found;

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

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left == null ? null : left.trim();
    }

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found == null ? null : found.trim();
    }

}
