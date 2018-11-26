package com.min.controller;

import cn.com.webxml.ArrayOfString;
import cn.com.webxml.WeatherWS;
import cn.com.webxml.WeatherWSSoap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/test")
@Api(description="测试Controller")
public class WeatherController {

	@GetMapping("/a")
	@ApiOperation("测试请求")
	public @ResponseBody String getMessage(){
		return "test请求成功";
	}
	
	@GetMapping("/getWeather")
	@ApiOperation("获取天气信息")
	@ApiImplicitParam(name = "address", value = "查询地址", defaultValue = "重庆", required = false, dataType = "string", paramType = "query")
	public static List<String> getWeather(@RequestParam(defaultValue="重庆",required=false) String address){
		WeatherWS ww = new WeatherWS();
		WeatherWSSoap ws = ww.getWeatherWSSoap();
		ArrayOfString weather = ws.getWeather(address, null);
		List<String> list = weather.getString();
		return list;
	}
}
