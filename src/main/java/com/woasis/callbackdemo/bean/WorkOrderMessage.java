package com.woasis.callbackdemo.bean;

/**
 * 工单回调信息
 */
public class WorkOrderMessage extends MessageInfo {

    //车架号
    private String vin;
    //工单号
    private String workorderno;
    //工单状态
    private Integer status;
    //工单原因
    private String reason;
    //操作用户
    private Integer userid;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getWorkorderno() {
        return workorderno;
    }

    public void setWorkorderno(String workorderno) {
        this.workorderno = workorderno;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
