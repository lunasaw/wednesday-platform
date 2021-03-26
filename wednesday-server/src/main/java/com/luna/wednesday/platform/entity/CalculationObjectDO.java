package com.luna.wednesday.platform.entity;

import java.util.Date;

/**
 * (tb_calculation_object#)
 *
 * @author luna
 * @since 2021/02/24 16:32:55
 */
public class CalculationObjectDO {

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
    private String            type;
    /**  */
    private String            content;
    /**  */
    private String            remarks;

    public String getRemarks() {
        return remarks;
    }

    public CalculationObjectDO setRemarks(String remarks) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

}
