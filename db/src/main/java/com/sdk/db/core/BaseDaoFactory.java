package com.sdk.db.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BaseDaoFactory {

    private static BaseDaoFactory instance;

    private final String DB_NAME = "ximoon.db";

    private SQLiteDatabase sqLiteDatabase;

    private BaseDaoFactory(Context context){
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(context.getDatabasePath(DB_NAME),null);
    }

    public static BaseDaoFactory getInstance(Context context){
        if(instance == null){
            synchronized (BaseDaoFactory.class){
                if(instance == null){
                    instance = new BaseDaoFactory(context);
                }
            }
        }
        return instance;
    }

    public <T> BaseDao<T> getBaseDao(Class<T> entityClass){
        BaseDao baseDao = new BaseDao();
        baseDao.init(sqLiteDatabase,entityClass);
        return baseDao;
    }

}
