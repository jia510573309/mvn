package com.min.index;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
	
	@RequestMapping("index")
	public String getIndex() {
		return "微信公众号后台接口---最后更新于2019年1月24日14:04:23";
	}
}
