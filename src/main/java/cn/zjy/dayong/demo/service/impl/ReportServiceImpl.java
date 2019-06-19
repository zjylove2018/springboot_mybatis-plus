package cn.zjy.dayong.demo.service.impl;

import cn.zjy.dayong.demo.mapper.ReportMapper;
import cn.zjy.dayong.demo.pojo.Report;
import cn.zjy.dayong.demo.service.ReportService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/4/19
 * Time:18:28
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    /**
     * 查询所有的订单
     * @return
     */
    @Override
    public List<Report> findAllReport() {

        List<Report> reports = reportMapper.findAllReport();
        return reports;
    }
}
