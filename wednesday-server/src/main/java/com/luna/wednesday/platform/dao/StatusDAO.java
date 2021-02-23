package com.luna.wednesday.platform.dao;

import java.util.List;

import com.luna.wednesday.platform.entity.StatusDO;
import org.apache.ibatis.annotations.*;


/**
 * (tb_status).
 *
 * @author luna
 * @since 2021/02/04 15:35:58
 */
@Mapper
public interface StatusDAO {

    /**
     * 根据id查询
     *
     * @param id
     * @return StatusDO
     */
    @Select("select  id, create_time, modified_time, version, agent_id, content  from tb_status where id = #{id}")
    @Results({
        @Result(column = "id", property = "id", id = true),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "modified_time", property = "modifiedTime"),
        @Result(column = "version", property = "version"),
        @Result(column = "agent_id", property = "agentId"),
        @Result(column = "content", property = "content"),
    })
    StatusDO get(@Param("id") Long id);

    /**
     * 单个插入
     *
     * @param statusDO StatusDO
     * @return
     */
    @Insert("insert into tb_status(create_time, modified_time, version, agent_id, content) "
        + "values(now(), now(), 0, #{agentId}, #{content})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(StatusDO statusDO);

    /**
     * 批量插入
     *
     * @param list StatusDO列表
     */
    @Insert("<script>insert into tb_status(create_time, modified_time, version, agent_id, content) values "
        + "<foreach collection='list' index='index' item='n' separator=','> "
        + "(now(), now(), 0, #{n.agentId}, #{n.content})"
        + "</foreach></script>")
    void insertBatch(@Param("list") List<StatusDO> list);

    /**
     * 更新
     *
     * @param statusDO
     */
    @Update("update tb_status set id = #{id}, modified_time = now(), version = version, agent_id = #{agentId}, content = #{content} where id = #{id} and version=#{version}")
    void update(StatusDO statusDO);

    /**
     * 单个删除
     *
     * @param id id
     */
    @Delete("delete from tb_status where id = #{id}")
    void delete(Long id);

    /**
     * 批量删除
     *
     * @param ids ids
     */
    @Delete("<script>delete from tb_status where id in "
        + "<foreach collection='ids' index='index' item='id' open='(' separator=',' close=')'>"
        + "#{id}"
        + "</foreach></script>")
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 统计
     *
     * @return 数量
     */
    @Select("select count(*) from tb_status")
    int count();

    @Select("select  id, create_time, modified_time, version, agent_id, content  from tb_status where  agent_id=#{agentId}")
    @Results({
        @Result(column = "id", property = "id", id = true),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "modified_time", property = "modifiedTime"),
        @Result(column = "version", property = "version"),
        @Result(column = "agent_id", property = "agentId"),
        @Result(column = "content", property = "content"),
    })
    StatusDO getByAgentId(@Param("agentId") long agentId);
}
