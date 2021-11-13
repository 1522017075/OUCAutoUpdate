package httpjar.schedule;

import com.blade.ioc.annotation.Bean;
import com.blade.task.annotation.Schedule;
import httpjar.model.po.User;
import httpjar.service.AutoUpdateService;
import httpjar.util.WebUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Bean
@Slf4j
public class AutoUpdateSchedule {

    AutoUpdateService autoUpdateService = WebUtil.getBean(AutoUpdateService.class);

    @Schedule(name = "autoUpdateTask", cron = "0 0 8 * * ?")
    public void runScheduledTask() {
        System.out.println("每天早上八点定时任务启动，给所有启动按钮的人上报~");
        List<User> autoUpdateUsers = autoUpdateService.getAutoUpdateUsers();
        autoUpdateUsers.forEach(user -> {
            autoUpdateService.autoUpdate(user.getId());
            log.info("[上报提醒] 用户 " + user.getAccount() + " 已上报成功!");
        });
    }
}
