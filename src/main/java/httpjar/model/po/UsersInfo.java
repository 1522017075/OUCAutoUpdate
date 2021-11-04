package httpjar.model.po;

public class UsersInfo {
    private Integer id;

    private String formData;

    private String loginKey;

    private String sendKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData == null ? null : formData.trim();
    }

    public String getLoginKey() {
        return loginKey;
    }

    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey == null ? null : loginKey.trim();
    }

    public String getSendKey() {
        return sendKey;
    }

    public void setSendKey(String sendKey) {
        this.sendKey = sendKey == null ? null : sendKey.trim();
    }
}