package com.luna.wednesday.platform.dto;

import com.iteknical.wednesday.computing.dto.constant.AttackModeConstant;

import java.util.Date;

/**
 * @author luna
 */
public class HashcatProjectDTO {

    /** id (Not Null) */
    private Long    id;
    /** 创建时间 (Not Null) */
    private Date    createTime;
    /** 修改时间 (Not Null) */
    private Date    modifiedTime;
    /** 版本 (Not Null) */
    private Integer version;
    /** 计算类型 hashcat 或者 其他 (Not Null) */
    private String  type;
    /** 计算对象id (Not Null) */
    private Long    calculationObjectId;
    /** 状态 (Not Null) */
    private String  status;
    /** 总keyspace大小 */
    private Long    keyspace;
    /** 掩码 */
    private String  mask;
    /** 见{@link AttackModeConstant} */
    private int     attackMode;
    /** 实际行数 */
    private long    lines;
    /**  */
    private String  remarks;

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

    public Long getCalculationObjectId() {
        return calculationObjectId;
    }

    public void setCalculationObjectId(Long calculationObjectId) {
        this.calculationObjectId = calculationObjectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getKeyspace() {
        return keyspace;
    }

    public void setKeyspace(Long keyspace) {
        this.keyspace = keyspace;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public int getAttackMode() {
        return attackMode;
    }

    public void setAttackMode(int attackMode) {
        this.attackMode = attackMode;
    }

    public long getLines() {
        return lines;
    }

    public void setLines(long lines) {
        this.lines = lines;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
