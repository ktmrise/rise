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
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

public class UcloudProvider {



    @Value("${ucloud.ufile.public-key}")
    private String publicKey;
    @Value("${ucloud.ufile.private-key}")
    private String privateKey;







    public String upload(InputStream fileStream,String mediaType,String fileName) {
        // Bucket相关API的授权器
        ObjectAuthorization objectAuthorization = new UfileObjectLocalAuthorization(
                publicKey, privateKey);

        ObjectConfig config = new ObjectConfig("http://www.your_domain.com");
        File file = new File("your file path");

        String[] strings = fileName.split("\\.");
        String newFileName = null;
        if (strings.length > 1) {
            newFileName = UUID.randomUUID().toString() + "." + strings[strings.length - 1];
        } else {
            return null;
        }
        try {
            PutObjectResultBean response = UfileClient.object(objectAuthorization, config)
                    .putObject(fileStream, mediaType)
                    .nameAs(newFileName)
                    .toBucket("")
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
                    .setOnProgressListener(new OnProgressListener() {
                        @Override
                        public void onProgress(long bytesWritten, long contentLength) {

                        }
                    })
                    .execute();
        } catch (UfileClientException e) {
            e.printStackTrace();
        } catch (UfileServerException e) {
            e.printStackTrace();
        }
        return null;
    }

}
