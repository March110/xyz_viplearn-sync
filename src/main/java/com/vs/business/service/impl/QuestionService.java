
package com.vs.business.service.impl;

import com.vs.business.bean.Question;
import com.vs.business.dao.IQuestionDao;
import com.vs.business.service.IQuestionService;
import com.vs.core.persistence.IGenericDao;
import com.vs.core.service.impl.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService extends GenericService<Question, Integer> implements IQuestionService {

  @Autowired
  private IQuestionDao questionDao;

  @Override
  protected IGenericDao getDao() {
    return questionDao;
  }


  @Override
  public List<Map<String, Object>> selectTpoInfo() {
    List<Map<String, Object>> list = this.findByStatementPostfix(".selectTpoInfo", new String[]{"SSTT"}, new Object[]{"SSTT"}, null, 999, 1);
    return list;
  }

  @Override
  public List<Map<String, Object>> selectQuestionCodeNameByPid(int pId, String qCategoryName) {
    List<Map<String, Object>> list = this.findByStatementPostfix(".selectQuestionCodeNameByPid", new String[]{"pId", "childType"}, new Object[]{pId, qCategoryName}, null, 999, 1);
    return list;
  }

  @Override
  public List<Map<String, Object>> countByTopic(String[] pros, Object[] values) {
    List<Map<String, Object>> list = this.findByStatementPostfix(".countByTopic", pros, values, null, 999, 1);
    return list;
  }

  @Override
  public void initAvgAndQpCountCache() {
    long t1 = System.currentTimeMillis();
    System.out.print("initAvgAndQpCountCache====初始化开始");
    List<Integer> qIds = questionDao.findIdsByMap(new String[]{},new Object[]{},null);
    try {
      for(int i=0;i<qIds.size();i++){
        Map avgMap = new HashMap<>();
        Map query = new HashMap<>();
        int totalRightCount = questionDao.countByStatementPostfix(".getTotalRightNumByQid",new String[]{"qId", "clientType"},new Object[]{qIds.get(i), 0});
        int practiceTimes = questionDao.countByStatementPostfix(".getTotalPracticeTimesByQid",new String[]{"qId", "clientType"},new Object[]{qIds.get(i), 0});
        int qPageCount = questionDao.countByStatementPostfix(".getQPageCount",new String[]{"qId"},new Object[]{qIds.get(i)});
        int avg = 0;
        if(practiceTimes != 0){
          avg = totalRightCount/practiceTimes;
        }
        query.put("Q_ID",qIds.get(i));
        avgMap.put("Q_ID",qIds.get(i));
        avgMap.put("avg",avg);
        avgMap.put("qpCount",qPageCount);
        questionDao.upsert(query,avgMap,"avgAndQpCount");
      }
      long t2 = System.currentTimeMillis();
      System.out.print("initAvgAndQpCountCache====初始化结束题目共"+qIds.size()+"条 "+(t2-t1)+"ms");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
