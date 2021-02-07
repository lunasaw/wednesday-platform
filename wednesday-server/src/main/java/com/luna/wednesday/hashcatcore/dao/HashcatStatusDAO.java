package com.luna.wednesday.hashcatcore.dao;

import com.luna.wednesday.hashcatcore.entity.HashcatStatusDO;
import org.apache.ibatis.annotations.*;

/**
 * @author Tony
 */
@Mapper
public interface HashcatStatusDAO {
    @Insert("INSERT INTO tb_hashcat_status (id, create_time, modified_time, version, agent_id, content) VALUES (#{id}, now(), now(), 0, #{agentId}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(HashcatStatusDO hashcatStatusDO);

    @Update("UPDATE tb_hashcat_status SET modified_time=now(), version=version+1, content=#{content} WHERE id=#{id} and version=#{version}")
    int update(HashcatStatusDO hashcatStatusDO);

    @Select("SELECT id, create_time, modified_time, version, agent_id, content FROM tb_hashcat_status WHERE id=#{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "modifiedTime", column = "modified_time"),
        @Result(property = "version", column = "version"),
        @Result(property = "agentId", column = "agent_id"),
        @Result(property = "content", column = "content")
    })
    HashcatStatusDO get(@Param("id") long id);

    @Select("SELECT id, create_time, modified_time, version, agent_id, content FROM tb_hashcat_status WHERE agent_id=#{agentId}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "modifiedTime", column = "modified_time"),
        @Result(property = "version", column = "version"),
        @Result(property = "agentId", column = "agent_id"),
        @Result(property = "content", column = "content")
    })
    HashcatStatusDO getByAgentId(@Param("agentId") long agentId);
}
