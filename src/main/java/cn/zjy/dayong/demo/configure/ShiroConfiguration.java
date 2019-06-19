package cn.zjy.dayong.demo.configure;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/3/11
 * Time:16:20
 */
@Configuration
public class ShiroConfiguration {

    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //必须设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setLoginUrl("/login");
        //未受权的界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
//        Map<String, Filter> filtMap = new HashMap<>();  //自定义拦截器
//        shiroFilterFactoryBean.setFilters(filtMap);

        return shiroFilterFactoryBean;
    }

    /**
     * cookie对象
     * @return
     */
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("remeberMe");
        //记住我cookie生效时间30天 ,单位秒;
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }

    /**
     * cookie管理对象:记住我功能
     */
    public CookieRememberMeManager rememberMeManager(){

        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }
}
