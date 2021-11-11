package httpjar;

import com.blade.Blade;
import com.blade.security.web.cors.CorsMiddleware;
import httpjar.webhook.CrossOriginWebHook;

/*
 * 项目打jar包只能在项目根目录mvn clean install进行打包，因为spring-context与spring-aop包命名空间冲突，已在pom配置plugin打包,合并命名空间内文件解决之
 */
public class HelloApplication {
    public static void main(String[] args) {
        Blade.of()
            .get("/", ctx -> ctx.text("Hello Blade"))
            .use(new CrossOriginWebHook())
            .start(HelloApplication.class, args);
    }
}
