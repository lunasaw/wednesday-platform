package com.luna.wednesday.hashcatcore.dao;

import java.util.List;

import com.luna.wednesday.hashcatcore.entity.HashcatTaskDO;
import org.apache.ibatis.annotations.*;

/**
 * (tb_hashcat_task).
 *
 * @author luna
 * @since 2021/02/06 16:55:24
 */
@Mapper
public interface HashcatTaskDAO {

    /**
     * 根据id查询
     *
     * @param id
     * @return HashcatTaskDO
     */
    @Select("SELECT  id, task_id, create_time, modified_time, version, skip, limit  FROM tb_hashcat_task WHERE id = #{id}")
    @Results({
        @Result(column = "id", property = "id", id = true),
        @Result(column = "task_id", property = "taskId"),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "modified_time", property = "modifiedTime"),
        @Result(column = "version", property = "version"),
        @Result(column = "skip", property = "skip"),
        @Result(column = "limit", property = "limit"),
    })
    HashcatTaskDO get(@Param("id") Long id);

    /**
     * 单个插入
     *
     * @param hashcatTaskDO HashcatTaskDO
     * @return
     */
    @Insert("INSERT INTO tb_hashcat_task(id, task_id, create_time, modified_time, version, skip, limit) "
        + "VALUES (#{id}, #{taskId}, #{createTime}, #{modifiedTime}, #{version}, #{skip}, #{limit})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(HashcatTaskDO hashcatTaskDO);

    /**
     * 批量插入
     *
     * @param list HashcatTaskDO列表
     */
    @Insert("<script>INSERT INTO tb_hashcat_task(id, task_id, create_time, modified_time, version, skip, limit) VALUES "
        + "<foreach collection='list' index='index' item='n' separator=','> "
        + "(#{n.id}, #{n.taskId}, #{n.createTime}, #{n.modifiedTime}, #{n.version}, #{n.skip}, #{n.limit})"
        + "</foreach></script>")
    void insertBatch(@Param("list") List<HashcatTaskDO> list);

    /**
     * 更新
     *
     * @param hashcatTaskDO
     */
    @Update("UPDATE tb_hashcat_task SET id = #{id}, task_id = #{taskId}, create_time = #{createTime}, modified_time = #{modifiedTime}, version = #{version}, skip = #{skip}, limit = #{limit} WHERE id = #{id}")
    void update(HashcatTaskDO hashcatTaskDO);

    /**
     * 单个删除
     *
     * @param id id
     */
    @Delete("DELETE FROM tb_hashcat_task WHERE id = #{id}")
    void delete(@Param("id") Long id);

    /**
     * 批量删除
     *
     * @param ids ids
     */
    @Delete("<script>DELETE FROM tb_hashcat_task WHERE ID IN "
        + "<foreach collection='ids' index='index' item='id' open='(' separator=',' close=')'>"
        + "#{id}"
        + "</foreach></script>")
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 统计
     *
     * @return 数量
     */
    @Select("SELECT COUNT(*) FROM tb_hashcat_task")
    int count();
}
