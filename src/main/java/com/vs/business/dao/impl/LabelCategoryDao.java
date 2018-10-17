package com.vs.business.dao.impl;

import com.vs.business.bean.LabelCategory;
import com.vs.business.dao.ILabelCategoryDao;
import com.vs.core.persistence.impl.MybatisGenericDao;
import org.springframework.stereotype.Component;

@Component
public class LabelCategoryDao extends MybatisGenericDao<LabelCategory, Integer> implements ILabelCategoryDao {
}
