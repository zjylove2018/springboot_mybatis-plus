package cn.zjy.dayong.demo.realm;

import cn.zjy.dayong.demo.pojo.User;
import cn.zjy.dayong.demo.utils.HS256;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/3/11
 * Time:15:36
 * shiro框架的realm类
 */
public class MyShiroRealm extends AuthorizingRealm {

    /**
     * 接受用户受权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roleSet = new HashSet<>();
        roleSet.add("100002");
        info.addRoles(roleSet);

        Set<String> permissionSet = new HashSet<>();
        permissionSet.add("权限添加");
        permissionSet.add("权限删除");
        info.setStringPermissions(permissionSet);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();      //用户名
        String password = String.valueOf(token.getPassword());  //密码
        String host = token.getHost();              //IP地址
        Map<String, Object> map = new HashMap<>();
        map.put("nickname", username);
        String paw = password + username;
        String paw256 = HS256.returnSign(paw);
        map.put("paw256", paw256);

        User user = new User();
        user.setUsername("神仙");
        user.setPassword("123456");

        return new SimpleAuthenticationInfo(user, password, user.getUsername());
    }
}
