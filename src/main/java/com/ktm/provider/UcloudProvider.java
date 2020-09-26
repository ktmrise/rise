package com.ktm.provider;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.BucketAuthorization;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileBucketLocalAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import cn.ucloud.ufile.http.OnProgressListener;
import com.ktm.Exception.CustomizeErrorCode;
import com.ktm.Exception.CustomizeException;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;


@Component
public class UcloudProvider {


    @Value("${ucloud.ufile.public-key}")
    private String publicKey;

    @Value("${ucloud.ufile.private-key}")
    private String privateKey;

    @Value("${ucloud.ufile.bucket-name}")
    private String bucketName;

    @Value("${ucloud.ufile.customHost}")
    private String customHost;

    @Value("${ucloud.ufile.expire}")
    private int expireTime;


    public String upload(InputStream fileStream, String mediaType, String fileName) {
        // Bucket相关API的授权器
        ObjectAuthorization objectAuthorization = new UfileObjectLocalAuthorization(
                publicKey, privateKey);

        ObjectConfig config = new ObjectConfig(customHost);

        String[] strings = fileName.split("\\.");
        String newFileName;
        if (strings.length > 1) {
            newFileName = UUID.randomUUID().toString() + "." + strings[strings.length - 1];
        } else {
            return null;
        }
        try {
            PutObjectResultBean response = UfileClient.object(objectAuthorization, config)
                    .putObject(fileStream, mediaType)
                    .nameAs(newFileName)
                    .toBucket(bucketName)
                    /**
                     * 是否上传校验MD5, Default = true
                     */
                    //  .withVerifyMd5(false)
                    /**
                     * 指定progress callback的间隔, Default = 每秒回调
                     */
                    //  .withProgressConfig(ProgressConfig.callbackWithPercent(10))
                    /**
                     * 配置进度监听
                     */
                    .setOnProgressListener((bytesWritten, contentLength) -> {

                    })
                    .execute();
            if (response != null && response.getRetCode() == 0) {
                return UfileClient.object(objectAuthorization, config).getDownloadUrlFromPrivateBucket(newFileName, bucketName, expireTime).createUrl();
            } else {
                throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
            }
        } catch (UfileClientException | UfileServerException e) {
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }


    }
}



