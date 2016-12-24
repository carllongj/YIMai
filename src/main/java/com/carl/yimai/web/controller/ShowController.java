package com.carl.yimai.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title: com.carl.yimai.web.controller ShowController</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/24 18:16
 * @Version 1.0
 */
@Controller
public class ShowController {

    @RequestMapping("index.action")
    public String showIndex(){

        return "index";
    }
}
