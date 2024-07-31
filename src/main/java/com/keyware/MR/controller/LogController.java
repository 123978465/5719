package com.keyware.MR.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.keyware.MR.service.LogService;
import com.keyware.MR.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * <p>
 * 日志表 前端控制器
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
@Controller
@Api(value = "日志管理", tags = "日志管理")
@RequestMapping("/log")
public class LogController {


    @Autowired
    private LogService logService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @GetMapping(value = "/")
    @ApiOperation(value = "查询日志列表", notes = "查询日志列表")
    public ResponseEntity<Page<Log>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize, String startTime, String endTime) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        QueryWrapper<Log> logQueryWrapper = new QueryWrapper<>();
        //开始时间不为空和结束时间不为空
        if (StringUtil.isNotEmpty(startTime) && StringUtil.isNotEmpty(endTime)){
            logQueryWrapper.between("create_time",startTime,endTime);
        }
        Page<Log> aPage = logService.page(new Page<>(current, pageSize),logQueryWrapper);
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Log> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(logService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Log params) {
        logService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        logService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> delete(@RequestBody Log params) {
        logService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
