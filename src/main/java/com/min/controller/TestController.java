package com.min.controller;

import com.alibaba.fastjson.JSONObject;
import com.min.form.UserForm;
import com.min.menu.ClickButton;
import com.min.menu.ViewButton;
import com.min.service.UserService;
import com.min.utils.HttpUtils;
import com.min.utils.R;
import com.min.utils.ValidatorUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class TestController {

	@Autowired
	private UserService userService;
	

	@RequestMapping("test")
	public @ResponseBody String test() {
		return "mvn -- request Success ! ! !update2 at 2018年11月29日16:29:25";
	}
	
	@RequestMapping("login")
	public @ResponseBody String login(@RequestBody UserForm form) {
		ValidatorUtils.check(form);
		System.err.println("------小程序测试请求成功-------username:"+form.getUserName()+"password:"+form.getUserPwd());
		return "true";
	}
	
	@GetMapping("ball")
	public @ResponseBody String ball() {
		//双色球
		List<Integer> ballNums = new ArrayList<>();
		//红球
		for (int i = 0; i < 6; i++) {
			ballNums.add((int) (Math.random() * 33 + 1));
			Collections.sort(ballNums);
			for (int j = 1; j < ballNums.size(); j++) {
				if (ballNums.get(j) == ballNums.get(j - 1)) {//判断重复
					i--;
					ballNums.remove(j);
					break;
				}
			}
		}
		//蓝球
		ballNums.add((int) (Math.random() * 16 + 1));
		String s = ballNums.toString();
		return s;
	}
	
	@RequestMapping("user")
	public @ResponseBody R getUser() {
		System.err.println("~~~测试用户数据~~~");
		return R.ok(userService.selectUser());
	}
	
	
	public static void main(String[] args) {
		ClickButton cbt = new ClickButton();
		cbt.setKey("image");
		cbt.setName("回复图片");
		cbt.setType("click");

		ViewButton vbt = new ViewButton();
		vbt.setUrl("http://www.baidu.com");
		vbt.setName("百度");
		vbt.setType("view");

		JSONArray sub_button = new JSONArray();
		sub_button.add(cbt);
		sub_button.add(vbt);

		JSONObject buttonOne = new JSONObject();
		buttonOne.put("name", "菜单");
		buttonOne.put("sub_button", sub_button);

		JSONArray button = new JSONArray();
		button.add(vbt);
		button.add(buttonOne);
		button.add(cbt);

		JSONObject menujson = new JSONObject();
		menujson.put("button", button);
		System.out.println(menujson);
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
				+ "15_U28ng0DN1vFiq2WjKfcp-HM2OWfRZizuxFam8ZQZRDc2fgFtTyl7apo6iFrFDKySQgaq0H3Pc1rqh1moljyI4bXb0LFy_FtkoVb0cXs_szg40w7O1SwW6TdAwlDGF2GL9muYrzjho9NOy1kNXVViABAMKK";

		
		try {
			String rs = HttpUtils.sendPost(url, menujson.toJSONString());
			System.out.println(rs);
		} catch (Exception e) {
			System.out.println("请求错误！");
		}

	}
}
