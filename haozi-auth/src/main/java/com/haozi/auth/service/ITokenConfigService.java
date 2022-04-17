package com.haozi.auth.service;

import com.haozi.auth.dao.po.TokenConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author haozi
 * @since 2022-04-17
 */
public interface ITokenConfigService extends IService<TokenConfig> {

    /**
     * 取token配置
     * @return
     */
    TokenConfig tokenConfig();

}
