package com.vs.core.persistence.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.vs.core.persistence.IGenericDao;
import com.vs.tools.MongoTools;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

/**
 * This class has to use some deprecated classes.
 */
@SuppressWarnings("unchecked")
public class MybatisGenericDao<T, PkT extends Serializable> implements IGenericDao<T, PkT> {

  public static final String POSTFIX_SELECTBYID = ".selectById";
  public static final String POSTFIX_SELECTBYIDS = ".selectByIds";
  public static final String POSTFIX_SELECTBYMAP = ".selectByMap";
  public static final String POSTFIX_PKSELECTMAP = ".pkSelectByMap";
  public static final String POSTFIX_COUNT = ".count";
  public static final String POSTFIX_COUNTLIKEBYMAP = ".countLikeByMap";
  public static final String POSTFIX_INSERT = ".insert";
  public static final String POSTFIX_DELETEBYID = ".deleteById";
  public static final String POSTFIX_DELETEBYIDS = ".deleteByIds";
  public static final String POSTFIX_DELETEBYIDSMAP = ".deleteByIdsMap";
  public static final String POSTFIX_DELETEBYMAP = ".deleteByMap";
  public static final String POSTFIX_UPDATE = ".update";
  public static final String POSTFIX_UPDATEBYMAP = ".updateByMap";
  public static final String POSTFIX_UPDATEBYIDSMAP = ".updateByIdsMap";

  protected Class<T> clazz;

  protected String clazzName;

  @Autowired
  protected SqlSessionFactory sessionFactory;

  @Autowired
  private SqlSession masterSqlMapClientTemplate;

  @Autowired
  private MongoTemplate mongoTemplate;

  /**
   * Constructs a new mybatis DAO.
   */
  public MybatisGenericDao() {
    // 通过范型反射，取得在子类中定义的class.
    clazz =
            (Class<T>)
                    ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    clazzName = clazz.getSimpleName();
    try {
      T t = clazz.getConstructor().newInstance();
    } catch (NoSuchMethodException
            | InvocationTargetException
            | InstantiationException
            | IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Integer count(String propertyName, Object propertyValue) {
    return count(new String[]{propertyName}, new Object[]{propertyValue});
  }

  @Override
  public Integer count(String[] propertyNames, Object[] propertyValues) {

    Map<String, Object> map = new HashMap<>();
    for (int i = 0; i < propertyNames.length; i++) {
      map.put(propertyNames[i], propertyValues[i]);
    }
    return (Integer) masterSqlMapClientTemplate.selectOne(clazz.getName() + POSTFIX_COUNT, map);
  }

  @Override
  public T findById(PkT id) {
    if (id == null) {
      return null;
    }

    return (T) masterSqlMapClientTemplate.selectOne(clazz.getName() + POSTFIX_SELECTBYID, id);
  }

  @Override
  public List<T> findByIds(List<PkT> ids) {
    if (ids == null || ids.size() == 0) {
      return Collections.emptyList();
    }

    return (List<T>)
            masterSqlMapClientTemplate.selectList(clazz.getName() + POSTFIX_SELECTBYIDS, ids);
  }

  @Override
  public List<T> findByMap(String[] properties, Object[] propertyValues, String orderBy) {

    //  if (properties.length == 0) {
    //   return Collections.emptyList();
    //  }

    Map<String, Object> map = new HashMap<String, Object>();
    for (int i = 0; i < properties.length; i++) {
      map.put(properties[i], propertyValues[i]);
    }
    if (orderBy != null) {
      map.put("orderBy", orderBy);
    }
    return (List<T>)
            masterSqlMapClientTemplate.selectList(clazz.getName() + POSTFIX_SELECTBYMAP, map);
  }

  @Override
  public List findIdsByMap(String[] properties, Object[] propertyValues, String orderBy) {

    Map<String, Object> map = new HashMap<String, Object>();
    for (int i = 0; i < properties.length; i++) {
      map.put(properties[i], propertyValues[i]);
    }
    if (orderBy != null) {
      map.put("orderBy", orderBy);
    }
    return masterSqlMapClientTemplate.selectList(clazz.getName() + POSTFIX_PKSELECTMAP, map);
  }

  @Override
  public List<T> findAll() {
    return (List<T>) masterSqlMapClientTemplate.selectList(clazz.getName() + POSTFIX_SELECTBYMAP, null);
  }

  @Override
  public List<T> pageQueryBy(
          String[] properties, Object[] propertyValues, String orderBy, int pageSize, int pageNo) {

    //  if (properties.length == 0) {
    //   return Collections.emptyList();
    //  }

    Map<String, Object> map = new HashMap<String, Object>();
    for (int i = 0; i < properties.length; i++) {
      map.put(properties[i], propertyValues[i]);
    }
    if (orderBy != null) {
      map.put("orderBy", orderBy);
    }
    map.put("limit", true);
    map.put("start", (pageNo - 1) * pageSize); // limit 操作
    map.put("end", pageSize);
    return (List<T>)
            masterSqlMapClientTemplate.selectList(clazz.getName() + POSTFIX_SELECTBYMAP, map);
  }

  @Override
  public Serializable insert(T o) throws Exception {

    return (Serializable) masterSqlMapClientTemplate.insert(clazz.getName() + POSTFIX_INSERT, o);
  }

  @Override
  public List<Serializable> insert(final List<T> o) throws Exception {
    List<Serializable> ids = new ArrayList<>();
//    masterSqlMapClientTemplate.execute(
//        new SqlMapClientCallback() {
//          @Override
//          public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
//            executor.startBatch();
//            try {
//              for (T t : o) {
//                insert(t);
//              }
//            } catch (Exception ex) {
//              ex.printStackTrace();
//            }
//            executor.executeBatch();
//            return null;
//          }
//        });
    return ids;
  }

  @Override
  public T update(T o) throws Exception {
    masterSqlMapClientTemplate.update(clazz.getName() + POSTFIX_UPDATE, o);
    return o;
  }

  @Override
  public int update(PkT id, String[] properties, Object[] propertyValues) throws Exception {
    if (properties.length == 0) {
      return 0;
    }
    Map<String, Object> map = new HashMap<String, Object>();
    for (int i = 0; i < properties.length; i++) {
      map.put(properties[i], propertyValues[i]);
    }
    map.put("id", id);
    return masterSqlMapClientTemplate.update(clazz.getName() + POSTFIX_UPDATEBYMAP, map);
  }

  @Override
  public int updateByIdsMap(List<PkT> ids, String[] properties, Object[] propertyValues)
          throws Exception {
    if (properties.length == 0) {
      return 0;
    }
    Map<String, Object> map = new HashMap<String, Object>();
    for (int i = 0; i < properties.length; i++) {
      map.put(properties[i], propertyValues[i]);
    }
    map.put("ids", ids);
    return masterSqlMapClientTemplate.update(clazz.getName() + POSTFIX_UPDATEBYIDSMAP, map);
  }

  @Override
  public void deleteById(PkT id) throws Exception {
    if (id == null) {
      return;
    }
    masterSqlMapClientTemplate.delete(clazz.getName() + POSTFIX_DELETEBYID, id);
  }

  @Override
  public void deleteByIds(List<PkT> ids) throws Exception {
    if (ids == null || ids.size() == 0) {
      return;
    }
    masterSqlMapClientTemplate.delete(clazz.getName() + POSTFIX_DELETEBYIDS, ids);
  }

  @Override
  public void deleteByIdsMap(List<PkT> ids, String[] properties, Object[] propertyValues)
          throws Exception {

    if (properties.length == 0) {
      return;
    }

    Map<String, Object> map = new HashMap<String, Object>();
    for (int i = 0; i < properties.length; i++) {
      map.put(properties[i], propertyValues[i]);
    }
    map.put("ids", ids);
    masterSqlMapClientTemplate.delete(clazz.getName() + POSTFIX_DELETEBYIDSMAP, map);
  }

  @Override
  public int deleteByMap(String[] properties, Object[] propertyValues) throws Exception {
    if (properties.length == 0) {
      return 0;
    }

    Map<String, Object> map = new HashMap<String, Object>();
    for (int i = 0; i < properties.length; i++) {
      map.put(properties[i], propertyValues[i]);
    }
    return masterSqlMapClientTemplate.delete(clazz.getName() + POSTFIX_DELETEBYMAP, map);
  }

  //--------------------------------------自定义SQLMAP操作-----------------------------------//

  @Override
  public List<Map<String, Object>> findByStatementPostfix(
          String statementPostfix,
          String[] properties,
          Object[] propertyValues,
          String orderBy,
          int pageSize,
          int pageNo) {
    Map<String, Object> map = new HashMap<String, Object>();
    for (int i = 0; i < properties.length; i++) {
      map.put(properties[i], propertyValues[i]);
    }
    if (orderBy != null) {
      map.put("orderBy", orderBy);
    }
    map.put("limit", true);
    map.put("start", (pageNo - 1) * pageSize); // limit 操作
    map.put("end", pageSize);
    List<Map<String, Object>> list =
            masterSqlMapClientTemplate.selectList(clazz.getName() + statementPostfix, map);
    return list;
  }

  @Override
  public List<Map<String, Object>> findByStatementPostfix(
          String statementPostfix, String[] properties, Object[] propertyValues, String orderBy) {
    Map<String, Object> map = new HashMap<String, Object>();
    for (int i = 0; i < properties.length; i++) {
      map.put(properties[i], propertyValues[i]);
    }
    if (orderBy != null) {
      map.put("orderBy", orderBy);
    }
    List<Map<String, Object>> list =
            masterSqlMapClientTemplate.selectList(clazz.getName() + statementPostfix, map);
    return list;
  }

  @Override
  public List<String> findByStatementPostfix(String statementPostfix, String[] properties, Object[] propertyValues) {
    Map<String, Object> map = new HashMap<String, Object>();
    for (int i = 0; i < properties.length; i++) {
      map.put(properties[i], propertyValues[i]);
    }
    List<String> list =
            masterSqlMapClientTemplate.selectList(clazz.getName() + statementPostfix, map);
    return list;
  }

  @Override
  public void updateByStatementPostfix(
          String statementPostfix, String[] properties, Object[] propertyValues) throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    for (int i = 0; i < properties.length; i++) {
      map.put(properties[i], propertyValues[i]);
    }
    masterSqlMapClientTemplate.update(clazz.getName() + statementPostfix, map);
  }

  @Override
  public void deleteByStatementPostfix(
          String statementPostfix, String[] properties, Object[] propertyValues) {
    Map<String, Object> map = new HashMap<String, Object>();
    for (int i = 0; i < properties.length; i++) {
      map.put(properties[i], propertyValues[i]);
    }
    masterSqlMapClientTemplate.delete(clazz.getName() + statementPostfix, map);
  }

  @Override
  public Integer countByStatementPostfix(
          String statementPostfix, String[] properties, Object[] propertyValues) {
    Map<String, Object> map = new HashMap<String, Object>();
    for (int i = 0; i < properties.length; i++) {
      map.put(properties[i], propertyValues[i]);
    }
    return (Integer)
            masterSqlMapClientTemplate.selectOne(clazz.getName() + statementPostfix, map);
  }

  @Override
  public void insertByStatementPostfix(
          String statementPostfix, String[] properties, Object[] propertyValues) {
    Map<String, Object> map = new HashMap<String, Object>();
    for (int i = 0; i < properties.length; i++) {
      map.put(properties[i], propertyValues[i]);
    }
    masterSqlMapClientTemplate.insert(clazz.getName() + statementPostfix, map);
  }

  @Override
  public List<T> findTByStatementPostfix(String statementPostfix,
                                         String[] properties, Object[] propertyValues, String orderBy,
                                         String order) {
    Map<String, Object> map = new HashMap<String, Object>();
    for (int i = 0; i < properties.length; i++) {
      map.put(properties[i], propertyValues[i]);
    }
    if (null != orderBy) {
      map.put("orderBy", orderBy);
      map.put("order", order);
    }
    return (List<T>) masterSqlMapClientTemplate.selectOne(clazz.getName() + statementPostfix, map);
  }

  //mongo
  @Override
  public long countBy(String[] prokeys, Object[] provalues, String collectionName) {
    org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query();
    Criteria criteria = new Criteria();
    Criteria[] criterias = new Criteria[prokeys.length];
    for (int i = 0; i < prokeys.length; i++) {
      criterias[i] = Criteria.where(prokeys[i].toString()).is(provalues[i]);
    }
    criteria.andOperator(criterias);
    query.addCriteria(criteria);
    long result = mongoTemplate.count(query, collectionName);
    return result;
  }

  @Override
  public Map findOne(String[] prokeys, Object[] provalues, String collectionName, String orderBy, Integer order) {
    MongoCollection coll = MongoTools.getCollection(collectionName);
    Map m = new HashMap<>();
    Document doc = new Document();
    for (int i = 0; i < prokeys.length; i++) {
      if (i == 0) {
        doc.put(prokeys[i], provalues[i]);
      } else {
        doc.append(prokeys[i], provalues[i]);
      }
    }
    Document sort = new Document();
    if (orderBy != null) {
      sort = new Document(orderBy, order);
    }
    FindIterable iter = coll.find(doc).skip(0).limit(1).sort(sort);
    MongoCursor cursor = iter.iterator();
    if (cursor.hasNext()) {
      m = (Map) cursor.next();
    }
    return m;
  }

  @Override
  public Map findOneFilterFeilds(String[] prokeys, Object[] provalues,String[] includeFields, String collectionName) {
    MongoCollection coll = MongoTools.getCollection(collectionName);
    Map m = new HashMap<>();
    Document doc = new Document();
    Document filter = new Document();
    for (int i = 0; i < prokeys.length; i++) {
      if (i == 0) {
        doc.put(prokeys[i], provalues[i]);
      } else {
        doc.append(prokeys[i], provalues[i]);
      }
    }
    for(int i=0;i<includeFields.length;i++){
      filter.append(includeFields[i],true);
    }
    FindIterable iter = coll.find(doc).projection(filter).skip(0).limit(1);
    MongoCursor cursor = iter.iterator();
    if (cursor.hasNext()) {
      m = (Map) cursor.next();
    }
    return m;
  }
  @Override
  public T findOne(Map<String,Object> querys, String collectionName) {
    org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query();
    for(Map.Entry e:querys.entrySet()){
      query.addCriteria(Criteria.where(e.getKey().toString()).is(e.getValue()));
    }
    return mongoTemplate.findOne(query,clazz,collectionName);
  }

  @Override
  public List<Map<String,Object>> findByPage(String[] prokeys,Object[] provalues, String collectionName,int skip,int size,String orderBy ,Integer order) {
    MongoCollection coll = MongoTools.getCollection(collectionName);
    List<Map<String, Object>> list = new ArrayList<>();
    Document doc = new Document();
    for (int i = 0; i < prokeys.length; i++) {
      doc.append(prokeys[i], provalues[i]);
    }
    FindIterable iter = coll.find(doc).skip(skip).limit(size).sort(new Document(orderBy, order));
    MongoCursor cursor = iter.iterator();
    while (cursor.hasNext()) {
      Map m = (Map) cursor.next();
      list.add(m);
    }
    return list;
  }

  @Override
  public List<Map<String, Object>> findAll(String[] prokeys, Object[] provalues, String collectionName, String orderBy, Integer order) {
    MongoCollection coll = MongoTools.getCollection(collectionName);
    List<Map<String, Object>> list = new ArrayList<>();
    Document doc = new Document();
    for (int i = 0; i < prokeys.length; i++) {
      doc.append(prokeys[i], provalues[i]);
    }
    FindIterable iter = coll.find(doc).sort(new Document(orderBy, order));
    MongoCursor cursor = iter.iterator();
    while (cursor.hasNext()) {
      Map m = (Map) cursor.next();
      list.add(m);
    }
    return list;
  }

  @Override
  public List<Map<String, Object>> findAllByArrOrderAndProjection(String[] prokeys, Object[] provalues,String[] includeFields, String collectionName, String[] orderBys, Integer[] orders) {
    MongoCollection coll = MongoTools.getCollection(collectionName);
    List<Map<String, Object>> list = new ArrayList<>();
    Document doc = new Document();
    Document filter = new Document();
    Document order = new Document();
    for (int i = 0; i < prokeys.length; i++) {
      doc.append(prokeys[i], provalues[i]);
    }
    for (int i = 0; i < orderBys.length; i++) {
      order.append(orderBys[i], orders[i]);
    }
    for(int i=0;i<includeFields.length;i++){
      filter.append(includeFields[i],true);
    }
    FindIterable iter = coll.find(doc).projection(filter).sort(order);
    MongoCursor cursor = iter.iterator();
    while (cursor.hasNext()) {
      Map m = (Map) cursor.next();
      list.add(m);
    }
    return list;
  }

  @Override
  public void upsert(Map<String, Object> querys, Map<String, Object> updates, String collectionName) {
    org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query();
    for (Map.Entry e : querys.entrySet()) {
      query.addCriteria(Criteria.where(e.getKey().toString()).is(e.getValue()));
    }
    Update update = new Update();
    for (Map.Entry e : updates.entrySet()) {
      update.set(e.getKey().toString(), e.getValue());
    }
    // 新版本entityClass必传
    mongoTemplate.upsert(query, update, Map.class, collectionName);
  }

  @Override
  public void updateOne(Integer id, String[] prokeys, Object[] provalues, String collectionName) {
    org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query();
    Criteria criteria = new Criteria("Q_ID");
    criteria.is(id);
    Update update = new Update();
    for (int i = 0; i < prokeys.length; i++) {
      update.set(prokeys[i].toString(), provalues[i]);
    }
    query.addCriteria(criteria);
    mongoTemplate.updateFirst(query, update, Map.class, collectionName);
  }

  @Override
  public void updateOne(String key, Integer id,String[] prokeys,Object[] provalues, String collectionName) {
    org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query();
    Criteria criteria = new Criteria(key);
    criteria.is(id);
    Update update = new Update();
    for (int i = 0; i < prokeys.length; i++) {
      update.set(prokeys[i].toString(), provalues[i]);
    }
    query.addCriteria(criteria);
    mongoTemplate.updateFirst(query, update, Map.class, collectionName);
  }
  @Override
  public void insert(Map<String, Object> map, String collectionName) throws IllegalAccessException {
    mongoTemplate.insert(map, collectionName);
  }
  @Override
  public void insertMongo(T t) {
    mongoTemplate.insert(t);
  }
  @Override
  public void removeOne(Integer id, String collectionName) {
    org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query();
    Criteria criteria = new Criteria("Q_ID");
    criteria.is(id);
    query.addCriteria(criteria);
    mongoTemplate.remove(query,clazz,collectionName);
  }
}
