package cn.zjy.dayong.demo.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/6/5
 * Time:17:58
 */
@Data
public class VacTask {

    private String id;
    private String name;
    private Vacation vac;
    private Date createTime;

}
