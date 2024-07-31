package com.keyware.MR.controller;

import com.keyware.MR.ScheduleTask;
import com.keyware.MR.config.AccessSystem;
import com.keyware.MR.config.AccessSystemConfig;
import com.keyware.MR.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * <p>
 * 备份恢复表 前端控制器
 * </p>
 *
 * @author caizhihui
 * @since 2023-10-19
 */
@Controller
@Api(value = "备份恢复", tags = "备份恢复")
@RequestMapping("/dbBackupRecovery")
public class DbBackupRecoveryController {
    private final static Logger logger = LoggerFactory.getLogger(DbBackupRecoveryController.class);
    //    @Value("${path.mysqlFilePath}")
    private String mysqlFilePath = AccessSystemConfig.getValueByKey("path.mysqlFilePath");
    //    @Value("${path.mysqlPath}")
    private String mysqlPath = AccessSystemConfig.getValueByKey("path.mysqlPath");
    @Autowired
    private LogService logService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @ApiOperation(value = "查看配置", notes = "查看配置")
    @PostMapping(value = "/getValue")
    public ResponseEntity getValue() {
        mysqlFilePath = AccessSystemConfig.getValueByKey("path.mysqlFilePath");
        mysqlPath = AccessSystemConfig.getValueByKey("path.mysqlPath");
        System.out.println(mysqlPath);
        System.out.println(mysqlFilePath);
        return null;
    }

    private void writeScript(String command,String fileName){
                if(new File(mysqlPath+fileName).exists()){
                    new File(mysqlPath+fileName).delete();
                }else {
                    try {
                        new File(mysqlPath).mkdirs();
                        new File(mysqlPath+fileName);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        try (FileWriter writer = new FileWriter(mysqlPath+fileName)) {
            writer.write(command);
            System.out.println("内容成功写入到文件 " + mysqlPath+fileName);
        } catch (IOException e) {
            System.out.println("写入文件时出现错误: " + e.getMessage());
        }
    }

    /*
     * Description: 系统备份
     * @param
     * @Return: {@link org.springframework.http.ResponseEntity}
     * @Author: caizhihui
     * @Date: 2023/12/13 13:56
     */
    @ApiOperation(value = "备份", notes = "备份")
    @PostMapping(value = "/backup")
    public ResponseEntity backup() {
        mysqlFilePath = AccessSystemConfig.getValueByKey("path.mysqlFilePath");
        mysqlPath = AccessSystemConfig.getValueByKey("path.mysqlPath");
        Runtime run = Runtime.getRuntime();
        try {
            InputStream inputStream = ScheduleTask.class.getClassLoader().getResourceAsStream("script/backup.bat");
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(reader);
            String backUpShell = bufferTranStr(br);
            writeScript(backUpShell,"backup.bat");
            Process exec = run.exec(mysqlPath+"backup.bat");
            exec.waitFor();
            download(response, mysqlFilePath + "uuid.sql", request);
            logService.savelog("系统备份", (String) request.getSession().getAttribute("userName"));
        } catch (IOException e) {
            logger.error("1",e);
            return new ResponseEntity<>("备份失败", HttpStatus.OK);
        } catch (InterruptedException e) {
            logger.error("1",e);
            return new ResponseEntity<>("备份失败", HttpStatus.OK);
        }
        return new ResponseEntity<>("备份成功", HttpStatus.OK);
    }

    private  String  bufferTranStr(BufferedReader br){
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString().replace("**",mysqlFilePath);
    }

    /*
     * Description: 系统恢复
     * @param
     * @Return: {@link org.springframework.http.ResponseEntity}
     * @Author: caizhihui
     * @Date: 2023/12/13 13:56
     */
    @PostMapping(value = "/recovery")
    @ApiOperation(value = "恢复", notes = "恢复")
    public ResponseEntity recovery(@RequestPart("multipartfile") MultipartFile multipartfile) {
        mysqlFilePath = AccessSystemConfig.getValueByKey("path.mysqlFilePath");
        mysqlPath = AccessSystemConfig.getValueByKey("path.mysqlPath");
        //将上传的备份文件保存到本地
        uploadFileLocal(multipartfile);
        //执行恢复脚本
        Runtime run = Runtime.getRuntime();
        try {
            InputStream inputStream = ScheduleTask.class.getClassLoader().getResourceAsStream("script/recovery.bat");
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(reader);
            String recoveryShell = bufferTranStr(br);
            System.out.println(recoveryShell);
            writeScript(recoveryShell,"recovery.bat");
            Process exec = run.exec(mysqlPath+"recovery.bat");
            exec.waitFor();
            logService.savelog("系统恢复", (String) request.getSession().getAttribute("userName"));
        } catch (IOException e) {
            return new ResponseEntity<>("恢复失败", HttpStatus.OK);
        } catch (InterruptedException e) {
            return new ResponseEntity<>("恢复失败", HttpStatus.OK);
        }
        return new ResponseEntity<>("恢复成功", HttpStatus.OK);
    }

    public void download(HttpServletResponse response, String Path, HttpServletRequest request) {
        InputStream fis = null;
        OutputStream toClient = null;
        ServletOutputStream outputStream = null;
        try {
            // path是指欲下载的文件的路径。
            File file = new File(Path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf("."));
            filename = filename.substring(0, filename.lastIndexOf("."));
            // 以流的形式下载文件。
            fis = new BufferedInputStream(new FileInputStream(Path));
            byte[] buffer = new byte[fis.available()];
            if (fis.read(buffer) > 0) {
                // 清空response
//        response.reset();
                // 设置response的Header
                // 非IE
                if (request.getHeader("user-agent").toLowerCase().contains("msie") || request.getHeader("user-agent").toLowerCase().contains("like gecko")) {
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(filename.getBytes("GB2312"), "ISO8859-1") + ext + "\"");
                } else {
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(filename.getBytes("utf-8"), "ISO8859-1") + ext + "\"");
                }
                response.addHeader("Content-Length", "" + file.length());
                response.addHeader("Access-Control-Expose-Headers", "filename");
                response.addHeader("code", "1");
                outputStream = response.getOutputStream();
                toClient = new BufferedOutputStream(outputStream);
                response.setContentType("application/octet-stream");
                toClient.write(buffer);
                toClient.flush();
            }
        } catch (Exception e) {
            logger.error("下载失败!" + e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error("关闭流失败!");
                }
            }
            if (toClient != null) {
                try {
                    toClient.close();
                } catch (IOException e) {
                    logger.error("关闭流失败!");
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    logger.error("关闭流失败!");
                }
            }
        }
    }

    private void uploadFileLocal(MultipartFile multipartFile) {
        InputStream inputStream = null;
        OutputStream out = null;
        BufferedInputStream bin = null;
        BufferedOutputStream toClient = null;
        String originalFilename = null;
        String recoveryFilePath =  mysqlFilePath;
        try {
            if (multipartFile != null) {
                originalFilename = multipartFile.getOriginalFilename();
                if (originalFilename != null) {
                    inputStream = multipartFile.getInputStream();
                    //组合文件名
                    recoveryFilePath += File.separator;
                    String fileName = multipartFile.getOriginalFilename();
                    File fileDir = new File(recoveryFilePath);
                    //如果文件夹不存在，则创建
                    if (!fileDir.exists() && !fileDir.isDirectory()) {
                        fileDir.mkdirs();
                    }
                    recoveryFilePath += "uuid.sql";
                    if (recoveryFilePath != null) {
                        out = new FileOutputStream(recoveryFilePath);
                        bin = new BufferedInputStream(inputStream);
                        toClient = new BufferedOutputStream(out);
                        //写入数据
                        int len = 0;
                        byte[] b = new byte[1024];
                        while ((len = bin.read(b)) != -1) {
                            toClient.write(b, 0, len);
                        }
                        toClient.flush();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("上传文件失败!");
        } finally {
            if (toClient != null) {
                try {
                    toClient.close();
                } catch (IOException e) {
                    logger.error("关闭流失败！");
                }
            }
            if (bin != null) {
                try {
                    bin.close();
                } catch (IOException e) {
                    logger.error("关闭流失败！");
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("关闭流失败！");
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("关闭流失败！");
                }
            }
        }
    }

}
