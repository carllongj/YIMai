package com.carl.yimai.service.impl;

import cn.carl.net.ftp.FtpUploadStandardBean;
import cn.carl.web.upload.UploadTools;
import cn.carl.web.upload.operate.HashFile;
import cn.carl.web.upload.operate.OperateFile;
import com.carl.yimai.service.PictureService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图片上传服务实现
 * <p>Title: com.carl.yimai.service.impl PictureServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/25 23:06
 * @Version 1.0
 */
public class PictureServiceImpl implements PictureService {

    @Resource(name = "standardBean")
    private FtpUploadStandardBean standardBean;

    @Value("${PICTURE_PATH_NAME}")
    private String PICTURE_PATH_NAME;

    private OperateFile operateFile = new HashFile();

    @Override
    public String uploadPicture(MultipartFile file) throws IOException {
        //获取图片的原始名称
        String filename = file.getOriginalFilename();
        //获取文件的流
        InputStream inputStream = file.getInputStream();
        //进行文件的上传
        String url = UploadTools.FTPUpload(standardBean, filename, inputStream, operateFile, false);

        return PICTURE_PATH_NAME + url;
    }
}
