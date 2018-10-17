package com.vs.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IGenericService<T, PkT extends Serializable> {

  Integer count(final String propertyName, final Object propertyValue);

  Integer count(final String[] propertyNames, final Object[] propertyValues);

  T findById(PkT id);

  List<T> findByIds(List<PkT> ids);

  List<T> findByMap(String[] properties, Object[] propertyValues, String orderBy);

  List<PkT> findIdsByMap(String[] properties, Object[] propertyValues, String orderBy);

  List<T> findAll();

  Serializable insert(T o) throws Exception;

  T update(T o) throws Exception;

  int update(PkT id, String[] properties, Object[] propertyValues) throws Exception;

  int updateByIdsMap(List<PkT> ids, String[] properties, Object[] propertyValues)
      throws Exception;

  void deleteById(PkT id) throws Exception;

  void deleteByIds(List<PkT> ids) throws Exception;

  void deleteByIdsMap(List<PkT> ids, String[] properties, Object[] propertyValues)
      throws Exception;

  int deleteByMap(String[] properties, Object[] propertyValues) throws Exception;

  List<T> pageQueryBy(
          String[] properties, Object[] propertyValues, String orderBy, int pageSize, int pageNo);

  //自定义获取列表页
  List<Map<String, Object>> findByStatementPostfix(
          String statementPostfix,
          String[] properties,
          Object[] propertyValues,
          String orderBy,
          int pageSize,
          int pageNo);

  List<Map<String, Object>> findByStatementPostfix(
          String statementPostfix,
          String[] properties,
          Object[] propertyValues,
          String orderBy);

  List<T> findTByStatementPostfix(String statementPostfix, String[] properties, Object[] propertyValues, String orderBy, String order);

  List<String> findByStatementPostfix(String statementPostfix, String[] properties, Object[] propertyValues);

  Integer countByStatementPostfix(
          String statementPostfix, String[] properties, Object[] propertyValues);

  void updateByStatementPostfix(
          String statementPostfix, String[] properties, Object[] propertyValues) throws Exception;

  int pageCount(String[] propertyNames, Object[] propertyValues, int pageSize);

  // mongo
  List<Map<String,Object>> findAll(String[] prokeys,Object[] provalues, String collectionName,String orderBy ,Integer order);
  List<Map<String,Object>> findByPage(String[] prokeys,Object[] provalues, String collectionName,int skip,int size,String orderBy ,Integer order) ;
  List<Map<String,Object>> findAllByArrOrderAndProjection(String[] prokeys,Object[] provalues,String[] includeFields, String collectionName,String[] orderBys ,Integer[] orders);
  Map findOne(String[] prokeys,Object[] provalues, String collectionName,String orderBy ,Integer order);
  Map findOneFilterFeilds(String[] prokeys, Object[] provalues,String[] includeFields, String collectionName);
  long countBy(String[] prokeys,Object[] provalues, String collectionName);
  void upsert(Map<String,Object> querys,Map<String,Object> updates, String collectionName) ;
}
