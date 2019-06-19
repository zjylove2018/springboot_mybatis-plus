package cn.zjy.dayong.demo.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/4/19
 * Time:17:32
 *
 * 报表明细 实体类
 */
@Data
@NoArgsConstructor
public class Report {

    //报表id
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;

    //开销地方
    private String overheadName;

    //开销金额(单个)
    private double overheadMoney;

    //开销数量
    private Integer overheadCount;

    //开销日期
    private String overheadDate;

    //开销人员
    private String overheadPeople;

    //是否已经审批
    public boolean isApproval;

    //开销总金额
    private double sumMoney;

}
