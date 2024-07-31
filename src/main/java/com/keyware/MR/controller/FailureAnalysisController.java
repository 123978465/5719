package com.keyware.MR.controller;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.keyware.MR.entity.FailureAnalysisInfo;
import com.keyware.MR.entity.Faultlibrary;
import com.keyware.MR.entity.Process;
import com.keyware.MR.mapper.FaultlibraryMapper;
import com.keyware.MR.service.FailureAnalysisService;
import com.keyware.MR.service.FaultlibraryService;
import com.keyware.MR.service.LogService;
import com.keyware.MR.service.ProcessService;
import com.keyware.MR.util.AjaxMessage;
import com.keyware.MR.util.RoutingExcelReader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/failure")
@Api(value = "故障分析", tags = "故障分析")
public class FailureAnalysisController {
    @Autowired
    FailureAnalysisService failureAnalysisService;
    @Autowired
    private FaultlibraryService faultlibraryService;
    //
    @Autowired
    private ProcessService processService;
    @Autowired
    private LogService logService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    //    @Autowired
//    private FaultlibraryMapper faultlibraryMapper;
    @ApiOperation("查询故障库列表（分页）")
    @GetMapping(value = "/")
    public ResponseEntity<Page<Faultlibrary>> pageList(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        logService.savelog("查询故障列表",(String) request.getSession().getAttribute("userName"));
        Page<Faultlibrary> aPage = faultlibraryService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    //    @GetMapping(value = "/select")
//    public ResponseEntity<List<Faultlibrary>> List(){
//        List<Faultlibrary> select = faultlibraryMapper.select();
//        return new ResponseEntity<>(select, HttpStatus.OK);
//    }
    @ApiOperation("查询工序列表（list集合）")
    @GetMapping(value = "/getProcessList")
    public ResponseEntity<AjaxMessage<List<Process>>> getProcessList() {
        logService.savelog("查询工序列表",(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>(new AjaxMessage<>("1", "", processService.list(), "123"), HttpStatus.OK);
    }

    @ApiOperation("查询工序（根据工序id）")
    @GetMapping(value = "/getProcessById")
    public ResponseEntity<AjaxMessage<Process>> getProcessById(Integer id) {
        logService.savelog("根据id查询工序",(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>(new AjaxMessage<>("1", "", processService.getById(id), null), HttpStatus.OK);
    }

    @ApiOperation("根据工序id查询相关信息，包括故障库及工序描述")
    @GetMapping(value = "/getInfoByProcessId")
    public ResponseEntity<AjaxMessage<FailureAnalysisInfo>> getInfoByProcessId(Integer processId) {
        logService.savelog("根据工序id查询相关信息，包括故障库及工序描述",(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>(failureAnalysisService.getAnalysisInfoByPId(processId), HttpStatus.OK);
    }
    @ApiOperation("获取全部故障库信息集合")
    @GetMapping(value = "/getInfoList")
    public ResponseEntity<AjaxMessage<List<FailureAnalysisInfo>>> getInfoList() {
        logService.savelog("获取全部故障库信息集合",(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>(failureAnalysisService.getInfoList(), HttpStatus.OK);
    }
    @ApiOperation("创建新分析数据")
    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody FailureAnalysisInfo params) {
        try {
            processService.save2(params.getProcess());
            for (Faultlibrary faultlibrary : params.getFaultlibraryList()) {
                logService.savelog("创建分析数据"+faultlibrary.getStepName(),(String) request.getSession().getAttribute("userName"));
                faultlibraryService.save(faultlibrary);
            }
        }catch (Exception e){
            return new ResponseEntity<>(new AjaxMessage<FailureAnalysisInfo>("0","e",params,null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new AjaxMessage<FailureAnalysisInfo>("1","新建成功",params,null), HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        Faultlibrary byId = faultlibraryService.getById(id);
        faultlibraryService.removeById(id);
        logService.savelog("删除工序"+byId.getStepName(),(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }
//    (@RequestBody Faultlibrary params)

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody FailureAnalysisInfo params) {
        try {
            for (Faultlibrary faultlibrary : params.getFaultlibraryList()) {
                faultlibraryService.update2(faultlibrary);
            }
              processService.save2(params.getProcess());
        }catch (Exception e){
            return new ResponseEntity<>(new AjaxMessage<FailureAnalysisInfo>("0",e.getMessage(),params,null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new AjaxMessage<FailureAnalysisInfo>("1","更新成功",params,null), HttpStatus.OK);
    }

    @ApiOperation("进行故障分析")
    @GetMapping("/analysis")
    public ResponseEntity<?> analysis() {
        System.out.println(  failureAnalysisService. dataGeneration("0.98","0.89"));
        logService.savelog("进行故障分析",(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<AjaxMessage>(new AjaxMessage("1", "code 0 失败 ，1 成功 ", null, "123"), HttpStatus.OK);
    }
    @ApiOperation("生成数据")
    @GetMapping("/dataGeneration")
    public ResponseEntity<?> dataGeneration(@RequestParam String max,@RequestParam String min) {
        String dataGeneration = failureAnalysisService.dataGeneration("0.98", "0.89");
        return new ResponseEntity<AjaxMessage>(new AjaxMessage("1", "code 0 失败 ，1 成功 ", dataGeneration, "123"), HttpStatus.OK);
    }




    /**
     * 导入
     * @param
     * @param file
     * @return
     */
    @ApiOperation("导入")
    @PostMapping("/input")
    public AjaxMessage<Object> input(  MultipartFile file) {
        importExcel(file);
        return new AjaxMessage<>("0","true",null,null);
    }
    public void importExcel(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            RoutingExcelReader listener = new RoutingExcelReader();
            EasyExcel.read(inputStream, Faultlibrary.class, listener).sheet().doRead();
            List<Faultlibrary> users = listener.getUsers();
//            faultlibraryService.saveBatch(users);
            // 处理读取到的数据
            users.forEach(user -> System.out.println(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 导出
     * @param equipmentId
     * @return
     */
    @ApiOperation("导出")
    @GetMapping("/export")
    public void export(HttpServletResponse response, String equipmentId) {
//        QueryWrapper<FailureAnalysisInfo> wrapper = new QueryWrapper<>();
//        wrapper.eq("EQUIPMENTID",equipmentId);   faultlibraryService.list();
        List<Faultlibrary> list = new ArrayList<>();
        exportExcel(response,list);
        System.out.println("导出文件中-----");
    }
    public void exportExcel(HttpServletResponse response, List<Faultlibrary> users) {
        // 设置响应头
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("故障信息.xlsx", "UTF-8");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName);
            // 写入Excel数据
            EasyExcel.write(response.getOutputStream(), Faultlibrary.class)
                    .sheet("故障信息")
                    .doWrite(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
