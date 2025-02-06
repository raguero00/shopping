package com.gft.shopping.api.shoppingApi.repositories;

import com.gft.shopping.api.shoppingApi.AbstractIntegrationTest;
import com.gft.shopping.api.shoppingApi.utils.TestData;
import com.gft.shopping.api.shoppingApi.domain.entities.BagEntity;
import com.gft.shopping.api.shoppingApi.domain.entities.ShoppingListEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class ShoppingListRepositoryIT extends AbstractIntegrationTest {

//	private final ShoppingListRepository underTest;
//
//	@Autowired
//	public ShoppingListRepositoryIT(ShoppingListRepository underTest) {
//		this.underTest = underTest;
//	}
//
//	@Test
//	public void testThatShoppingListsCanBeCreatedAndRecalled() {
//		ShoppingListEntity shoppingList = TestData.Entity.testShoppingListEntity();
//		underTest.save(shoppingList);
//
//		Optional<ShoppingListEntity> foundShoppingList = underTest.findById(shoppingList.getId());
//		assertThat(foundShoppingList).isPresent();
//		assertThat(foundShoppingList.get()).isEqualTo(shoppingList);
//	}
//
//	@Test
//	public void testThatShoppingListsCanBeCreatedAndRecalledWithNoBags() {
//		ShoppingListEntity shoppingList = TestData.Entity.testShoppingListEntity();
//		underTest.save(shoppingList);
//
//		Optional<ShoppingListEntity> foundShoppingList = underTest.findById(shoppingList.getId());
//		assertThat(foundShoppingList).isPresent();
//		assertThat(foundShoppingList.get()).isEqualTo(shoppingList);
//		assertThat(foundShoppingList.get().getBags()).isNullOrEmpty();
//	}
//
//	@Test
//	public void testThatShoppingListsCanBeCreatedAndRecalledWithOneBag() {
//		ShoppingListEntity shoppingList = TestData.Entity.testShoppingListEntity();
//		BagEntity bagEntity = TestData.Entity.testBagEntity1(shoppingList);
//		shoppingList.setBags(Collections.singletonList(bagEntity));
//		underTest.save(shoppingList);
//
//		Optional<ShoppingListEntity> foundShoppingList = underTest.findById(shoppingList.getId());
//		assertThat(foundShoppingList).isPresent();
//		assertThat(foundShoppingList.get()).isEqualTo(shoppingList);
//		assertThat(foundShoppingList.get().getBags()).isNotNull();
//		assertThat(foundShoppingList.get().getBags().size()).isEqualTo(1);
//	}
//
//	@Test
//	public void testThatMultipleShoppingListsCanBeCreatedAndRecalledWithNoBags() {
//		ShoppingListEntity shoppingList1 = TestData.Entity.testShoppingListEntity();
//		underTest.save(shoppingList1);
//		ShoppingListEntity shoppingList2 = TestData.Entity.testShoppingListEntity();
//		underTest.save(shoppingList2);
//		ShoppingListEntity shoppingList3 = TestData.Entity.testShoppingListEntity();
//		underTest.save(shoppingList3);
//
//		List<ShoppingListEntity> result = underTest.findAll();
//		assertThat(result)
//			.hasSize(3)
//			.containsExactly(shoppingList1, shoppingList2, shoppingList3);
//	}
//
//	@Test
//	public void testThatShoppingListCanBeUpdated() {
//		ShoppingListEntity shoppingList = TestData.Entity.testShoppingListEntity();
//		underTest.save(shoppingList);
//
//		shoppingList.setDescription("DESCRIPTION UPDATED");
//		underTest.save(shoppingList);
//
//		Optional<ShoppingListEntity> foundShoppingList = underTest.findById(shoppingList.getId());
//		assertThat(foundShoppingList).isPresent();
//		assertThat(foundShoppingList.get()).isEqualTo(shoppingList);
//	}
//
//	@Test
//	public void testThatShoppingListCanBeDeleted() {
//		ShoppingListEntity shoppingList = TestData.Entity.testShoppingListEntity();
//		underTest.save(shoppingList);
//		underTest.deleteById(shoppingList.getId());
//
//		Optional<ShoppingListEntity> foundItem = underTest.findById(shoppingList.getId());
//		assertThat(foundItem).isEmpty();
//	}

}
