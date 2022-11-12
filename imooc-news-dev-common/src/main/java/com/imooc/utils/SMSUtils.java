package com.imooc.utils;

import com.aliyun.tea.TeaException;
import com.imooc.utils.extend.AliyunResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SMSUtils {

    @Autowired
    public AliyunResource aliyunResource;

    public void sendSMS(String mobile, String code) throws Exception {

        com.aliyun.dysmsapi20170525.Client client = SMSUtils.createClient(aliyunResource.getAccessKeyID(),
                aliyunResource.getAccessKeySecret());
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest =
                new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                .setPhoneNumbers(mobile)
                .setTemplateParam("{\"code\":\"" + code + "\"}");
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            client.sendSmsWithOptions(sendSmsRequest, runtime);
        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
//                                                aliyunResource.getAccessKeyID(),
//                                                aliyunResource.getAccessKeySecret());
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        CommonRequest request = new CommonRequest();
//        request.setSysMethod(MethodType.POST);
//        request.setSysDomain("dysmsapi.aliyuncs.com");
//        request.setSysVersion("2017-05-25");
//        request.setSysAction("SendSms");
//        request.putQueryParameter("RegionId", "cn-hangzhou");
//
//        request.putQueryParameter("PhoneNumbers", mobile);
//        request.putQueryParameter("SignName", "风间影月");
//        request.putQueryParameter("TemplateCode", "SMS_183761535");
//        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
//
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret)
            throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }


}
