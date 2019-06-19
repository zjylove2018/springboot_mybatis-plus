package cn.zjy.dayong.demo.mapper;

import cn.zjy.dayong.demo.pojo.Report;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/4/19
 * Time:18:29
 */
@Mapper
public interface ReportMapper extends BaseMapper<Report> {

    //查询所有的订单
    List<Report> findAllReport();
}
