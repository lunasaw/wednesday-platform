package com.luna.wednesday.platform.dao;

import java.util.List;

import com.luna.wednesday.platform.entity.HashDO;
import org.apache.ibatis.annotations.*;

/**
 * (tb_hash).
 *
 * @author luna
 * @since 2021/02/23 22:51:06
 */
@Mapper
public interface HashDAO {

    /**
     * 根据id查询
     *
     * @param id
     * @return HashDO
     */
    @Select("SELECT  id, create_time, modified_time, version, name, `left`, found  FROM tb_hash WHERE id = #{id}")
    @Results({
        @Result(column = "id", property = "id", id = true),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "modified_time", property = "modifiedTime"),
        @Result(column = "version", property = "version"),
        @Result(column = "name", property = "name"),
        @Result(column = "left", property = "left"),
        @Result(column = "found", property = "found"),
    })
    HashDO get(@Param("id") Long id);

    /**
     * 单个插入
     *
     * @param hashDO HashDO
     * @return
     */
    @Insert("INSERT INTO tb_hash(create_time, modified_time, version, name, `left`, found) "
        + "VALUES (now(), now(), 0, #{name}, #{left}, #{found})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(HashDO hashDO);

    /**
     * 批量插入
     *
     * @param list HashDO列表
     */
    @Insert("<script>INSERT INTO tb_hash(create_time, modified_time, version, name, `left`, found) VALUES "
        + "<foreach collection='list' index='index' item='n' separator=','> "
        + "(now(), now(), 0, #{n.name}, #{n.left}, #{n.found})"
        + "</foreach></script>")
    void insertBatch(@Param("list") List<HashDO> list);

    /**
     * 更新
     *
     * @param hashDO
     */
    @Update("UPDATE tb_hash SET id = #{id}, modified_time = now(), version = #{version}, name = #{name}, `left` = #{left}, found = #{found} WHERE id = #{id}")
    void update(HashDO hashDO);

    /**
     * 单个删除
     *
     * @param id id
     */
    @Delete("DELETE FROM tb_hash WHERE id = #{id}")
    void delete(@Param("id") Long id);

    /**
     * 批量删除
     *
     * @param ids ids
     */
    @Delete("<script>DELETE FROM tb_hash WHERE ID IN "
        + "<foreach collection='ids' index='index' item='id' open='(' separator=',' close=')'>"
        + "#{id}"
        + "</foreach></script>")
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 统计
     *
     * @return 数量
     */
    @Select("SELECT COUNT(*) FROM tb_hash")
    int count();
}
