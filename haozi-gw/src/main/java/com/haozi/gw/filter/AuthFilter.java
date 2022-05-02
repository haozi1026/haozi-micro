package com.haozi.gw.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.text.AntPathMatcher;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import com.haozi.account.manager.AccountManager;
import com.haozi.common.constants.AuthConstants;
import com.haozi.common.model.ResponseResult;
import com.haozi.common.model.dto.auth.AccountInfo;
import com.haozi.common.util.JackSonUtil;
import com.haozi.gw.config.AnonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/5/2 10:49 下午
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    AnonConfig anonConfig;

    @Autowired
    AccountManager accountManager;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String targetPath = exchange.getRequest().getURI().getPath();
        List<String> anonUrls = anonConfig.getUrls();
        //是否允许匿名访问
        if (isAllowAnon(targetPath, anonUrls)) {
            return chain.filter(exchange);
        }
        String accessToken = exchange.getRequest().getHeaders().getFirst(HEADER_ACCESS_TOKEN);

        if (StrUtil.isBlank(accessToken)) {
            String unLoginMsg = unLogion();
            exchange.getResponse().setRawStatusCode(HttpStatus.HTTP_FORBIDDEN);
            return writeMsg(exchange.getResponse(), unLoginMsg);
        }
        //jwt 校验
        JWT jwt = null;
        try {
            jwt = JWTUtil.parseToken(accessToken);
            JWTValidator.of(jwt).validateDate();
        } catch (Exception ex) {
            // token校验不过
            String loginOutTime = loginOutTime();
            exchange.getResponse().setRawStatusCode(HttpStatus.HTTP_FORBIDDEN);
            return writeMsg(exchange.getResponse(), loginOutTime);
        }

        String loginId = Convert.toStr(jwt.getPayload(AuthConstants.ACCESS_TOKEN_PAY_LOAD));
        if (StrUtil.isBlank(loginId)) {
            String loginOutTime = loginOutTime();
            exchange.getResponse().setRawStatusCode(HttpStatus.HTTP_FORBIDDEN);
            return writeMsg(exchange.getResponse(), loginOutTime);
        }
        Optional<AccountInfo> optionalAccountInfo = accountManager.getAccountInfo(loginId);

        if (optionalAccountInfo.isPresent() == false) {
            String loginOutTime = loginOutTime();
            exchange.getResponse().setRawStatusCode(HttpStatus.HTTP_FORBIDDEN);
            return writeMsg(exchange.getResponse(), loginOutTime);
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 未登录提示
     *
     * @return
     */
    private String unLogion() {
        ResponseResult<String> fail = ResponseResult.fail("未登录用户，禁止访问");
        return JackSonUtil.toJson(fail);
    }

    /**
     * 未登录提示
     *
     * @return
     */
    private String loginOutTime() {
        ResponseResult<String> fail = ResponseResult.fail("登录超时，请重新登录");
        return JackSonUtil.toJson(fail);
    }

    /**
     * 是否允许匿名访问
     *
     * @return
     */
    private boolean isAllowAnon(String targetUrl, List<String> anonUrls) {
        if (CollUtil.isEmpty(anonUrls)) {
            return false;
        }
        return anonUrls.stream().anyMatch(url -> antPathMatcher.match(url, targetUrl));
    }

    /**
     * 写响应信息
     *
     * @param response
     * @param msg
     */
    private Mono<Void> writeMsg(ServerHttpResponse response, String msg) {
        DataBuffer wrap = response.bufferFactory().wrap(msg.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(wrap));
    }

    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    /**
     * 请求头 accessToken
     */
    private final String HEADER_ACCESS_TOKEN = "ACCESS-TOKEN";
}
