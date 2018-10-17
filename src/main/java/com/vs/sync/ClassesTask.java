package com.vs.sync;

import com.vs.business.service.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClassesTask {

  @Autowired
  private IClassesService classesService;

  /**
   * 每天2点执行一次
   */
  @Scheduled(cron = "0 0 2 */1 * ?")
  public void quartzClassType() throws Exception {
    classesService.quartzClassType();
  }
}
