package com.luna.wednesday.platform.dao;

import java.util.List;

import com.luna.wednesday.platform.entity.SpeedDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;


/**
 * (tb_speed).
 *
 * @author luna
 * @since 2021/02/03 19:57:21
 */
@Mapper
public interface SpeedDAO {

    /**
     * 根据id查询
     *
     * @param id
     * @return SpeedDO
     */
    @Select("select id, create_time, modified_time, version, project_id, agent_id, task_size, running_second, indicators from tb_speed where id = #{id}")
    @Results({
        @Result(column = "id", jdbcType = JdbcType.BIGINT, property = "id", id = true),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "modified_time", property = "modifiedTime"),
        @Result(column = "version", property = "version"),
        @Result(column = "project_id", property = "projectId"),
        @Result(column = "agent_id", property = "agentId"),
        @Result(column = "task_size", property = "taskSize"),
        @Result(column = "running_second", property = "runningSecond"),
        @Result(column = "indicators", property = "indicators"),
    })
    SpeedDO get(@Param("id") Long id);

    /**
     * 单个插入
     *
     * @param speedDO SpeedDO
     * @return
     */
    @Insert("insert into tb_speed(create_time, modified_time, version, project_id, agent_id, task_size, running_second, indicators) "
        + "values(now(), now(), 0, #{projectId}, #{agentId}, #{taskSize}, #{runningSecond}, #{indicators})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(SpeedDO speedDO);

    /**
     * 批量插入
     *
     * @param list SpeedDO列表
     */
    @Insert("<script>insert into tb_speed(create_time, modified_time, version, project_id, agent_id, task_size, running_second, indicators) values "
        + "<foreach collection='list' index='index' item='n' separator=','> "
        + "(now(), now(), 0, #{n.projectId}, #{n.agentId}, #{n.taskSize}, #{n.runningSecond}, #{n.indicators})"
        + "</foreach></script>")
    void insertBatch(@Param("list") List<SpeedDO> list);

    /**
     * 更新
     *
     * @param speedDO
     */
    @Update("update tb_speed set id = #{id}, modified_time = now(), version = version + 1, project_id = #{projectId}, agent_id = #{agentId}, task_size = #{taskSize}, running_second = #{runningSecond}, indicators = #{indicators} where id = #{id} and version=#{version}")
    void update(SpeedDO speedDO);

    /**
     * 单个删除
     *
     * @param id id
     */
    @Delete("delete from tb_speed where id = #{id}")
    void delete(Long id);

    /**
     * 批量删除
     *
     * @param ids ids
     */
    @Delete("<script>delete from tb_speed where id in "
        + "<foreach collection='ids' index='index' item='id' open='(' separator=',' close=')'>"
        + "#{id}"
        + "</foreach></script>")
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 统计
     *
     * @return 数量
     */
    @Select("select count(*) from tb_speed")
    int count();

    /**
     * 项目和跑的设备保证唯一性
     * 
     * @param agentId
     * @param projectId
     * @return
     */
    @Select("select id, create_time, modified_time, version, project_id, agent_id, task_size, running_second, indicators from tb_speed where id = #{id} and project_id =#{projectId}")
    @Results({
        @Result(column = "id", jdbcType = JdbcType.BIGINT, property = "id", id = true),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "modified_time", property = "modifiedTime"),
        @Result(column = "version", property = "version"),
        @Result(column = "project_id", property = "projectId"),
        @Result(column = "agent_id", property = "agentId"),
        @Result(column = "task_size", property = "taskSize"),
        @Result(column = "running_second", property = "runningSecond"),
        @Result(column = "indicators", property = "indicators"),
    })
    SpeedDO getByAgentIdAndProjectId(@Param("agentId") Long agentId, @Param("projectId") Long projectId);
}
