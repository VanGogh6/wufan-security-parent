package com.wufan.web.utils;

import com.wufan.web.entities.Clazz;
import com.wufan.web.entities.DesignStudent;
import com.wufan.web.entities.SysUser;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

/**
 * @author wufan
 * @date 2020/4/16 0016 22:21
 */
public class ExcelUtils {

    /**
     * 导出方法
     */
    public static XSSFWorkbook getXSSFWorkbook(List<DesignStudent> lists, String designName){
        XSSFWorkbook wb=new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        XSSFRow row0 = sheet.createRow(0);
        //序号  学号  姓名  手机号 课程设计名称  学生所在班级
        row0.createCell(0).setCellValue("序号");
        row0.createCell(1).setCellValue("学号");
        row0.createCell(2).setCellValue("姓名");
        row0.createCell(3).setCellValue("手机号");
        row0.createCell(4).setCellValue("课程设计名称");//designName
        row0.createCell(5).setCellValue("是否完成");//designName
        row0.createCell(6).setCellValue("班级");
        for (int i = 0; i <lists.size() ; i++) {
            XSSFRow row1 = sheet.createRow(i + 1);
            DesignStudent designStudent = lists.get(i);
            if (designStudent!=null){
                Clazz clazz = designStudent.getClazz();
                SysUser student = designStudent.getStudent();
                if (clazz!=null && student!=null){
                    row1.createCell(0).setCellValue(i+1);
                    row1.createCell(1).setCellValue(student.getUsername());
                    row1.createCell(2).setCellValue(student.getNickName());
                    row1.createCell(3).setCellValue(student.getMobile());
                    row1.createCell(4).setCellValue(designName);
                    Integer upload = designStudent.getUpload();
                    String flag="";
                    if (upload==0){
                        flag="未完成";
                    }else {
                        flag="已经完成";
                    }
                    row1.createCell(5).setCellValue(flag);
                    row1.createCell(6).setCellValue(clazz.getName());
                }
            }
        }
        return wb;
    }
}
