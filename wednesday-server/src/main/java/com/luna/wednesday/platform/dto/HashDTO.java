package com.luna.wednesday.platform.dto;

import java.util.Date;
import java.util.List;

/**
 * @author luna@mac
 * @className HashDTO.java
 * @description TODO
 * @createTime 2021年02月25日 14:21:00
 */
public class HashDTO {

    /** (Not Null) */
    private Long           id;
    /** (Not Null) */
    private Date           createTime;
    /** (Not Null) */
    private Date           modifiedTime;
    /** (Not Null) */
    private Integer        version;
    /** (Not Null) */
    private String         type;
    /** hash */
    private List<FoundDTO> found;

    private List<String>   left;

    private String         remarks;

    public String getRemarks() {
        return remarks;
    }

    public HashDTO setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public List<FoundDTO> getFound() {
        return found;
    }

    public void setFound(List<FoundDTO> found) {
        this.found = found;
    }

    public List<String> getLeft() {
        return left;
    }

    public void setLeft(List<String> left) {
        this.left = left;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
