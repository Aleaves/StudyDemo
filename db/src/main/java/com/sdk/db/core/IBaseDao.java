package com.sdk.db.core;

public interface IBaseDao<T> {
    long insert(T entity);
}
