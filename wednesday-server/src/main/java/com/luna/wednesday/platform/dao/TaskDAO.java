package com.luna.wednesday.platform.dao;

import java.util.List;

import com.luna.wednesday.platform.entity.TaskDO;
import org.apache.ibatis.annotations.*;

/**
 * (tb_task).
 *
 * @author luna
 * @since 2021/02/03 19:57:21
 */
@Mapper
public interface TaskDAO {

    /**
     * 根据id查询
     *
     * @param id
     * @return TaskDO
     */
    @Select("select  id, create_time, modified_time, version, project_id, status, skip, limit  from tb_task where id = #{id}")
    @Results({
        @Result(column = "id", property = "id", id = true),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "modified_time", property = "modifiedTime"),
        @Result(column = "version", property = "version"),
        @Result(column = "project_id", property = "projectId"),
        @Result(column = "status", property = "status"),
        @Result(column = "skip", property = "skip"),
        @Result(column = "limit", property = "limit"),
    })
    TaskDO get(@Param("id") Long id);

    /**
     * 单个插入
     *
     * @param taskDO TaskDO
     * @return
     */
    @Insert("insert into tb_task(create_time, modified_time, version, project_id, status, skip, limit) "
        + "values(now(), now(), 0, #{projectId}, #{status}, #{skip}, #{limit})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(TaskDO taskDO);

    /**
     * 批量插入
     *
     * @param list TaskDO列表
     */
    @Insert("<script>insert into tb_task(create_time, modified_time, version, project_id, status, skip, limit) values "
        + "<foreach collection='list' index='index' item='n' separator=','> "
        + "(now(), now(), 0, #{n.projectId}, #{n.status}, #{n.skip}, #{n.limit})"
        + "</foreach></script>")
    void insertBatch(@Param("list") List<TaskDO> list);

    /**
     * 更新
     *
     * @param taskDO
     */
    @Update("update tb_task set id = #{id}, create_time = #{createTime}, modified_time = now(), version = version, project_id = #{projectId}, status = #{status}, skip = #{skip}, limit = #{limit} where id = #{id} and version=#{version}")
    void update(TaskDO taskDO);

    /**
     * 单个删除
     *
     * @param id id
     */
    @Delete("delete from tb_task where id = #{id}")
    void delete(Long id);

    /**
     * 批量删除
     *
     * @param ids ids
     */
    @Delete("<script>delete from tb_task where id in "
        + "<foreach collection='ids' index='index' item='id' open='(' separator=',' close=')'>"
        + "#{id}"
        + "</foreach></script>")
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 统计
     *
     * @return 数量
     */
    @Select("select count(*) from tb_task")
    int count();

    /**
     * 通过projectId和状态查询Task
     * 
     * @param id
     * @param status
     * @return
     */
    @Select("select  id, create_time, modified_time, version, project_id, status, skip, limit  from tb_task WHERE project_id=#{projectId} AND status=#{status} LIMIT 1")
    @Results({
        @Result(column = "id", property = "id", id = true),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "modified_time", property = "modifiedTime"),
        @Result(column = "version", property = "version"),
        @Result(column = "project_id", property = "projectId"),
        @Result(column = "status", property = "status"),
        @Result(column = "skip", property = "skip"),
        @Result(column = "limit", property = "limit"),
    })
    TaskDO getOneByProjectIdAndStatus(@Param("id") Long id, @Param("status") String status);

    /**
     * 通过projectId 项目状态 拿出第一个项目的所有Task
     * 
     * @param projectId
     * @param status
     * @param limit
     * @return
     */
    @Select("select  id, create_time, modified_time, version, project_id, status, skip, limit  from tb_task WHERE WHERE project_id=#{projectId} AND status=#{status} LIMIT #{limit}")
    @Results({
        @Result(column = "id", property = "id", id = true),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "modified_time", property = "modifiedTime"),
        @Result(column = "version", property = "version"),
        @Result(column = "project_id", property = "projectId"),
        @Result(column = "status", property = "status"),
        @Result(column = "skip", property = "skip"),
        @Result(column = "limit", property = "limit"),
    })
    List<TaskDO> listByProjectIdAndStatusWithLimit(@Param("projectId") long projectId, @Param("status") String status,
        @Param("limit") int limit);

    /**
     * 通过projectId和非本状态查询Task
     *
     * @param projectId
     * @param status
     * @return
     */
    @Select("select  id, create_time, modified_time, version, project_id, status, skip, limit  from tb_task WHERE WHERE project_id=#{projectId} AND status!=#{status} LIMIT 1")
    @Results({
        @Result(column = "id", property = "id", id = true),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "modified_time", property = "modifiedTime"),
        @Result(column = "version", property = "version"),
        @Result(column = "project_id", property = "projectId"),
        @Result(column = "status", property = "status"),
        @Result(column = "skip", property = "skip"),
        @Result(column = "limit", property = "limit"),
    })
    TaskDO getOneByProjectIdAndNotStatus(@Param("projectId") long projectId, @Param("status") String status);

    /**
     * 状态查询任务List
     * 
     * @param status
     * @return
     */
    @Select("select  id, create_time, modified_time, version, project_id, status, skip, limit  from tb_task WHERE WHERE WHERE status=#{status}")
    @Results({
        @Result(column = "id", property = "id", id = true),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "modified_time", property = "modifiedTime"),
        @Result(column = "version", property = "version"),
        @Result(column = "project_id", property = "projectId"),
        @Result(column = "status", property = "status"),
        @Result(column = "skip", property = "skip"),
        @Result(column = "limit", property = "limit"),
    })
    List<TaskDO> listByStatus(@Param("status") String status);
}
