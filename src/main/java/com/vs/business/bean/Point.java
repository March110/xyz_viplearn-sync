package com.vs.business.bean;

public class Point extends BaseBean{
  //id
  private int uuid;
  //类型（0：听力做对1题；1：阅读做对1题；2：写作做题完成一篇；3：口语做题完成一篇；4：填写目标分数；5：设置头像；6：绑定手机；7：绑定微信；8：打卡）
  private int type;
  //状态（0：正常；1：停用）
  private int status;
  //分
  private int point;

  public int getUuid() {
    return uuid;
  }

  public void setUuid(int uuid) {
    this.uuid = uuid;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public int getPoint() {
    return point;
  }

  public void setPoint(int point) {
    this.point = point;
  }
}
