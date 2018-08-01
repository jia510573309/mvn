package com.demo.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.webxml.ArrayOfString;
import cn.com.webxml.WeatherWS;
import cn.com.webxml.WeatherWSSoap;


@RestController
@RequestMapping("/test")
@Api(description="测试Controller")
public class TestController {

	@GetMapping("/getHello")
	@ApiOperation("测试请求")
	public  String getMessage(){
		return "Hello.";
	}
	
	@GetMapping("/getWeather")
	@ApiOperation("获取天气信息")
	@ApiImplicitParam(name = "address", value = "查询地址", defaultValue = "重庆", required = false, dataType = "string", paramType = "query")
	public List<String> getWeather(@RequestParam(defaultValue="重庆",required=false) String address){
		WeatherWS ww = new WeatherWS();
		WeatherWSSoap ws = ww.getWeatherWSSoap();
		ArrayOfString weather = ws.getWeather(address, null);
		List<String> list = weather.getString();
		return list;
		
	}
}
