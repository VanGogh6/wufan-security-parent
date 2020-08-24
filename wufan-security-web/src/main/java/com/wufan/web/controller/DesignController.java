package com.wufan.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wufan.base.result.MyResult;
import com.wufan.web.entities.Design;
import com.wufan.web.entities.Subject;
import com.wufan.web.entities.SysUser;
import com.wufan.web.service.DesignService;
import com.wufan.web.service.SubjectService;
import com.wufan.web.service.SysUserService;
import com.wufan.web.utils.MyStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wufan
 * @date 2020/4/11 0011 12:41
 */
@RequestMapping("/design")
@Controller
@Slf4j
public class DesignController {

    @Autowired
    private DesignService designService;

    @Autowired
    private SysUserService sysUserServiceImpl;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("")
    public String index(){
        log.info("跳转课程设计首页");
        return "/design/design-list";
    }

    /**
     * 分页查询课程设计列表
     * @param page 分页对象: size, current
     * @param design 查询条件 : no, name
     * @return
     */
    @PreAuthorize("hasAuthority('design:list')")
    @PostMapping("/page")
    @ResponseBody
    public MyResult page(Page<Design> page, Design design) {
        IPage<Design> iPage = designService.selectPage(page, design);
        if (iPage!=null){
            List<Design> records = iPage.getRecords();
            for (int i = 0; i <records.size() ; i++) {
                Design design1 = records.get(i);
                if (design1!=null){
                    String designId = design1.getId();
                    //1.查询该课程设计所著学科 subject-name
                    Subject subject=subjectService.getSubjectByDesignId(designId);
                    if (subject!=null){
                        design1.setSubject(subject);
                    }
                    //2.查询是哪位老师创建的
                    SysUser teacher=sysUserServiceImpl.getTeacherByDesignId(designId);
                    design1.setTeacher(teacher);
                    //3.查询该课程设计有多少学生选择
                    if (designId!=null){
                        List<SysUser> studentList= sysUserServiceImpl.getStudentList(designId);
                        design1.setStudents(studentList);
                    }
                }
            }
        }
        return MyResult.ok(iPage);
    }

    /**
     * 添加或更新课程设计转发跳转
     * @param id
     * @param model
     * @return
     */
    @PreAuthorize("hasAnyAuthority('design:add', 'design:edit')")
    @RequestMapping(value={"/form", "/form/{id}"},method = {RequestMethod.GET,RequestMethod.PUT})
    public String form(@PathVariable(required = false) String id, Model model) {
       if (id!=null){
           // 1, 查询课程设计
           Design design = designService.getById(id);
           model.addAttribute("design", design);
       }else {
           model.addAttribute("design", new Design());
       }
        return  "/design/design-form";
    }

    /**
     * 新增或修改课程设计
     * @param design
     * @return
     */
    @PreAuthorize("hasAnyAuthority('design:add', 'design:edit')")
    @RequestMapping(value = "",method = {RequestMethod.POST,RequestMethod.PUT})
    public String put(Design design){
        if (design.getId()==null){
            String uuid = MyStringUtils.getUUID();
            design.setId(uuid);
        }
        log.info("进入新增或修改课程设计"+design);
        designService.saveOrUpdate(design);
        return "/design/design-list";
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('design:delete')")
    @DeleteMapping("/{id}") // /user/{id}
    @ResponseBody
    public MyResult deleteById(@PathVariable String id) {
        log.info("删除方法");
        // 假删除，只做更新
        designService.removeById(id);
        return MyResult.ok();
    }

}
