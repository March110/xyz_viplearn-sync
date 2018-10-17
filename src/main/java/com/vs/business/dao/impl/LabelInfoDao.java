package com.vs.business.dao.impl;

import com.vs.business.bean.LabelInfo;
import com.vs.business.dao.ILabelInfoDao;
import com.vs.core.persistence.impl.MybatisGenericDao;
import org.springframework.stereotype.Component;

@Component
public class LabelInfoDao extends MybatisGenericDao<LabelInfo, Integer> implements ILabelInfoDao {

}
