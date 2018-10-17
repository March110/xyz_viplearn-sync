package com.vs.core.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 泛化DAO基类.
 *
 * @param <T>   DAO访问的entity
 * @param <PkT> DAO访问的entity的主键类
 */
public interface IGenericDao<T, PkT extends Serializable> {

	Integer count(final String propertyName, final Object propertyValue);

	Integer count(final String[] propertyNames, final Object[] propertyValues);

	Integer countByStatementPostfix(
          String statementPostfix, String[] properties, Object[] propertyValues);

	T findById(PkT id);

	List<T> findByIds(List<PkT> ids);

	List<T> findByMap(String[] properties, Object[] propertyValues, String orderBy);

	List<PkT> findIdsByMap(String[] properties, Object[] propertyValues, String orderBy);

	List<T> findAll();

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

	void updateByStatementPostfix(
          String statementPostfix, String[] properties, Object[] propertyValues) throws Exception;

	void deleteByStatementPostfix(
          String statementPostfix, String[] properties, Object[] propertyValues);

	void insertByStatementPostfix(
          String statementPostfix, String[] properties, Object[] propertyValues);

	Serializable insert(T o) throws Exception;

	List<Serializable> insert(final List<T> o) throws Exception;

	List<Map<String, Object>> findByStatementPostfix(
          String statementPostfix,
          String[] properties,
          Object[] propertyValues,
          String orderBy,
          int pageSize,
          int pageNo);

	List<Map<String, Object>> findByStatementPostfix(
          String statementPostfix, String[] properties, Object[] propertyValues, String orderBy);

	List<T> findTByStatementPostfix(String statementPostfix,
                                         String[] properties, Object[] propertyValues, String orderBy,
                                         String order);

	List<String> findByStatementPostfix(String statementPostfix, String[] properties, Object[] propertyValues);

	// mongo
	List<Map<String,Object>> findAll(String[] prokeys,Object[] provalues, String collectionName,String orderBy ,Integer order);
	List<Map<String,Object>> findAllByArrOrderAndProjection(String[] prokeys,Object[] provalues,String[] includeFields, String collectionName,String[] orderBys ,Integer[] orders);
	//  查找一个
	Map findOne(String[] prokeys,Object[] provalues, String collectionName,String orderBy ,Integer order);
	//限制字段
	Map findOneFilterFeilds(String[] prokeys, Object[] provalues,String[] includeFields, String collectionName);
	//分页查找
	List<Map<String,Object>> findByPage(String[] prokeys,Object[] provalues, String collectionName,int skip,int size,String orderBy ,Integer order) ;
	long countBy(String[] prokeys,Object[] provalues, String collectionName) ;
	void upsert(Map<String,Object> querys,Map<String,Object> updates, String collectionName) ;
	void updateOne(Integer id,String[] prokeys,Object[] provalues,String collectionName);
	void updateOne(String key, Integer id,String[] prokeys,Object[] provalues,String collectionName);
	void insert(Map<String, Object> map,String collectionName) throws IllegalAccessException;
	void removeOne(Integer id,String collectionName);
	T findOne(Map<String,Object> querys, String collectionName);
	void insertMongo(T t);
}
