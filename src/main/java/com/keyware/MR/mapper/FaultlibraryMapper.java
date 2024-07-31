package com.keyware.MR.mapper;

import com.keyware.MR.entity.Faultlibrary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yaojz
 * @since 2023-03-08
 */
@Repository
public interface FaultlibraryMapper extends BaseMapper<Faultlibrary> {
//    @Select("select P.PROCESS , F.* from  FaultLibrary F , Process P")
//    List<Faultlibrary> select();
    @Select("SELECT COUNT(DISTINCT FAULTNUMBER) FROM FaultLibrary WHERE PROCESSID = ${PROCESSID};")
    Integer faultNumberForProcessId(@Param("PROCESSID") Integer processId);

}
