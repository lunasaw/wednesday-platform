package com.luna.wednesday.platform.dao;

import java.util.List;

import com.luna.wednesday.platform.entity.ProjectDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;


/**
 * (tb_project).
 *
 * @author luna
 * @since 2021/02/03 19:40:18
 */
@Mapper
public interface ProjectDAO{


    /**
     * 根据id查询
     *
     * @param id
     * @return ProjectDO
     */
    @Select("select  id, create_time, modified_time, version, project_name, project_status, project_lines  from tb_project where id = #{id}")
    @Results({
            @Result(column = "id",property= "id", id = true),
            @Result(column = "create_time",property="createTime"),
            @Result(column = "modified_time",property="modifiedTime"),
            @Result(column = "version",property="version"),
            @Result(column = "project_name",property="projectName"),
            @Result(column = "project_status",property="projectStatus"),
            @Result(column = "project_lines",property="projectLines"),
    })
    ProjectDO get(@Param("id")Long  id);

    /**
     * 单个插入
     *
     * @param projectDO ProjectDO
     * @return
     */
    @Insert("insert into tb_project(create_time, modified_time, version, project_name, project_status, project_lines) "
            + "values(now(), now(), 0, #{projectName}, #{projectStatus}, #{projectLines})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(ProjectDO projectDO);

    /**
     * 批量插入
     *
     * @param list ProjectDO列表
     */
    @Insert("<script>insert into tb_project(create_time, modified_time, version, project_name, project_status, project_lines) values "
            + "<foreach collection='list' index='index' item='n' separator=','> "
            + "(now(), now(), 0, #{n.projectName}, #{n.projectStatus}, #{n.projectLines})"
            + "</foreach></script>")
    void insertBatch(@Param("list") List<ProjectDO> list);

    /**
     * 更新
     *
     * @param projectDO
     */
    @Update("update tb_project set id = #{id}, create_time = #{createTime}, modified_time = now(), version = version, project_name = #{projectName}, project_status = #{projectStatus}, project_lines = #{projectLines} where id = #{id} and version=#{version}")
    void update(ProjectDO  projectDO);

    /**
     * 单个删除
     *
     * @param id id
     */
    @Delete("delete from tb_project where id = #{id}")
    void delete(Long id);

    /**
     * 批量删除
     *
     * @param ids ids
     */
    @Delete("<script>delete from tb_project where id in "
            + "<foreach collection='ids' index='index' item='id' open='(' separator=',' close=')'>"
            + "#{id}"
            + "</foreach></script>")
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 统计
     *
     * @return 数量
     */
    @Select("select count(*) from tb_project")
    int count();
}
