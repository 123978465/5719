package com.keyware.MR.service;

import com.keyware.MR.entity.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-14
 */
public interface DictionaryService extends IService<Dictionary> {
    String dataVal(Dictionary dictionary);
}
