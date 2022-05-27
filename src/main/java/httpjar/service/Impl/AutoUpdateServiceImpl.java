package httpjar.service.Impl;

import httpjar.mapper.UserMapper;
import httpjar.mapper.UsersInfoMapper;
import httpjar.mapper.UsersUpdateRecordMapper;
import httpjar.model.po.User;
import httpjar.model.po.UsersInfo;
import httpjar.model.po.UsersUpdateRecord;
import httpjar.service.AutoUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Slf4j
public class AutoUpdateServiceImpl implements AutoUpdateService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UsersInfoMapper usersInfoMapper;

    @Autowired
    private UsersUpdateRecordMapper usersUpdateRecordMapper;

    private String UserFilePath = "/app/OUC_Auto_Update/userFiles/";
    @Override
    public List<User> getAutoUpdateUsers() {
        return userMapper.selectAutoUpdateUsers();
    }

    @Override
    public void autoUpdate(Integer userId) {
        // 执行上报
        boolean isSuccess = execAutoUpdateByUserId(userId);

        // 保存上报成功/失败信息
        UsersUpdateRecord record = new UsersUpdateRecord();
        record.setUpdateTime(new Date());
        record.setIsSuccess(isSuccess);
        record.setUserId(userId);
        usersUpdateRecordMapper.insert(record);

        // 清空个人信息文件
//        clearPath(new File(UserFilePath));
    }

    @Override
    public boolean execAutoUpdateByUserId(Integer userId) {
        Boolean result = true;
        try {
            File path = new File(UserFilePath);
            if (path.exists() == false) {
                path.mkdirs();
            }
            UsersInfo usersInfo = usersInfoMapper.selectByPrimaryKey(userId);
            // 检测是否有生成输入文件，没有就生成该文件
            File formDataFile = new File(UserFilePath + userId + "_formData.txt");
            checkFileAndContent(formDataFile, usersInfo.getFormData());
            File loginKeyFile = new File(UserFilePath + userId + "_loginKey.txt");
            checkFileAndContent(loginKeyFile, usersInfo.getLoginKey());
            File sendKeyFile = new File(UserFilePath + userId + "_sendKey.txt");
            checkFileAndContent(sendKeyFile, usersInfo.getSendKey());

//            String command="docker run -v /app/OUC_Auto_Update:/usr/src/myapp -w /usr/src/myapp python:latest python helloworld.py" + userId;   //程序路径
            String command = "python /app/OUC_Auto_Update/helloworld.py " + userId;
            Process process = Runtime.getRuntime().exec(command);
            int pythonExecReturnCode = process.waitFor();
            if(pythonExecReturnCode != 0) {
                result = false;
                log.info("已调用python脚本， 结果为" + pythonExecReturnCode + ", 请及时查看日志!");
            } else {
                log.info("已调用python脚本， 结果为" + pythonExecReturnCode);
            }

        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void cancelAutoUpdateByUserId(Integer userId) {
        userMapper.cancelAutoUpdateByUserId(userId);
    }

    @Override
    public void startAutoUpdateByUserId(Integer userId) {
        userMapper.startAutoUpdateByUserId(userId);
    }

    private void checkFileAndContent(File file, String content) throws IOException {
        // 不存在就创建
        if (file.exists() == false) {
            file.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(content);
            out.close();
            System.out.println(file.getAbsolutePath() + "创建成功！");
        }
    }

    public void clearPath(File file) {
        if(!file.exists()){
            return;
        }
        if(file.isFile() || file.list()==null) {
            file.delete();
            System.out.println("删除了"+file.getName());
        }else {
            File[] files = file.listFiles();
            for(File a:files) {
                clearPath(a);
            }
//            file.delete();
        }
    }

}
