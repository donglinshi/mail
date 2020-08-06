package com.cskaoyan.mail.utils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author 史栋林
 * @date 2020/8/5 20:53
 */
public class HttpUtils {

    /**
     * @description:获取请求体中的请求体字符数据
     * @params:request
     * @author: 史栋林
     */
    public static String getRequestBody(HttpServletRequest request) throws IOException {

        //对请求中的数据进行处理，不规则形
        ServletInputStream servletInputStream = request.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length = 0;
        byte[] bytes = new byte[1024];
        while ((length = servletInputStream.read(bytes)) != -1){
            byteArrayOutputStream.write(bytes,0,length);
        }
        //获取到请求体中的数据
        return byteArrayOutputStream.toString("utf-8");
    }
}
