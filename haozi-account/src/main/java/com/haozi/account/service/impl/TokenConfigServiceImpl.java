package com.haozi.account.service.impl;

import com.haozi.account.dao.po.TokenConfig;
import com.haozi.account.dao.mapper.TokenConfigMapper;
import com.haozi.account.service.ITokenConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author haozi
 * @since 2022-04-16
 */
@Service
public class TokenConfigServiceImpl extends ServiceImpl<TokenConfigMapper, TokenConfig> implements ITokenConfigService {

}
