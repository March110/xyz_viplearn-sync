package com.vs.business.bean;

import java.util.List;

public class LabelInfo extends BaseBean {
  private int uuid;
  private String name;
  private int categoryId;
  private int parentLabelId;
  private int level;
  private String disName;
  private int isQuestionScore;
  private String cataCode;
  private int isActive;
  private String enName;
  private String projectCode;
  private int orderIndex;

  private List<LabelInfo> topic2s;
  private String labelIds;
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

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public int getParentLabelId() {
    return parentLabelId;
  }

  public void setParentLabelId(int parentLabelId) {
    this.parentLabelId = parentLabelId;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public String getDisName() {
    return disName;
  }

  public void setDisName(String disName) {
    this.disName = disName;
  }

  public int getIsQuestionScore() {
    return isQuestionScore;
  }

  public void setIsQuestionScore(int isQuestionScore) {
    this.isQuestionScore = isQuestionScore;
  }

  public String getCataCode() {
    return cataCode;
  }

  public void setCataCode(String cataCode) {
    this.cataCode = cataCode;
  }

  public int getIsActive() {
    return isActive;
  }

  public void setIsActive(int isActive) {
    this.isActive = isActive;
  }

  public String getEnName() {
    return enName;
  }

  public void setEnName(String enName) {
    this.enName = enName;
  }

  public String getProjectCode() {
    return projectCode;
  }

  public void setProjectCode(String projectCode) {
    this.projectCode = projectCode;
  }

  public int getOrderIndex() {
    return orderIndex;
  }

  public void setOrderIndex(int orderIndex) {
    this.orderIndex = orderIndex;
  }

  public List<LabelInfo> getTopic2s() {
    return topic2s;
  }

  public void setTopic2s(List<LabelInfo> topic2s) {
    this.topic2s = topic2s;
  }

  public String getLabelIds() {
    return labelIds;
  }

  public void setLabelIds(String labelIds) {
    this.labelIds = labelIds;
  }

}
