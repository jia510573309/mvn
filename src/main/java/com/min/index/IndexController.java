package com.min.index;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
	
	@RequestMapping("index")
	public String getIndex() {
		return "微信公众号后台接口--- update by 2018年12月6日12:34:40";
	}
	
}
