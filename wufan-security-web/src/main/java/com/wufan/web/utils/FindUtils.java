package com.wufan.web.utils;

import com.wufan.web.entities.Design;
import com.wufan.web.entities.Subject;
import com.wufan.web.entities.SysUser;
import com.wufan.web.service.DesignService;
import com.wufan.web.service.SubjectService;
import com.wufan.web.service.SysUserService;

import java.util.List;

/**
 * @author wufan
 * @date 2020/4/13 0013 0:06
 */
public class FindUtils {

    /**
     * 已验证
     * 在专业中调用
     * 通过专业id查询所有学科 major_subject
     */
    public static List<Subject> getSubjectListByMajorId(String majorId, SubjectService subjectServiceImpl){
        List<Subject> subjectList=subjectServiceImpl.getSubjectList(majorId);
        return subjectList;
    }

    /**
     * 更具老师id
     * 查询老师的教学的所有学科 teacher_subject
     */
    public static List<Subject> getSubjectListByUsername(String username, SubjectService subjectServiceImpl){
        List<Subject> subjectList=subjectServiceImpl.getSubjectListByUsername(username);
        return subjectList;
    }



    /**
     * 已验证
     * 在学科中调用
     * 通过学科id查询所有课程设计
     */
    public static List<Design> getDesignListBySubjectId(String subjectId,DesignService designServiceImpl){
        List<Design> designList=designServiceImpl.getDesignList(subjectId);
        return designList;
    }



    /**
     * 已验证
     * 通过课程设计id查询所有选择该课程设计的学生
     */
    public static List<SysUser> getStudentListByDesignId(String designId, SysUserService sysUserServiceImpl){
        List<SysUser> studentList=sysUserServiceImpl.getStudentList(designId);
        return studentList;
    }



}
