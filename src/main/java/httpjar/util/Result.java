package httpjar.util;

public class Result<T> {
    private boolean status;// 是否成功标志

    private T data;// 成功时返回的数据

    public Result() {
    }

    // 构造器1
    public Result(boolean status) {
        this.status = status;
        this.data = (T) "null";
    }

    // 构造器2
    public Result(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}