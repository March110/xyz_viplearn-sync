package com.vs.business.dao.impl;

import com.vs.business.bean.Point;
import com.vs.business.dao.IPointDao;
import com.vs.core.persistence.impl.MybatisGenericDao;
import org.springframework.stereotype.Component;

@Component
public class PointDao extends MybatisGenericDao<Point, String> implements IPointDao {

}
