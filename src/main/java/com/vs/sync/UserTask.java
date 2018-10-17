package com.vs.sync;

import com.vs.business.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserTask {

  @Autowired
  private IUserService userService;

  /**
   * 同步用户等级和积分（6点到24点每小时触发一次，如果修改时间，同时要修改刷新时间间隔）
   */
  @Scheduled(cron = "0 0 6-23 * * ?")
  public void syncUserLevel() throws Exception {
    userService.syncUserLevel();
  }
}
