package com.luna.wednesday.platform.dao;

import java.util.List;

import com.luna.wednesday.platform.entity.StatusLogDO;
import org.apache.ibatis.annotations.*;


/**
 * (tb_status_log).
 *
 * @author luna
 * @since 2021/02/04 15:38:44
 */
@Mapper
public interface StatusLogDAO {

    /**
     * 根据id查询
     *
     * @param id
     * @return StatusLogDO
     */
    @Select("select  id, create_time, agent_id, content  from tb_status_log where id = #{id}")
    @Results({
        @Result(column = "id", property = "id", id = true),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "agent_id", property = "agentId"),
        @Result(column = "content", property = "content"),
    })
    StatusLogDO get(@Param("id") Long id);

    /**
     * 单个插入
     *
     * @param statusLogDO StatusLogDO
     * @return
     */
    @Insert("insert into tb_status_log(create_time, agent_id, content) "
        + "values(now(), #{agentId}, #{content})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(StatusLogDO statusLogDO);

    /**
     * 批量插入
     *
     * @param list StatusLogDO列表
     */
    @Insert("<script>insert into tb_status_log(create_time, agent_id, content) values "
        + "<foreach collection='list' index='index' item='n' separator=','> "
        + "(now(), #{n.agentId}, #{n.content})"
        + "</foreach></script>")
    void insertBatch(@Param("list") List<StatusLogDO> list);

    /**
     * 更新
     *
     * @param statusLogDO
     */
    @Update("update tb_status_log set id = #{id}, agent_id = #{agentId}, content = #{content} where id = #{id}")
    void update(StatusLogDO statusLogDO);

    /**
     * 单个删除
     *
     * @param id id
     */
    @Delete("delete from tb_status_log where id = #{id}")
    void delete(Long id);

    /**
     * 批量删除
     *
     * @param ids ids
     */
    @Delete("<script>delete from tb_status_log where id in "
        + "<foreach collection='ids' index='index' item='id' open='(' separator=',' close=')'>"
        + "#{id}"
        + "</foreach></script>")
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 统计
     *
     * @return 数量
     */
    @Select("select count(*) from tb_status_log")
    int count();
}
