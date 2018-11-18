package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.utils.PinYinUtil;


@Controller
@RequestMapping("/")
public class TestController {

	@RequestMapping("index")
	public String getIndex(){
		return "index.html";
	}
	
	@RequestMapping("test")
	public @ResponseBody String test(){
		return "mvn -- request Success ! ! !";
	}
	
	public static void main(String[] args) {
		String s = "加多宝";
		String firstSpell = PinYinUtil.getFirstSpell(s);
		System.out.println(firstSpell);
	}
}
