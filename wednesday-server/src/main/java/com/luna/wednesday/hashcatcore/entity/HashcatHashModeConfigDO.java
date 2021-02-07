package com.luna.wednesday.hashcatcore.entity;

import java.util.Date;

import com.iteknical.wednesday.computing.dto.constant.HashModeConstant;

public class HashcatHashModeConfigDO {
    /** 即hashmode，见{@link HashModeConstant} */
    private int    id;
    private Date   createTime;
    private Date   modifiedTime;
    private int    version;

    /** 名称 */
    private String name;
    /** 每个task的实际行数，注意不是keyspace数 */
    private long   linesPerTask;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLinesPerTask() {
        return linesPerTask;
    }

    public void setLinesPerTask(long linesPerTask) {
        this.linesPerTask = linesPerTask;
    }
}
