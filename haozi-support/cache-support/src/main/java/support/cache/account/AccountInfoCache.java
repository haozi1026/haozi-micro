package support.cache.account;

import cn.hutool.core.util.StrUtil;
import com.haozi.common.dto.account.AccountInfo;
import com.haozi.common.exception.ParamMissingException;
import support.cache.KVCache;

import java.util.Optional;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 1:00 下午
 */
public abstract class AccountInfoCache implements KVCache<AccountInfo> {

    /**
     * 账号缓存
     *
     * @param accounId
     * @param accountInfo
     */
    public void cacheAccount(String accounId, AccountInfo accountInfo, long seconds) {

        if (StrUtil.isBlank(accounId)) {
            throw new ParamMissingException("accountid", "缓存账号时");
        }
        if(accountInfo == null){
            throw new ParamMissingException("accountInfo", "缓存账号时");
        }
        //生成key
        String key = generateKey(accounId);
        //缓存
        cache(key, accountInfo, seconds);
    }


    public Optional<AccountInfo> getAccountInfo(String accountId) {
        String key = generateKey(accountId);
        return get(key);
    }

    private String generateKey(String accountId) {
        return StrUtil.format("{}-{}", cacheKey, accountId);
    }

    final String cacheKey = "ACCOUNT-INFO";

}
