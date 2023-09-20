//package com.fruityveggies.www.web;
//
//import java.security.Principal;
//import java.util.List;
//
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import com.fruityveggies.www.repository.CartFruity;
//import com.fruityveggies.www.repository.CartItem;
//import com.fruityveggies.www.service.CartFruityService;
//import com.fruityveggies.www.service.MemberService;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Controller
//@RequiredArgsConstructor
//@Slf4j
//public class CartFrutiyController {
//
//    private final CartFruityService cartService;
//    private final MemberService memberService;
//    
//    @GetMapping("/cart/cart")
//    public String myCart() {
//        return "/cart/cart";
//    }
//    
//    @GetMapping("/{id}/cart")
//    public String myCart(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal Principal principalDetails) {
//        return "/cart/cart";
//     // 로그인 User == 접속 User
////        if(principalDetails.getUser().getId() == id){
////            // User의 장바구니를 가져온다.
////            Cart cart = principalDetails.getUser().getCart();
////            // 장바구니의 아이템을 가져온다.
////
////            List<CartItem> cartItems = cartService.userCartView(cart);
////
////            int totalPrice = 0;
////            for(CartItem cart_item : cartItems){
////                totalPrice += ( cart_item.getItem().getPrice() * cart_item.getCount());
////            }
////
////            model.addAttribute("cartItems",cartItems);
////            model.addAttribute("totalPrice",totalPrice);
////            model.addAttribute("user",userPageService.findUser(id));
////
////            return "/cart/cart";
////        }else{
////            return "redirect:/main";
////        }
//    }
//}
