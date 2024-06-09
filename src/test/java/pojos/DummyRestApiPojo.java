package pojos;

import java.util.List;

public class DummyRestApiPojo {
    private String status;
    private List<EmployeePojo> data; // Employee pojolardan olusan bir list
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<EmployeePojo> getData() {
        return data;
    }

    public void setData(List<EmployeePojo> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DummyRestApiPojo{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
