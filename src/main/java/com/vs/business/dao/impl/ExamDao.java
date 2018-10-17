package com.vs.business.dao.impl;

import com.vs.business.bean.Exam;
import com.vs.business.dao.IExamDao;
import com.vs.core.persistence.impl.MybatisGenericDao;
import org.springframework.stereotype.Component;

@Component
public class ExamDao extends MybatisGenericDao<Exam, String> implements IExamDao {

}
