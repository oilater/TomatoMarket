package com.example.tomato.entity;

import javax.persistence.*;

import com.example.tomato.constant.ItemSellStatus;
import com.example.tomato.dto.ItemFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="item") //테이블명
@Getter
@Setter
@ToString
public class Item extends BaseEntity {
	//not null이 아닐때는 필드 타입을 객체(예 int - Integer)로 지정해야 한다. 

	@Id
	@Column(name="item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; //상품코드
	
	@Column(nullable = false, length = 50)
	private String itemNm; //상품명
	
	@Column(nullable = false, name = "price")
	private int price; //가격

	@Lob
	@Column(nullable = false)
	private String itemDetail; // 상품 상세설명
	
	@Enumerated(EnumType.STRING)
	private ItemSellStatus itemSellStatus; //상품 판매 상태
	
	public void updateItem(ItemFormDto itemFormDto) {
		this.itemNm = itemFormDto.getItemNm();
		this.price = itemFormDto.getPrice();
		this.itemDetail = itemFormDto.getItemDetail();
		this.itemSellStatus = itemFormDto.getItemSellStatus();
		
	}
	
	
}
