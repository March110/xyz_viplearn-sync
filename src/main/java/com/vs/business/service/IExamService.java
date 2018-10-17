package com.vs.business.service;

import com.vs.business.bean.Exam;
import com.vs.core.service.IGenericService;

public interface IExamService extends IGenericService<Exam, Integer> {

  void initRankCache();
  void initTpoQuestionsCountCache();
}
