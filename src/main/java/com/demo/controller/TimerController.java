package com.demo.controller;

import java.util.Arrays;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TimerController {
//    @Scheduled(cron = "/5 * ?")
//    public void scheduled(){
//        System.out.println("=====>>>>>使用cron  {}"+System.currentTimeMillis());
//    }
	
//	@Value("${testParam.test}")//一.获取application.yml中的属性值。需要在类上加@Configuration
//	private String test;
//	public String getString(){
//		return this.test;
//	}
	//@Autowired
	//private Environment env;二.获取application.yml中的属性值
	//System.out.println(env.getProperty("testParam.test"));
    @Scheduled(fixedRate = 120000)
    public void scheduled() {
    	MailController.sendEmail("温江");
    }
    public static void main(String[] args) {
		String[] str = {"c","b1","a11"};
		Arrays.sort(str);
		System.out.println(str[0]+"-"+str[1]+"-"+str[2]);
	}
}