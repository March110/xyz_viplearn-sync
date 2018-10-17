package com.vs.business.bean;

public class User extends BaseBean {
  //id
  private int uuid;
  //地址
  private String address;
  //邮箱
  private String email;
  //简介
  private String intro;
  //是否验证邮箱（0：否；1：是）
  private int isValEmail;
  //是否验证手机（0：否；1：是）
  private int isValPhone;
  //手机号
  private String phoneNo;
  //性别（0：未知；1：男；2：女; 3：保密）
  private int sex;
  //状态（0：正常；1：停用）
  private int status;
  //用户名
  private String name;
  //用户头像
  private String photo;
  //用户密码
  private String password;
  //用户成长值
  private int point;
  //用户等级
  private int level;
  //用户等级是否升级
  private int isLevel;
  //年龄
  private int age;
  //省
  private String province;
  //市
  private String city;
  //阶段（0：未知；1：研究生；2：大学生；3：高中生；4：初中生；5：已工作；6：其他）
  private int stage;
  //是否在线（0：否；1：是）
  private int isOnline;
  //是否在线-题库（0：否；1：是）
  private int isOnlineExam;
  //是否绑定微博
  private int isWB;
  //是否绑定QQ
  private int isQQ;
  //是否绑定微信
  private int isWX;
  //学号
  private String stuNo;
  //备注
  private String memo;

  public String getStuNo() {
    return stuNo;
  }

  public void setStuNo(String stuNo) {
    this.stuNo = stuNo;
  }

  public int getIsWB() {
    return isWB;
  }

  public void setIsWB(int isWB) {
    this.isWB = isWB;
  }

  public int getIsQQ() {
    return isQQ;
  }

  public void setIsQQ(int isQQ) {
    this.isQQ = isQQ;
  }

  public int getIsWX() {
    return isWX;
  }

  public void setIsWX(int isWX) {
    this.isWX = isWX;
  }

  public int getIsOnlineExam() {
      return isOnlineExam;
  }

  public void setIsOnlineExam(int isOnlineExam) {
    this.isOnlineExam = isOnlineExam;
  }

  public int getIsOnline() {
    return isOnline;
  }

  public void setIsOnline(int isOnline) {
    this.isOnline = isOnline;
  }

  public int getStage() {
    return stage;
  }

  public void setStage(int stage) {
    this.stage = stage;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getIsLevel() {
    return isLevel;
  }

  public void setIsLevel(int isLevel) {
    this.isLevel = isLevel;
  }

  public int getUuid() {
    return uuid;
  }

  public void setUuid(int uuid) {
    this.uuid = uuid;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getIntro() {
    return intro;
  }

  public void setIntro(String intro) {
    this.intro = intro;
  }

  public int getIsValEmail() {
    return isValEmail;
  }

  public void setIsValEmail(int isValEmail) {
    this.isValEmail = isValEmail;
  }

  public int getIsValPhone() {
    return isValPhone;
  }

  public void setIsValPhone(int isValPhone) {
    this.isValPhone = isValPhone;
  }

  public String getPhoneNo() {
    return phoneNo;
  }

  public void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo;
  }

  public int getSex() {
    return sex;
  }

  public void setSex(int sex) {
    this.sex = sex;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getPoint() {
    return point;
  }

  public void setPoint(int point) {
    this.point = point;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }
}
