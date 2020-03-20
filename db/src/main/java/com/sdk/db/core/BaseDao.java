package com.sdk.db.core;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.sdk.db.annotation.DbField;
import com.sdk.db.annotation.DbTable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BaseDao<T> implements IBaseDao<T> {

    private SQLiteDatabase sqLiteDatabase;
    private String tableName;
    private Class<T> entityClass;
    private HashMap<String, Field> cacheMap;

    protected void init(SQLiteDatabase sqLiteDatabase, Class<T> entityClass) {
        this.sqLiteDatabase = sqLiteDatabase;
        this.entityClass = entityClass;
        //获取表名  如果有注解且不为空 则获取注解值   否则取类名
        DbTable dbTable = entityClass.getAnnotation(DbTable.class);
        if (null != dbTable && !TextUtils.isEmpty(dbTable.value())) {
            tableName = dbTable.value();
        } else {
            tableName = entityClass.getSimpleName();
        }
        //建表sql语句
        String createTableSql = getCreateTableSql();
        sqLiteDatabase.execSQL(createTableSql);
        cacheMap = new HashMap<>();
        initCacheMap();
    }

    /**
     *
     */
    private void initCacheMap() {
        String sql = "select * from " + tableName;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        //获取所有表列名
        String[] columnNames = cursor.getColumnNames();
        //获取所有成员变量
        Field[] columnFields = entityClass.getDeclaredFields();
        //将字段访问权限打开
        for (Field field : columnFields) {
            field.setAccessible(true);
        }
        for (String columnName : columnNames) {
            Field columnField = null;
            for (Field field : columnFields) {
                String fieldName = null;
                DbField dbField = field.getAnnotation(DbField.class);
                if (null != dbField && !TextUtils.isEmpty(dbField.value())) {
                    fieldName = dbField.value();
                } else {
                    fieldName = field.getName();
                }
                if (TextUtils.equals(columnName, fieldName)) {
                    columnField = field;
                    break;
                }
            }
            if (null != columnField) {
                cacheMap.put(columnName, columnField);
            }
        }
    }


    /**
     * 拼接创建表格的sql语句
     *
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
            boolean isUnique = false;
            if (null != dbField && !TextUtils.isEmpty(dbField.value())) {
                typeValue = dbField.value();
                isUnique = dbField.unique();
            } else {
                typeValue = field.getName();
            }

            if (type == String.class) {
                stringBuilder.append(typeValue + " TEXT" + (isUnique ? " UNIQUE," : ","));
            } else if (type == Integer.class || type == int.class) {
                stringBuilder.append(typeValue + " INTEGER" + (isUnique ? " UNIQUE," : ","));
            } else if (type == Long.class) {
                stringBuilder.append(typeValue + " BIGINT" + (isUnique ? " UNIQUE," : ","));
            } else if (type == Double.class || type == double.class) {
                stringBuilder.append(typeValue + " DOUBLE" + (isUnique ? " UNIQUE," : ","));
            } else if (type == byte[].class) {
                stringBuilder.append(typeValue + " BLOB" + (isUnique ? " UNIQUE," : ","));
            } else {
                //不支持该类型
                continue;
            }
        }
        //删除最后一个符号
        if (stringBuilder.charAt(stringBuilder.length() - 1) == ',') {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @Override
    public long insert(T entity) {
        Map<String, String> map = getValues(entity);
        ContentValues contentValues = getContentValues(map);
        return sqLiteDatabase.insert(tableName, null, contentValues);
    }

    @Override
    public long update(T entity, T where) {
        Map<String,String> map = getValues(entity);
        ContentValues values = getContentValues(map);
        Map<String,String> whereMap = getValues(where);
        Condition condition = new Condition(whereMap);
        return sqLiteDatabase.update(tableName,values,condition.whereCause,condition.whereArgs);
    }

    @Override
    public int delete(T entity) {
        Map<String, String> map = getValues(entity);
        Condition condition = new Condition(map);
        return sqLiteDatabase.delete(tableName, condition.whereCause, condition.whereArgs);
    }

    @Override
    public List<T> query(T where) {
        return query(where, null, null, null);
    }

    @Override
    public List<T> query(T where, String orderBy, Integer startIndex, Integer limit) {
        Map<String, String> map = getValues(where);
        String limitString = null;
        if (startIndex != null && limit != null) {
            limitString = startIndex + "," + limit;
        }
        Condition condition = new Condition(map);
        Cursor cursor = sqLiteDatabase.query(tableName, null, condition.whereCause, condition.whereArgs, null, null, orderBy, limitString);
        //解析游标
        List<T> result = getResult(cursor, where);
        return result;
    }

    /**
     * 解析游标
     *
     * @param cursor
     * @param where
     * @return
     */
    private List<T> getResult(Cursor cursor, T where) {
        ArrayList list = new ArrayList();
        Object item = null;
        while (cursor.moveToNext()) {
            try {
                item = where.getClass().newInstance();
                Iterator iterator = cacheMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    String columnName = (String) entry.getKey();
                    Integer columnIndex = cursor.getColumnIndex(columnName);
                    Field field = (Field) entry.getValue();
                    Class type = field.getType();
                    if (columnIndex != -1) {
                        if (type == String.class) {
                            field.set(item, cursor.getString(columnIndex));
                        } else if (type == double.class || type == Double.class) {
                            field.set(item, cursor.getDouble(columnIndex));
                        } else if (type == int.class || type == Integer.class) {
                            field.set(item, cursor.getInt(columnIndex));
                        } else if (type == long.class || type == Long.class) {
                            field.set(item, cursor.getInt(columnIndex));
                        } else if (type == byte[].class) {
                            field.set(item, cursor.getBlob(columnIndex));
                        } else {
                            continue;
                        }
                    }
                }
                list.add(item);
            } catch (Exception e) {

            }
        }
        cursor.close();
        return list;
    }

    /**
     * map转ContentValues
     *
     * @param map
     * @return
     */
    private ContentValues getContentValues(Map<String, String> map) {
        ContentValues contentValues = new ContentValues();
        for (String key : map.keySet()) {
            String value = map.get(key);
            if (null != value) {
                contentValues.put(key, value);
            }
        }
        return contentValues;
    }

    /**
     * 将对象的值 和key对应起来 存到map中
     *
     * @param entity
     * @return
     */
    private Map<String, String> getValues(T entity) {
        HashMap<String, String> map = new HashMap<>();
        Iterator<Field> fieldIterator = cacheMap.values().iterator();
        while (fieldIterator.hasNext()) {
            Field field = fieldIterator.next();
            field.setAccessible(true);
            try {
                Object object = field.get(entity);
                if (null == object) {
                    continue;
                }
                String value = object.toString();
                String key = null;
                DbField dbField = field.getAnnotation(DbField.class);
                if (null != dbField && !TextUtils.isEmpty(dbField.value())) {
                    key = dbField.value();
                } else {
                    key = field.getName();
                }
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    map.put(key, value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    private class Condition {
        private String whereCause;
        private String[] whereArgs;

        public Condition(Map<String, String> whereMap) {
            ArrayList list = new ArrayList();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("1=1");
            for (String key : whereMap.keySet()) {
                String value = whereMap.get(key);
                if (!TextUtils.isEmpty(value)) {
                    stringBuilder.append(" and " + key + " =?");
                    list.add(value);
                }
            }
            this.whereCause = stringBuilder.toString();
            this.whereArgs = (String[]) list.toArray(new String[list.size()]);
        }
    }

}
