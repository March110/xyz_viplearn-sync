package com.vs.business.service.impl;

import com.vs.business.bean.Classes;
import com.vs.business.dao.IClassesDao;
import com.vs.business.service.IClassesService;
import com.vs.core.persistence.IGenericDao;
import com.vs.core.service.impl.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ClassesService extends GenericService<Classes, Integer> implements IClassesService {

	@Autowired
	private IClassesDao classesDao;

	@Override
	protected IGenericDao getDao() {
		return this.classesDao;
	}

	/**
	 * 定时任务判断学团状态
	 */
	@Override
	public void quartzClassType() throws Exception {
		try {
			List<Classes> classList = classesDao.findByMap(new String[]{"status", "businessType"}, new Object[]{0, 1}, null);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date curDate = new Date();

			for (Classes classes : classList) {
				Date startDate = sdf.parse(classes.getStartTime());
				Date endDate = sdf.parse(classes.getEndTime());
				int classType = 0;
				if (startDate.getTime() > curDate.getTime()) {
					//报名中
					classType = 1;
				} else if (startDate.getTime() <= curDate.getTime() && endDate.getTime() > curDate.getTime()) {
					//已开课
					classType = 2;
				} else if (endDate.getTime() <= curDate.getTime()) {
					//已结束
					classType = 3;
				}

				if (classType != classes.getClassType()) {
					//如果新的classType与老的classType相同则不更新,否则更新
					classes.setClassType(classType);
					classesDao.update(classes);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
}
