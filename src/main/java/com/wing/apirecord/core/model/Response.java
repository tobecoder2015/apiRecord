package com.wing.apirecord.core.model;

import com.wing.apirecord.utils.GzipUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/23.
 */
@Data
@Slf4j
public class Response {

    private Map<String, String> headers = new HashMap<>();
    private int code = 200;
    private String body = "";

    private byte[] bodyByte = null;

    public void addHeader(String key, String value) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        headers.put(key, value);
    }

    @Override
    public String toString() {
        return "code:" + this.getCode() + " header:" + this.getHeaders() + "    body:" + this.getBody();

    }

    public void addBytes(byte[] data) {
        if (bodyByte == null) {
            bodyByte = new byte[data.length];
            System.arraycopy(data, 0, bodyByte, 0, data.length);
        } else {
            byte[] data3 = new byte[bodyByte.length + data.length];
            System.arraycopy(bodyByte, 0, data3, 0, bodyByte.length);
            System.arraycopy(data, 0, data3, bodyByte.length, data.length);
            bodyByte = data3;
        }

    }

    public void generateBody() {
        try {
            String contentType = headers.get("Content-Type");
            String contentEncoding = headers.get("Content-Encoding");

            log.debug("其他类型 ：" + contentType);
            log.debug("编码类型 ：" + contentEncoding);

            if (contentEncoding != null && contentEncoding.equalsIgnoreCase("gzip")) {
                body = GzipUtil.unCompress(bodyByte);
                log.debug("gzip 操作成功：" + body);
            } else {
                body = new String(bodyByte);
                log.debug("非gzip 操作成功：" + body);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
