package httpjar.webhook;

import com.blade.ioc.annotation.Bean;
import com.blade.mvc.RouteContext;
import com.blade.mvc.hook.WebHook;

public class CrossOriginWebHook implements WebHook {

    @Override
    public boolean before(RouteContext context) {
        context.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        context.header("Access-Control-Allow-Origin", "*");
        context.header("Access-Control-Allow-Headers", "*");
        context.response().header("Access-Control-Allow-Headers", "*");
        return true;
    }
}
