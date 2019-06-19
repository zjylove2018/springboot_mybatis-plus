package cn.zjy.dayong.demo.service;

import cn.zjy.dayong.demo.pojo.Report;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/4/19
 * Time:18:27
 */
public interface ReportService extends IService<Report> {

    //查询所有的订单
    List<Report> findAllReport();
}
