package com.luna.wednesday.platform.entity;

import java.util.Date;

/**
 * (tb_agent#)
 *
 * @author luna
 * @since 2021/02/03 19:57:21
 */
public class AgentDO{

    private static final long serialVersionUID = 1L;

   /** id (Not Null) */
    private Long id;
   /** 创建时间 (Not Null) */
    private Date createTime;
   /** 修改时间 (Not Null) */
    private Date modifiedTime;
   /** 版本 (Not Null) */
    private Integer version;
   /** key (Not Null) */
    private String key;
   /** 类型 (Not Null) */
    private String type;
   /** api secretkey (Not Null) */
    private String hardware;
   /** 创建版本 */
    private String agentVersion;
   /** 最后上线时间 */
    private Date lastSeen;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware == null ? null : hardware.trim();
    }

    public String getAgentVersion() {
        return agentVersion;
    }

    public void setAgentVersion(String agentVersion) {
        this.agentVersion = agentVersion == null ? null : agentVersion.trim();
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }


}
