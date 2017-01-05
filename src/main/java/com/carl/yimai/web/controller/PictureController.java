package com.carl.yimai.web.controller;

import com.carl.yimai.service.PictureService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 图片上传controller
 * <p>Title: com.carl.yimai.web.controller PictureController</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/1/4 20:40
 * @Version 1.0
 */
@Controller
@RequestMapping("/picture")
public class PictureController {

    @Resource(name = "pictureService")
    private PictureService pictureService;

    /**
     * 上传图片的功能
     * 测试结果 √
     * @param picture
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Result pictureUpload(MultipartFile picture) throws IOException {
        String url = pictureService.uploadPicture(picture);
        return Result.ok(url);
    }
}
