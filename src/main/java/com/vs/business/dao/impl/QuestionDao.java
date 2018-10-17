package com.vs.business.dao.impl;

import com.vs.business.bean.Question;
import com.vs.business.dao.IQuestionDao;
import com.vs.core.persistence.impl.MybatisGenericDao;
import org.springframework.stereotype.Component;

@Component
public class QuestionDao extends MybatisGenericDao<Question, Integer> implements IQuestionDao {

}
