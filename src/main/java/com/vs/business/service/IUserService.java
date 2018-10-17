package com.vs.business.service;

import com.vs.business.bean.User;
import com.vs.core.service.IGenericService;

public interface IUserService extends IGenericService<User, Integer> {

	void syncUserLevel() throws Exception;
	void syncUserLevelByUser(User user, String createTimeStart, String createTimeEnd) throws Exception;

}
