package com.gft.shoppingList.repositories;

import com.gft.shoppingList.AbstractIntegrationTest;
import com.gft.shoppingList.utils.TestData;
import com.gft.shoppingList.domain.entities.BagEntity;
import com.gft.shoppingList.domain.entities.ItemEntity;
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
public class BagRepositoryIT extends AbstractIntegrationTest {

	private final BagRepository underTest;

	@Autowired
	public BagRepositoryIT(BagRepository underTest) {
		this.underTest = underTest;
	}

	@Test
	public void testThatBagCanBeCreatedAndRecalledWithNoShoppingListAndWithNoItems() {
		BagEntity bagEntity = TestData.Entity.testBagEntity1();
		underTest.save(bagEntity);

		Optional<BagEntity> foundBag = underTest.findById(bagEntity.getId());
		assertThat(foundBag).isPresent();
		assertThat(foundBag.get()).isEqualTo(bagEntity);
		assertThat(foundBag.get().getShoppingLists()).isNullOrEmpty();
		assertThat(foundBag.get().getItems()).isNullOrEmpty();
	}

	@Test
	public void testThatBagCanBeCreatedAndRecalledWithNoShoppingListAndWithItems() {
		BagEntity bagEntity = TestData.Entity.testBagEntity1();
		ItemEntity itemEntity1 = TestData.Entity.testItemEntity1(bagEntity);

		bagEntity.setItems(Collections.singletonList(itemEntity1));
		underTest.save(bagEntity);

		Optional<BagEntity> foundBag = underTest.findById(bagEntity.getId());
		assertThat(foundBag).isPresent();
		assertThat(foundBag.get()).isEqualTo(bagEntity);
		assertThat(foundBag.get().getShoppingLists()).isNullOrEmpty();
		assertThat(foundBag.get().getItems()).isNotNull();
		assertThat(foundBag.get().getItems().size()).isGreaterThan(0);
	}

	@Test
	public void testThatMultipleBagsCanBeCreatedAndRecalledWithNoItems() {
		BagEntity bagEntity1 = TestData.Entity.testBagEntity1();
		underTest.save(bagEntity1);
		BagEntity bagEntity2 = TestData.Entity.testBagEntity1();
		underTest.save(bagEntity2);
		BagEntity bagEntity3 = TestData.Entity.testBagEntity1();
		underTest.save(bagEntity3);

		List<BagEntity> result = underTest.findAll();
		assertThat(result)
			.hasSize(3)
			.containsExactly(bagEntity1, bagEntity2, bagEntity3);
	}

	@Test
	public void testThatBagCanBeUpdated() {
		BagEntity bagEntity = TestData.Entity.testBagEntity1();
		underTest.save(bagEntity);

		bagEntity.setDescription("DESCRIPTION UPDATED");
		underTest.save(bagEntity);

		Optional<BagEntity> foundBag = underTest.findById(bagEntity.getId());
		assertThat(foundBag).isPresent();
		assertThat(foundBag.get()).isEqualTo(bagEntity);
	}

	@Test
	public void testThatBagCanBeDeleted() {
		BagEntity bagEntity = TestData.Entity.testBagEntity1();
		underTest.save(bagEntity);
		underTest.deleteById(bagEntity.getId());

		Optional<BagEntity> foundItem = underTest.findById(bagEntity.getId());
		assertThat(foundItem).isEmpty();
	}
}
