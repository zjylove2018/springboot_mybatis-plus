package cn.zjy.dayong.demo.controller;

import cn.zjy.dayong.demo.pojo.User;
import cn.zjy.dayong.demo.service.UserService;
import cn.zjy.dayong.demo.utils.ExcelUtil;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/11/14
 * Time:15:29
 * 导出excle
 */
@Controller
@RequestMapping("/report")
public class ExcleReportController {
    /**
     * 日志打印
     */
    private final Logger logger = LoggerFactory.getLogger(ExcleReportController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/excleUser")
    public void exportExcle(HttpServletRequest request, HttpServletResponse response){
        //获取数据
        List<User> userList = userService.selectPage(new Page<User>(2, 6)).getRecords();
        //excel标题
        String[] title = {"姓名","年龄","地址","性别","用户名","密码","电话","邮箱"};
        String [] [] count = new String[userList.size()][title.length];
        //excel文件名
        String fileName = "数据表" + System.currentTimeMillis() + ".xlsx";
        //sheet名
        String sheetName = "用户数据";
        for (int i = 0; i <userList.size() ; i++) {
            count[i] = new String[title.length];
            User user = userList.get(i);
            count[i][0] = user.getName();
            count[i][1] = user.getAge().toString();
            count[i][2] = user.getAddress();
            count[i][3] = user.getUserGender();
            count[i][4] = user.getUsername();
            count[i][5] = user.getPassword();
            count[i][6] = user.getUserPhone();
            count[i][7] = user.getUserEmail();
        }
        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkboot(sheetName, title, count, null);

        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.close();
            logger.info("导出数据成功,数据为:{}", userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
