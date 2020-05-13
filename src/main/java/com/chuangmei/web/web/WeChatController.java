package com.chuangmei.web.web;

import com.alibaba.fastjson.JSONObject;
import com.chuangmei.web.entity.AccessToken;
import com.chuangmei.web.entity.Company;
import com.chuangmei.web.entity.Person;
import com.chuangmei.web.repository.CompanyDao;
import com.chuangmei.web.repository.PersonDao;
import com.chuangmei.web.utils.RedisUtils;
import com.chuangmei.web.utils.WeChatUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author bai.yan
 * @date 2019/10/9 14:26
 */
@Controller
@RequestMapping("weChat")
public class WeChatController {
    public static final String APPID="wxa07b7706a14c06b7";
    public static final String APPSECRET="a0af43c009cf70f38c8fc57d418e7f74";
    public static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private PersonDao personDao;
    /**
     * 微信URL接入验证
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @RequestMapping("/test")
    @ResponseBody
    public String validate(String signature, String timestamp, String nonce, String echostr) {
        //1. 将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = {timestamp, nonce, "weChat"};
        Arrays.sort(arr);
        //2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder sb = new StringBuilder();
        for (String temp : arr) {
            sb.append(temp);
        }
        //3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        String sha1 = WeChatUtil.sha1(sb.toString());
        if (sha1.equals(signature)) {
            //接入成功
            return echostr;
        }
        //接入失败
        return null;
    }

    /**
     * @param request
     * @param model
     * @Description: 跳转到员工绑定下拉页
     * @Author: bai.yan
     * @Date: 2019/10/12 11:56
     */
    @RequestMapping("/getSelect")
    public String getSelect(HttpServletRequest request, Model model) {
        String code = request.getParameter("code");
        String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code".replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code);
        JSONObject jsonObj = WeChatUtil.doGetStr(URL);
        String openid = jsonObj.get("openid").toString();
        String token = getAccessToken().getToken();
//        String userInfo = WeChatUtil.getUserInfo(token, openid);
//        List<Company> list = companyDao.findAll();
        model.addAttribute("openid", openid);
//        model.addAttribute("list", list);
        return "weChat/select";
    }

    @RequestMapping("/sendTemplate")
    @ResponseBody
    public String sendTemplate(String tel,String map,String modelId){
        String token = getAccessToken().getToken();
        List<Person> list = personDao.getOpenidTel(tel);
        if(list.size()>0){
            boolean template = WeChatUtil.registTemplate(token, list.get(0).getOpenid(), map,modelId);
            if(template){
                return "success";
            }else {
                return "failare";
            }
        }
        return "failare";
    }

    /**
     *
     * 获取access_token
     * @return
     */
    public AccessToken getAccessToken() {
        AccessToken tokens = redisUtils.getAccessTokenInRedis("tokens");
        if(tokens != null){
            return tokens;
        }
        AccessToken token = new AccessToken();
        String url=ACCESS_TOKEN_URL.replace("APPID",APPID ).replace("APPSECRET",APPSECRET);
        JSONObject jsonObject= WeChatUtil.doGetStr(url);
        if(jsonObject!=null) {
            if(jsonObject.containsKey("access_token")) {
                token.setToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getIntValue("expires_in"));
                System.out.println(jsonObject.getString("access_token"));
            }else {
                System.out.println("获取access_token失败");
            }
        }

        // 将access_token信息翻入缓存
        redisUtils.putAccessTokenInRedis("tokens", token);
        return token;
    }
}
