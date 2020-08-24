package com.wufan.web.controller;

/**
 * @author wufan
 * @date 2020/4/12 0012 19:05
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wufan.base.result.MyResult;
import com.wufan.web.entities.Design;
import com.wufan.web.entities.Major;
import com.wufan.web.entities.Subject;
import com.wufan.web.service.DesignService;
import com.wufan.web.service.MajorService;
import com.wufan.web.service.SubjectService;
import com.wufan.web.utils.MyStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/subject")
@Controller
@Slf4j
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private DesignService designService;
    @Autowired
    private MajorService majorService;

    @GetMapping("/list")
    public String index(){
        return "/subject/subject-list";
    }

    /**
     * 分页查询列表
     * @param page 分页对象: size, current
     * @param subject 查询条件 : no, name
     * @return
     */
    @PreAuthorize("hasAuthority('subject:list')")
    @PostMapping("/page")
    @ResponseBody
    public MyResult page(Page<Subject> page, Subject subject) {
        log.info("分页页面");
        IPage<Subject> iPage = subjectService.selectPage(page, subject);
        List<Subject> records = iPage.getRecords();
        for (int i = 0; i <records.size() ; i++) {
            Subject sub = records.get(i);
            if (sub!=null){
                String subId = sub.getId();
                //1.查询所属专业
                Major major=majorService.getMajorBySubjectId(subId);
                if (major!=null){
                    sub.setMajor(major);
                }
                //2.根据学科id查询所有Design
                List<Design> designs = designService.getDesignList(subId);
                sub.setDesigns(designs);
            }
        }
        return MyResult.ok(iPage);
    }

    /**
     * 添加或更新转发跳转
     * @param id
     * @param model
     * @return
     */
    @PreAuthorize("hasAnyAuthority('subject:add', 'subject:edit')")
    @RequestMapping(value={"/form", "/form/{id}"},method = {RequestMethod.GET,RequestMethod.PUT})
    public String form(@PathVariable(required = false) String id, Model model) {
        if (id!=null){
            // 1, 查询课程设计
            Subject subject = subjectService.getById(id);
            model.addAttribute("subject", subject);
        }else {
            model.addAttribute("subject", new Subject());
        }
        return  "/subject/subject-form";
    }

    /**
     * 新增或修改
     * @param subject
     * @return
     */
    @PreAuthorize("hasAnyAuthority('subject:add', 'subject:edit')")
    @RequestMapping(value = "",method = {RequestMethod.POST,RequestMethod.PUT})
    public String put(Subject subject){
        if (subject.getId()==null || "".equals(subject.getId())){
            String uuid = MyStringUtils.getUUID();
            log.info(uuid);
            subject.setId(uuid);
        }
        log.info("进入新增或修改"+subject);
        subjectService.saveOrUpdate(subject);
        return "/subject/subject-list";
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('subject:delete')")
    @DeleteMapping("/{id}")
    @ResponseBody
    public MyResult deleteById(@PathVariable String id) {
        log.info("删除方法");
        // 假删除，只做更新
        subjectService.removeById(id);
        return MyResult.ok();
    }

}
