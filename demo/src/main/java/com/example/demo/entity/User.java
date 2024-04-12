package com.example.demo.entity;

import java.util.Map;

public class User {
    private int userid;
    private String username;
    private String password;
    private String phone_number;
    private String region;
    private String onjob;
    private int type;

    public User(int userid, String username, String password, String phone_number, String region, String onjob,int type) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.phone_number = phone_number;
        this.region = region;
        this.onjob = onjob;
        this.type = type;
    }
    public User(Map<String,String>map){
            int userid = 0;
            String username = "";
            String password = "";
            String hone_number = "";
            String regin = "";
            String onjob = "";
            int  type = 0;
            try{
                userid = Integer.parseInt(map.get("userid"));
            }catch(Exception e){}
            try{
                username = map.get("username");
            }catch(Exception e){}
            try{
                password = map.get("password");
            }catch(Exception e){}
            try{
                phone_number = map.get("phone_number");
            }catch(Exception e){}
            try{
                regin = map.get("regin");
            }catch(Exception e){}
            try{
                onjob = map.get("onjob");
            }catch(Exception e){}
            try{
                type = Integer.parseInt(map.get("type"));
            }catch(Exception e){}

            this.userid = userid;
            this.username = username;
            this.password = password;
            this.phone_number = phone_number;
            this.region = region;
            this.onjob = onjob;
            this.type = type;

        }
            public String getPhone_number(){
                return phone_number;
            }

            public void setPhone_number(String phone_number){
                this.phone_number = phone_number;
            }

            public int getUserid(){
                return userid;
            }

            public void setUserid(int userid){
                this.userid = userid;
            }

            public String getUsername(){
                return username;
            }

            public void setUsername(String username){
                this.username = username;
            }

            public String getPassword(){
                return password;
            }

            public void setPassword(String password){
                this.password = password;
            }

            public String getRegion(){
                return region;
            }
            
            public void setRegion(String region){
              
                this.region = region;
            }
            public String getOnjob(){
                return onjob;
            }

            public void setOnjob(String onjob){
                this.onjob = onjob;
            }

            public int getType(){
                return type;
            }
            
            public void setType(int type){
                this.type =type; 
            }

}

