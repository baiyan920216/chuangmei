package com.chuangmei.web.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chuangmei.web.entity.AccessToken;
import com.chuangmei.web.entity.TemplateData;
import com.chuangmei.web.entity.WechatTemplate;
import com.google.common.collect.Lists;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author bai.yan
 * @date 2019/10/12 13:26
 */
public class WeChatUtil {
    public static final String users_list_URL="https://api.weixin.qq.com/cgi-bin/user?access_token=TOKEN&next_openid=OPENID";
    public static final String GET_USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**
     * get请求
     * @param url
     * @return
     */
    public static JSONObject doGetStr(String url) {
        DefaultHttpClient httpClient=new DefaultHttpClient();
        HttpGet httpGet=new HttpGet(url);
        JSONObject jsonObject=null;

        try {
            HttpResponse response=httpClient.execute(httpGet);
            HttpEntity entity=response.getEntity();
            if(entity!=null) {
                String result= EntityUtils.toString(entity,"utf-8");
                jsonObject= JSONObject.parseObject(result);

            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    /**
     * post请求
     * @param url
     * @param outStr
     * @return
     */
    public  static JSONObject doPostStr(String url,String outStr){
        DefaultHttpClient httpClient=new DefaultHttpClient();
        HttpPost httpPost=new HttpPost(url);
        JSONObject jsonObject=null;

        try {
            httpPost.setEntity(new StringEntity(outStr,"utf-8"));
            HttpResponse response=httpClient.execute(httpPost);
            String result=EntityUtils.toString(response.getEntity(),"utf-8");
            jsonObject=JSONObject.parseObject(result);
        }catch (Exception e) {
            e.printStackTrace();
        }


        return jsonObject;
    }

    /**
     * 获取微信用户账号的相关信息
     * @param openId  用户的openId，这个通过当用户进行了消息交互的时候，才有
     * @return
     */
    public static String getUserInfo(String token,String openId){
        String url = GET_USERINFO_URL.replace("ACCESS_TOKEN" , token);
        url = url.replace("OPENID" ,openId);
        JSONObject jsonObject = doGetStr(url);
        return jsonObject.toString();
    }

    /**
     * @Description: 获取用户列表
     * @param
     * @Author: bai.yan
     * @Date: 2019/10/12 11:57
     */
    public List<String> getUserList(String token) {
        String url=users_list_URL.replace("TOKEN",token ).replace("OPENID","");
        JSONObject jsonObject= doGetStr(url);
        List<String> list = null;
        if(jsonObject!=null) {
            if(jsonObject.containsKey("data")) {
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                list = JSONObject.parseArray(jsonArray.toJSONString(), String.class);
            }else {
                System.out.println("获取用户列表失败");
            }
        }
        return list;
    }


    /**
     * 用户注册成功的模板消息
     */
    public static boolean registTemplate(String token, String openid,String map,String modelId) {

        // 发送模板消息
        String resultUrl2 = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + token;
        // 封装基础数据
        String toString = template1(openid,map, modelId);

        JSONObject jsonObject = doPostStr(resultUrl2, toString);
        int result = 0;
        if (null != jsonObject) {
            if (0 != jsonObject.getIntValue("errcode")) {
                result = jsonObject.getIntValue("errcode");
                //StaticLog.error("错误 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.get("errmsg").toString());
                System.out.println(result+"模板发送失败");
                return false;
            }
        }
        System.out.println("模板发送成功");
        //StaticLog.info("模板消息发送结果：" + result);
        return true;
    }

    /**
     * @Description: sha1加密
     * @param decript
     * @Author: bai.yan
     * @Date: 2019/10/12 11:56
     */
    public static String sha1(String decript) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            System.out.println(hexString.toString());
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String template1(String openid,String map,String modelId) {
        Map<String,String> m = (Map<String,String>) JSONArray.parse(map);
        WechatTemplate wechatTemplate = new WechatTemplate();
        wechatTemplate.setTemplate_id(modelId);
        wechatTemplate.setTouser(openid);
        // wechatTemplate.setUrl("http://tw.xxx.com/member/member.html?id=" + user.getId());
        Map<String, TemplateData> mapdata = new HashMap<>();

        for(Map.Entry<String, String> entry : m.entrySet()){
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();
            System.out.println(mapKey+":"+mapValue);

            TemplateData td = new TemplateData();
            td.setValue(mapValue);
            mapdata.put(mapKey, td);
        }





        // 封装模板数据
//        if(m.get("first") != null){
//            TemplateData first = new TemplateData();
//            first.setValue(m.get("first"));
//            mapdata.put("first", first);
//        }
//        if(m.get("keyword1") != null) {
//            TemplateData keyword1 = new TemplateData();
//            keyword1.setValue(m.get("keyword1"));
//            mapdata.put("keyword1", keyword1);
//        }
//        if(m.get("keyword2") != null) {
//            TemplateData keyword2 = new TemplateData();
//            keyword2.setValue(m.get("keyword2"));
//            mapdata.put("keyword2", keyword2);
//        }
//
//        if(m.get("keyword3") != null) {
//            TemplateData keyword3 = new TemplateData();
////            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////            String format = formatter.format(new Date());
//            keyword3.setValue(m.get("keyword3"));
//            mapdata.put("keyword3", keyword3);
//        }
//
//        if(m.get("keyword4") != null) {
//            TemplateData keyword4 = new TemplateData();
//            keyword4.setValue(m.get("keyword4"));
//            mapdata.put("keyword4", keyword4);
//        }
//
//        if(m.get("remark") != null) {
//            TemplateData remark = new TemplateData();
//            remark.setValue(m.get("remark"));
//            mapdata.put("remark", remark);
//        }
        wechatTemplate.setData(mapdata);
        String toString = JSONObject.toJSONString(wechatTemplate).toString();
        return toString;
    }

    public static String template2(String openid) {
        WechatTemplate wechatTemplate = new WechatTemplate();
        wechatTemplate.setTemplate_id("YwbR5gGpOBFf0WST-9XL7hDSMePJOgTfUjWqaLvZaPs");
        wechatTemplate.setTouser(openid);
        // wechatTemplate.setUrl("http://tw.xxx.com/member/member.html?id=" + user.getId());
        Map<String, TemplateData> mapdata = new HashMap<>();
        // 封装模板数据
        TemplateData first = new TemplateData();
        first.setValue("XXX,您好,您有一条故障通知");
        first.setColor("#173177");
        mapdata.put("first", first);

        TemplateData keyword1 = new TemplateData();
        keyword1.setValue("数据采集失败");
        //keyword1.setColor("#173177");
        mapdata.put("keyword1", keyword1);

        TemplateData keyword2 = new TemplateData();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(new Date());
        keyword2.setValue(format);
        //keyword2.setColor("#173177");
        mapdata.put("keyword2", keyword2);

        TemplateData remark = new TemplateData();
        remark.setValue("请及时前去处理");
        //remark.setColor("#173177");
        mapdata.put("remark", remark);

        wechatTemplate.setData(mapdata);
        String toString = JSONObject.toJSONString(wechatTemplate).toString();
        return toString;
    }

}
