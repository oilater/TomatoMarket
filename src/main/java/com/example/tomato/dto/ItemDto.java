package com.example.tomato.dto;

import java.time.LocalDateTime;

import com.example.tomato.constant.ItemSellStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
	private Long id; //상품 코드
	
	private String itemNm; // 상품명
	
	private int price; // 가격
	
	private String itemDetail; // 상품 상세설명

	private ItemSellStatus itemSellStatus; 
	
	private LocalDateTime regTime; //등록 시간
}
