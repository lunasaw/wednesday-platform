package com.luna.wednesday.hashcatcore.dao;

import com.luna.wednesday.hashcatcore.entity.HashcatHashModeConfigDO;
import org.apache.ibatis.annotations.*;

/**
 * @author Tony
 */
@Mapper
public interface HashcatHashModeConfigDAO {
    @Select("SELECT id, create_time, modified_time, version, name, lines_per_task FROM tb_hashcat_hash_mode_config WHERE id=#{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "modifiedTime", column = "modified_time"),
        @Result(property = "version", column = "version"),
        @Result(property = "name", column = "name"),
        @Result(property = "linesPerTask", column = "lines_per_task")
    })
    HashcatHashModeConfigDO get(@Param("id") long id);
}
