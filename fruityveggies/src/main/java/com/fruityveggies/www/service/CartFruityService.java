//package com.fruityveggies.www.service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.fruityveggies.www.domain.UserDomain;
//import com.fruityveggies.www.repository.CartFruity;
//import com.fruityveggies.www.repository.CartItem;
//import com.fruityveggies.www.repository.CartItemRepository;
//import com.fruityveggies.www.repository.CartFruityRepository;
//import com.fruityveggies.www.repository.ItemOption;
//import com.fruityveggies.www.repository.UserRepository;
//import com.fruityveggies.www.repository.member.Member;
//import com.fruityveggies.www.repository.member.MemberRepository;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Transactional
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class CartFruityService {
//
//    private final CartFruityRepository cartRepository;
//    private final CartItemRepository cartItemRepository;
//    private final MemberRepository memberRepository;
//    
//
//    // User에게 장바구니 생성
//    public void createCartForUser(Long userId) {
//        log.info("createCartForUser()");
//        
//        Optional<Member> userOptional = memberRepository.findById(userId);
//        if (userOptional.isPresent()) {
//            Member user = userOptional.get();
//            CartFruity cart = new CartFruity();
//            cart.setUser_id(user.getId());
//            cartRepository.save(cart);
//        } else {
//            throw new IllegalArgumentException("User not found with ID: " + userId);
//        }
//    }
//    
//    
//    // 장바구니에 Item 추가하기
//    @Transactional
//    public void addCart(Member user, ItemOption itemOption, int count) {
//        CartFruity cart = cartRepository.findByUserId(user.getId());
//        
//        // cart가 비어있다면 생성.
//        if(cart == null) {
//            CartFruity cart1 = new CartFruity();
//            cart1.setUser_id(user.getId());
//            cart1.setTotal_price(0);
//            cartRepository.save(cart1);
//        }
//        // cartitem 생성
//        CartItem cartItem = cartItemRepository.findByCartIdAndItemOptionId(cart.getId(), itemOption.getId());
//        
//        // cartitem이 비어있다면 바로 생성.
//        if(cartItem == null) {
//            CartItem carItem = new CartItem();
//            carItem.setCart_id(cartItem.getCart_id());
//            cartItem.setItemOption_id(cartItem.getItemOption_id());
//            carItem.setCount(cartItem.getCount() + 1);
//            cartItemRepository.save(carItem);
//            
//        } else {
//            // 비어있지 않다면 그만큼 갯수를 추가.
//            cartItem.addCount(count);
//        }
//    }
//    
//    
//    // 장바구니 조회하기
//    public List<CartItem> userCartView(CartFruity cart) {
//        List<CartItem> cartItems = cartItemRepository.findAll();
//        List<CartItem> userItems = new ArrayList<>();
//        
//        for(CartItem cartItem : cartItems) {
//            if(cartItem.getCart_id() == cart.getId()) {
//                userItems.add(cartItem);
//            }
//        }
//        return userItems;
//    }
//    
//    // 장바구니 item 삭제
//    public void cartItemDelete(Long id) {
//        cartItemRepository.deleteById(id);
//    }
//    
//    // 장바구니 item 전체 삭제
//    public void cartItemDeleteAll(Long id) {
//        List<CartItem> cartItems = cartItemRepository.findAll();
//        
//        for(CartItem cartItem : cartItems) {
//            if(cartItem.getCart_id() == id) {
//                cartItem.setCount(cartItem.getCount() - 1);
//                cartItemRepository.deleteById(cartItem.getId());
//            }
//        }
//    }
//    
//}
