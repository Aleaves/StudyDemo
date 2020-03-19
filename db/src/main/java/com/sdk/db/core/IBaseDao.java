package com.sdk.db.core;

import java.util.List;

public interface IBaseDao<T> {

    long insert(T entity);

    long update(T tentity,T where);

    int delete(T entity);

    List<T> query(T where);

    List<T> query(T where,String orderBy,Integer startIndex,Integer limit);

}
