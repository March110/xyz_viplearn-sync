package com.vs.business.bean;

import java.util.List;

public class LabelCategory extends BaseBean {
  private int uuid;
  private String name;
  private String category;
  private String type2;
  private int orderNumber;
  private String nameCode;
  private String typeCode;
  private String type2Code;
  private String projectCode;
  private int isPublish;
  private List<LabelInfo> labelInfos;

  public int getIsPublish() {
    return isPublish;
  }

  public void setIsPublish(int isPublish) {
    this.isPublish = isPublish;
  }

  public int getUuid() {
    return uuid;
  }

  public void setUuid(int uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getType2() {
    return type2;
  }

  public void setType2(String type2) {
    this.type2 = type2;
  }

  public int getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(int orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getNameCode() {
    return nameCode;
  }

  public void setNameCode(String nameCode) {
    this.nameCode = nameCode;
  }

  public String getTypeCode() {
    return typeCode;
  }

  public void setTypeCode(String typeCode) {
    this.typeCode = typeCode;
  }

  public String getType2Code() {
    return type2Code;
  }

  public void setType2Code(String type2Code) {
    this.type2Code = type2Code;
  }

  public String getProjectCode() {
    return projectCode;
  }

  public void setProjectCode(String projectCode) {
    this.projectCode = projectCode;
  }

  public List<LabelInfo> getLabelInfos() {
    return labelInfos;
  }

  public void setLabelInfos(List<LabelInfo> labelInfos) {
    this.labelInfos = labelInfos;
  }
}

