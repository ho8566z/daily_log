package com.office.calendaradmin.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    final private AdminService adminService;

    // 관리자 목록 /admin/list
    @GetMapping("/list")
    public String list() {
        log.info("list()");

        String nextPage = "admin/list";

        return nextPage;

    }

    // 관리자 목록 조회
    @GetMapping("/admins")
    @ResponseBody
    public Object admins() {
        log.info("admins()");

        Map<String, Object> resultMap = adminService.admins();

        return resultMap;

    }


}
