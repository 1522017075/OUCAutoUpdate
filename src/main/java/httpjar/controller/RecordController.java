package httpjar.controller;

import com.alibaba.fastjson.JSON;
import com.blade.mvc.annotation.BodyParam;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.http.Response;
import httpjar.model.po.User;
import httpjar.model.po.UsersUpdateRecord;
import httpjar.model.vo.UserVO;
import httpjar.service.RecordService;
import httpjar.service.UserService;
import httpjar.util.Result;
import httpjar.util.WebUtil;

import java.util.List;

@Path
public class RecordController {

    @PostRoute("/record")
    public void getRecord(@BodyParam UserVO userVO, Response response){
        RecordService recordService = WebUtil.getBean(RecordService.class);
        List<UsersUpdateRecord> usersUpdateRecords = recordService.selectRecordByUserId(userVO.getId());
        String result = JSON.toJSONString(new Result(usersUpdateRecords != null, usersUpdateRecords));
        response.text(result);
    }

}
