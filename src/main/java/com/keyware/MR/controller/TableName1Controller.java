package com.keyware.MR.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.keyware.MR.entity.Data;
import com.keyware.MR.entity.TableName2;
import com.keyware.MR.service.DataService;
import com.keyware.MR.service.TableName2Service;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.keyware.MR.service.TableName1Service;
import com.keyware.MR.entity.TableName1;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.SpinnerUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

import static org.apache.commons.io.FilenameUtils.getName;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yaojz
 * @since 2023-04-06
 */
@Controller
@RequestMapping("/tableName")
public class TableName1Controller {


    @Autowired
    private TableName1Service tableName1Service;
    @Autowired
    private TableName2Service tableName2Service;
//    static Map<String,String> map = new HashMap<>();
    @Autowired
    private DataService dataService;
    @GetMapping(value = "/")
    public ResponseEntity<Page<TableName1>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<TableName1> aPage = tableName1Service.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TableName1> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(tableName1Service.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/getbyap/{ap}")
    public ResponseEntity<TableName1> getByap(@PathVariable("ap") String ap) {
        QueryWrapper<TableName1> wrapper = new QueryWrapper<>();
        wrapper.eq("ap",ap);
        wrapper.orderByDesc("ap");
        return new ResponseEntity<>(tableName1Service.getOne(wrapper), HttpStatus.OK);
    }
    @GetMapping(value = "/list")
    public ResponseEntity<List<TableName1>> getlist() {
        return new ResponseEntity<>(tableName1Service.list(), HttpStatus.OK);
    }

    @GetMapping(value = "/zhengli")
    public ResponseEntity<List<TableName1>> zhengli(HttpServletResponse response) {



//        dataPerfect();

//        return null;
//
            List<TableName2> list = tableName2Service.list();
        ExcelWriter excelWriter;
        String fileName = "数据完善度";
        try {
            fileName = URLEncoder.encode(fileName, "utf-8");
//            response.addHeader("Access-Control-Expose-Headers","Authorization");
//            response.addHeader("Authorization", fileName  + ".xlsx");
            if (response != null) {
                response.setContentType("application/vnd.ms-excel");
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-disposition", "attachment;filename=" + fileName);
                response.addHeader("Access-Control-Expose-Headers", "filename");
                excelWriter = EasyExcel.write(response.getOutputStream()).build();
            } else{
                excelWriter = EasyExcel.write("").build();}
            WriteSheet writeSheet = EasyExcel.writerSheet(0, "数据").head(TableName2.class).build();
            excelWriter.write(list, writeSheet);

            //千万别忘记关流,finish会帮忙关流
            excelWriter.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
//            i = new BigDecimal(i).add(new BigDecimal(0.01)).doubleValue();
        }
//        List<Data> list1 = dataService.list(wrapper.lambda().eq(Data::getAp,"101"));
//         isnulls(list1);






    public void dataPerfect() {
        TableName2 tableName2 = new TableName2();

        QueryWrapper<TableName1> wrapper = new QueryWrapper<>();

        Double i=0.00;
        for ( i =0.00  ; i<12000 ;i++ ) {

            wrapper.eq("ap", String.valueOf(i / 100));
            List<TableName1> list = tableName1Service.list(wrapper);
            wrapper.clear();
            System.out.println(i / 100 + ":" + list.size());
            if (list.size() != 0) {
                isnulltablename(list);
            }
        }
    }
    public void average(){

    }
    public void isnulltablename(List<TableName1> tableName1List){
        Data data = new Data();
        TableName1 tableName1 = new TableName1();
        for (TableName1 tableName1for : tableName1List) {
            List<Field> fields = Arrays.asList(tableName1for.getClass().getDeclaredFields());
//            System.out.println(fields);
            for (Field f : fields) {
                f.setAccessible(true);
//                /**
//                  excludeFieldNames.contains(f.getName()   该字段为被校验的字段
//                  f.get(object) != null     该字段是否存在
//                  StringUtils.isNotBlank(f.get(object).toString()) 该字段是否为空

                try {
                    String methodNameGet = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                    Method methodGet = tableName1.getClass().getDeclaredMethod(methodNameGet);
                    if (f.get(tableName1for) != null) {
                        if (methodGet.invoke(tableName1) == null || Double.valueOf((String) methodGet.invoke(tableName1)) > Double.valueOf((String) f.get(tableName1for))) {
                            if (f.get(tableName1for) != null && Double.valueOf((String)f.get(tableName1for))>0.00) {
                                String methodNameSet = "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                                try {
                                    Method methodSet = tableName1.getClass().getDeclaredMethod(methodNameSet, new Class[]{String.class});
                                    methodSet.invoke(tableName1, f.get(tableName1for));
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        TableName2 tableName2 = new TableName2();
        BeanUtils.copyProperties(tableName1,tableName2);
        tableName2Service.save(tableName2);
        }
    public void isnulls(List<Data> datalist){
        Data data = new Data();
        TableName1 tableName1 = new TableName1();
        for (Data datafor : datalist){
            List<Field> fields = Arrays.asList(data.getClass().getDeclaredFields());
//            System.out.println(fields);
            for (Field f : fields) {
                f.setAccessible(true);
//                /**
//                  excludeFieldNames.contains(f.getName()   该字段为被校验的字段
//                  f.get(object) != null     该字段是否存在
//                  StringUtils.isNotBlank(f.get(object).toString()) 该字段是否为空

                try {
                    String methodNameGet = "get" + f.getName().substring(0,1).toUpperCase()+f.getName().substring(1);
                        Method methodGet =  data.getClass().getDeclaredMethod(methodNameGet);
                        if (f.get(datafor)!=null){
                            if (methodGet.invoke(data)==null||Double.valueOf((String) methodGet.invoke(data)) > Double.valueOf((String) f.get(datafor))){
                                if (f.get(datafor)!=null){
                                    String methodNameSet = "set" + f.getName().substring(0,1).toUpperCase()+f.getName().substring(1);
                                    try{
                                        Method methodSet =  data.getClass().getDeclaredMethod(methodNameSet,new Class[]{String.class});
                                        methodSet.invoke(data, f.get(datafor));
                                    }catch(Exception ex){
                                        ex.printStackTrace();
                                    }
                                }
                             }
                        }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }


        }

//        List<Field> fields = Arrays.asList(data.getClass().getDeclaredFields());
//        System.out.println(fields);
//        for (Field f : fields) {
//            f.setAccessible(true);
////                /**
////                  excludeFieldNames.contains(f.getName()   该字段为被校验的字段
////                  f.get(object) != null     该字段是否存在
////                  StringUtils.isNotBlank(f.get(object).toString()) 该字段是否为空
//
//            try {
//                if (f.get(data)!=null){
//                if (StringUtils.isNotBlank(f.get(data).toString())) {
//                    System.out.println("===");
//                     }
//                }
//
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//        }

        TableName1 name = tableName1;
        BeanUtils.copyProperties(data,name);
        System.out.println(name);
        System.out.println(data);
        if (name!=null){

            try {
                tableName1Service.save(name);
            }catch (Exception e){
                throw e;
            }



        }
//        System.out.println(data);
    }




//        List<Field> fields = Arrays.asList(data.getClass().getDeclaredFields());
//        System.out.println(fields);
//        for (Field f : fields) {
//            f.setAccessible(true);
////                /**
////                  excludeFieldNames.contains(f.getName()   该字段为被校验的字段
////                  f.get(object) != null     该字段是否存在
////                  StringUtils.isNotBlank(f.get(object).toString()) 该字段是否为空
//
//            try {
//                if (f.get(data)!=null){
//                if (StringUtils.isNotBlank(f.get(data).toString())) {
//                    System.out.println("===");
//                }
//                }
//
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//
//}

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody TableName1 params) {
        tableName1Service.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        tableName1Service.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> delete(@RequestBody TableName1 params) {
        tableName1Service.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
