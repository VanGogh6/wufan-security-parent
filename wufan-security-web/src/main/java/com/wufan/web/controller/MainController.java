package com.wufan.web.controller;

import com.wufan.web.entities.SysUser;
import com.wufan.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author wufan
 * @date 2020/4/11 0011 2:37
 */
@Controller
public class MainController {

    @RequestMapping({"/index", "/", ""})
    public String index(Map<String, Object> map,Authentication authentication) {
        // 第1方式：
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal != null && principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails)principal;
            String username = userDetails.getUsername();
            map.put("username", username);
        }
        return "index";// resources/templates/index.html
    }

    /**
     * 测试使用，部署删除
     * @param authentication
     * @return
     */
    @RequestMapping("/user/info")
    @ResponseBody
    public Object userInfo(Authentication authentication) {
        return authentication.getPrincipal();
    }

    /**
     * 测试使用，部署删除
     * @param userDetails
     * @return
     */
    @RequestMapping("/user/info2")
    @ResponseBody
    public Object userInfo2(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/editPassword")
    public String pass(String password1,String password2,@AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        SysUser user = sysUserService.findByUsername(username);
        SysUser u=new SysUser();
        u.setId(user.getId());
        if (password1!="" && password1!=null){
            String password = passwordEncoder.encode(password1);
            u.setPassword(password);
            //修改密码
            sysUserService.updateById(u);
        }
        return "redirect:/";
    }


}
