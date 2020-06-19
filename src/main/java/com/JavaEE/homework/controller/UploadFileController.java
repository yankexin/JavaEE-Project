package com.JavaEE.homework.controller;

import com.JavaEE.homework.bo.ResponseBean;
import com.JavaEE.homework.constant.WebConstant;
import com.JavaEE.homework.util.FileUploadUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

@RestController
public class UploadFileController {

    private static final int FILE_SIZE_MAX = 10 * 1024 * 1024;    // 上传限制大小

    /**
     * @param multipartFile
     * @description: 通用文件上传处理器
     * @return: java.util.Map<java.lang.String   ,   java.lang.Object>
     */
    @RequestMapping(value = "/uploadFile", produces = "application/json;charset=UTF-8")
    public Map<String, Object> fileUpload(@RequestParam("file") MultipartFile multipartFile) {
        ResponseBean responseBean = new ResponseBean();
            if (multipartFile != null) {
                String realName = multipartFile.getOriginalFilename(); // 原始文件名
                String suffix = FileUploadUtils.fileSuffix(realName); // 文件名后缀
                String tmpFileName = FileUploadUtils.createTmpFileName(suffix); // 生成保证不重复的临时文件名

                if (multipartFile.getSize() > FILE_SIZE_MAX) {
                    responseBean.putError("上传失败：文件大小不得超过10MB");
                    return responseBean.getResponseMap();
                }

                File tmpFile = new File(WebConstant.WEB_FILE_ROOT + "/static/file/",tmpFileName);

                try {
                    multipartFile.transferTo(tmpFile); // 写入本地
                    responseBean.putData("filePath", "/file/" + tmpFileName);
                } catch (IOException e) {
                    responseBean.putError("上传失败：" + e.getMessage());
                    e.printStackTrace();
                }
            }
        return responseBean.getResponseMap();
    }

}
