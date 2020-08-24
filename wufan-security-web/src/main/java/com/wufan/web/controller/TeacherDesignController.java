package com.wufan.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wufan.base.result.MyResult;
import com.wufan.security.properites.SecurityProperties;
import com.wufan.web.entities.*;
import com.wufan.web.service.*;
import com.wufan.web.utils.ExcelUtils;
import com.wufan.web.utils.FileUtils;
import com.wufan.web.utils.MyStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 老师管理课程设计
 * 给自己学科添加课程设计
 *
 * @author wufan
 * @date 2020/4/11 0011 16:22
 */
@Controller
@RequestMapping("/teacher/design")
@Slf4j
public class TeacherDesignController {
    @Autowired
    private DesignService designService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SysUserService sysUserServiceImpl;
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 跳转到教师管理课程设计页面
     * @return
     */
    @PreAuthorize("hasAuthority('teacher:design')")
    @GetMapping("")
    public String index(HttpServletRequest request){
        log.info("跳转到老师管理课程设计页面");
        request.getSession().setAttribute("designId",null);
        return "/teacher/teacher-design-list";
    }


    /**
     *
     * @return
     */
    @PreAuthorize("hasAuthority('teacher:design')")
    @PostMapping("/page")
    @ResponseBody
    public MyResult page(Page<Design> page, Design design,@AuthenticationPrincipal UserDetails userDetails,HttpServletRequest request) {
        IPage<Design> iPage=null;
        //1.获取老师id
        String username = userDetails.getUsername();
        if (username!=null){
            SysUser teacher = sysUserServiceImpl.findByUsername(username);
            if (teacher!=null){
                //2.根据老师id查询老师所有的课程设计,并且分页
                iPage = designService.getPage(page, design,teacher);
                if (iPage!=null){
                    List<Design> records = iPage.getRecords();
                    for (int i = 0; i <records.size() ; i++) {
                        Design des = records.get(i);
                        if (des!=null){
                            //3.查询该课程设计所属学科
                            Subject subject = subjectService.getSubjectByDesignId(des.getId());
                            if (subject!=null){
                                des.setSubject(subject);
                                //4.查询该课程设计的创建者
                                des.setTeacher(teacher);
                                //5.通过DesignId与subjectId在 subjectDesign表中获取主键，用于以后修改课程设设计所属学科
                                SubjectDesign subjectDesign=subjectDesignService.getBySubjectIdAndDesignId(subject.getId(),des.getId());
                                if (subjectDesign!=null){
                                    des.setSubjectDesignId(subjectDesign.getId());
                                }
                            }
                        }
                    }
                }
            }
        }
        return MyResult.ok(iPage);
    }



    /**
     * 跳转到添加页面
     * location.href 默认Get请求
     * @return
     */
    @PreAuthorize("hasAuthority('teacher:design:add')")
    @GetMapping("/add")
    public String toAdd(@AuthenticationPrincipal UserDetails userDetails, Model model){
        //1.查询到当前老师拥有的学科(在前端可以选择)
        List<Subject> subjects = subjectService.getSubjectListByUsername(userDetails.getUsername());
        //2.返回给前端
        model.addAttribute("subjects",subjects);
        return "/teacher/teacher-design-add";
    }

    @Autowired
    private SubjectDesignService subjectDesignService;
    @Autowired
    private DesignTeacherService designTeacherService;

    @PreAuthorize("hasAuthority('teacher:design:add')")
    @PostMapping("/add")
    public String add(String subjectId,Design design,@AuthenticationPrincipal UserDetails userDetails){
        String designId = MyStringUtils.getUUID();
        if (subjectId!="" && null!=design){
            //1.生成课程设计，并存储
            design.setId(designId);
            design.setCreateTime(new Date());
            boolean save = designService.save(design);
            //2.按学科添加课程设计 操作subject_design表
            if (save){
                System.out.println(subjectId);
                subjectDesignService.save(new SubjectDesign(MyStringUtils.getUUID(),subjectId,designId));
            }
            //3.标识是哪个老师创建的  操作design_teacher表
            SysUser teacher = sysUserServiceImpl.findByUsername(userDetails.getUsername());
            designTeacherService.save(new DesignTeacher(MyStringUtils.getUUID(),String.valueOf(teacher.getId()),designId));
        }
        //4.返回给前端
        return "redirect:/teacher/design";
    }
    @Autowired
    private ClazzService clazzService;

    /**
     * 下发课程设计时
     * 可能一个学科有多个班级
     * 咋们按班级下发课程设计
     * @return
     */
    @PreAuthorize("hasAuthority('teacher:design:add')")
    @GetMapping("/push/{id}")
    public String toPush(@PathVariable("id") String id,@AuthenticationPrincipal UserDetails userDetails,Model model){
        SysUser teacher = sysUserServiceImpl.findByUsername(userDetails.getUsername());
        Long teacherId = teacher.getId();
        List<Clazz> clazzs= clazzService.getClazzByTeacherId(teacherId);
        model.addAttribute("clazzs",clazzs);
        //3.把值传回页面
        Design newDesign = designService.getById(id);

        model.addAttribute("design",newDesign);
        return "/teacher/teacher-design-push";
    }

    @Autowired
    private DesignStudentService designStudentService;
    /**
     * 按班级分发课程设计
     * @return
     */
    @PreAuthorize("hasAuthority('teacher:design:add')")
    @PostMapping("/push")
    public String Push(String clazzId,Design design,Model model){
        //查找在clazzId下的班级的所有学生
        if (clazzId!=null && design!=null){
            String designId = design.getId();
            List<SysUser> students=sysUserServiceImpl.getStudentsByClazzId(clazzId);
            for (int i = 0; i <students.size() ; i++) {
                SysUser student = students.get(i);
                //给这些学生下发课程设计
                if (student!=null){
                    Long studentId = student.getId();
                    String newStudentId = Long.toString(studentId);
                    if (studentId!=null && designId!=null){
                        //已经下发过的同学无需再次下发课程设计
                        DesignStudent designStudent=designStudentService.getByDesignId(designId,newStudentId);
                        if (designStudent==null){
                            boolean save = designStudentService.save(new DesignStudent(MyStringUtils.getUUID(), designId,newStudentId));
                            log.info("给学生下发课程设计中");
                            if (save){
                                model.addAttribute("msg","下发成功");
                            }
                        }
                    }
                }
            }
        }
        return "redirect:/teacher/design";
    }


    @GetMapping("/look/{id}")
    public String toLook(@PathVariable("id")String id, HttpServletRequest request,@AuthenticationPrincipal UserDetails userDetails,Model model){
        HttpSession session = request.getSession();
        session.setAttribute("designId",id);
        SysUser teacher = sysUserServiceImpl.findByUsername(userDetails.getUsername());
        Long teacherId = teacher.getId();
        List<Clazz> clazzs= clazzService.getClazzByTeacherId(teacherId);
        model.addAttribute("clazzs",clazzs);
        return "/teacher/teacher-design-look";
    }

    /**
     * page分页条件
     * designStudent1模糊条件
     * 查看学生完成情况
     * @return
     */
    @PostMapping("/look")
    @ResponseBody
    public MyResult look(Page<DesignStudent> page,DesignStudent designStudent1,HttpServletRequest request){//已知参数design_id=id
        IPage<DesignStudent> pages = null;
        HttpSession session = request.getSession();
        String id1 = (String) session.getAttribute("designId");

        if (id1!=null){
            // 课程设计名称（design）  是否完成(design_student)  上传时间(design_student)   学号(sys_user) 姓名(sys_user)
            pages=designStudentService.getDesignStudentsByDesignId(page,id1,designStudent1.getDesignId());
            List<DesignStudent> lists = pages.getRecords();
            for (int i = 0; i <lists.size() ; i++) {
                DesignStudent designStudent = lists.get(i);
                String studentId = designStudent.getStudentId();
                if (studentId!=null){
                    SysUser student = sysUserServiceImpl.getById(studentId);
                    designStudent.setStudent(student);
                }
                String designId = designStudent.getDesignId();
                if (designId!=null){
                    Design design = designService.getById(designId);
                    designStudent.setDesign(design);
                }
            }
        }
        return pages!=null?MyResult.ok(pages):MyResult.ok();
    }

    /**
     *
     * @param id 专业ID
     * @param userDetails
     * @return
     */
    @GetMapping("/edit/{id}/{subjectDesignId}")
    public String toEdit(@PathVariable("subjectDesignId") String subjectDesignId,@PathVariable("id") String id,@AuthenticationPrincipal UserDetails userDetails,Model model){
        //回显当前课程设计     //遍历老师所教的所属学科
        Design design = designService.getById(id);
        model.addAttribute("design",design);
        model.addAttribute("subjectDesignId",subjectDesignId);
        log.info("subjectDesignId"+subjectDesignId);
        //1.拿到当前用户所有学科
        List<Subject> list = subjectService.getSubjectListByUsername(userDetails.getUsername());
        model.addAttribute("list",list);
        log.info("subjectDesignId22==="+subjectDesignId);
        return "/teacher/teacher-design-edit";
    }

    @PostMapping("/edit")
    public String edit(Design design,String subjectDesignId,String subjectId){
        //1.更新课程设计
        boolean b = designService.saveOrUpdate(design);
        if (b && design!=null){
            String designId = design.getId();
            subjectDesignService.saveOrUpdate(new SubjectDesign(subjectDesignId,subjectId,designId));
        }
        return "redirect:/teacher/design";
    }

    /**
     *按照传过来的课程设计id删除课程设计，在删除选择了该课程设计的学生
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public MyResult delete(@PathVariable("id") String id){
        log.info("删除");
        boolean f=false;
        //1.按照传过来的课程设计id删除课程设计
        boolean b = designService.removeById(id);
        //2.在删除选择了该课程设计的学生
        if (b){
           f =designStudentService.deleteByDesignId(id);
        }
        return f==true?MyResult.ok():MyResult.ok("删除失败");
    }

    @Autowired
    private MajorService majorService;

    @RequestMapping("/person")// /teacher/design/person
    @PreAuthorize("hasAuthority('teacher:design')")
    public String person(@AuthenticationPrincipal UserDetails userDetails,Model model){
        //1.查询这个老师个人信息（姓名邮箱等）
        String username = userDetails.getUsername();
        SysUser teacher = sysUserServiceImpl.findByUsername(username);
        if (teacher!=null &&teacher.getId()!=null){
            Long id = teacher.getId();
            //2.查询下是哪个专业的老师(查询专业，一个老师只能在一个专业下教学)
            String teacherId=Long.toString(id);
            Major major= majorService.getMajorByTeacherId(teacherId);
            //3.查询下这个老师带的学科（查询学科集合，老师可以教学自己所在专业的多个学科）
            List<Subject> subjects = subjectService.getSubjectListByUsername(username);
            Date d = new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
            model.addAttribute("teacher",teacher!=null?teacher:new SysUser());
            model.addAttribute("major",major!=null?major:new Major());
            model.addAttribute("subjects",subjects);
            model.addAttribute("date",sdf.format(d));
        }
        return "/teacher/person";
    }


    // /taecher/design/person/edit
    @GetMapping("/person/edit")
    public String toEdit(@AuthenticationPrincipal UserDetails userDetails,Model model){
        String username = userDetails.getUsername();
        SysUser teacher = sysUserServiceImpl.findByUsername(username);
        model.addAttribute("teacher",teacher);
        return "/teacher/person-edit";
    }

    /**
     * 更新
     * @param teacher
     * @return
     */
    @PostMapping("/person/edit")
    public String edit(SysUser teacher){
        sysUserServiceImpl.updateById(teacher);
        return "redirect:/teacher/design/person";
    }

    @Autowired
    private ClazzStudentService clazzStudentService;
    /**
     * 导出未完成的同学为excel表格
     */
    @RequestMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response){
        //存储学生学号，姓名        课程设计名称    学生所在班级
        String designId=(String)request.getSession().getAttribute("designId");
        //1.通过designId得到 DesignStudent集合
        List<DesignStudent> lists = designStudentService.getRecords(designId);
        //2.拿到课程设计
        Design design = designService.getById(designId);
        String designName = design.getName();
        for (int i = 0; i <lists.size() ; i++) {
            DesignStudent designStudent = lists.get(i);
            if (designStudent!=null){
                String studentId = designStudent.getStudentId();
                if (studentId!=null){
                    //3.找到该课程设计的学生
                    SysUser student = sysUserServiceImpl.getById(studentId);
                    designStudent.setStudent(student);
                    //4.找到班级id
                    List<ClazzStudent> clazzStudents=clazzStudentService.getByStudentId(studentId);
                    clazzStudents.size();
                    if (clazzStudents!=null && clazzStudents.size()>0){
                        ClazzStudent clazzStudent = clazzStudents.get(0);
                        if (clazzStudent!=null){
                            String clazzId = clazzStudent.getClazzId();
                            Clazz clazz = clazzService.getById(clazzId);
                            designStudent.setClazz(clazz);
                        }
                    }
                }
            }
        }
        XSSFWorkbook wb= ExcelUtils.getXSSFWorkbook(lists,designName);
        String fileName = designName+"完成情况.xlsx";
        OutputStream outputStream =null;
        try {
            fileName = URLEncoder.encode(fileName,"UTF-8");
            //设置ContentType请求信息格式
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * 压缩包方式下载
     * 遍历指定文件夹下的文件并下载
     * @param id
     * @param request
     * @param response
     */
    @GetMapping("/download-one/{id}")
    public void downloadOne(@PathVariable("id") String id,HttpServletRequest request, HttpServletResponse response) throws IOException {
        DesignStudent designStudent = designStudentService.getById(id);

        String zipDirectoryPath = designStudent.getUrl();//学生上传的文件夹目录地址

        if (zipDirectoryPath==null || "".equals(zipDirectoryPath)){//传输空文件，或相应没有文件，文件夹为空
            response.setContentType("text/html;charset=utf-8");//设置编码格式，以防前端页面出现中文乱码
            PrintWriter printWriter = response.getWriter();//创建输出流
            printWriter.println("<script language='JavaScript'>alert('该用户没有上传文件'+<a href='/teacher/design'>回首页</a>);</script>");
        }else {
            String tempFilePath = securityProperties.getAuthentication().getTempFilePath();
            //调用文件压缩，下载，删除压缩包方法
            boolean b = FileUtils.downloadZip(response, zipDirectoryPath, tempFilePath,"课程设计.zip");
        }
    }



    /**
     * 下载所有学生课设
     * @param response
     * @throws FileNotFoundException
     */
    @GetMapping("/downloadAll")
    public void downloadAll(HttpServletResponse response) throws IOException {
        String tempFilePath = securityProperties.getAuthentication().getTempFilePath();
        String zipDirectoryPath = securityProperties.getAuthentication().getFilePath();//获取所有的文件夹地址
        FileUtils.downloadZip(response,zipDirectoryPath,tempFilePath,"课程设计.zip");
    }

    /**
     * 按班级下载课程设计
     * @param response
     */
    @GetMapping("/downloadByClazz")
    public void downloadByClazz(HttpServletResponse response,String clazzName) throws FileNotFoundException {
        String filePath = securityProperties.getAuthentication().getFilePath();
        String tempFilePath = securityProperties.getAuthentication().getTempFilePath();
        String dir=filePath+"\\"+clazzName;
        FileUtils.downloadZip(response,dir,tempFilePath,"课程设计.zip");
    }

}
