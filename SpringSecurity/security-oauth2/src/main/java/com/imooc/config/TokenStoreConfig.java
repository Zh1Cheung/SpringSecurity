package com.imooc.config;

import com.imooc.properties.OAuth2Properties;
import com.imooc.security.jwt.JwtTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * token存储到redis,默认是在内存不行
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/15 1:27
 */


/**
 * JWT(Json Web Token) 特点：
 *                      1，自包含：jwt token包含有意义的信息
 *                      2，密签
 *                      3，可扩展
 *                          1.两个增强器：
 *                                    1，第一个叫JwtAccessTokenConverter，作用是添加JWT签名，将uuid的token转为jwt，用秘钥签名
 *                                    2，第二个叫 TokenEnhancer ，作用是往jwt里添加自定义的信息。由于默认生成uuid token的方法是private，所以通过ImoocJwtTokenEnhancer 往jwt里添加一些自定义的信息
 *                                       最后在认证服务器ImoocAuthenticationServerConfig里，拿到增强器链TokenEnhancerChain，判断系统里这两个增强器是不是空，非空的话把这两个增强器连起来，加到TokenEndpoint 。
 *
 *  实现：配置JwtAccessTokenConverter，新建一个配置类，先配置TokenStore 为JwtTokenStore ，
 *  然后JwtTokenStore 需要JwtAccessTokenConverter 这个转换器，在转换器里设置签名。
 *
 *
 */
@Configuration
public class TokenStoreConfig {
    /**
     * redis连接工厂
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    /**
     * 用于存放token
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "merryyou.security.oauth2", name = "storeType", havingValue = "redis")
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * jwt TOKEN配置信息
     */
    @Configuration
    @ConditionalOnProperty(prefix = "merryyou.security.oauth2", name = "storeType", havingValue = "jwt", matchIfMissing = true)
    public static class JwtTokenCofnig{

        @Autowired
        private OAuth2Properties oAuth2Properties;

        /**
         * 使用jwtTokenStore存储token
         * @return
         */
        @Bean
        public TokenStore jwtTokenStore(){
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        /**
         * 用于生成jwt
         * @return
         */
        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
            accessTokenConverter.setSigningKey(oAuth2Properties.getJwtSigningKey());//生成签名的key
            return accessTokenConverter;
        }

        /**
         * 用于扩展JWT
         * @return
         */
        @Bean
        @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer(){
            return new JwtTokenEnhancer();
        }

    }
}
