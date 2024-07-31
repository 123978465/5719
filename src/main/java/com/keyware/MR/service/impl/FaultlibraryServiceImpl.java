package com.keyware.MR.service.impl;

import com.keyware.MR.entity.Faultlibrary;
import com.keyware.MR.mapper.FaultlibraryMapper;
import com.keyware.MR.service.FaultlibraryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yaojz
 * @since 2023-03-08
 */
@Service
public class FaultlibraryServiceImpl extends ServiceImpl<FaultlibraryMapper, Faultlibrary> implements FaultlibraryService {


    @Override
    public boolean save2(Faultlibrary faultlibrary) {

        return save(dataPerfection(faultlibrary));
    }

    private Faultlibrary dataPerfection(Faultlibrary faultlibrary){

        faultlibrary.setExecutionStep(
                faultlibrary.getExecutionStepForBefore()
                        +faultlibrary.getExecutionStepParamMin()
                        +"~"
                        +faultlibrary.getExecutionStepParamMax()
                        +faultlibrary.getExecutionStepParamUnit()
        );

        if (faultlibrary.getOutputForBefore()!=null){
        faultlibrary.setOutput(
                faultlibrary.getOutputForBefore()
                        +faultlibrary.getMinOutput()
                        +"~"
                        +faultlibrary.getMaxOutput()
                        +faultlibrary.getOutputUnit()
        );
        }
        return faultlibrary;
    }
    @Override
    public boolean update2(Faultlibrary faultlibrary) {
        return updateById(dataPerfection(faultlibrary));
    }
}
