
package com.mrlonewolfer.basicdbdemo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserBeanRoot {

    @Expose
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public static class User {

        @SerializedName("Fname")
        private String fname;
        @Expose
        private String id;
        @SerializedName("Lname")
        private String lname;
        @SerializedName("Mobile")
        private String mobile;

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        @Override
        public String toString() {
            return "User{" +
                    "fname='" + fname + '\'' +
                    ", id='" + id + '\'' +
                    ", lname='" + lname + '\'' +
                    ", mobile='" + mobile + '\'' +
                    '}';
        }
    }
}
