/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.vs.business.service;

import com.vs.business.bean.Question;
import com.vs.core.service.IGenericService;

import java.util.List;
import java.util.Map;

public interface IQuestionService extends IGenericService<Question, Integer> {


    List<Map<String,Object>> selectTpoInfo();

    List<Map<String,Object>> selectQuestionCodeNameByPid(int pId, String qCategoryName);

    List<Map<String,Object>> countByTopic(String[] strings, Object[] objects);

    void initAvgAndQpCountCache();

//    List<Map<String,Object>> getResultCount(int userId, String qIds, int clientType) throws Exception;
//
//    List<Map<String,Object>> getResultCountWithoutUser(String qIds, int clientType) throws Exception;
//
//    List<Map<String,Object>> getDoneCountBatch(String relationIds, int userId, Integer qCategory, int type, int clientType) throws ExecutionException;
//
//    List<Map<String,Object>> getDoesInfoBatch(String qIds, int userId, int clientType) throws ExecutionException;
//
//    List<Map<String,Object>> getDoesInfoBatchWithoutUser(String qIds, int clientType) throws ExecutionException;
//
//    int getQuestionCountByTopic(String topicId) throws ExecutionException;
//
//    int getTpoCount() throws ExecutionException;
//
////    List<LabelInfo> getTopicsByIdCache(int id) throws ExecutionException;
//
//    List getTopicsByparentLabelIdCache(String types) throws ExecutionException;
//
//    List<Map<String,Object>> getListenCounts(String qIds);
}
