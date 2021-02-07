package com.luna.wednesday.hashcatcore.dao;

import java.util.List;

import com.luna.wednesday.hashcatcore.entity.HashcatProjectDO;
import org.apache.ibatis.annotations.*;

/**
 * (tb_hashcat_project).
 *
 * @author luna
 * @since 2021/02/06 16:54:26
 */
@Mapper
public interface HashcatProjectDAO {

    /**
     * 根据id查询
     *
     * @param id
     * @return HashcatProjectDO
     */
    @Select("SELECT  id, project_id, create_time, modified_time, version, mask, keyspace, hash_mode, attack_mode, hash_id, lines  FROM tb_hashcat_project WHERE id = #{id}")
    @Results({
        @Result(column = "id", property = "id", id = true),
        @Result(column = "project_id", property = "projectId"),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "modified_time", property = "modifiedTime"),
        @Result(column = "version", property = "version"),
        @Result(column = "mask", property = "mask"),
        @Result(column = "keyspace", property = "keyspace"),
        @Result(column = "hash_mode", property = "hashMode"),
        @Result(column = "attack_mode", property = "attackMode"),
        @Result(column = "hash_id", property = "hashId"),
        @Result(column = "lines", property = "lines"),
    })
    HashcatProjectDO get(@Param("id") Long id);

    /**
     * 单个插入
     *
     * @param hashcatProjectDO HashcatProjectDO
     * @return
     */
    @Insert("INSERT INTO tb_hashcat_project(id, project_id, create_time, modified_time, version, mask, keyspace, hash_mode, attack_mode, hash_id, lines) "
        + "VALUES (#{id}, #{projectId}, #{createTime}, #{modifiedTime}, #{version}, #{mask}, #{keyspace}, #{hashMode}, #{attackMode}, #{hashId}, #{lines})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(HashcatProjectDO hashcatProjectDO);

    /**
     * 批量插入
     *
     * @param list HashcatProjectDO列表
     */
    @Insert("<script>INSERT INTO tb_hashcat_project(id, project_id, create_time, modified_time, version, mask, keyspace, hash_mode, attack_mode, hash_id, lines) VALUES "
        + "<foreach collection='list' index='index' item='n' separator=','> "
        + "(#{n.id}, #{n.projectId}, #{n.createTime}, #{n.modifiedTime}, #{n.version}, #{n.mask}, #{n.keyspace}, #{n.hashMode}, #{n.attackMode}, #{n.hashId}, #{n.lines})"
        + "</foreach></script>")
    void insertBatch(@Param("list") List<HashcatProjectDO> list);

    /**
     * 更新
     *
     * @param hashcatProjectDO
     */
    @Update("UPDATE tb_hashcat_project SET id = #{id}, project_id = #{projectId}, create_time = #{createTime}, modified_time = #{modifiedTime}, version = #{version}, mask = #{mask}, keyspace = #{keyspace}, hash_mode = #{hashMode}, attack_mode = #{attackMode}, hash_id = #{hashId}, lines = #{lines} WHERE id = #{id}")
    void update(HashcatProjectDO hashcatProjectDO);

    /**
     * 单个删除
     *
     * @param id id
     */
    @Delete("DELETE FROM tb_hashcat_project WHERE id = #{id}")
    void delete(@Param("id") Long id);

    /**
     * 批量删除
     *
     * @param ids ids
     */
    @Delete("<script>DELETE FROM tb_hashcat_project WHERE ID IN "
        + "<foreach collection='ids' index='index' item='id' open='(' separator=',' close=')'>"
        + "#{id}"
        + "</foreach></script>")
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 统计
     *
     * @return 数量
     */
    @Select("SELECT COUNT(*) FROM tb_hashcat_project")
    int count();

    /**
     * 平台项目Id 查询Hashcat项目
     * 
     * @param projectId
     * @return
     */
    @Select("SELECT  id, project_id, create_time, modified_time, version, mask, keyspace, hash_mode, attack_mode, hash_id, lines  FROM tb_hashcat_project WHERE project_id = #{projectId}")
    @Results({
        @Result(column = "id", property = "id", id = true),
        @Result(column = "project_id", property = "projectId"),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "modified_time", property = "modifiedTime"),
        @Result(column = "version", property = "version"),
        @Result(column = "mask", property = "mask"),
        @Result(column = "keyspace", property = "keyspace"),
        @Result(column = "hash_mode", property = "hashMode"),
        @Result(column = "attack_mode", property = "attackMode"),
        @Result(column = "hash_id", property = "hashId"),
        @Result(column = "lines", property = "lines"),
    })
    HashcatProjectDO getByProjectId(@Param("projectId") Long projectId);
}
