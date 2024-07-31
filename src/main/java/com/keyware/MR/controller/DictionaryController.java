package com.keyware.MR.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.util.StringUtil;
import com.google.common.base.Charsets;
import com.keyware.MR.entity.Child;
import com.keyware.MR.entity.Department;
import com.keyware.MR.service.ChildService;
import com.keyware.MR.service.LogService;
import com.keyware.MR.util.AjaxMessage;
import com.keyware.MR.util.IdGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.keyware.MR.service.DictionaryService;
import com.keyware.MR.entity.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_TYPE;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-14
 */
@Controller
@Api(value = "字典管理", tags = "字典管理")
@RequestMapping("/dictionary")
public class DictionaryController {


    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private LogService logService;
    @Autowired
    private ChildService childService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @GetMapping(value = "/")
    @ApiOperation(value = "查询字典项列表", notes = "查询字典项列表")
    public ResponseEntity<Page<Dictionary>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize , Dictionary dictionary) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        //字典项名称模糊查询
        QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
        if (StringUtil.isNotEmpty(dictionary.getName())) {
            dictionaryQueryWrapper.like("name", dictionary.getName());
        }

        logService.savelog("查询字典项列表", (String) request.getSession().getAttribute("userName"));
        Page<Dictionary> aPage = dictionaryService.page(new Page<>(current, pageSize), dictionaryQueryWrapper);
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查看字典项详情", notes = "查看字典项详情")
    public ResponseEntity<List> getById(@PathVariable("id") String id) {
        QueryWrapper<Child> childQueryWrapper = new QueryWrapper<>();
        childQueryWrapper.eq("pid", id);
        List<Child> list = childService.list(childQueryWrapper);
        logService.savelog("查看字典项详情", (String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @ApiOperation(value = "新增字典项", notes = "新增字典项")
    public ResponseEntity<AjaxMessage<Dictionary>> create(@RequestBody Dictionary params) {
        String dataVal = dictionaryService.dataVal(params);
        if(!dataVal.equals("")){
            return new ResponseEntity<AjaxMessage<Dictionary>>(new AjaxMessage<Dictionary>("0",dataVal,params,null), HttpStatus.OK);
        }
        params.setId(IdGenerator.uuid32());
        params.setCreateTime(new Date());
        boolean save = dictionaryService.save(params);
        if (save){
            logService.savelog("新增字典项", (String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<Dictionary>>(new AjaxMessage<Dictionary>("1","添加成功",params,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<Dictionary>>(new AjaxMessage<Dictionary>("0","添加失败",params,null), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/createDateVal")
    @ApiOperation(value = "新增字典项值", notes = "新增字典项值")
    public ResponseEntity<AjaxMessage<Child>> createDateVal(@RequestBody Child params) {
        String dataVal = childService.dataVal(params);
        if(!dataVal.equals("")){
            return new ResponseEntity<AjaxMessage<Child>>(new AjaxMessage<Child>("0",dataVal,params,null), HttpStatus.OK);
        }
        params.setId(IdGenerator.uuid32());
        params.setCreateTime(new Date());
        boolean save = childService.save(params);
        if (save){
            logService.savelog("新增字典项值", (String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<Child>>(new AjaxMessage<Child>("1","添加成功",params,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<Child>>(new AjaxMessage<Child>("0","添加失败",params,null), HttpStatus.OK);
        }
    }
    @PostMapping(value = "/updateDateVal")
    @ApiOperation(value = "修改字典项值", notes = "修改字典项值")
    public ResponseEntity<AjaxMessage<Child>> updateDateVal(@RequestBody Child params) {
        String dataVal = childService.dataVal(params);
        if(!dataVal.equals("")){
            return new ResponseEntity<AjaxMessage<Child>>(new AjaxMessage<Child>("0",dataVal,params,null), HttpStatus.OK);
        }
//        params.setId(IdGenerator.uuid32());
//        params.setCreateTime(new Date());
//        childService.save(params);0
        boolean bool = childService.updateById(params);
        if (bool){
            logService.savelog("修改字典项值", (String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<Child>>(new AjaxMessage<Child>("1","编辑成功",params,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<Child>>(new AjaxMessage<Child>("0","编辑失败",params,null), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/deleteDateVal")
    @ApiOperation(value = "删除字典项值", notes = "删除字典项值")
    public ResponseEntity<AjaxMessage<String>> deleteDateVal(@RequestParam("id") String id) {
        boolean bool = childService.removeById(id);
        if (bool){
            logService.savelog("删除字典项值", (String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("1","删除成功",id,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("0","删除失败",id,null), HttpStatus.OK);
        }
    }
    @PostMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除字典项", notes = "删除字典项")
    public ResponseEntity<AjaxMessage<String>> delete(@PathVariable("id") String id) {
        boolean bool = dictionaryService.removeById(id);
        if (bool){
            logService.savelog("删除字典项", (String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("1","删除成功",id,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("0","删除失败",id,null), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新字典项", notes = "更新字典项")
    public ResponseEntity<AjaxMessage<Dictionary>> delete(@RequestBody Dictionary params) {
        String dataVal = dictionaryService.dataVal(params);
        if(!dataVal.equals("")){
            return new ResponseEntity<AjaxMessage<Dictionary>>(new AjaxMessage<Dictionary>("0",dataVal,params,null), HttpStatus.OK);
        }
        boolean bool = dictionaryService.updateById(params);
        if (bool){
            logService.savelog("编辑字典项", (String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<Dictionary>>(new AjaxMessage<Dictionary>("1","编辑成功",params,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<Dictionary>>(new AjaxMessage<Dictionary>("0","编辑失败",params,null), HttpStatus.OK);
        }
    }

    @GetMapping (value = "downloadExcel")
    @ApiOperation(value = "导出字典", notes = "导出字典")
    public ResponseEntity<Object> downloadExcel(@RequestParam("ids") List<String> ids) {
        InputStream inputStream = null;
        ServletOutputStream outputStream = null;
        OutputStream toClient = null;
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //读取模版文件
            ClassPathResource classPathResource = new ClassPathResource("template/template.xlsx");
            inputStream = classPathResource.getInputStream();
            XSSFWorkbook book = new XSSFWorkbook(inputStream);
            XSSFSheet sheetAt = book.getSheetAt(0);
            int i = 1;
            for (String id :ids){
                //查询主字典信息
                Dictionary byId = dictionaryService.getById(id);
                //查询子字典信息
                QueryWrapper<Child> childQueryWrapper = new QueryWrapper<>();
                childQueryWrapper.eq("pid", id);
                List<Child> list = childService.list(childQueryWrapper);
                //写入主字典信息
                XSSFRow row = sheetAt.getRow(i);
                XSSFCell cell = row.createCell(0);
                cell.setCellValue(byId.getId());
                XSSFCell cell2 = row.createCell(1);
                cell2.setCellValue(byId.getName());
                XSSFCell cell3 = row.createCell(2);
                cell3.setCellValue(byId.getDescrib());
                XSSFCell cell4 = row.createCell(3);
                cell4.setCellValue(sim.format(byId.getCreateTime()));
                XSSFCell cell5 = row.createCell(4);
                cell5.setCellValue(byId.getId());
                i++;
                //写入子字典信息

                for (Child child : list) {
                    XSSFRow row1 = sheetAt.getRow(i);
                    XSSFCell cell1 = row1.createCell(0);
                    cell1.setCellValue(child.getId());
                    XSSFCell cell12 = row1.createCell(1);
                    cell12.setCellValue(child.getName());
                    XSSFCell cell13 = row1.createCell(2);
                    cell13.setCellValue(child.getDescrib());
                    XSSFCell cell14 = row1.createCell(3);
                    cell14.setCellValue(sim.format(child.getCreateTime()));
                    XSSFCell cell15 = row1.createCell(4);
                    cell15.setCellValue(child.getId());
                    i++;
                }
                i++;
            }

            writeToExcel(response, book, "字典");
//            logService.savelog("导出字典:" + byId.getName(), (String) request.getSession().getAttribute("userName"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("导出字典失败");
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (toClient != null) {
                    toClient.close();
                }
            } catch (IOException e) {
                System.out.println("关闭流失败");
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }

    public void writeToExcel(HttpServletResponse response, Workbook wb, String fileName) {
        OutputStream os = null;
        try {
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
//            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", new String(URLEncoder.encode(fileName + ".xlsx", Charsets.UTF_8.name())
                    .getBytes(Charsets.UTF_8), Charsets.ISO_8859_1)));
            os = response.getOutputStream();
            wb.write(os);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != wb) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
