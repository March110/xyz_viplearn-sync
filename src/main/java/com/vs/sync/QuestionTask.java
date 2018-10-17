package com.vs.sync;

import com.vs.business.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QuestionTask {

  @Autowired
  private IQuestionService questionService;

  /**
   * 七天执行一次  23点执行
   */
  @Scheduled(cron = "0 0 23 */7 * ?")
  public void initAvgAndQpCountCache() {
    questionService.initAvgAndQpCountCache();
  }
}
