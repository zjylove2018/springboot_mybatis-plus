package cn.zjy.dayong.demo.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/6/5
 * Time:17:57
 */
@Data
public class Vacation {

    /**
     * 申请人
     */
    private String applyUser;
    private int days;
    private String reason;
    private Date applyTime;
    private String applyStatus;

    /**
     * 审核人
     */
    private String auditor;
    private String result;
    private Date auditTime;

}
