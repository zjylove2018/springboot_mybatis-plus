package cn.zjy.dayong.demo.activiti;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/6/4
 * Time:14:35
 *
 * activiti工作流接口
 */
@Service
public class ActivitiService {

    public void activiti(){
        System.out.println("activiti任务已经执行***********");
    }

    public List<String> users(){
        return Arrays.asList("神仙","李超");
    }
}
