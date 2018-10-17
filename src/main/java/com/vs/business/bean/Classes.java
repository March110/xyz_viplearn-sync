package com.vs.business.bean;

public class Classes extends BaseBean {
  private int uuid;
  //科目编号
  private int subjectId;
  //科目名称（冗余）
  private String subjectName;
  //名称
  private String name;
  //简介
  private String description;
  //开始时间
  private String startTime;
  //结束时间
  private String endTime;
  //原始价
  private float price;
  //优惠金额
  private float discount;
  //限制人数
  private int limitCount;
  //报名人数
  private int signCount;
  //班主任微信号
  private String teacherWechatAccount;
  //是否热报(待开发)
  private boolean isHot;
  //业务类型（0：未发布，1：已发布）
  private int businessType;
  //业务类型（1：报名中，2：已开课，3：已结束）
  private int classType;
  //状态（0：正常；1：删除）
  private int status;
  //考试类别（0:托福，1:雅思，2:GRE，3:GMAT）
  private int examType;
  //课程强度
  private int intensity;
  //每日计划
  private String plan;
  //解决痛点
  private String pain;
  //适合人群
  private String suitable;
  //报名信息
  private String registInfo;

  public int getUuid() {
    return uuid;
  }

  public void setUuid(int uuid) {
    this.uuid = uuid;
  }

  public int getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(int subjectId) {
    this.subjectId = subjectId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public float getDiscount() {
    return discount;
  }

  public void setDiscount(float discount) {
    this.discount = discount;
  }

  public int getLimitCount() {
    return limitCount;
  }

  public void setLimitCount(int limitCount) {
    this.limitCount = limitCount;
  }

  public int getSignCount() {
    return signCount;
  }

  public void setSignCount(int signCount) {
    this.signCount = signCount;
  }

  public String getTeacherWechatAccount() {
    return teacherWechatAccount;
  }

  public void setTeacherWechatAccount(String teacherWechatAccount) {
    this.teacherWechatAccount = teacherWechatAccount;
  }

  public boolean getHot() {
    return isHot;
  }

  public void setHot(boolean hot) {
    isHot = hot;
  }

  public int getBusinessType() {
    return businessType;
  }

  public void setBusinessType(int businessType) {
    this.businessType = businessType;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public int getClassType() {
    return classType;
  }

  public void setClassType(int classType) {
    this.classType = classType;
  }

  public int getExamType() {
    return examType;
  }

  public void setExamType(int examType) {
    this.examType = examType;
  }

  public int getIntensity() {
    return intensity;
  }

  public void setIntensity(int intensity) {
    this.intensity = intensity;
  }

  public String getPlan() {
    return plan;
  }

  public void setPlan(String plan) {
    this.plan = plan;
  }

  public String getPain() {
    return pain;
  }

  public void setPain(String pain) {
    this.pain = pain;
  }

  public String getSuitable() {
    return suitable;
  }

  public void setSuitable(String suitable) {
    this.suitable = suitable;
  }

  public String getRegistInfo() {
    return registInfo;
  }

  public void setRegistInfo(String registInfo) {
    this.registInfo = registInfo;
  }
}