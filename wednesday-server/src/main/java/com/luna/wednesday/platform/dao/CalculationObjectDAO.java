package com.luna.wednesday.platform.dao;

import org.apache.ibatis.annotations.*;

import com.luna.wednesday.platform.entity.CalculationObjectDO;

/**
 * (tb_calculation_object).
 *
 * @author luna
 * @since 2021/02/24 16:32:55
 */
@Mapper
public interface CalculationObjectDAO {

    /**
     * 根据id查询
     *
     * @param id
     * @return CalculationObjectDO
     */
    @Select("SELECT id, create_time, modified_time, version, type, content, remarks FROM tb_calculation_object WHERE id=#{id}")
    @Results({
        @Result(column = "id", property = "id", id = true),
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "modified_time", property = "modifiedTime"),
        @Result(column = "version", property = "version"),
        @Result(column = "type", property = "type"),
        @Result(column = "content", property = "content"),
        @Result(column = "remarks", property = "remarks"),
    })
    CalculationObjectDO get(@Param("id") Long id);

    /**
     * 单个插入
     *
     * @param calculationObjectDO CalculationObjectDO
     * @return
     */
    @Insert("INSERT INTO tb_calculation_object(create_time, modified_time, version, type, content, remarks) "
        + "VALUES (now(), now(), 0, #{type}, #{content}, #{remarks})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(CalculationObjectDO calculationObjectDO);

    /**
     * 更新
     *
     * @param calculationObjectDO
     */
    @Update("UPDATE tb_calculation_object SET modified_time=now(), version=version+1, content=#{content}, remarks=#{remarks} WHERE version=#{version}")
    void update(CalculationObjectDO calculationObjectDO);

    /**
     * 单个删除
     *
     * @param id id
     */
    @Delete("DELETE FROM tb_calculation_object WHERE id=#{id}")
    void deleteById(@Param("id") Long id);

    /**
     * 统计
     *
     * @return 数量
     */
    @Select("SELECT COUNT(*) FROM tb_calculation_object")
    int count();
}
