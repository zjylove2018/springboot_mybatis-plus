package cn.zjy.dayong.demo.activiti;

import cn.zjy.dayong.demo.pojo.Vacation;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/6/4
 * Time:14:38
 * activiti工作流测试类
 *
 * 要搭好环境得修改三个地方,否则报错:
 *      1.启动类:在注解@SpringBootApplication添加, exclude = {SecurityAutoConfiguration.class}
 *      2.application.properties:添加spring.activiti.check-process-definitions=false
 *      3.在pom.xml配置文件中要去掉myBatis的一个依赖<groupId>tk.mybatis</groupId>
 *              <exclusions>
 *                 <exclusion>
 *                     <groupId>javax.persistence</groupId>
 *                     <artifactId>persistence-api</artifactId>
 *                 </exclusion>
 *             </exclusions>
 */
@Controller
public class ActivitiTest {

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @RequestMapping("/activiti/startProcess")
    public void TestStartProcess(){
        String username = "李李李李";
        System.out.println("Start.........");
        //开始流程
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("myProcess_1");
        //查询当前任务
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        //申明任务
        taskService.claim(task.getId(), username);
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        //完成任务
        taskService.complete(task.getId(), map);
        System.out.println("流程启动结束");
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
//        deploymentBuilder.addClasspathResource("processes/MyProcess.bpmn");
//        deploymentBuilder.deploy();
//        RuntimeService runtimeService = processEngine.getRuntimeService();
//        runtimeService.startProcessInstanceByKey("myProcess");
        System.out.println("ok......");
    }
}
