package com.meituan.hb.apirecord.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Slf4j
public class GzipUtil {

    /**
     * gzip加密
     * @param data
     * @return
     * @throws Exception
     * byte[]
     */
    public static byte[] gzip(byte[] data) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(data);
        gzip.finish();
        gzip.close();
        byte[] ret = bos.toByteArray();
        bos.close();
        return ret;
    }

    /**
     *  gzip解密
     * @param data
     * @return
     * @throws Exception
     * byte[]
     */
    public static byte[] ungzip(byte[] data) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        GZIPInputStream gzip = new GZIPInputStream(bis);
        byte[] buf = new byte[1024];
        int num = -1;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((num = gzip.read(buf, 0, buf.length)) != -1) {
            bos.write(buf, 0, num);
        }
        gzip.close();
        bis.close();
        byte[] ret = bos.toByteArray();
        bos.flush();
        bos.close();
        return ret;
    }


    /**
     * gizp数据解压
     * @param bytes
     * @return
     * @throws IOException
     * String
     */
    public static String uncompress(byte[] bytes) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer))>= 0) {
            out.write(buffer, 0, n);
        }
        // toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)
        return out.toString();
    }



    /**
     * gizp解压
     * @param buf
     * @return
     * @throws IOException
     * byte[]
     */
    public static byte[] unGzip(byte[] buf) throws IOException {
        GZIPInputStream gzi = null;
        ByteArrayOutputStream bos = null;
        try {
            gzi = new GZIPInputStream(new ByteArrayInputStream(buf));
            bos = new ByteArrayOutputStream(buf.length);
            int count = 0;
            byte[] tmp = new byte[2048];
            while ((count = gzi.read(tmp)) != -1) {
                bos.write(tmp, 0, count);
            }
            buf = bos.toByteArray();
        } finally {
            if (bos != null) {
                bos.flush();
                bos.close();
            }
            if (gzi != null)
                gzi.close();
        }
        return buf;
    }


    public static byte[] compress(String str)  {
        ByteArrayOutputStream out =null;
        GZIPOutputStream gzip=null;
        try{
            if (str == null || str.length() == 0) {
                return null;
            }
            out = new ByteArrayOutputStream();
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes("utf-8"));
            gzip.finish();
            return out.toByteArray();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(gzip!=null){
                    gzip.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static String unCompress(byte []by) {
        ByteArrayOutputStream out = null;
        GZIPInputStream gunzip = null;
        try {
            if (by == null || by.length == 0) {
                return "";
            }
            out = new ByteArrayOutputStream();
            gunzip = new GZIPInputStream(new ByteArrayInputStream(by));
            byte[] buffer = new byte[1024];
            int n;
            while ((n = gunzip.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
            out.flush();

        } catch (Exception e) {
            log.error(e.getMessage());
            return "";
        } finally {
            try {

                if (gunzip != null) {
                    gunzip.close();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        try {
            return new String(out.toByteArray(), "utf-8");
        } catch (Exception e) {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e1) {
                log.error(e.getMessage());
            }
            return "";
        }
    }


    public static void main(String[] args) throws IOException {

        String str = "806715668,1091464537,1061006120,1142513520";

        System.out.println(str.length());
        System.out.println(compress(str).length);
        System.out.println(compress(str));
        System.out.println(unCompress(compress(str)));
    }
}
