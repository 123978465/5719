package com.keyware.MR.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.util.StringUtil;
import com.keyware.MR.service.LogService;
import com.keyware.MR.util.IdGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.keyware.MR.service.FaultreasonService;
import com.keyware.MR.entity.Faultreason;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 故障原因表 前端控制器
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-15
 */
@Controller
@RequestMapping("/faultreason")
@Api(value = "故障原因", tags = "故障原因")
public class FaultreasonController {


    @Autowired
    private FaultreasonService faultreasonService;
    @Autowired
    private LogService logService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @GetMapping(value = "/")
    @ApiOperation(value = "查询故障原因列表", notes = "查询故障原因列表")
    public ResponseEntity<Page<Faultreason>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize, Faultreason faultreason) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        //查询故障现象下的故障原因
        QueryWrapper<Faultreason> faultreasonQueryWrapper = new QueryWrapper<>();
        faultreasonQueryWrapper.eq("pid",faultreason.getPid());
        //故障名称模糊查询
        if (StringUtil.isNotEmpty(faultreason.getName())){
            faultreasonQueryWrapper.like("name",faultreason.getName());
        }
        //故障部位查询
        if (StringUtil.isNotEmpty(faultreason.getPosition())){
            faultreasonQueryWrapper.like("position",faultreason.getPosition());
        }
        logService.savelog("查询故障原因列表",(String) request.getSession().getAttribute("userName"));
        Page<Faultreason> aPage = faultreasonService.page(new Page<>(current, pageSize),faultreasonQueryWrapper);
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @ApiOperation(value = "查询故障部位下拉框", notes = "查询故障部位下拉框")
    @GetMapping(value = "select/{id}")
    public ResponseEntity<List> getSelect(@PathVariable("id") String id) {
        QueryWrapper<Faultreason> faultreasonQueryWrapper = new QueryWrapper<>();
        faultreasonQueryWrapper.eq("pid",id);
        List<Faultreason> list = faultreasonService.list(faultreasonQueryWrapper);
        logService.savelog("查询故障部位下拉框",(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "根据id查询故障原因详情", notes = "根据id查询故障原因详情")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Faultreason> getById(@PathVariable("id") String id) {
        Faultreason byId = faultreasonService.getById(id);
        logService.savelog("查询故障原因详情",(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @ApiOperation(value = "新建故障原因", notes = "新建故障原因")
    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Faultreason params) {
        params.setId(IdGenerator.uuid32());
        params.setCreateTime(new Date());
        faultreasonService.save(params);
        logService.savelog("新建故障原因:"+params.getName(),(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除故障原因", notes = "删除故障原因")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        Faultreason byId = faultreasonService.getById(id);
        logService.savelog("删除故障原因:"+byId.getName(),(String) request.getSession().getAttribute("userName"));
        faultreasonService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "编辑故障原因", notes = "编辑故障原因")
    @PostMapping(value = "/update")
    public ResponseEntity<Object> delete(@RequestBody Faultreason params) {
        faultreasonService.updateById(params);
        logService.savelog("删除故障原因:"+params.getName(),(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
