package com.vs.business.service.impl;

import com.vs.business.bean.Point;
import com.vs.business.bean.User;
import com.vs.business.dao.IUserDao;
import com.vs.business.service.IExamService;
import com.vs.business.service.IPointService;
import com.vs.business.service.IUserService;
import com.vs.core.persistence.IGenericDao;
import com.vs.core.service.impl.GenericService;
import com.vs.tools.DateTime;
import com.vs.tools.PropertiesTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService extends GenericService<User, Integer> implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IExamService examService;
	@Autowired
	private IPointService pointService;

	@Override
	protected IGenericDao getDao() {
		return this.userDao;
	}

	@Override
	public void syncUserLevel() throws Exception {
		// 1小时前
		String createTimeStart = DateTime.getAFewHoursAgo(1);
		// 当前时间
		String createTimeEnd = DateTime.getAFewHoursAgo(0);
		// 查询待同步的用户列表
		List<Map<String, Object>> userList = examService.findByStatementPostfix(".getSyncUser", new String[]{"createTimeStart", "createTimeEnd"}, new Object[]{createTimeStart, createTimeEnd}, "");
		for (Map<String, Object> userMap : userList) {
			int createId = (int) userMap.get("createId");
			// 用户信息
			User user = this.findById(createId);
			this.syncUserLevelByUser(user, createTimeStart, createTimeEnd);
		}
	}

	@Override
	public void syncUserLevelByUser(User user, String createTimeStart, String createTimeEnd) throws Exception {
		// 用户不存在
		if (user == null) return;
		// 查询该用户待同步的做题信息
		List<Map<String, Object>> examList = examService.findByStatementPostfix(".getSyncExamAnswer", new String[]{"createId", "createTimeStart", "createTimeEnd"}, new Object[]{user.getUuid(), createTimeStart, createTimeEnd}, "");
		if (examList != null && !examList.isEmpty()) {
			PropertiesTools pt = new PropertiesTools();
			for (Map<String, Object> examMap : examList) {
				int QCategory = (int) examMap.get("QCategory");
				Long count = (Long) examMap.get("count");
				// 做题类型每日最高分、做题得分
				int pointMax = Integer.valueOf(pt.getValue("point", "pointMax_" + QCategory));
				int point = count.intValue() * Integer.valueOf(pt.getValue("point", "point_" + QCategory));
				// 今日总得分
				int pointToday = pointService.countByStatementPostfix(".countPoint", new String[]{"createId", "status", "type", "createTime"},
								new Object[]{user.getUuid(), 0, QCategory, DateTime.getDateTime().substring(0, 10)});
				// 最终得分 + 当前总得分 <= 每日最高分
				int pointFinal = pointToday + point <= pointMax ? point : pointMax - pointToday;
				if (pointFinal > 0) {
					// 1、添加积分
					this.insertPoint(user.getUuid(), QCategory, pointFinal);
					// 2、修改等级
					// 总积分
					int pointAll = user.getPoint() + pointFinal;
					int level = this.getLevel(pointAll);
					int isLevel = user.getIsLevel();
					if (level > user.getLevel()) isLevel = 1;
					this.update(user.getUuid(), new String[]{"point", "level", "isLevel"}, new Object[]{pointAll, level, isLevel});
				}
			}
		}
	}

	/**
	 * 获取等级
	 */
	public int getLevel(int point) {
		PropertiesTools pt = new PropertiesTools();
		int level, maxPoint;
		for (level = 1; level < 30; level++) {
			maxPoint = Integer.valueOf(pt.getValue("point", "Lv" + level));
			if (point < maxPoint) break;
		}
		return level;
	}

	public void insertPoint(int userId, int type, int point) throws Exception {
		// 插入积分
		Point p = new Point();
		p.setStatus(0);
		p.setCreateTime(DateTime.getDateTime());
		p.setCreateId(userId);
		p.setType(type);
		p.setPoint(point);
		pointService.insert(p);
	}

}
