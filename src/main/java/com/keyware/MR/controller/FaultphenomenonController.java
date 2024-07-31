package com.keyware.MR.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.keyware.MR.entity.Faultreason;
import com.keyware.MR.entity.Flowpath;
import com.keyware.MR.entity.Technician;
import com.keyware.MR.service.FaultreasonService;
import com.keyware.MR.service.FlowpathService;
import com.keyware.MR.service.LogService;
import com.keyware.MR.util.AjaxMessage;
import com.keyware.MR.util.IdGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.keyware.MR.service.FaultphenomenonService;
import com.keyware.MR.entity.Faultphenomenon;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * <p>
 * 故障现象表 前端控制器
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-15
 */
@Controller
@Api(value = "故障现象", tags = "故障现象")
@RequestMapping("/faultphenomenon")
public class FaultphenomenonController {


    @Autowired
    private FaultphenomenonService faultphenomenonService;
    @Autowired
    private FaultreasonService faultreasonService;
    @Autowired
    private FlowpathService flowpathService;
    @Autowired
    private LogService logService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @GetMapping(value = "/")
    @ApiOperation(value = "查询故障现象列表", notes = "查询故障现象列表")
    public ResponseEntity<AjaxMessage< Page<Faultphenomenon>>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Faultphenomenon> aPage = faultphenomenonService.page(new Page<>(current, pageSize));
        logService.savelog("查询故障现象列表",(String) request.getSession().getAttribute("userName"));
        return  new ResponseEntity<AjaxMessage< Page<Faultphenomenon>>>(new AjaxMessage< Page<Faultphenomenon>>("1","查询成功",aPage,null), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据id查询故障现象信息", notes = "根据id查询故障现象信息")
    public ResponseEntity<AjaxMessage< Faultphenomenon>> getById(@PathVariable("id") String id) {
        Faultphenomenon byId = faultphenomenonService.getById(id);
        logService.savelog("查询故障现象:"+byId.getFaultPhenomenon(),(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<AjaxMessage< Faultphenomenon>>(new AjaxMessage<Faultphenomenon>("1","查询成功",byId,null), HttpStatus.OK);

    }

    @PostMapping(value = "/create")
    @ApiOperation(value = "创建故障现象", notes = "创建故障现象")
    public ResponseEntity<AjaxMessage<Faultphenomenon>> create(@RequestBody Faultphenomenon params) {
        String dataVal = faultphenomenonService.dataVal(params);
        if (!dataVal.equals("")){
            return new ResponseEntity<AjaxMessage<Faultphenomenon>>(new AjaxMessage<Faultphenomenon>("0",dataVal,params,null), HttpStatus.OK);
        }
        params.setId(IdGenerator.uuid32());
        params.setCreateTime(new Date());
        boolean save = faultphenomenonService.save(params);
        if (save){
            //保存操作日志
            logService.savelog("创建故障现象:"+params.getFaultPhenomenon(),(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<Faultphenomenon>>(new AjaxMessage<Faultphenomenon>("1","编辑成功",params,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<Faultphenomenon>>(new AjaxMessage<Faultphenomenon>("0","编辑失败",params,null), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除故障现象", notes = "删除故障现象")
    public ResponseEntity<AjaxMessage<String>> delete(@PathVariable("id") String id) {
        Faultphenomenon byId = faultphenomenonService.getById(id);

        boolean bool = faultphenomenonService.removeById(id);
        //删除故障原因
        QueryWrapper<Faultreason> faultreasonQueryWrapper = new QueryWrapper<>();
        faultreasonQueryWrapper.eq("pid",id);
        faultreasonService.remove(faultreasonQueryWrapper);
        //删除排故流程
        QueryWrapper<Flowpath> flowpathQueryWrapper = new QueryWrapper<>();
        flowpathQueryWrapper.eq("pid",id);
        flowpathService.remove(flowpathQueryWrapper);
        if (bool){
            //保存操作日志
            logService.savelog("删除故障现象:"+byId.getFaultPhenomenon(),(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("1","删除成功",id,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("0","删除失败",id,null), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新故障现象", notes = "更新故障现象")
    public ResponseEntity<AjaxMessage<Faultphenomenon>> delete(@RequestBody Faultphenomenon params) {
        String dataVal = faultphenomenonService.dataVal(params);
        if (!dataVal.equals("")){
            return new ResponseEntity<AjaxMessage<Faultphenomenon>>(new AjaxMessage<Faultphenomenon>("0",dataVal,params,null), HttpStatus.OK);
        }
        logService.savelog("更新故障现象:"+params.getFaultPhenomenon(),(String) request.getSession().getAttribute("userName"));
        boolean bool = faultphenomenonService.updateById(params);
        if (bool){
            //保存操作日志
            logService.savelog("编辑技术人员",(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<Faultphenomenon>>(new AjaxMessage<Faultphenomenon>("1","编辑成功",params,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<Faultphenomenon>>(new AjaxMessage<Faultphenomenon>("0","编辑失败",params,null), HttpStatus.OK);
        }
    }
}
