package com.vs.business.service;

import com.vs.business.bean.Classes;
import com.vs.core.service.IGenericService;

public interface IClassesService extends IGenericService<Classes, Integer> {

	void quartzClassType() throws Exception;
}
