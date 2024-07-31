package com.keyware.MR.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.keyware.MR.entity.FailureAnalysisInfo;
import com.keyware.MR.entity.TableName1;
import com.keyware.MR.entity.TableName2;
import com.keyware.MR.service.LogService;
import com.keyware.MR.util.AjaxMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.keyware.MR.service.DataService;
import com.keyware.MR.entity.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Yaojz
 * @since 2023-04-06
 */
@Controller
@Api(value = "实验数据相关", tags = "实验数据")
@RequestMapping("/data")
public class DataController {


    @Autowired
    private DataService dataService;
    @Autowired
    private LogService logService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<Data>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Data> page = dataService.page(new Page<>(current, pageSize));
//        Page<Data> aPage = dataService.page(new Page<>(current, pageSize));
        //保存操作日志
        logService.savelog("查询实验数据","");
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Data> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(dataService.getById(id), HttpStatus.OK);
    }

    @ApiOperation("根据油门杆参数")
    @GetMapping(value = "/getbyap/{ap}")
    public ResponseEntity<Object> getByap(@PathVariable("ap") String ap) {
        QueryWrapper<Data> wrapper = new QueryWrapper<>();
        wrapper.eq("ap", ap);
        //保存操作日志
        logService.savelog("查询油门杆参数","");
        return new ResponseEntity<>(new AjaxMessage<Data>("1", "成功", dataService.getOne(wrapper), null), HttpStatus.OK);
    }

    @ApiOperation("实验数据集合")
    @GetMapping(value = "/list")
    public ResponseEntity<Object> getlist() {
        //保存操作日志
        logService.savelog("查询实验数据集合","");
        return new ResponseEntity<>(new AjaxMessage<List<Data>>("1", new Data().getCount().toString(), dataService.list(), null), HttpStatus.OK);
    }

    @ApiOperation("实验数据集合")
    @GetMapping(value = "/listForUnity")
    public ResponseEntity<Object> getlistForUnity() {
        HashMap<String, Data> dataHashMap = new HashMap<>();
        List<Data> dataList = dataService.list();

        for (int i = 0; i < 4; i++) {
            dataHashMap.put("data" + i, dataList.get(i));
        }
        //保存操作日志
        logService.savelog("查询实验数据集合","");
        return new ResponseEntity<>(new AjaxMessage<HashMap<String, Data>>("1", dataList.get(0).getCount().toString(), dataHashMap, null), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Data params) {
        if (dataService.save(params)) {
            //保存操作日志
            logService.savelog("创建实验数据"+params.getBianma()+" 成功","");
            return new ResponseEntity<>(new AjaxMessage<Data>("1", "创建成功", params, null), HttpStatus.OK);
        }
        logService.savelog("创建实验数据"+params.getBianma()+" 失败","");
        return new ResponseEntity<>(new AjaxMessage<Data>("0", "创建失败", params, null), HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        Data byId = dataService.getById(id);
        if (dataService.removeById(id)) {
            logService.savelog("删除实验数据"+byId.getBianma()+" 成功","");
            return new ResponseEntity<>(new AjaxMessage<String>("1", "删除成功", id, null), HttpStatus.OK);
        }
        logService.savelog("删除实验数据"+byId.getBianma()+" 失败","");
        return new ResponseEntity<>(new AjaxMessage<String>("0", "删除失败", id, null), HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> delete(@RequestBody Data params) {
        if (dataService.updateById(params)) {
            logService.savelog("更新实验数据"+params.getBianma()+" 成功","");
            return new ResponseEntity<>(new AjaxMessage<Data>("1", "更新成功", params, null), HttpStatus.OK);
        }
        logService.savelog("更新实验数据"+params.getBianma()+" 失败","");
        return new ResponseEntity<>(new AjaxMessage<Data>("0", "更新失败", params, null), HttpStatus.OK);

    }

    @PostMapping(value = "/nullgai")
    public ResponseEntity<Object> nullgai() {
        curveAdjustment();
        return new ResponseEntity<>(new AjaxMessage<Data>("0", "更新失败", null, null), HttpStatus.OK);

    }

    public void curveAdjustment(){
        List<Data> dataList = dataService.list();
        for (int i = 0 ; i< dataList.size() - 1; i ++) {
            if ( dataList.get(i).getTFOUR()/* =======*/ == null ) {
                int s = i - 3;
                int i1 = (dataList.size() - 3);
                if (s<i1){
                String a2 = dataList.get(s).getTFOUR/* =======*/();
                    dataList.get(i).setTFOUR(a2);/* =======*/
                dataService.updateById(dataList.get(i));}

            }
        }
    }
    public void isnulltablename() {

        for (Data data : dataService.list()) {

            List<Field> fields = Arrays.asList(data.getClass().getDeclaredFields());
//            System.out.println(fields);
            for (Field f : fields) {
                f.setAccessible(true);
//                /**
//                  excludeFieldNames.contains(f.getName()   该字段为被校验的字段
//                  f.get(object) != null     该字段是否存在
//                  StringUtils.isNotBlank(f.get(object).toString()) 该字段是否为空
                try {
                    String methodNameGet = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                    Method methodGet = data.getClass().getDeclaredMethod(methodNameGet);
                    if (methodGet.invoke(data) == null) {
                        String methodNameSet = "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                        Method methodSet = data.getClass().getDeclaredMethod(methodNameSet, new Class[]{String.class});
                        methodSet.invoke(data, "0");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            dataService.updateById(data);
//            System.out.println(data);
        }

//        if ( dataList.get(i).getA2/* =======*/() == null ) {
//            if (i - 4 > 0){
//                int s = (int) ((Math.random()*100)%4) ;
//                System.out.println(  s + "====");
//                String a2 = dataList.get(i-s).getA2/* =======*/();
//                System.out.println(i-s+"====="+a2 +"========"+i);
//                dataList.get(i).setA2/* =======*/(a2);
//                dataService.updateById(dataList.get(i));
//            }



//        List<Data> dataList = dataService.list();
//        for (int i = 60 ; i< dataList.size() - 1; i ++) {
//            BigDecimal subtract = new BigDecimal(dataList.get(i - 30).getGTHz()).subtract(new BigDecimal(dataList.get(i - 29).getGTHz()));
//            System.out.println(subtract+"---");
//            int s = i - 1;
//            System.out.println(s+"===");
//            String gtHz = dataList.get(s).getGTHz();
////                System.out.println(dataList.get(s)+"==="+gtHz);
//            BigDecimal subtract1 = new BigDecimal(gtHz)
//                    .add(subtract);
//            System.out.println(subtract1+"++++");
//            dataList.get(i).setGTHz(subtract1.toString());/* =======*/
////                dataService.updateById(dataList.get(i));
//
//        }

    }
}
