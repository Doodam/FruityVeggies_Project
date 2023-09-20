package com.fruityveggies.www.repository.rescue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RescueOrderRepository extends JpaRepository<RescueOrder, Long> {

	
	List<RescueOrder> findByOrderByIdDesc();
}