package com.zscalerlabsession.zscalerlabsession.Request;

public class UpdatePasswordRequest {
    String emailId;
    String newPassword;
    String oldPassword;

    public UpdatePasswordRequest(){
        super();
    }

    public UpdatePasswordRequest(String emailId, String newPassword, String oldPassword){
        this.emailId = emailId;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
