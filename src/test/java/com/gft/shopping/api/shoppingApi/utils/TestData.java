package com.gft.shopping.api.shoppingApi.utils;

import com.gft.shopping.api.shoppingApi.domain.dto.Bag;
import com.gft.shopping.api.shoppingApi.domain.dto.Item;
import com.gft.shopping.api.shoppingApi.domain.dto.ShoppingList;
import com.gft.shopping.api.shoppingApi.domain.entities.BagEntity;
import com.gft.shopping.api.shoppingApi.domain.entities.ItemEntity;
import com.gft.shopping.api.shoppingApi.domain.entities.ShoppingListEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public final class TestData {

	public TestData() { }

	public static class Entity {

		public static ShoppingListEntity testShoppingListEntity() {
			return ShoppingListEntity.builder()
				.name("TestShoppingList")
				.description("TestShoppingList Description")
				.status(1)
				.creationDate(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0))
				.build();
		}

		public static BagEntity testBagEntity1() {
			return BagEntity.builder()
				.name("Bag 1 Name")
				.description("Bag 1 Description")
				.maxAmount(Double.parseDouble("300"))
				.status(1)
				.creationDate(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0))
				.build();
		}

		public static BagEntity testBagEntity1WithoutIdAndDates() {
			return BagEntity.builder()
				.name("Bag 1 Name")
				.description("Bag 1 Description")
				.maxAmount(Double.parseDouble("300"))
				.status(1)
				.items(new ArrayList<>())
				.shoppingLists(new ArrayList<>())
				//.creationDate(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0))
				.build();
		}

		public static BagEntity testBagEntity1(ShoppingListEntity shoppingListEntity) {
			return BagEntity.builder()
				.name("Bag 1 Name")
				.description("Bag 1 Description")
				.maxAmount(Double.parseDouble("300"))
				.status(1)
				.creationDate(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0))
				//.shoppingList(shoppingListEntity)
				.build();
		}

		public static BagEntity testBagEntity2() {
			return BagEntity.builder()
				.name("Bag 2 Name")
				.description("Bag 2 Description")
				.maxAmount(Double.parseDouble("350"))
				.status(1)
				.creationDate(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0))
				.build();
		}

		public static ItemEntity testItemEntity1() {
			return ItemEntity.builder()
				.description("Item 1 Description")
				.originalBarcode("111111111")
				.secondaryBarcode("999999999")
				.originalAmount(BigDecimal.valueOf(37.00))
				.secondaryAmount(BigDecimal.valueOf(12.25))
				.receiptNumber("12345678")
				.alternateReceiptNumber("20-1")
				.storeName("Store Name")
				.status(1)
				.creationDate(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0))
				.build();
		}

		public static ItemEntity testItemEntity1(BagEntity bagEntity) {
			return ItemEntity.builder()
				.description("Item 1 Description")
				.originalBarcode("111111111")
				.secondaryBarcode("999999999")
				.originalAmount(BigDecimal.valueOf(37.00))
				.secondaryAmount(BigDecimal.valueOf(12.25))
				.receiptNumber("12345678")
				.alternateReceiptNumber("20-1")
				.storeName("Store Name")
				.status(1)
				.creationDate(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0))
				.bag(bagEntity)
				.build();
		}

		public static ItemEntity testItemEntity2() {
			return ItemEntity.builder()
				.description("Item 2 Description")
				.originalBarcode("222222222")
				.secondaryBarcode("888888888")
				.originalAmount(BigDecimal.valueOf(26.00))
				.secondaryAmount(BigDecimal.valueOf(7.99))
				.receiptNumber("87654321")
				.alternateReceiptNumber("20-2")
				.storeName("Store Name 2")
				.status(1)
				.creationDate(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0))
				.build();
		}

		public static ItemEntity testItemEntity2(BagEntity bagEntity) {
			return ItemEntity.builder()
				.description("Item 2 Description")
				.originalBarcode("222222222")
				.secondaryBarcode("888888888")
				.originalAmount(BigDecimal.valueOf(26.00))
				.secondaryAmount(BigDecimal.valueOf(7.99))
				.receiptNumber("87654321")
				.alternateReceiptNumber("20-2")
				.storeName("Store Name 2")
				.status(1)
				.creationDate(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0))
				.bag(bagEntity)
				.build();
		}

		public static ItemEntity testItemEntity3() {
			return ItemEntity.builder()
				.description("Item 3 Description")
				.originalBarcode("333333333")
				.secondaryBarcode("777777777")
				.originalAmount(BigDecimal.valueOf(22.00))
				.secondaryAmount(BigDecimal.valueOf(11.50))
				.receiptNumber("43218765")
				.alternateReceiptNumber("20-3")
				.storeName("Store Name 3")
				.status(1)
				.creationDate(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0))
				.build();
		}

		public static ItemEntity testItemEntity3(BagEntity bagEntity) {
			return ItemEntity.builder()
				.description("Item 3 Description")
				.originalBarcode("333333333")
				.secondaryBarcode("777777777")
				.originalAmount(BigDecimal.valueOf(22.00))
				.secondaryAmount(BigDecimal.valueOf(11.50))
				.receiptNumber("43218765")
				.alternateReceiptNumber("20-3")
				.storeName("Store Name 3")
				.status(1)
				.creationDate(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0))
				.bag(bagEntity)
				.build();
		}
	}

	public static class Dto {
		public static ShoppingList testShoppingList() {
			return ShoppingList.builder()
				.name("TestShoppingList")
				.description("TestShoppingList Description")
				.status(1)
				.creationDate(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0))
				.build();
		}

		public static Bag testBag1() {
			return Bag.builder()
				.name("Bag 1 Name")
				.description("Bag 1 Description")
				.maxAmount(Double.parseDouble("300"))
				.status(1)
				.creationDate(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0))
				.build();
		}

		public static Bag testBag1WithoutIdAndDates() {
			return Bag.builder()
				.name("Bag 1 Name")
				.description("Bag 1 Description")
				.maxAmount(Double.parseDouble("300"))
				.status(1)
				.items(new ArrayList<>())
				.creationDate(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0))
				.build();
		}

		public static Bag testBag2() {
			return Bag.builder()
				.name("Bag 2 Name")
				.description("Bag 2 Description")
				.maxAmount(Double.parseDouble("350"))
				.status(1)
				.creationDate(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0))
				.build();
		}

		public static Item testItem1() {
			return Item.builder()
				.description("Item 1 Description")
				.originalBarcode("111111111")
				.secondaryBarcode("999999999")
				.originalAmount(BigDecimal.valueOf(37.00))
				.secondaryAmount(BigDecimal.valueOf(12.25))
				.receiptNumber("12345678")
				.alternateReceiptNumber("20-1")
				.storeName("Store Name")
				.status(1)
				.creationDate(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0))
				.build();
		}

		public static Item testItem2() {
			return Item.builder()
				.description("Item 2 Description")
				.originalBarcode("222222222")
				.secondaryBarcode("888888888")
				.originalAmount(BigDecimal.valueOf(26.00))
				.secondaryAmount(BigDecimal.valueOf(7.99))
				.receiptNumber("87654321")
				.alternateReceiptNumber("20-2")
				.storeName("Store Name 2")
				.status(1)
				.creationDate(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0))
				.build();
		}

		public static Item testItem3() {
			return Item.builder()
				.description("Item 3 Description")
				.originalBarcode("333333333")
				.secondaryBarcode("777777777")
				.originalAmount(BigDecimal.valueOf(22.00))
				.secondaryAmount(BigDecimal.valueOf(11.50))
				.receiptNumber("43218765")
				.alternateReceiptNumber("20-3")
				.storeName("Store Name 3")
				.status(1)
				.creationDate(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0, 0))
				.build();
		}
	}

}