package com.min.controller;

import com.min.utils.CheckUtil;
import com.min.utils.MessageUtil;
import com.min.utils.TextMessageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class WeChatApproveController {

	@GetMapping("/app")
	public void login(HttpServletRequest request,HttpServletResponse response){
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
		String toUserName = map.get("ToUserName");
		String fromUserName = map.get("FromUserName");
		String msgType = map.get("MsgType");
		String content = map.get("Content");
		String message = null;
		//处理文本类型,回复相应的封装的内容
		if("text".equals(msgType)){
			TextMessageUtil textMessage = new TextMessageUtil();
			String regex = "[\\u4e00-\\u9fa5]+";
			String mess = "";
			try {
				if(!content.matches(regex)){//用户输入内容不全为汉字的话直接抛出异常
					throw new Exception();
				}
				List<String> weather = WeatherController.getWeather(content);//获取天气信息
				String msg = weather.toString();
				if(msg.contains("免费")){//免费次数受限
					mess = "名额有限,请明日再来(功能完善中，敬请期待...)";
				}else{
					String[] split = msg.split("。");
					//根据返回的天气信息截取其中的一部分发送给用户
					//(输入错误的地区查询不到数据则这里会异常ArrayIndexOutOfBoundsException)
					for (int i = 0; i < 7; i++) {
						mess += split[i];
					}
				}
			} catch (Exception e) {
				//e.printStackTrace();
				mess = "功能完善中，敬请期待....Jm)";
			}
			System.out.println(
					"发送者:"+toUserName+"\t发送内容:"+content+"\t发送时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			message = textMessage.initMessage(fromUserName, toUserName,mess);
		
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
