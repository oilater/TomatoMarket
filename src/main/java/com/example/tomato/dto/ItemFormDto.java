package com.example.tomato.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.example.tomato.constant.ItemSellStatus;
import com.example.tomato.entity.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemFormDto {
	private Long id; // 상품 코드

	@NotBlank(message = "상품명은 필수 입력 값입니다.")
	private String itemNm; // 상품명

	@NotNull(message = "가격은 필수 입력 값입니다.")
	private int price; // 가격

	@NotBlank(message = "상품 상세설명은 필수 입력 값입니다.")
	private String itemDetail; // 상품 상세설명

	private ItemSellStatus itemSellStatus; // 상품 판매상태

	private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

	private List<Long> itemImgIds = new ArrayList<>(); // 상품의 이미지 아이디를 저장 -> 수정시에 이미지 아이디를 담아 둘 용도

	private static ModelMapper modelMapper = new ModelMapper();

	public Item createItem() {
		return modelMapper.map(this, Item.class);
	}

	public static ItemFormDto of(Item item) {
		return modelMapper.map(item, ItemFormDto.class);
	}
}
