package com.fruityveggies.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fruityveggies.www.dto.RescueOrderDto;
import com.fruityveggies.www.repository.rescue.RescueOrder;
import com.fruityveggies.www.repository.rescue.RescueOrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RescueOrderService {

    private final RescueOrderRepository rescueOrderRepository;
    
    
    @Transactional
    public List<RescueOrder> read() {
    	
    	log.info("read");
    	
    	return rescueOrderRepository.findByOrderByIdDesc();
    }
    
    public RescueOrder create (RescueOrderDto dto) {
        
        log.info("create(dto={})", dto);
        
       // String ss = String.join(", ", dto.getDislikeList());
      //  log.info("asdf={}",ss);
        
        RescueOrder entity = dto.toEntity();
        log.info("entity={}", entity );
        
        rescueOrderRepository.save(entity);
        log.info("entity={}", entity);
        
        return entity;
        
    }
}