package com.vs.business.dao.impl;

import com.vs.business.bean.Classes;
import com.vs.business.dao.IClassesDao;
import com.vs.core.persistence.impl.MybatisGenericDao;
import org.springframework.stereotype.Component;

@Component
public class ClassesDao extends MybatisGenericDao<Classes, String> implements IClassesDao {

}
