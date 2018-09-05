package com.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.utils.CheckUtil;
import com.demo.utils.MessageUtil;
import com.demo.utils.TextMessageUtil;

@Controller
@RequestMapping("/admin")
public class WeChatApproveController {

	@GetMapping("/app")
	public void login(HttpServletRequest request,HttpServletResponse response){
		System.out.println("success");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter out = null;
		try {
			  out = response.getWriter();
			if(CheckUtil.checkSignature(signature, timestamp, nonce)){
				out.write(echostr);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
	@PostMapping("/app")
	public void getMessage(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		//将微信请求xml转为map格式，获取所需的参数
		Map<String,String> map = MessageUtil.xmlToMap(request);
		String ToUserName = map.get("ToUserName");
		String FromUserName = map.get("FromUserName");
		String MsgType = map.get("MsgType");
		String Content = map.get("Content");
		String message = null;
		//处理文本类型,回复相应的封装的内容
		if("text".equals(MsgType)){
			TextMessageUtil textMessage = new TextMessageUtil();
			String regex = "[\\u4e00-\\u9fa5]+";
			String mess = "";
			try {
				if(!Content.matches(regex)){//用户输入内容不全为汉字的话直接抛出异常
					throw new Exception();
				}
				List<String> weather = WeatherController.getWeather(Content);//获取天气信息
				String msg = weather.toString();
				if(msg.contains("免费")){//免费的总是那么有限，查询次数有限。多了明天再来
					mess = "名额有限,请明日再来(功能完善中，敬请期待...)";
				}else{
					String[] split = msg.split("。");
					//这是一个非常恶心的做法,根据返回的天气信息截取其中的一部分发送给用户
					//(输入错误的地区查询不到数据则这里会异常ArrayIndexOutOfBoundsException)
					for (int i = 0; i < 7; i++) {
						mess += split[i];
					}
				}
			} catch (Exception e) {
				//e.printStackTrace();
				mess = "输入地区可查询天气信息(功能完善中，敬请期待...)";
			}
			System.out.println(
					"发送者:"+ToUserName+"\t发送内容:"+Content+"\t发送时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			message = textMessage.initMessage(FromUserName, ToUserName,mess);
		}
		try {
			out = response.getWriter();
			out.write(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.close();
	}
}
