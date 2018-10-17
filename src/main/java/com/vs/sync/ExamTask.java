package com.vs.sync;

import com.vs.business.service.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExamTask {

  @Autowired
  private IExamService examService;

  /**
   * 两小时一次
   */
  @Scheduled(cron = "0 0 */2 * * ?")
  public void initRankCache() {
    examService.initRankCache();
  }

  /**
   * 3天执行一次，2点执行
   */
  @Scheduled(cron = "0 0 2 */3 * ?")
  public void initTpoQuestionsCountCache() {
    examService.initTpoQuestionsCountCache();
  }
}
