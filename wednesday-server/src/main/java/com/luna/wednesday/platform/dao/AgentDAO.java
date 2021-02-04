package com.luna.wednesday.platform.dao;

import java.util.List;

import com.luna.wednesday.platform.entity.AgentDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;


/**
 * (tb_agent).
 *
 * @author luna
 * @since 2021/02/03 19:57:21
 */
@Mapper
public interface AgentDAO{


    /**
     * 根据id查询
     *
     * @param id
     * @return AgentDO
     */
    @Select("select  id, create_time, modified_time, version, key, type, hardware, agent_version, last_seen  from tb_agent where id = #{id}")
    @Results({
    	@Result(column = "id",jdbcType= JdbcType.BIGINT ,property= "id", id = true),
		@Result(column = "create_time",property="createTime"),
		@Result(column = "modified_time",property="modifiedTime"),
		@Result(column = "version",property="version"),
		@Result(column = "key",property="key"),
		@Result(column = "type",property="type"),
		@Result(column = "hardware",property="hardware"),
		@Result(column = "agent_version",property="agentVersion"),
		@Result(column = "last_seen",property="lastSeen"),
		})
    AgentDO get(@Param("id")Long  id);

    /**
     * 单个插入
     *
     * @param agentDO AgentDO
     * @return
     */
    @Insert("insert into tb_agent(create_time, modified_time, version, key, type, hardware, agent_version, last_seen) "
        + "values(now(), now(), 0, #{key}, #{type}, #{hardware}, #{agentVersion}, #{lastSeen})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(AgentDO agentDO);

    /**
     * 批量插入
     *
     * @param list AgentDO列表
     */
    @Insert("<script>insert into tb_agent(create_time, modified_time, version, key, type, hardware, agent_version, last_seen) values "
        + "<foreach collection='list' index='index' item='n' separator=','> "
        + "(now(), now(), 0, #{n.key}, #{n.type}, #{n.hardware}, #{n.agentVersion}, #{n.lastSeen})"
        + "</foreach></script>")
    void insertBatch(@Param("list") List<AgentDO> list);

    /**
     * 更新
     *
     * @param agentDO
     */
    @Update("update tb_agent set id = #{id}, create_time = #{createTime}, modified_time = now(), version = version, key = #{key}, type = #{type}, hardware = #{hardware}, agent_version = #{agentVersion}, last_seen = #{lastSeen} where id = #{id} and version=#{version}")
    void update(AgentDO agentDO);

    /**
     * 单个删除
     *
     * @param id id
     */
    @Delete("delete from tb_agent where id = #{id}")
    void delete(Long id);

    /**
     * 批量删除
     *
     * @param ids ids
     */
    @Delete("<script>delete from tb_agent where id in "
        + "<foreach collection='ids' index='index' item='id' open='(' separator=',' close=')'>"
        + "#{id}"
        + "</foreach></script>")
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 统计
     *
     * @return 数量
     */
    @Select("select count(*) from tb_agent")
    int count();
}
