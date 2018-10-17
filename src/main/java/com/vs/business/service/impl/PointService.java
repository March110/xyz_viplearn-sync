package com.vs.business.service.impl;

import com.vs.business.bean.Point;
import com.vs.business.dao.IPointDao;
import com.vs.business.dao.impl.PointDao;
import com.vs.business.service.IPointService;
import com.vs.core.persistence.IGenericDao;
import com.vs.core.service.impl.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService extends GenericService<Point, Integer> implements IPointService {

	@Autowired
	private IPointDao pointDao;

	@Override
	protected IGenericDao getDao() {
		return this.pointDao;
	}

}
