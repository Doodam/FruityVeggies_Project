package com.fruityveggies.www.web;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fruityveggies.www.dto.RescueOrderDto;
import com.fruityveggies.www.repository.rescue.RescueOrder;
import com.fruityveggies.www.service.RescueOrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class RescueOrderController {

	private final RescueOrderService rescueOrderService;
   
   @GetMapping("/rescueorder/rescue_order")
   public String rescuorder(@RequestParam String resId, Model model) {
      
	  log.info("rescuorder get");
	  
	  log.info("resId={}", resId);
	  
	  model.addAttribute("resId",resId);
	  
      return "/rescueorder/rescue_order";
   }
   
   @GetMapping("/rescueorder/rescue_order2")
   public String rescuorder2(Model model, @RequestParam String id) {
      
	   log.info("read()");
	   
	   List<RescueOrder> list = rescueOrderService.read();
	   
	   log.info("lis11111111111111t={}", list);
	   
	   model.addAttribute("rescues", list);
	   
      return "/rescueorder/rescue_order2";
   }
   
   
   @PostMapping("/rescueorder/rescue_order2")
   public String rescuorderPost(RescueOrderDto dto, @RequestParam String boxsize, @RequestParam List<String> dislikeList) {
	   log.info("create(dto={}) rescue", dto);
	   log.info("Received boxsize: {}", boxsize);
	   log.info("dislikeList: {}", dislikeList);
	   
	   dto.setBoxsize(boxsize); // 사용자 입력값을 dto에 설정
	    
	    if ("singlebox".equals(dto.getBoxsize())) {
	        dto.setBoxprice(15500);
	    } else {
	        dto.setBoxprice(25000);
	    }
	    
	    
	    if (dislikeList.size() >= 3) {
	        dto.setDislikeid1(dislikeList.get(0));
	        dto.setDislikeid2(dislikeList.get(1));
	        dto.setDislikeid3(dislikeList.get(2));
	    } 

	    
	   log.info("DTO after setting values: {}", dto);
	   
	   rescueOrderService.create(dto);
      
      return "redirect:/rescueorder/rescue_order2?id="+dto.getResId();
   }
   
   
   
   @GetMapping(value = "/success")
   public String paymentResult(Model model, @RequestParam(value = "orderId") String orderId,
           @RequestParam(value = "amount") Integer amount,
           @RequestParam(value = "paymentKey") String paymentKey) throws Exception {

       log.info("success()");
       
//       if (orderId.startsWith("sample-") && amount != amount) {
//           throw new RuntimeException("해킹의심 : 결제 요청 금액이 아닙니다.");
//       }

       String secretKey = "test_sk_BE92LAa5PVb4QG2plwJ87YmpXyJj:";

       Base64.Encoder encoder = Base64.getEncoder();
       byte[] encodedBytes = encoder.encode(secretKey.getBytes("UTF-8"));
       String authorizations = "Basic " + new String(encodedBytes, 0, encodedBytes.length);

       URL url = new URL("https://api.tosspayments.com/v1/payments/" + paymentKey);

       HttpURLConnection connection = (HttpURLConnection) url.openConnection();
       connection.setRequestProperty("Authorization", authorizations);
       connection.setRequestProperty("Content-Type", "application/json");
       connection.setRequestMethod("POST");
       connection.setDoOutput(true);
       JSONObject obj = new JSONObject();
       obj.put("orderId", orderId);
       obj.put("amount", amount);

       OutputStream outputStream = connection.getOutputStream();
       outputStream.write(obj.toString().getBytes("UTF-8"));

       int code = connection.getResponseCode();
       boolean isSuccess = code == 200 ? true : false;
       model.addAttribute("isSuccess", isSuccess);

       InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

       Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
       JSONParser parser = new JSONParser();
       JSONObject jsonObject = (JSONObject) parser.parse(reader);
       responseStream.close();
       model.addAttribute("responseStr", jsonObject.toJSONString());
       System.out.println(jsonObject.toJSONString());

       model.addAttribute("method", (String) jsonObject.get("method"));
       model.addAttribute("orderName", (String) jsonObject.get("orderName"));

       if (((String) jsonObject.get("method")) != null) {
           if (((String) jsonObject.get("method")).equals("카드")) {
               model.addAttribute("cardNumber", (String) ((JSONObject) jsonObject.get("card")).get("number"));
           } else if (((String) jsonObject.get("method")).equals("계좌이체")) {
               model.addAttribute("bank", (String) ((JSONObject) jsonObject.get("transfer")).get("bank"));
           } else if (((String) jsonObject.get("method")).equals("휴대폰")) {
               model.addAttribute("customerMobilePhone", (String) ((JSONObject) jsonObject.get("mobilePhone")).get("customerMobilePhone"));
           }
       } else {
           model.addAttribute("code", (String) jsonObject.get("code"));
           model.addAttribute("message", (String) jsonObject.get("message"));
       }

       return "/freshmarket/success";
   }

   @GetMapping(value = "/fail")
   public String paymentResult(
           Model model,
           @RequestParam(value = "message") String message,
           @RequestParam(value = "code") Integer code
   ) throws Exception {

       model.addAttribute("code", code);
       model.addAttribute("message", message);

       return "/freshmarket/fail";
   }
   
   
   
}