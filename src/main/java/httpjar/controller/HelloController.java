package httpjar.controller;

import com.alibaba.fastjson.JSON;
import com.blade.mvc.annotation.*;
import com.blade.mvc.http.Response;
import httpjar.model.po.User;
import httpjar.model.vo.UserVO;
import httpjar.service.AutoUpdateService;
import httpjar.service.TestService;
import httpjar.service.UserService;
import httpjar.util.Result;
import httpjar.util.WebUtil;

import jdk.nashorn.internal.parser.JSONParser;

import java.util.List;

@Path
public class HelloController {

	/*
	 * https://www.baeldung.com/blade
	 * We can using this frame to run a http without tomcat in Bank
	 * 因为此框架在使用时，由于controller类的对象 是blade框架new出来的(Spring容器要求所有对象由Spring来创建)
	 * 所以无法使用@Autowired注解获取Service实现类（不影响Service层中使用@Autowired去获取Mapper对象）
	 * 需手动通过context对象获取对应Service进行（已封装好context类）  该bug耗时一天查出
	 */

	@PostRoute("/checkUser")
	public void checkUser1(@BodyParam UserVO userVO, Response response){
		UserService userService = WebUtil.getBean(UserService.class);
		User user = userService.checkUser(userVO.getAccount());
		String result = JSON.toJSONString(new Result<User>(user != null, user));
		response.text(result);
	}

	@PostRoute("/registerUser")
	public void registerUser(@BodyParam UserVO userVO, Response response){
		UserService userService = WebUtil.getBean(UserService.class);
		if (userService.checkUser(userVO.getAccount()) != null){
			String result = JSON.toJSONString(new Result<String>(false, "已注册"));
			response.text(result);
		} else {
			User user = userService.registerUser(userVO.getAccount());
			String result = JSON.toJSONString(new Result<User>(user != null, user));
			response.text(result);
		}
	}
}
