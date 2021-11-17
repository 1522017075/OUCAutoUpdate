package httpjar.controller;

import com.alibaba.fastjson.JSON;
import com.blade.mvc.annotation.BodyParam;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.http.Response;
import httpjar.model.po.User;
import httpjar.model.vo.UserVO;
import httpjar.service.AutoUpdateService;
import httpjar.util.Result;
import httpjar.util.WebUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
@Path
public class AutoUpdateController {

    private AutoUpdateService autoUpdateService = WebUtil.getBean(AutoUpdateService.class);

    @GetRoute("/dajiayiqishangbao")
    public void list(){
        List<User> autoUpdateUsers = autoUpdateService.getAutoUpdateUsers();
        for (User autoUpdateUser : autoUpdateUsers) {
            autoUpdateService.execAutoUpdateByUserId(autoUpdateUser.getId());
            log.info(autoUpdateUser.getAccount() + "已上报成功\n");
        }
    }


    @PostRoute("/updateBySelf")
    public void updateBySelf(@BodyParam UserVO userVO, Response response){
        autoUpdateService.autoUpdate(userVO.getId());
        log.info("[上报提醒] 用户 " + userVO.getAccount() + " 已上报成功!");

        String result = JSON.toJSONString(new Result<String>(true));
        response.text(result);
    }

    @PostRoute("/cancelAutoUpdate")
    public void cancelAutoUpdate(@BodyParam UserVO userVO, Response response){
        autoUpdateService.cancelAutoUpdateByUserId(userVO.getId());
        log.info(userVO.getAccount() + " 已取消每日定时上报!");

        String result = JSON.toJSONString(new Result<String>(true));
        response.text(result);
    }

    @PostRoute("/startAutoUpdate")
    public void startAutoUpdate(@BodyParam UserVO userVO, Response response){
        autoUpdateService.startAutoUpdateByUserId(userVO.getId());
        log.info(userVO.getAccount() + " 已开启每日定时上报!");

        String result = JSON.toJSONString(new Result<String>(true));
        response.text(result);
    }


}
