package com.wufan.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wufan.base.result.MyResult;
import com.wufan.security.properites.SecurityProperties;
import com.wufan.web.entities.*;
import com.wufan.web.service.*;
import com.wufan.web.utils.FileUtils;
import com.wufan.web.utils.MyStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 学生的课程设计
 * 包括上传课程设计，修改已经上传的课程设计，删除等
 * @author wufan
 * @date 2020/4/11 0011 16:00
 */
@Controller
@RequestMapping("/student/design")
@Slf4j
public class StudentDesignController {
    @Autowired
    private DesignStudentService designStudentService;
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private DesignService designService;

    @Autowired
    private SysUserService sysUserService;
    /**
     * 学生跳转课程设计首页页面
     * @return
     */
    @PreAuthorize("hasAuthority('student:design')")
    @GetMapping("")
    public String index(){

        return "/student/student-design-list";
    }

    @PreAuthorize("hasAuthority('student:design')")
    @ResponseBody
    @PostMapping("/page")
    public MyResult page(Page<DesignStudent> page ,@AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        SysUser student = sysUserService.findByUsername(username);
        String studentId =Long.toString(student.getId()) ;
        LambdaQueryWrapper<DesignStudent> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(DesignStudent::getStudentId,studentId);
        IPage<DesignStudent> iPage = designStudentService.page(page, queryWrapper);
        List<DesignStudent> designStudents=iPage.getRecords();
        //查询学生自己的所有课程设计（包括完成的和未完成的） 查询所属学科
        if (designStudents!=null){
            for (int i = 0; i <designStudents.size() ; i++) {
                DesignStudent designStudent = designStudents.get(i);
                if (designStudent!=null){
                    String designId = designStudent.getDesignId();
                    //2.查询到课程设计
                    Design design = designService.getById(designId);
                    try {
                        //1.查询该课程设计所著学科 subject-name
                        Subject subject=subjectService.getSubjectByDesignId(designId);
                        design.setSubject(subject);
                        SysUser teacher=sysUserService.getTeacherByDesignId(designId);
                        design.setTeacher(teacher);
                    }catch (Exception e){

                    }
                    designStudent.setDesign(design);
                }
            }
        }
        return MyResult.ok(iPage);
    }


    /**
     * location.href默认提交的方法是Get请求
     * 学生跳转到上传课程设计页面
     * @return
     */
    @PreAuthorize("hasAuthority('student:design:upload')")
    @GetMapping("/upload/{id}")
    public String tuUpload(@PathVariable("id") String id, Model model){
        log.info("跳转到上传页面");
        System.out.println(id);
        model.addAttribute("id",id)
;        return "/student/student-design-upload";
    }


    @Autowired
    SecurityProperties securityProperties;

    /**
     *
     * @param id student-design的主键
     * @param request
     * @param userDetails
     * @return
     * @throws FileNotFoundException
     */
    @PreAuthorize("hasAuthority('student:design:upload')")
    @PostMapping("/upload")
    public String upload(String id,HttpServletRequest request,@AuthenticationPrincipal UserDetails userDetails) throws FileNotFoundException {
        String filePath = securityProperties.getAuthentication().getFilePath();
        StringBuilder fileDirPath = new StringBuilder(filePath);
        System.out.println(id);

        //上传地址命名规则
        //目录/C:/upload/20164081111/id
        //文件20164081111吴帆.zip或/20164081111吴帆.docx
        String no = userDetails.getUsername();//学号
        SysUser user = sysUserService.findByUsername(no);
        String studentId=Long.toString(user.getId());
        List<ClazzStudent> clazzStudents = clazzStudentService.getByStudentId(studentId);
        if (clazzStudents!=null && clazzStudents.size()>0){
            ClazzStudent clazzStudent = clazzStudents.get(0);
            Clazz clazz = clazzService.getById(clazzStudent.getClazzId());
            String clazzName = clazz.getName();//班级名称
            fileDirPath.append("/").append(clazzName).append("/").append(no).append("/").append(id);

        }else{
            fileDirPath.append("/").append(no).append("/").append(id);
        }
        String path = fileDirPath.toString();
        FileUtils.makeDir(path);
        boolean b = FileUtils.uploadFiles(request, path);
        System.out.println(b);
        if (b){
            //修改数据库信息，标志学生课设完成，跟新上传时间和地址
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date d=new Date();
            String date = sdf.format(d);
            DesignStudent designStudent=new DesignStudent();
            designStudent.setId(id);
            designStudent.setUpload(1);
            designStudent.setUploadTime(date);
            designStudent.setUrl(path);
            designStudentService.updateById(designStudent);
        }
        return "redirect:/student/design";
    }

    @Autowired
    private ClazzStudentService clazzStudentService;
    @Autowired
    private ClazzService clazzService;
    @GetMapping("/person")
    public String person(@AuthenticationPrincipal UserDetails userDetails,Model model){
        //1.查询这个老师个人信息（姓名邮箱等）
        String username = userDetails.getUsername();
        SysUser student = sysUserService.findByUsername(username);
        if (student!=null &&student.getId()!=null){
            Long id = student.getId();
            //2.查询下是哪个专业的老师(查询专业，一个老师只能在一个专业下教学)
            String studentId=Long.toString(id);

            List<ClazzStudent> clazzStudents = clazzStudentService.getByStudentId(studentId);
            if (clazzStudents!=null && clazzStudents.size()>0){
                ClazzStudent clazzStudent = clazzStudents.get(0);
                if (clazzStudent!=null){
                    String clazzId = clazzStudent.getClazzId();
                    Clazz clazz = clazzService.getById(clazzId);
                    model.addAttribute("clazz",clazz!=null?clazz:new Clazz());
                }
            }
            Date d = new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
            model.addAttribute("teacher",student!=null?student:new SysUser());

            model.addAttribute("date",sdf.format(d));
        }
        return "/student/person";
    }


    @GetMapping("/person/edit")
    public String toEdit(@AuthenticationPrincipal UserDetails userDetails,Model model){
        String username = userDetails.getUsername();
        SysUser teacher = sysUserService.findByUsername(username);
        model.addAttribute("teacher",teacher);
        return "/student/person-edit";
    }

    /**
     * 更新
     * @param teacher
     * @return
     */
    @PostMapping("/person/edit")
    public String edit(SysUser teacher){
        sysUserService.updateById(teacher);
        return "redirect:/student/design/person";
    }

    @PreAuthorize("hasAuthority('student:design')")
    @GetMapping("/select")
    public String toSelect(@AuthenticationPrincipal UserDetails userDetails,Model model){
        String username = userDetails.getUsername();
        SysUser student = sysUserService.findByUsername(username);
        Long id = student.getId();
        String studentId = Long.toString(id);

        //获取学生未选择过的课程设计返回给学生
        //在design_student中不存在，在design_teacher中存在
        //学生找到班级，班级找到专业，专业找到学科，学科下的课程设计 提取所有课程设计id
        List<Design> designs=designService.getDesignListByStudentId(studentId);
        model.addAttribute("designs",designs);
        return "/student/student-design-select";
    }

    /**
     * 学生选择课程设计
     * @param userDetails
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('student:design')")
    @GetMapping("/doSelect")
    public String doSelect(@AuthenticationPrincipal UserDetails userDetails,String id){
        DesignStudent designStudent=new DesignStudent();
        designStudent.setId(MyStringUtils.getUUID());
        designStudent.setDesignId(id);
        String username = userDetails.getUsername();
        SysUser byUsername = sysUserService.findByUsername(username);
        String s = Long.toString(byUsername.getId());
        designStudent.setStudentId(s);
        boolean save = designStudentService.save(designStudent);
        System.out.println(save);
        return "redirect:/student/design/select";
    }
}
