package com.luna.wednesday.platform.dao;

import java.util.List;

import com.luna.wednesday.platform.entity.TaskResultDO;
import org.apache.ibatis.annotations.*;


/**
 * (tb_task_result).
 *
 * @author luna
 * @since 2021/02/03 19:57:21
 */
@Mapper
public interface TaskResultDAO{


    /**
     * 根据id查询
     *
     * @param id
     * @return TaskResultDO
     */
    @Select("select id, create_time, modified_time, version, task_id, agent_id, status, result from tb_task_result where id = #{id}")
    @Results({
    	@Result(column = "id",property= "id", id = true),
		@Result(column = "create_time",property="createTime"),
		@Result(column = "modified_time",property="modifiedTime"),
		@Result(column = "version",property="version"),
		@Result(column = "task_id",property="taskId"),
		@Result(column = "agent_id",property="agentId"),
		@Result(column = "status",property="status"),
		@Result(column = "result",property="result"),
		})
    TaskResultDO get(@Param("id")Long  id);

    /**
     * 单个插入
     *
     * @param taskResultDO TaskResultDO
     * @return
     */
    @Insert("insert into tb_task_result(create_time, modified_time, version, task_id, agent_id, status, result) "
        + "values(now(), now(), 0, #{taskId}, #{agentId}, #{status}, #{result})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(TaskResultDO taskResultDO);

    /**
     * 批量插入
     *
     * @param list TaskResultDO列表
     */
    @Insert("<script>insert into tb_task_result(create_time, modified_time, version, task_id, agent_id, status, result) values "
        + "<foreach collection='list' index='index' item='n' separator=','> "
        + "(now(), now(), 0, #{n.taskId}, #{n.agentId}, #{n.status}, #{n.result})"
        + "</foreach></script>")
    void insertBatch(@Param("list") List<TaskResultDO> list);

    /**
     * 更新
     *
     * @param taskResultDO
     */
    @Update("update tb_task_result set id = #{id}, create_time = #{createTime}, modified_time = now(), version = version, task_id = #{taskId}, agent_id = #{agentId}, status = #{status}, result = #{result} where id = #{id} and version=#{version}")
    void update(TaskResultDO taskResultDO);

    /**
     * 单个删除
     *
     * @param id id
     */
    @Delete("delete from tb_task_result where id = #{id}")
    void delete(Long id);

    /**
     * 批量删除
     *
     * @param ids ids
     */
    @Delete("<script>delete from tb_task_result where id in "
        + "<foreach collection='ids' index='index' item='id' open='(' separator=',' close=')'>"
        + "#{id}"
        + "</foreach></script>")
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 统计
     *
     * @return 数量
     */
    @Select("select count(*) from tb_task_result")
    int count();
}
