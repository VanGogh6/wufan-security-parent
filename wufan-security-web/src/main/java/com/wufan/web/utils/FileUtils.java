package com.wufan.web.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author wufan
 * @date 2020/4/11 0011 21:43
 */
@Slf4j
public class FileUtils {
    /**
     *
     * @param request 含有存入的文件
     * @param fileDirPath 存入的地址
     * @return
     */
    public static boolean uploadFiles(HttpServletRequest request, String fileDirPath) {
       try {
           MultipartHttpServletRequest mRequest =null;
           if (request instanceof MultipartHttpServletRequest) {
               mRequest = (MultipartHttpServletRequest)(request);
               List<MultipartFile> files = mRequest.getFiles("files");
               for (int i = 0; i <files.size() ; i++) {
                   MultipartFile file = files.get(i);
                   if (file!=null && !"".equals(file)){
                       String filename = file.getOriginalFilename();
                       if (!"".equals(filename) && filename!=null){
                           //上传的文件有效且存在
                           log.info("上传的文件名称为"+filename);
                           //1.把文件存入的信息存入数据库
                           File file1=new File(fileDirPath,filename);
                           file.transferTo(file1);
                       }
                   }
               }
           }
       }catch (Exception e){
           log.info("上传文件异常"+e);
           return false;
       }
       return true;
    }


    /**
     * 创建文件夹
     */
    public static void makeDir(String path){
        File file=new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
    }

    /**
     * 下载文件
     * @param response
     * @param souFilePath
     */
    public static void downloadFile(HttpServletResponse response, String souFilePath,String downloadFileName) {
        try {
            File file=new File(souFilePath);
            InputStream f=new FileInputStream(file);
            response.reset();
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(downloadFileName,"utf-8"));
            ServletOutputStream outputStream = response.getOutputStream();
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                bis = new BufferedInputStream(f);
                bos = new BufferedOutputStream(outputStream);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
                bos.flush();
                bos.close();
                bis.close();
            } catch (final IOException e) {
                throw e;
            } finally {
                if (bis != null){
                    bis.close();
                }
                if (bos != null){
                    bos.close();
                }
            }
        }catch (Exception e){
            return;
        }
    }


    /**
     *
     * @param response
     * @param zipDirectoryPath 需要压缩的文件夹
     * @param outZipTempPath 临时压缩文件夹
     * @param downloadFileName 下载到浏览器的文件名
     * @return
     * @throws FileNotFoundException
     */
    public static boolean downloadZip(HttpServletResponse response, String zipDirectoryPath, String outZipTempPath,String downloadFileName) throws FileNotFoundException {
        boolean flag=true;

        File fileOutZip=new File(outZipTempPath);//压缩后的文件夹
        FileOutputStream outZip=new FileOutputStream(fileOutZip);
        try {
            ZipUtils.toZip(zipDirectoryPath,outZip,true);//压缩完成
        }catch (Exception e){
            log.info("压缩文件时错误："+e);
            flag=false;
        }

        if (flag){
            try {
                FileUtils.downloadFile(response,outZipTempPath,downloadFileName);
            }catch (Exception e){
                log.info("下载文件时错误："+e);
                flag=false;
            }
        }
        //3.删除压缩包
        if (flag){
            try {
                ZipUtils.deleteZip(outZipTempPath);
            }catch (Exception e){
                log.info("删除压缩包文件时错误："+e);
                flag =false;
            }
        }
        return flag;
    }

    public static void main(String[] args) throws FileNotFoundException {

    }

}
