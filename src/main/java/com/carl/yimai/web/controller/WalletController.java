package com.carl.yimai.web.controller;

import cn.carl.page.PageResult;
import com.carl.yimai.po.YmWalletAction;
import com.carl.yimai.service.WalletActionService;
import com.carl.yimai.service.WalletService;
import com.carl.yimai.web.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户账户的控制器
 * 需要被拦截器进行拦截
 * <p>Title: com.carl.yimai.web.controller WalletController</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/4/19 16:13
 * @Version 1.0
 */
@Controller
@RequestMapping("/wallet")
public class WalletController {

    @Resource(name = "walletService")
    private WalletService walletService;

    @Resource(name = "walletActionService")
    private WalletActionService walletActionService;

    @RequestMapping("/remain")
    @ResponseBody
    public Result getRemain(HttpServletRequest request){
        String userId = (String) request.getAttribute("userId");
        Result result = walletService.getCountRemain(userId);
        return result;
    }

    @RequestMapping("/show/actions")
    @ResponseBody
    public PageResult<YmWalletAction> getMyActions(HttpServletRequest request,
                                                   @RequestParam(defaultValue = "1") Integer page){
        String userId = (String) request.getAttribute("userId");

        if(page < 1 ){
            page = 1;
        }

        PageResult<YmWalletAction> pageResult = walletActionService.getActionsByUserId(userId, page);

        return pageResult;
    }
}
