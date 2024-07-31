package com.keyware.MR.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.keyware.MR.entity.*;
import com.keyware.MR.entity.Process;
import com.keyware.MR.mapper.ForwardanalysisMapper;
import com.keyware.MR.service.FaultlibraryService;
import com.keyware.MR.service.ForwardanalysisService;
import com.keyware.MR.service.LogService;
import com.keyware.MR.service.TableName2Service;
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
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/forward")
@Api(value = "正向运行" ,tags = "正向运行")
public class ForwardRunningController {

    @Autowired
    private ForwardanalysisService forwardanalysisService;
    @Autowired
    private TableName2Service TableName2Service;
    @Autowired
    private LogService logService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @GetMapping(value = "/")
    public ResponseEntity<Page<TableName2>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<TableName2> aPage = TableName2Service.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }


    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody TableName2 params) {
        TableName2Service.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        TableName2Service.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> delete(@RequestBody TableName2 params) {
        TableName2Service.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }

    @ApiOperation("根据工步id查询相关信息")
    @GetMapping(value = "/getInfoByProcessId")
    public ResponseEntity<AjaxMessage<List<Forwardanalysis>>> getInfoByProcessId(Integer processId) {
        QueryWrapper<Forwardanalysis> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Forwardanalysis::getExetionstepId,processId);
        forwardanalysisService.list(wrapper);
        logService.savelog("查询工序"+processId+"相关信息",(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>(new AjaxMessage<>("1", "",forwardanalysisService.list(wrapper), "123"), HttpStatus.OK);
    }
    @ApiOperation("获取全部故障库信息集合")
    @GetMapping(value = "/getInfoList")
    public ResponseEntity<AjaxMessage<List<FailureAnalysisInfo>>> getInfoList() {
        logService.savelog("获取故障信息集合",(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    @ApiOperation("查询工序列表（list集合）")
    @GetMapping(value = "/getProcessList")
    public ResponseEntity<AjaxMessage<List<Forwardanalysis>>> getProcessList() {
        QueryWrapper<Forwardanalysis> wrapper = new QueryWrapper<>();
        wrapper.select("EXETIONSTEP","EXETIONSTEPID");
        wrapper.groupBy("EXETIONSTEP","EXETIONSTEPID");
//        System.out.println(forwardanalysisService.list(wrapper));
        logService.savelog("查询工序列表",(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>(new AjaxMessage<>("1", "",forwardanalysisService.list(wrapper), "123"), HttpStatus.OK);
    }

    @Autowired
    private FaultlibraryService faultlibraryService;
    @ApiOperation("工作流程")
    @PostMapping("/Running")
    public ResponseEntity<?> running(@RequestParam String estimate,@RequestParam(required = false) String outputMax ,@RequestParam(required = false) String outputMin ,@RequestParam String outputUnit){
//
        logService.savelog("调用工作流程接口",(String) request.getSession().getAttribute("userName"));
        QueryWrapper<Faultlibrary> wrapper = new QueryWrapper<>();
         wrapper.lambda().groupBy(Faultlibrary::getFaultNumber);
        wrapper.select("FAULTNUMBER");
//        wrapper.lambda().eq("FAULTNUMBER",)
        List<Faultlibrary> list = faultlibraryService.list(wrapper);
        wrapper.clear();
        UpdateWrapper<Faultlibrary> updateWrapper = new UpdateWrapper<>();
        for (int i=0;i<list.size()-1;i++){
            System.out.println(list.get(i).getFaultNumber());
            updateWrapper.eq("FAULTNUMBER",list.get(i).getFaultNumber());
            if (i<11){
            updateWrapper.set("MODELANIMATION",(i+1));
            }else{
                updateWrapper.set("MODELANIMATION",0);
            }
            faultlibraryService.update(null,updateWrapper);
            updateWrapper.clear();
        }
        System.out.println(list);
//        Malfunction_01:


//        QueryWrapper<Forwardanalysis> wrapper = new QueryWrapper<>();
//        wrapper.select("EXETIONSTEPID","EXETIONSTEP");
//        wrapper.groupBy("EXETIONSTEP");
//        List<Forwardanalysis> list = forwardanalysisService.list(wrapper);
//        for (int i =0 ; i < list.size(); i++){
//           forwardanalysisMapper.insert1(String.valueOf(i+1),list.get(i).getExetionstep()) ;
//
//
//        }




//        QueryWrapper<Forwardanalysis> wrapper = new QueryWrapper<>();
////        Forwardanalysis forwardanalysis1 = new Forwardanalysis();/**/
//
//        List<Forwardanalysis> forwardanalyses = forwardanalysisMapper.select1();
//        for (Forwardanalysis forwardanalysis : forwardanalyses){
//            String estimate1 = forwardanalysis.getESTIMATE();
//            if (estimate1!=null&&estimate1.contains("±")&&estimate1.contains("(")){
//                String[] split = estimate1.substring(estimate1.indexOf("(")+1, estimate1.indexOf(")")).split("±");
//                if (split.length>1){
//                    forwardanalysis.setOUTPUTMIN(
//                            new BigDecimal(split[0]).subtract(
//                                    new BigDecimal(split[1])
//                            ).toString());
//                    forwardanalysis.setOUTPUTMAX(
//                            new BigDecimal(split[0]).add(
//                                    new BigDecimal(split[1])
//                            ).toString()
//                    );
//                    wrapper.lambda().eq(Forwardanalysis::getESTIMATE,estimate1);
//
//                    forwardanalysisService.update(forwardanalysis,wrapper);
//                    wrapper.clear();
////                    System.out.println(estimate1);
////                    System.out.println(forwardanalysis.getOUTPUTMAX()+"===="+forwardanalysis.getOUTPUTMIN());
////                    System.out.println(split[0] + "====" + split[1]);
//                }
//
//            } else if (estimate1.contains("~")&&estimate1.contains("(")) {
//                String[] split = estimate1.substring(estimate1.indexOf("(")+1, estimate1.indexOf(")")).split("~");
//                if (split.length>1){
//                    forwardanalysis.setOUTPUTMIN(split[0]);
//                    forwardanalysis.setOUTPUTMAX(split[1]);
//                    wrapper.lambda().eq(Forwardanalysis::getESTIMATE,estimate1);
//                    forwardanalysisService.update(forwardanalysis,wrapper);
//                    wrapper.clear();
//                    System.out.println(estimate1);
//                    System.out.println(forwardanalysis.getOUTPUTMAX()+forwardanalysis.getOUTPUTMIN());
//                    System.out.println(split[0] + "====" + split[1]);
//                }
//            }
//
//
//
//        }
//
//
        return new ResponseEntity<AjaxMessage>(new AjaxMessage("1","code 0 失败 ，1 成功",null,"123"),HttpStatus.OK);
    }

}
