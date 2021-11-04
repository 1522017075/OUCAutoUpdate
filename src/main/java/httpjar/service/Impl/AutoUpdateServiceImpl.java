package httpjar.service.Impl;

import httpjar.mapper.UserMapper;
import httpjar.mapper.UsersInfoMapper;
import httpjar.model.po.User;
import httpjar.model.po.UsersInfo;
import httpjar.service.AutoUpdateService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AutoUpdateServiceImpl implements AutoUpdateService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UsersInfoMapper usersInfoMapper;

    private String UserFilePath = "/app/OUC_Auto_Update/userFiles/";
    @Override
    public List<User> getAutoUpdateUsers() {
        return userMapper.selectAutoUpdateUsers();
    }

    @Override
    public void execAutoUpdateByUserId(Integer userId) {
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

            // TODO:执行py
//            String command="docker run -v /app/OUC_Auto_Update:/usr/src/myapp -w /usr/src/myapp python:latest python helloworld.py" + userId;   //程序路径
            String command = "python /app/OUC_Auto_Update/helloworld.py " + userId;
            Process process = Runtime.getRuntime().exec(command);
            int i = process.waitFor();
            System.out.println("已调用python脚本， 结果为" + i);

        } catch (Exception e) {
            e.printStackTrace();
        }
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


}
