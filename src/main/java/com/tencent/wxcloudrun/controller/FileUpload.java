package com.tencent.wxcloudrun.controller;


import com.tencent.wxcloudrun.utils.JsonData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@RestController
@RequestMapping("api/")
public class FileUpload {

    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
    @PostMapping("/upload")
    public JsonData importData(@RequestParam("file") MultipartFile file, HttpServletRequest req) throws IOException {
        String format = sdf.format(new Date());
        //文件上传的地址
//        String path = ResourceUtils.getURL("classpath:").getPath()+"static/upload"+format;
       String path = "/usr/local/software/"+"/upload"+format;
//        String path = "D:/usr/local/software/"+"/upload"+format;
        //用于查看路径是否正确
        System.out.println(path);
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        file.transferTo(new File(folder,newName));
//        String url = "http://localhost:8089" + realPath + "/" + newName;
//        String url = "http://localhost:8089/upload" +format+ "/" + newName;

        String url = "http://127.0.0.1:8089/upload" +format+ "/" + newName;
        System.out.println(url);
        return JsonData.buildSuccess(url, "上传成功");
    }
}
