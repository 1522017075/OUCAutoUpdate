package httpjar.service.Impl;

import httpjar.mapper.UsersUpdateRecordMapper;
import httpjar.model.po.UsersUpdateRecord;
import httpjar.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RecordServiceImpl implements RecordService {

    @Autowired
    private UsersUpdateRecordMapper usersUpdateRecordMapper;
    @Override
    public List<UsersUpdateRecord> selectRecordByUserId(Integer user_id) {
        return usersUpdateRecordMapper.selectRecordByUserId(user_id);
    }
}
