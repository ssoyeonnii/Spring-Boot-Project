package com.bitc.java501team3.dto;

import lombok.Data;

@Data
public class UserDTO {
    private int userIdx;
    private String userId;
    private String userPw;
    private String userName;
    private String userAddress;
    private String userFav;
    private String userAdCheck;
    private String userDeletedYn;

    public int getUserIdx() {
        return userIdx;
    }

    public void setUserIdx(int userIdx) {
        this.userIdx = userIdx;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserFav() {
        return userFav;
    }

    public void setUserFav(String userFav) {
        this.userFav = userFav;
    }

    public String getUserAdCheck() {
        return userAdCheck;
    }

    public void setUserAdCheck(String userAdCheck) {
        this.userAdCheck = userAdCheck;
    }
}
