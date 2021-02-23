package com.luna.wednesday.platform.dao;

import java.util.List;

import com.luna.wednesday.platform.entity.ProjectDO;
import org.apache.ibatis.annotations.*;


/**
 * (tb_project).
 *
 * @author luna
 * @since 2021/02/23 22:37:35
 */
@Mapper
public interface ProjectDAO {

    /**
     * 根据状态查询
     *
     * @param status
     * @return ProjectDO
     */
    @Select("SELECT  id, create_time, modified_time, version, name, status, content  FROM tb_project WHERE status = #{status}")
    @Results({
        @Result(column = "id", property = "id", id = true),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "modified_time", property = "modifiedTime"),
        @Result(column = "version", property = "version"),
        @Result(column = "name", property = "name"),
        @Result(column = "status", property = "status"),
        @Result(column = "content", property = "content"),
    })
    List<ProjectDO> listByStatus(@Param("status") String status);

    /**
     * 根据id查询
     *
     * @param id
     * @return ProjectDO
     */
    @Select("SELECT  id, create_time, modified_time, version, name, status, content  FROM tb_project WHERE id = #{id}")
    @Results({
        @Result(column = "id", property = "id", id = true),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "modified_time", property = "modifiedTime"),
        @Result(column = "version", property = "version"),
        @Result(column = "name", property = "name"),
        @Result(column = "status", property = "status"),
        @Result(column = "content", property = "content"),
    })
    ProjectDO get(@Param("id") Long id);

    /**
     * 单个插入
     *
     * @param projectDO ProjectDO
     * @return
     */
    @Insert("INSERT INTO tb_project(create_time, modified_time, version, name, status, content) "
        + "VALUES (now(), now(), 0, #{name}, #{status}, #{content})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(ProjectDO projectDO);

    /**
     * 批量插入
     *
     * @param list ProjectDO列表
     */
    @Insert("<script>INSERT INTO tb_project(create_time, modified_time, version, name, status, content) VALUES "
        + "<foreach collection='list' index='index' item='n' separator=','> "
        + "(now(), now(), 0, #{n.name}, #{n.status}, #{n.content})"
        + "</foreach></script>")
    void insertBatch(@Param("list") List<ProjectDO> list);

    /**
     * 更新
     *
     * @param projectDO
     */
    @Update("UPDATE tb_project SET id = #{id}, modified_time = now(), version = version+1, name = #{name}, status = #{status}, content = #{content} WHERE id = #{id} and version = #{version}")
    void update(ProjectDO projectDO);

    /**
     * 单个删除
     *
     * @param id id
     */
    @Delete("DELETE FROM tb_project WHERE id = #{id}")
    void delete(@Param("id") Long id);

    /**
     * 批量删除
     *
     * @param ids ids
     */
    @Delete("<script>DELETE FROM tb_project WHERE ID IN "
        + "<foreach collection='ids' index='index' item='id' open='(' separator=',' close=')'>"
        + "#{id}"
        + "</foreach></script>")
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 统计
     *
     * @return 数量
     */
    @Select("SELECT COUNT(*) FROM tb_project")
    int count();

    /**
     * 状态查询project
     *
     * @param status
     * @return
     */
    @Select("SELECT  id, create_time, modified_time, version, name, status, content  FROM tb_project WHERE status=#{status} LIMIT 1")
    @Results({
        @Result(column = "id", property = "id", id = true),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "modified_time", property = "modifiedTime"),
        @Result(column = "version", property = "version"),
        @Result(column = "name", property = "name"),
        @Result(column = "status", property = "status"),
        @Result(column = "content", property = "content"),
    })
    ProjectDO getOneByStatus(@Param("status") String status);
}
