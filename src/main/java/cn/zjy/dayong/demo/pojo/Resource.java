package cn.zjy.dayong.demo.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/10/23
 * Time:16:02
 */
//表示这个类是一个读取配置文件的类
@Configuration
@ConfigurationProperties(prefix = "com.haozz.opensource")
//指定所读取的配置文件的路径
@PropertySource(value = "classpath:resource.properties")
public class Resource {
    public Resource() {
    }

    private String name;
    private String website;
    private String language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
