package com.vs.business.service.impl;

import com.vs.business.bean.Exam;
import com.vs.business.bean.LabelCategory;
import com.vs.business.dao.IExamDao;
import com.vs.business.dao.ILabelCategoryDao;
import com.vs.business.dao.IQuestionDao;
import com.vs.business.service.IExamService;
import com.vs.core.persistence.IGenericDao;
import com.vs.core.service.impl.GenericService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class ExamService extends GenericService<Exam, Integer> implements IExamService {
	private final Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private IExamDao examDao;
	@Autowired
	private ILabelCategoryDao labelCategoryDao;
	@Autowired
	private IQuestionDao questionDao;

	@Override
	protected IGenericDao getDao() {
		return this.examDao;
	}

	@Override
	public void initRankCache() {
		try {
			logger.info("initRankCache====初始化开始");
			long t1 = System.currentTimeMillis();
			// 同步做题得分数据
			examDao.updateByStatementPostfix(".syncAnswer", new String[]{}, new String[]{});
			long t2 = System.currentTimeMillis();
			logger.info("initRankCache====初始化结束 "+(t2-t1)+"ms");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void initTpoQuestionsCountCache() {
		System.out.print("initQuestionsCountCache====初始化开始");
		List<Integer> tpoIds= labelCategoryDao.findIdsByMap(new String[]{"typeCode","type2Code","projectCode"},new Object[]{"ST","SSTT","0101"},"LC_ID desc");
		for(int i=112;i<=115;i++){
			for(int j=0;j<tpoIds.size();j++){
				List<Map<String, Object>> tpoCounts;
				Integer qCategory = Integer.valueOf(i);
				String[] tpoIdArr = tpoIds.get(j).toString().split(",");
				tpoCounts = questionDao.findByStatementPostfix(".countByTopic", new String[]{"tpoIds", "isDelete", "qCategory", "projectCode", "groupBy"}, new Object[]{tpoIdArr, 0, qCategory, "0101", "SubordinateQId"}, null, 999, 1);
				Map queryMap = new HashMap<>();
				Map updateMap = new HashMap<>();
				queryMap.put("key", i+"_"+tpoIds.get(j));
				updateMap.put("key", i+"_"+tpoIds.get(j));
				updateMap.put("tpoCounts", tpoCounts);
				examDao.upsert(queryMap, updateMap, "tpoCounts");
			}
		}
		System.out.print("initQuestionsCountCache====初始化结束tpo共"+tpoIds.size()+"条");
	}
}
