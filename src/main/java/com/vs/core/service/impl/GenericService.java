package com.vs.core.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vs.core.persistence.IGenericDao;
import com.vs.core.service.IGenericService;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericService<T, PkT extends Serializable>
        implements IGenericService<T, PkT> {

  protected abstract IGenericDao getDao();

  @Override
  public Integer count(String propertyName, Object propertyValue) {
    return getDao().count(propertyName, propertyValue);
  }

  @Override
  public Integer count(String[] propertyNames, Object[] propertyValues) {
    return getDao().count(propertyNames, propertyValues);
  }

  @Transactional
  @Override
  public void deleteById(PkT id) throws Exception {
    getDao().deleteById(id);
  }

  @Transactional
  @Override
  public void deleteByIds(List<PkT> ids) throws Exception {
    getDao().deleteByIds(ids);
  }

  @Transactional
  @Override
  public int deleteByMap(String[] properties, Object[] propertyValues) throws Exception {
    return getDao().deleteByMap(properties, propertyValues);
  }

  @Transactional
  @Override
  public void deleteByIdsMap(List<PkT> ids, String[] properties, Object[] propertyValues)
          throws Exception {
    getDao().deleteByIdsMap(ids, properties, propertyValues);
  }

  @Override
  public T findById(PkT id) {
    return (T) getDao().findById(id);
  }

  @Override
  public List<T> findByIds(List<PkT> ids) {
    return getDao().findByIds(ids);
  }

  @Override
  public List<PkT> findIdsByMap(String[] properties, Object[] propertyValues, String orderBy) {
    return getDao().findIdsByMap(properties, propertyValues, orderBy);
  }

  @Override
  public List<T> findByMap(String[] properties, Object[] propertyValues, String orderBy) {
    return getDao().findByMap(properties, propertyValues, orderBy);
  }

  @Override
  public List<T> findAll() {
    return getDao().findAll();
  }

  @Transactional
  @Override
  public Serializable insert(T o) throws Exception {
    return getDao().insert(o);
  }

  @Override
  public List<T> pageQueryBy(
          String[] properties, Object[] propertyValues, String orderBy, int pageSize, int pageNo) {
    return getDao().pageQueryBy(properties, propertyValues, orderBy, pageSize, pageNo);
  }

  @Override
  public List<Map<String, Object>> findByStatementPostfix(
          String statementPostfix,
          String[] properties,
          Object[] propertyValues,
          String orderBy,
          int pageSize,
          int pageNo) {
    return getDao()
            .findByStatementPostfix(
                    statementPostfix, properties, propertyValues, orderBy, pageSize, pageNo);
  }

  @Override
  public List<Map<String, Object>> findByStatementPostfix(
          String statementPostfix,
          String[] properties,
          Object[] propertyValues,
          String orderBy) {
    return getDao()
            .findByStatementPostfix(
                    statementPostfix, properties, propertyValues, orderBy);
  }

  @Transactional
  @Override
  public int update(PkT id, String[] properties, Object[] propertyValues) throws Exception {
    return getDao().update(id, properties, propertyValues);
  }

  @Transactional
  @Override
  public T update(T o) throws Exception {
    return (T) getDao().update(o);
  }

  @Transactional
  @Override
  public int updateByIdsMap(List<PkT> ids, String[] properties, Object[] propertyValues)
          throws Exception {
    return getDao().updateByIdsMap(ids, properties, propertyValues);
  }

  @Override
  public List<T> findTByStatementPostfix(String statementPostfix, String[] properties, Object[] propertyValues, String orderBy, String order) {
    return getDao().findTByStatementPostfix(statementPostfix, properties, propertyValues, orderBy, order);
  }

  @Override
  public List<String> findByStatementPostfix(String statementPostfix,String[] properties, Object[] propertyValues){
    return getDao().findByStatementPostfix(statementPostfix, properties, propertyValues);
  }

  public Integer countByStatementPostfix(
          String statementPostfix, String[] properties, Object[] propertyValues) {
    return getDao().countByStatementPostfix(statementPostfix, properties, propertyValues);
  }

  public void updateByStatementPostfix(
          String statementPostfix, String[] properties, Object[] propertyValues) throws Exception {
    getDao().updateByStatementPostfix(statementPostfix, properties, propertyValues);
  }

  @Override
  public int pageCount(String[] propertyNames, Object[] propertyValues, int pageSize) {
    Map<String, Object> map = new HashMap<>();
    Integer count = this.count(propertyNames, propertyValues);
    if (count == null) {
      count = 0;
    }
    int pageCount = count / pageSize;
    if (pageCount < 1) {
      pageCount = 1;
    }
    if (count > pageCount * pageSize) {
      pageCount = pageCount + 1;
    }
    return pageCount;
  }

  //mongo
  public List<Map<String,Object>> findAll(String[] prokeys,Object[] provalues, String collectionName,String orderBy ,Integer order){
    return getDao().findAll(prokeys,provalues,collectionName,orderBy,order);
  }
  @Override
  public List<Map<String,Object>> findByPage(String[] prokeys,Object[] provalues, String collectionName,int skip,int size,String orderBy ,Integer order) {
    return getDao().findByPage(prokeys,provalues,collectionName,skip,size,orderBy,order);
  }
  @Override
  public List<Map<String,Object>> findAllByArrOrderAndProjection(String[] prokeys,Object[] provalues,String[] includeFields, String collectionName,String[] orderBys ,Integer[] orders){
    return getDao().findAllByArrOrderAndProjection(prokeys,provalues,includeFields,collectionName,orderBys,orders);
  }
  //  查找一个
  public Map findOne(String[] prokeys,Object[] provalues, String collectionName,String orderBy ,Integer order){
    return getDao().findOne(prokeys,provalues,collectionName,orderBy,order);
  }
  @Override
  public Map findOneFilterFeilds(String[] prokeys, Object[] provalues,String[] includeFields, String collectionName){
    return getDao().findOneFilterFeilds(prokeys,provalues,includeFields,collectionName);
  }
  public long countBy(String[] prokeys,Object[] provalues, String collectionName) {
    return getDao().countBy(prokeys,provalues,collectionName);
  }
  @Override
  public void upsert(Map<String,Object> querys,Map<String,Object> updates, String collectionName){
    getDao().upsert(querys,updates,collectionName);
  }
}
