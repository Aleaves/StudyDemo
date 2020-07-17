package com.sdk.studydemo.utils;
import com.sdk.studydemo.utils.xxtea.XXTEA;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * Created by liulb1 on 2019/4/15.
 * 加密工具类
 */

public class DanaEntryUtils {

    /**
     * 头文件授权
     * @param body
     * @return
     */
    public static String getAuthorizationString(String body) {
        String topic = DanaConfig.TOPIC;
        String token = DanaConfig.TOKEN;
        String version = DanaConfig.INTERFACE_VERSION;
        return Md5Encrypt.md5(token + topic + version + token + body);
    }

    /**
     * 压缩加密
     * @param rawMessage
     * @return
     * @throws IOException
     */
    public static String encodeData(final String rawMessage) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream(rawMessage.getBytes().length);
        GZIPOutputStream gos = new GZIPOutputStream(os);
        gos.write(rawMessage.getBytes());
        gos.close();
        byte[] compressed = os.toByteArray();
        os.close();
        String token = DanaConfig.TOKEN;
        return XXTEA.encryptToBase64String(new String(Base64Coder.encode(compressed)),token);
    }


    /**
     * 获取事件id
     * @param event
     * @param did
     * @return
     */
    public static String getEventId(String event, String did) {
        long timestamp = System.currentTimeMillis();
        String project = DanaConfig.TOPIC;
        StringBuilder sb = new StringBuilder();
        sb.append(event);
        sb.append(project);
        sb.append(did);
        sb.append(timestamp);
        return Md5Encrypt.md5(sb.toString());
    }



}
