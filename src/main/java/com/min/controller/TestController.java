package com.min.controller;

import com.alibaba.fastjson.JSONObject;
import com.min.menu.ClickButton;
import com.min.menu.ViewButton;
import com.min.service.UserService;
import com.min.utils.HttpUtils;
import com.min.utils.R;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class TestController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("index")
	public String getIndex() {
		return "index.html";
	}

	@RequestMapping("test")
	public @ResponseBody String test() {
		return "mvn -- request Success ! ! !update2 at 2018年11月28日15:38:46";
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
