package com.keyware.MR.service;

import com.keyware.MR.entity.Faultlibrary;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yaojz
 * @since 2023-03-08
 */
@Service
public interface FaultlibraryService extends IService<Faultlibrary> {

    boolean save2(Faultlibrary faultlibrary);
    boolean update2(Faultlibrary faultlibrary);
}
