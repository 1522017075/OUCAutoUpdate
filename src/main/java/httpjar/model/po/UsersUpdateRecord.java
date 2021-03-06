package httpjar.model.po;

import java.util.Date;

public class UsersUpdateRecord {
    private Integer id;

    private Integer userId;

    private Date updateTime;

    private Boolean isSuccess;

    public UsersUpdateRecord() {
    }

    public UsersUpdateRecord(Integer userId, Date updateTime, Boolean isSuccess) {
        this.userId = userId;
        this.updateTime = updateTime;
        this.isSuccess = isSuccess;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}