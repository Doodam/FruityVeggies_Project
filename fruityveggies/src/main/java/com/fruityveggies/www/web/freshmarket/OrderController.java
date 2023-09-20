package com.fruityveggies.www.web.freshmarket;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fruityveggies.www.dto.ItemItemOptionDto;
import com.fruityveggies.www.email.dto.FreshmarketOrderDto;
import com.fruityveggies.www.repository.Cart;
import com.fruityveggies.www.repository.Item;
import com.fruityveggies.www.repository.Order;
import com.fruityveggies.www.repository.OrderItems;
import com.fruityveggies.www.service.ItemService;
import com.fruityveggies.www.service.OrderService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "/freshmarket")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final ItemService itemService;
    
    
//    @GetMapping("/freshmarketorder")
//    public String freshmarketOrder() {
//        log.info("freshmarketOrder={}");
//        
//        
//        
//        return "/freshmarket/freshmarketorder";
//    }
    
    @GetMapping("/freshmarketorder/{orderItemId}/{id}")
    public String getOrdersByOrderItemId_cart(@PathVariable Long orderItemId,@PathVariable String id, Model model ) {
        log.info("orderItemId={}", orderItemId);
        log.info("id={}", id);
        
        List<Order> orders = orderService.getOrdersByOrderItemId(orderItemId);
        
        List<Cart> lists = itemService.read(id);
        
        log.info("lists_cart={}"+lists);
        
        model.addAttribute("lists", lists);
        
        model.addAttribute("orders", orders);
        
        return "/freshmarket/freshmarketorder";
    }
    
    @GetMapping("/freshmarketorder/{userId}")
    public String getOrdersByOrderItemId_order(@PathVariable String userId, Model model ) {
        log.info("userId={}", userId);
        List<OrderItems> lists = itemService.read_order(userId);
        
        log.info("lists_order={}",lists);
        
        model.addAttribute("lists", lists);
        
        
        return "/freshmarket/freshmarketorder";
    }
    
    @PostMapping("/create")
    public String create(FreshmarketOrderDto dto) {
        log.info("createOrder(dto={})", dto);
        
        orderService.create(dto);
        
        return "redirect:/success";
                
    }
    
    
    @GetMapping(value = "/success")
    public String paymentResult(Model model, @RequestParam(value = "orderId") String orderId,
            @RequestParam(value = "amount") Integer amount,
            @RequestParam(value = "paymentKey") String paymentKey) throws Exception {

        log.info("success()");
        
//        if (orderId.startsWith("sample-") && amount != amount) {
//            throw new RuntimeException("해킹의심 : 결제 요청 금액이 아닙니다.");
//        }

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
