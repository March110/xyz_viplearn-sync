package com.vs.tools;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SendMessageTools {

  /** 华兴短信接口工具类. */
  public static String sendMessageForHX(String phoneNo, int type) {
    if (SendMessageTools.valPhoneNo(phoneNo)) {
      return null;
    }

    String strReg = "101100-WEB-HUAX-770301";
    String strPwd = "SGOIMFQM";

    //子通道号，可为空（预留参数一般为空）
    String strSourceAdd = null;
    //内容
    String strContent = null;
    //验证码
    int mobileCode = 0;

    mobileCode = RandomNoTools.getRandomNo();

    //模板
    try {
      if (type == 1) {
        strContent = HttpSend.paraTo16("您的手机注册验证码为：" + mobileCode + "，请在5分钟内输入。如非本人操作，请忽略本短信【超能学】");
      }
      if (type == 2) {
        strContent = HttpSend.paraTo16("您找回密码的验证码为：" + mobileCode + "，请在5分钟内输入。如非本人操作，请忽略本短信【超能学】");
      }
      if (type == 3) {
        strContent = HttpSend.paraTo16("您的手机登录验证码为：" + mobileCode + "，请在5分钟内输入。如非本人操作，请忽略本短信【超能学】");
      }
      if (type == 4) {
        strContent = HttpSend.paraTo16("您的手机绑定验证码为：" + mobileCode + "，请在5分钟内输入。如非本人操作，请忽略本短信【超能学】");
      }
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    //请求地址
    String strSmsUrl = "http://www.stongnet.com/sdkhttp/sendsms.aspx";
    //清秋参数
    String strSmsParam =
        "reg="
            + strReg
            + "&pwd="
            + strPwd
            + "&sourceadd="
            + strSourceAdd
            + "&phone="
            + phoneNo
            + "&content="
            + strContent;
    //返回值
    String strRes = null;

    //发送短信
    strRes = HttpSend.postSend(strSmsUrl, strSmsParam);
    String[] result = strRes.split("&");
    if ("result=0".equals(result[0])) {
      return String.valueOf(mobileCode);
    }
    return null;
  }

  /** 集团短信接口工具类. */
  public static String sendMessageForXDF(String phoneNo, int type, String appVersion) {
    try {
      //内容
      String strContent = null;
      //验证码
      int mobileCode = 0;

      mobileCode = RandomNoTools.getRandomNo();

      //模板
      if (type == 1) {
        strContent = "您正在注册新账号，验证码" + mobileCode + "。【66学网】";
      }
      if (type == 2) {
        strContent = "您正在找回登录密码，验证码" + mobileCode + "。【66学网】";
      }
      if (type == 3) {
        strContent = "您正在重置登录密码，验证码 " + mobileCode + "。【66学网】";
      }

      String result = null;
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      java.util.Date currentTime = new java.util.Date(); //得到当前系统时间
      String time = formatter.format(currentTime); //将日期时间格式化
      String method = "SendSmsV6"; //方法名称，固定值
      String appId = "90162"; //本应用 通行证appId，写在配置文件中，测试环境appId：101
      String appKey =
          "u2-66xue-67d58abb-c797-47c3-ae7f-9d908fccaf5e".substring(0, 5)
              + "@#$_v"; //本应用 通行证appKey，写在配置文件中，进行混淆处理，测试环境（未混淆前）：test_api_AppKey

      //state安全码，每次生成一个新的随机guid，不能去掉guid中间的中划线（例如：a0287faf-898b-4732-880d-bf984eff8823）
      //state是一次性参数，不允许重复使用
      String state = java.util.UUID.randomUUID().toString();
      String schoolId = "1005"; //报名系统学校Id，其它系统由中心单独分配一个schoolId，测试环境：1008
      String dept = "国外考试部"; //部门名称
      //发短信业务模块备注，如果一个系统中有多个模块都需要发短信，方便识别是哪个模块发出的短信，例如：Login、ResetPwd、Order
      String memo = "Register";
      //验签
      String signText = (method + appId + phoneNo + schoolId + state + time + appKey).toLowerCase();
      String sign = MD5Util.getMD5String(signText).toUpperCase(); //签名
      CloseableHttpClient httpClient = HttpClients.createDefault();
      HttpPost post = new HttpPost("http://smsapi.xdf.cn/api/apis/apiv5.ashx");
      //填充表单
      List<org.apache.http.NameValuePair> form = new ArrayList<org.apache.http.NameValuePair>();
      form.add(new BasicNameValuePair("method", method));
      form.add(new BasicNameValuePair("appId", appId));
      form.add(new BasicNameValuePair("mobile", phoneNo));
      form.add(new BasicNameValuePair("msg", strContent));
      form.add(new BasicNameValuePair("schoolId", schoolId));
      form.add(new BasicNameValuePair("dept", dept));
      form.add(new BasicNameValuePair("memo", memo));
      form.add(new BasicNameValuePair("state", state));
      form.add(new BasicNameValuePair("time", time));
      form.add(new BasicNameValuePair("sign", sign));
      UrlEncodedFormEntity urlEntity = new UrlEncodedFormEntity(form, Consts.UTF_8);
      post.setEntity(urlEntity);
      HttpResponse response = httpClient.execute(post);
      HttpEntity entity = response.getEntity();
      result = EntityUtils.toString(entity, "utf-8");
      System.out.println(result);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /** 阿里云短信接口工具类. */
  public static String sendMessageForAli(String phoneNo, int type, String appVersion) {
    try {
      if (SendMessageTools.valPhoneNo(phoneNo)) {
        return null;
      }

      //内容
      String strContent = "";
      //模板
      String strModel = "";
      //验证码
      int mobileCode = 0;

      mobileCode = RandomNoTools.getRandomNo();

      if (type == 1) {
        strModel = "SMS_120125412";
        strContent = "您的手机注册验证码为：${mobileCode}，请在5分钟内输入。如非本人操作，请忽略本短信";
      }
      if (type == 2) {
        strModel = "SMS_120130355";
        strContent = "您找回密码的验证码为：${mobileCode}，请在5分钟内输入。如非本人操作，请忽略本短信";
      }
      if (type == 3) {
        strModel = "SMS_120130357";
        strContent = "您的手机登录验证码为：${mobileCode}，请在5分钟内输入。如非本人操作，请忽略本短信";
      }
      if (type == 4) {
        strModel = "SMS_120130358";
        strContent = "您的手机绑定验证码为：${mobileCode}，请在5分钟内输入。如非本人操作，请忽略本短信";
      }

      //产品名称:云通信短信API产品,开发者无需替换
      String product = "Dysmsapi";
      //产品域名,开发者无需替换
      String domain = "dysmsapi.aliyuncs.com";

      //此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
      String accessKeyId = "LTAIP9X58YCkpY0K";
      String accessKeySecret = "e5MfOWxUUR1zmX82fsLNBZYnTPBsdQ";

      //可自助调整超时时间
      System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
      System.setProperty("sun.net.client.defaultReadTimeout", "10000");

      //初始化acsClient,暂不支持region化
      IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
      DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
      IAcsClient acsClient = new DefaultAcsClient(profile);

      //组装请求对象-具体描述见控制台-文档部分内容
      SendSmsRequest request = new SendSmsRequest();
      //必填:待发送手机号
      request.setPhoneNumbers(phoneNo);
      //必填:短信签名-可在短信控制台中找到
      request.setSignName("超能学");
      //必填:短信模板-可在短信控制台中找到
      request.setTemplateCode(strModel);
      //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
      request.setTemplateParam("{\"mobileCode\":" + mobileCode + "}");

      //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
      //request.setSmsUpExtendCode("90997");

      //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
      request.setOutId("yourOutId");

      //hint 此处可能会抛出异常，注意catch
      SendSmsResponse response = acsClient.getAcsResponse(request);
      System.out.println("短信接口返回的数据----------------");
      System.out.println("Code=" + response.getCode());
      System.out.println("Message=" + response.getMessage());
      System.out.println("RequestId=" + response.getRequestId());
      System.out.println("BizId=" + response.getBizId());
      return String.valueOf(mobileCode);
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  public static boolean valPhoneNo(String phoneNo) {
    boolean flag = false;
    PropertiesTools pt = new PropertiesTools();
    String tmp = pt.getValue("valPhoneNo", "phoneNo");
    String[] phoneNos = tmp.split(",");
    for (int i = 0; i < phoneNos.length; i++) {
      if (phoneNo.equals(phoneNos[i])) {
        flag = true;
        return flag;
      }
    }
    return flag;
  }

  public static void main(String[] strings){
    String code = sendMessageForAli("13681109674", 3, null);
    System.out.println(code);
  }
}
