package com.carl.yimai.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 上传图片Service
 * <p>Title: com.carl.yimai.service</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/25 23:04
 * @Version 1.0
 */
public interface PictureService {

    String uploadPicture(MultipartFile file) throws IOException;

}
