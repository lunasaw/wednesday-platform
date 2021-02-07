package com.luna.wednesday.hashcatcore.dao;

import com.luna.wednesday.hashcatcore.entity.HashDO;
import org.apache.ibatis.annotations.*;

/**
 * @author Tony
 */
@Mapper
public interface HashDAO {
    @Insert("INSERT INTO tb_hash (id, create_time, modified_time, version, name, content, result) VALUES (#{id}, now(), now(), 0, #{name}, #{content}, #{result})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(HashDO hashDO);

    @Update("UPDATE tb_hash SET modified_time=now(), version=version+1, content=#{content}, result=#{result} WHERE id=#{id} and version=#{version}")
    int update(HashDO hashDO);

    @Select("SELECT id, create_time, modified_time, version, name, content, result FROM tb_hash WHERE id=#{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "modifiedTime", column = "modified_time"),
        @Result(property = "version", column = "version"),
        @Result(property = "name", column = "name"),
        @Result(property = "content", column = "content"),
        @Result(property = "result", column = "result")
    })
    HashDO get(@Param("id") long id);

    @Select("SELECT id, create_time, modified_time, version, name, content, result FROM tb_hash WHERE name=#{name}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "modifiedTime", column = "modified_time"),
        @Result(property = "version", column = "version"),
        @Result(property = "name", column = "name"),
        @Result(property = "content", column = "content"),
        @Result(property = "result", column = "result")
    })
    HashDO getByName(@Param("name") String name);
}
