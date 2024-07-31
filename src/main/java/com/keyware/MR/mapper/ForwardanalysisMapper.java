package com.keyware.MR.mapper;

import com.keyware.MR.entity.Forwardanalysis;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yaojz
 * @since 2023-04-25
 */
public interface ForwardanalysisMapper extends BaseMapper<Forwardanalysis> {

    @Select("select * FROM ForwardAnalysis  where  ESTIMATE IS NOT NULL")
    List<Forwardanalysis> select1();
    @Update("update ForwardAnalysis set EXETIONSTEPID = #{exetionstepId} where EXETIONSTEP like #{exetionstep}")
    boolean insert1(@Param("exetionstepId") String exetionstepId , @Param("exetionstep") String exetionstep);
}
