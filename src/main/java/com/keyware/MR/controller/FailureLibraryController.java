package com.keyware.MR.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.keyware.MR.entity.Faultlibrary;
import com.keyware.MR.entity.Process;
import com.keyware.MR.service.FaultlibraryService;
import com.keyware.MR.service.LogService;
import com.keyware.MR.util.AjaxMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "工步相关", tags = "工步相关")
@Controller
@RequestMapping("/failureLibrary")
public class FailureLibraryController {

    @Autowired
    public FaultlibraryService faultlibraryService;
    @Autowired
    private LogService logService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @GetMapping(value = "/")
    public ResponseEntity<Page<Faultlibrary>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        logService.savelog("查询工步列表",(String) request.getSession().getAttribute("userName"));
        Page<Faultlibrary> aPage = faultlibraryService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Faultlibrary> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(faultlibraryService.getById(id), HttpStatus.OK);
    }
    @PostMapping(value = "/create")
    public ResponseEntity<AjaxMessage<Faultlibrary>> create(@RequestBody Faultlibrary params) {
        if (faultlibraryService.save2(params)) {
            logService.savelog("创建工步"+params.getStepName()+" 成功",(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<>(new AjaxMessage<Faultlibrary>("1","创建成功",params,null), HttpStatus.OK);
        }
        logService.savelog("创建工步"+params.getStepName()+" 失败",(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>(new AjaxMessage<Faultlibrary>("0","创建失败",params,null), HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        Faultlibrary byId = faultlibraryService.getById(id);
        if (faultlibraryService.removeById(id)) {
            logService.savelog("删除工步"+byId.getStepName()+" 成功",(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<>(new AjaxMessage<String>("1","删除成功",id,null), HttpStatus.OK);
        }
        logService.savelog("删除工步"+byId.getStepName()+" 成功",(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>(new AjaxMessage<String>("0","删除失败",id,null), HttpStatus.OK);
    }
    @PostMapping(value = "/delete/{processId}")
    @ApiOperation("根据工序id删除相关工步")
    public ResponseEntity<Object> deleteByProcess(@PathVariable("processId") String processId) {
        Faultlibrary byId = faultlibraryService.getById(processId);
        QueryWrapper<Faultlibrary> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Faultlibrary::getProcess,processId);
        if ( faultlibraryService.remove(wrapper)) {
            logService.savelog("工序"+byId.getStepName()+"删除成功",(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<>(new AjaxMessage<String>("1","根据工序id删除成功",processId,null), HttpStatus.OK);
        }
        logService.savelog("工序"+byId.getStepName()+"删除失败",(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>(new AjaxMessage<String>("0","根据工序id删除失败",processId,null), HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> delete(@RequestBody Faultlibrary params) {
        if (faultlibraryService.updateById(params)) {
            logService.savelog("工序"+params.getStepName()+"更新成功",(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<>(new AjaxMessage<Faultlibrary>("1","更新成功",params,null), HttpStatus.OK);
        }
        logService.savelog("工序"+params.getStepName()+"更新失败",(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>(new AjaxMessage<Faultlibrary>("0","更新失败",params,null), HttpStatus.OK);
    }


}
