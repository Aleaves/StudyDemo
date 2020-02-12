package com.sdk.dbdemo.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.sdk.dbdemo.annotation.DbField;
import com.sdk.dbdemo.annotation.DbTable;

import java.lang.reflect.Field;
import java.util.HashMap;

public class BaseDao<T> implements IBaseDao {

    private SQLiteDatabase sqLiteDatabase;

    private String tableName;

    private Class<T> entityClass;

    private boolean isInit = false;

    private HashMap<String,Field> cacheMap;

    public boolean init(SQLiteDatabase sqLiteDatabase, Class<T> entityClass) {
        this.sqLiteDatabase = sqLiteDatabase;
        this.entityClass = entityClass;
        if (!isInit) {
            //根据传入的class进行数据库表的创建
            DbTable dt = entityClass.getAnnotation(DbTable.class);
            if (dt != null && !TextUtils.isEmpty(dt.value())) {
                tableName = dt.value();
            } else {
                tableName = entityClass.getName();
            }

            if (!sqLiteDatabase.isOpen()) {
                return false;
            }

            String createTableSql = getCreateTableSql();
            sqLiteDatabase.execSQL(createTableSql);
            cacheMap = new HashMap<>();
            initCacheMap();
            isInit = true;
        }
        return isInit;
    }

    private void initCacheMap(){
        String sql = "select * from "+ tableName + "limit 1,";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        String[] columnNames = cursor.getColumnNames();
        Field[] columnFields = entityClass.getDeclaredFields();
        for (Field field : columnFields) {
            field.setAccessible(true);
        }

        for (String columnName : columnNames) {
            Field columnField = null;
            for (Field field : columnFields) {
                String fieldName = null;
                if(field.getAnnotation(DbField.class) != null){
                    fieldName = field.getAnnotation(DbField.class).value();
                }else{
                    fieldName = field.getName();
                }
                if(columnName.equals(fieldName)){
                    columnField = field;
                    break;
                }
            }

        }
    }

    private String getCreateTableSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table if not exists ");
        stringBuilder.append(tableName + "(");
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            Class type = field.getType();
            DbField dbField = field.getAnnotation(DbField.class);
            if (dbField != null && !TextUtils.isEmpty(dbField.value())) {
                if (type == String.class) {
                    stringBuilder.append(dbField.value() + " TEXT,");
                } else if (type == Integer.class) {
                    stringBuilder.append(dbField.value() + " INTEGER,");
                } else if (type == Long.class) {
                    stringBuilder.append(dbField.value() + " BIGINT,");
                } else if (type == Double.class) {
                    stringBuilder.append(dbField.value() + " DOUBLE,");
                } else if (type == byte[].class) {
                    stringBuilder.append(dbField.value() + " BLOB,");
                } else {
                    continue;
                }
            } else {
                if (type == String.class) {
                    stringBuilder.append(field.getName() + " TEXT,");
                } else if (type == Integer.class) {
                    stringBuilder.append(field.getName() + " INTEGER,");
                } else if (type == Long.class) {
                    stringBuilder.append(field.getName() + " BIGINT,");
                } else if (type == Double.class) {
                    stringBuilder.append(field.getName() + " DOUBLE,");
                } else if (type == byte[].class) {
                    stringBuilder.append(field.getName() + " BLOB,");
                } else {
                    continue;
                }
            }
        }
        if (stringBuilder.charAt(stringBuilder.length() - 1) == ',') {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @Override
    public long insert(Object entity) {
        return 0;
    }


}
