package com.keyware.MR.config.minio;

import com.keyware.MR.util.DateUtils;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PostPolicy;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import io.minio.http.Method;

import javax.annotation.Resource;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author:triumphxx
 * @Date:2023/5/28
 * @Time:20:59
 * @微信公众号：北漂码农有话说
 * @网站：https://blog.triumphxx.com.cn
 * @GitHub https://github.com/triumphxx
 * @Desc: 操作minio的服务
 **/
@Service
@Slf4j
public class MinIoService {
    @Resource
    private MinioClient minioClient;
    @Autowired
    private MinioConfiguration configuration;
    @Value("${spring.minio.url}")
    private String url;
    @Value("${spring.minio.bucket-name}")
    private String bucket;


    /**
     * 获取上传文件临时签名，作用是：前端获取到签名信息后可以直接将文件上传到minio
     * @param fileName
     * @param time
     * @return
     */
    public Map getPolicyUrl(String fileName, ZonedDateTime time) {
        PostPolicy postPolicy = new PostPolicy(configuration.getBucketName(), time);
        postPolicy.addEqualsCondition("key", fileName);
        try {
            Map<String, String> map = minioClient.getPresignedPostFormData(postPolicy);
            HashMap<String, String> policyMap = new HashMap<>();
            map.forEach((k,v)->{
                policyMap.put(k.replaceAll("-",""),v);
            });
            policyMap.put("host",configuration.getUrl()+"/"+configuration.getBucketName());
            return policyMap;
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param objectName
     * @param time
     * @param timeUnit
     * @return
     */
    public String getFileUrl(String objectName,int time, TimeUnit timeUnit) {
        try {
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(configuration.getBucketName())
                    .object(objectName)
                    .expiry(time, timeUnit).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 针对前端直接上传的是文件的格式
     * @param file 文件
     */
    public String uploadToFile(MultipartFile file) {
        String fileName="";
        // 使用putObject上传一个文件到存储桶中。
        try {
            InputStream inputStream = file.getInputStream();
            fileName = file.getOriginalFilename();
            fileName = DateUtils.getDateStamp().toString() + "_" + file.getOriginalFilename();
            fileName = DateUtils.datePath() + "/" + fileName;
            log.info("上传的文件名为：{}",fileName);
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(configuration.getBucketName())
                    .object(fileName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());
        } catch (Exception e){
            e.printStackTrace();
        }
        return url + "/" + bucket + "/" + fileName;
    }

    /**
     * 根据文件名访问指定有效期的文件访问链接
     * @param objectName
     * @param time
     * @param timeUnit
     * @return
     */
    public String getUrl(String objectName, int time, TimeUnit timeUnit) {
        String url = null;
        try {
            url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(configuration.getBucketName())
                    .object(objectName)
                    .expiry(time, timeUnit).build());
        }catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }
}
