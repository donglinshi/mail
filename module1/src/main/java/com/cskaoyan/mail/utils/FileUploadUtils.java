package com.cskaoyan.mail.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author 史栋林
 * @date 2020/8/5 17:06
 */
public class FileUploadUtils {

    public static Map<String, Object> parseRequest(HttpServletRequest request){

        //Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = request.getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        //设置一个缓存仓库，如果文件很大，就边缓存便上传
        factory.setRepository(repository);
        //Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置文件名中的乱码问题
        upload.setHeaderEncoding("utf-8");

        Map<String, Object> params = new HashMap<String, Object>();

        try {
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iterator = items.iterator();
            while (iterator.hasNext()){
                FileItem fileItem = iterator.next();
                if (fileItem.isFormField()){
                    //是一个常规的form表单数据
                    processFormField(fileItem,params);
                }else {
                    processUploadedFile(fileItem,params,request);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return params;
    }

    private static void processUploadedFile(FileItem fileItem, Map<String, Object> params, HttpServletRequest request) {
        String fieldName = fileItem.getFieldName();
        String fileName = fileItem.getName();
        String s = UUID.randomUUID().toString();
        fileName = s + "-"+ fileName;

        //取hashCode
        int hashCode = fileName.hashCode();
        String hexString = Integer.toHexString(hashCode);
        char[] chars = hexString.toCharArray();
        String uploadPath = "upload";
        for (char aChar : chars){
            uploadPath = uploadPath + "/" +aChar;
        }
        String relativePath = uploadPath + "/" +fileName;
        String realPath = request.getServletContext().getRealPath(relativePath);

        File file = new File(realPath);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }

        try {
            fileItem.write(file);
            params.put(fieldName,relativePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processFormField(FileItem fileItem, Map<String, Object> params) {
        String fieldName = fileItem.getFieldName();
        String value = null;

        try {
            value = fileItem.getString("utf-8");
            params.put(fieldName,value);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(fieldName + ":" + value);
    }
}
