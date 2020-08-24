package com.wufan.web.controller;

/**
 * @author wufan
 * @date 2020/4/12 0012 19:05
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wufan.base.result.MyResult;
import com.wufan.web.entities.Major;
import com.wufan.web.entities.Subject;
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

@RequestMapping("/major")
@Controller
@Slf4j
public class MajorController {
    @Autowired
    private MajorService majorService;

    @GetMapping("/list")
    public String index(){
        return "/major/major-list";
    }


    @Autowired
    private SubjectService subjectService;

    /**
     * 分页查询列表
     * @param page 分页对象: size, current
     * @param major 查询条件 : no, name
     * @return
     */
    @PreAuthorize("hasAuthority('major:list')")
    @PostMapping("/page")
    @ResponseBody
    public MyResult page(Page<Major> page, Major major) {
        log.info("分页页面");
        IPage<Major> iPage = majorService.selectPage(page, major);
        List<Major> records = iPage.getRecords();
        for (int i = 0; i <records.size() ; i++) {
            Major m = records.get(i);
            if (m!=null){
                String majorId = m.getId();
                if (majorId!=null){
                    //查询出该专业下的所有学科,通过Major_Subject中间表得到subject
                    List<Subject> subjects = subjectService.getSubjectList(majorId);
                    m.setSubjects(subjects);
                }
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
    @PreAuthorize("hasAnyAuthority('major:add', 'major:edit')")
    @RequestMapping(value={"/form", "/form/{id}"},method = {RequestMethod.GET,RequestMethod.PUT})
    public String form(@PathVariable(required = false) String id, Model model) {
        if (id!=null){
            // 1, 查询课程设计
            Major major = majorService.getById(id);
            model.addAttribute("major", major);
        }else {
            model.addAttribute("major", new Major());
        }
        return  "/major/major-form";
    }

    /**
     * 新增或修改
     * @param major
     * @return
     */
    @PreAuthorize("hasAnyAuthority('design:add', 'design:edit')")
    @RequestMapping(value = "",method = {RequestMethod.POST,RequestMethod.PUT})
    public String put(Major major){
        if (major.getId()==null || "".equals(major.getId())){
            String uuid = MyStringUtils.getUUID();
            log.info(uuid);
            major.setId(uuid);
        }
        log.info("进入新增或修改专业"+major);
        majorService.saveOrUpdate(major);
        return "/major/major-list";
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('design:delete')")
    @DeleteMapping("/{id}")
    @ResponseBody
    public MyResult deleteById(@PathVariable String id) {
        log.info("删除方法");
        // 假删除，只做更新
        majorService.removeById(id);
        return MyResult.ok();
    }

}
