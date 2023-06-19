package util;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class FileUploadUtil extends HttpServlet {

    public HashMap<String, Object> uploadFile(ServletConfig servletConfig, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        // 1.创建SmartUpload对象
        SmartUpload su = new SmartUpload();

        // 2.SmartUpload对象 初始化工作
        su.initialize(servletConfig, request, response);
        // 3.设置过滤（允许的后缀，  不允许的后缀）
        su.setAllowedFilesList("jpg,dmp,gif,jpeg,png");
        try {
            su.setDeniedFilesList("exe,bat,jsp,htm,html,doc,txt,,");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        try {
            // 4.准备上传
            su.upload();
        } catch (SmartUploadException | IOException e) {
            e.printStackTrace();
        }
        Files files = su.getFiles();
        // 6.获取上传文件个数
        int count = files.getCount();
        // 7.遍历
        String path = "";
        boolean flag = true;
        for (int i = 0; i < count; i++) {
            // 当前file对象
            File file = files.getFile(i);
            String oldFileName = file.getFileName();
            if("".equals(oldFileName)){
                flag = false;
            }
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String dateStr = simpleDateFormat.format(date);
            String fileName = dateStr + oldFileName;
            path = "upload/" + fileName;
            if (!file.isMissing()) {
                try {
                    file.saveAs(path, SmartUpload.SAVE_VIRTUAL);
                } catch (SmartUploadException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
        com.jspsmart.upload.Request req = su.getRequest();
        HashMap<String, Object> map = new HashMap<>();
        map.put("req", req);
        if(flag){
            map.put("path", path);
        }
        return map;
    }

}
