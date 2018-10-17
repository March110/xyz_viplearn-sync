package com.vs.business.dao.impl;

import com.vs.business.bean.User;
import com.vs.business.dao.IUserDao;
import com.vs.core.persistence.impl.MybatisGenericDao;
import org.springframework.stereotype.Component;

@Component
public class UserDao extends MybatisGenericDao<User, String> implements IUserDao {

}
