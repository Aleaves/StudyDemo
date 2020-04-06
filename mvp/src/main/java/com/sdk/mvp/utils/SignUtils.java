package com.sdk.mvp.utils;


import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by liulb1 on 2019/3/11.
 */

public class SignUtils {

    //private final static String APP_KEY = "Iac1O16fUIH0QVAQqd5SQ947Q";
    private final static String APP_KEY = "cZZqE43VLVjUC7YxpnBpnwrZ1NMyfuW6";
    private final static String BEAT_KEY = "A2KgsktAgLpizWEytyGKAdPRyYs";

    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 获取签名
     * @param map
     * @return
     */
    public static String getSignParams(Map<String, String> map){
        //return getSafeSign(map,APP_KEY);
        return getSafeSign(map, "cZZqE43VLVjUC7YxpnBpnwrZ1NMyfuW6");
    }

    /**
     * 获取心跳签名
     * @param map
     * @return
     */
    public static String getBeatSignParams(Map<String, String> map){
        return getSafeSign(map,BEAT_KEY);
    }

    private static String getSafeSign(Map<String, String> map, String appkey) {
        List<String> query_string = new ArrayList<String>();
        try {
            for (String key : map.keySet()) {
                if (key.equals("sig") || key.equals("sign")) {
                    continue;
                }
                //query_string.add(key + "=" + URLEncoder.encode(map.get(key),"UTF-8"));
                query_string.add(key + "=" + map.get(key));
            }
            Collections.sort(query_string);
            String str = appkey + "" + query_string.toString().replace("[", "").replace("]", "").replace(", ", "&");
            // 创建具有指定算法名称的信息摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
            byte[] results = md.digest(str.getBytes());
            // 将得到的字节数组变成字符串返回\
            return byteArrayToHexString(results);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 轮换字节数组为十六进制字符串
     *
     * @param b 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 将一个字节转化成十六进制形式的字符串
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public final static String MD5(String pwd) {
        //用于加密的字符
        char md5String[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
            byte[] btInput = pwd.getBytes();

            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update(btInput);

            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();

            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {   //  i = 0
                byte byte0 = md[i];  //95
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
                str[k++] = md5String[byte0 & 0xf];   //   F
            }

            //返回经过加密后的字符串
            return new String(str);

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * TODO  生成获得resourcesid参数签名
     *
     * @param args PayArgs变成map形式
     * @return String 加密前的支付参数
     * @detial 将post参数按照参数名称正序排序；
     * 将参数名称和参数的值，以”=”链接；
     * 按照排序后的顺序，将各个”key=value”以”&”链接，成一个字符串；
     * 在最终生成的字符串开头，添加appkey，生成新的字符串；
     * 将最终的字符串进行md5编码，即得到签名参数sign；
     */
    public static String getResouceidMessage(Map<String, String> args) {
        Set<Map.Entry<String, String>> entries = null;
        Iterator<Map.Entry<String, String>> iterator = null;
        List<String> list = null;
        StringBuffer sb = new StringBuffer("mcx+&C%E5ecPo3LG");
        if (args == null || args.size() == 0)
            return null;
        entries = args.entrySet();
        iterator = entries.iterator();

        list = new ArrayList<String>();


        while (iterator.hasNext()) {
            list.add(iterator.next().getKey());
        }
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {

            sb.append(list.get(i) + "=" + args.get(list.get(i)) + "&");
        }
        if (sb.lastIndexOf("&") == sb.length() - 1)
            sb.deleteCharAt(sb.length() - 1);
        String temp = sb.toString();
        return temp;
    }


}
