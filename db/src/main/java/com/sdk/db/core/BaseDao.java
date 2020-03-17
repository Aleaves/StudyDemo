package com.sdk.db.core;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import android.widget.FrameLayout;

import com.sdk.db.annotation.DbField;
import com.sdk.db.annotation.DbTable;

import java.lang.reflect.Field;

public class BaseDao<T> implements IBaseDao<T> {

    private SQLiteDatabase sqLiteDatabase;
    private String tableName;
    private Class<T> entityClass;

    protected  boolean init(SQLiteDatabase sqLiteDatabase,Class<T> entityClass){
        this.sqLiteDatabase = sqLiteDatabase;
        this.entityClass = entityClass;

        //获取表名  如果有注解且不为空 则获取注解值   否则取类名
        DbTable dbTable = entityClass.getAnnotation(DbTable.class);
        if(null != dbTable && !TextUtils.isEmpty(dbTable.value())){
            tableName = dbTable.value();
        }else{
            tableName = entityClass.getSimpleName();
        }

        //建表sql语句
        String createTableSql = getCreateTableSql();
        Log.i("==========",createTableSql);

        return true;
    }

    /**
     * 拼接创建表哥的sql语句
     * @return
     */
    private String getCreateTableSql() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists ");
        stringBuilder.append(tableName + "(");

        //通过反射获取所有成员变量
        Field[] fields = entityClass.getDeclaredFields();
        //遍历所有成员变量
        for (Field field : fields) {
            Class type = field.getType();
            String typeValue;
            DbField dbField = field.getAnnotation(DbField.class);
            if(null != dbField && !TextUtils.isEmpty(dbField.value())){
                typeValue = dbField.value();
            }else{
                typeValue = field.getName();
            }
            if(type == String.class){
                stringBuilder.append(typeValue + " TEXT,");
            }else if(type == Integer.class || type == int.class){
                stringBuilder.append(typeValue + " INTEGER,");
            }else if(type == Long.class){
                stringBuilder.append(typeValue + " BIGINT,");
            }else if(type == Double.class){
                stringBuilder.append(typeValue + " DOUBLE,");
            }else if(type == byte[].class){
                stringBuilder.append(typeValue + " BLOB,");
            }else{
                //不支持该类型
                continue;
            }
        }
        //删除最后一个符号
        if(stringBuilder.charAt(stringBuilder.length() -1) == ','){
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
        stringBuilder.append(")");

        return stringBuilder.toString();
    }

    @Override
    public long insert(T entity) {
        return 0;
    }

}
