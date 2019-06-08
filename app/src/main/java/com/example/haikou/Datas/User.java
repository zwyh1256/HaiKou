package com.example.haikou.Datas;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser{
    //补充其他属性
    private  String fakename;//昵称
    private  String introd;//简介
    private  String sex; //性别
    private  String birthday; //生日

    public String getFakename()
    {
        return  fakename;
    }
    public void  setFakename(String fakename)
    {
        this.fakename=fakename;
    }
    public String getIntrod()
    {
        return  introd;
    }
    public  void  setIntrod(String introd)
    {
        this.introd=introd;
    }
    public  String getSex()
    {
        return  sex;
    }
    public  void  setSex(String sex)
    {
        this.sex=sex;
    }
    public  String getBirthday()
    {
        return birthday;
    }
    public  void  setBirthday(String birthday)
    {
        this.birthday=birthday;
    }
}