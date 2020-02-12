package com.sdk.dbdemo.db;

public interface IBaseDao<T> {

    long insert(T entity);

}
