package httpjar.service;

import httpjar.model.po.UsersUpdateRecord;

import java.util.List;

public interface RecordService {
    List<UsersUpdateRecord> selectRecordByUserId(Integer user_id);
}
