package com.sdk.dbdemo.db;

import android.database.sqlite.SQLiteDatabase;

public class BaseDaoFactory {

    private static final BaseDaoFactory instance = new BaseDaoFactory();


    public static BaseDaoFactory getInstance(){
        return instance;
    }

    private SQLiteDatabase sqLiteDatabase;
    private String sqlitePath;

    private BaseDaoFactory(){

        sqlitePath = "data/data/com.sdk.dbdemo/ne.db";
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(sqlitePath,null);

    }

    public <T> BaseDao<T> getBaseDao(Class<T> entityClass){

        BaseDao baseDao = null;

        try {
            baseDao = BaseDao.class.newInstance();
            baseDao.init(sqLiteDatabase,entityClass);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return baseDao;

    }

}
