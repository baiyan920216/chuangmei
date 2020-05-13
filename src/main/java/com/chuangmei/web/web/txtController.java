package com.chuangmei.web.web;

import com.chuangmei.web.entity.Company;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author bai.yan
 * @date 2019/10/11 17:46
 */
@Controller
public class txtController {

    @RequestMapping("/MP_verify_G8Vhpf02RreldR3U.txt")
    @ResponseBody
    public String getTxt(){

        return "G8Vhpf02RreldR3U";
    }
}
