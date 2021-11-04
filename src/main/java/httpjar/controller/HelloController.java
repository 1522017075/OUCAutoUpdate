package httpjar.controller;

import com.alibaba.fastjson.JSON;
import com.blade.mvc.annotation.PathParam;
import com.blade.mvc.http.Response;
import httpjar.model.po.User;
import httpjar.service.AutoUpdateService;
import httpjar.service.TestService;
import httpjar.service.UserService;
import httpjar.util.Result;
import httpjar.util.WebUtil;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
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
	
	@GetRoute("/dajiayiqishangbao")
	public void list(){
		AutoUpdateService autoUpdateService = WebUtil.getBean(AutoUpdateService.class);
		List<User> autoUpdateUsers = autoUpdateService.getAutoUpdateUsers();
		for (User autoUpdateUser : autoUpdateUsers) {
			autoUpdateService.execAutoUpdateByUserId(autoUpdateUser.getId());
			System.out.println(autoUpdateUsers.get(0).getAccount() + "已上报成功\n");
		}
	}

	@GetRoute("/checkUser/:account")
	public void checkUser(@PathParam String account, Response response){
		UserService userService = WebUtil.getBean(UserService.class);
		boolean userExist = userService.checkUser(account);
		String message = userExist ? "200" : "404";
		String result = JSON.toJSONString(new Result<String>(userExist, message));
		response.text(result);
	}

	@GetRoute("/registerUser/:account")
	public void registerUser(@PathParam String account, Response response){
		UserService userService = WebUtil.getBean(UserService.class);
		if (userService.checkUser(account)){
			String result = JSON.toJSONString(new Result<String>(false, "已注册"));
			response.text(result);
		} else {
			User user = userService.registerUser(account);
			String result = JSON.toJSONString(new Result<User>(user != null, user));
			response.text(result);
		}
	}
}
