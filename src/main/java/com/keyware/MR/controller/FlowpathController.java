package com.keyware.MR.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.keyware.MR.service.LogService;
import com.keyware.MR.util.IdGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.keyware.MR.service.FlowpathService;
import com.keyware.MR.entity.Flowpath;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * <p>
 * 排故流程 前端控制器
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-15
 */
@Controller
@Api(value = "排故流程", tags = "排故流程")
@RequestMapping("/flowpath")
public class FlowpathController {


    @Autowired
    private FlowpathService flowpathService;
    @Autowired
    private LogService logService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @GetMapping(value = "/")
    @ApiOperation(value = "查询排故流程列表", notes = "查询排故流程列表")
    public ResponseEntity<Page<Flowpath>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize, String pid) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        QueryWrapper<Flowpath> flowpathQueryWrapper = new QueryWrapper<>();
        flowpathQueryWrapper.eq("pid",pid);
        Page<Flowpath> aPage = flowpathService.page(new Page<>(current, pageSize),flowpathQueryWrapper);
        logService.savelog("查询排故流程列表",(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据id查询排故流程信息", notes = "根据id查询排故流程信息")
    public ResponseEntity<Flowpath> getById(@PathVariable("id") String id) {
        Flowpath byId = flowpathService.getById(id);
        logService.savelog("查询排故流程:"+byId.getName(),(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @ApiOperation(value = "新建排故流程", notes = "新建排故流程")
    public ResponseEntity<Object> create(@RequestBody Flowpath params) {
        params.setCreateTime(new Date());
        params.setId(IdGenerator.uuid32());
        params.setCreateUser((String) request.getSession().getAttribute("userName"));
        flowpathService.save(params);
        logService.savelog("新建排故流程:"+params.getName(),(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除排故流程", notes = "删除排故流程")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        Flowpath byId = flowpathService.getById(id);
        logService.savelog("删除排故流程:"+byId.getName(),(String) request.getSession().getAttribute("userName"));
        flowpathService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "编辑排故流程", notes = "编辑排故流程")
    @PostMapping(value = "/update")
    public ResponseEntity<Object> delete(@RequestBody Flowpath params) {
        flowpathService.updateById(params);
        logService.savelog("编辑排故流程:"+params.getName(),(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
