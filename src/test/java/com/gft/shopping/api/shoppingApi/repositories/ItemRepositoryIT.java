package com.gft.shopping.api.shoppingApi.repositories;

import com.gft.shopping.api.shoppingApi.AbstractIntegrationTest;
import com.gft.shopping.api.shoppingApi.utils.TestData;
import com.gft.shopping.api.shoppingApi.domain.entities.BagEntity;
import com.gft.shopping.api.shoppingApi.domain.entities.ItemEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class ItemRepositoryIT extends AbstractIntegrationTest {

	@Autowired
	private ItemRepository underTest;

	@Test
	public void testThatItemCanBeCreatedAndRecalled() {
		BagEntity bagEntity = TestData.Entity.testBagEntity1();
		ItemEntity itemEntity = TestData.Entity.testItemEntity1(bagEntity);
		underTest.save(itemEntity);

		Optional<ItemEntity> foundItem = underTest.findById(itemEntity.getId());
		assertThat(foundItem).isPresent();
		assertThat(foundItem.get()).isEqualTo(itemEntity);
		assertThat(foundItem.get().getBag()).isNotNull();
		assertThat(foundItem.get().getBag().getId()).isNotNull();
	}

	@Test
	public void testThatMultipleItemsCanBeCreatedAndRecalled() {
		BagEntity bagEntity = TestData.Entity.testBagEntity1();
		ItemEntity itemEntity1 = TestData.Entity.testItemEntity1(bagEntity);
		underTest.save(itemEntity1);
		ItemEntity itemEntity2 = TestData.Entity.testItemEntity1(bagEntity);
		underTest.save(itemEntity2);
		ItemEntity itemEntity3 = TestData.Entity.testItemEntity1(bagEntity);
		underTest.save(itemEntity3);

		List<ItemEntity> result = underTest.findAll();
		assertThat(result)
			.hasSize(3)
			.containsExactly(itemEntity1, itemEntity2, itemEntity3);
	}

	@Test
	public void testThatItemCanBeUpdated() {
		BagEntity bagEntity = TestData.Entity.testBagEntity1();
		ItemEntity itemEntity = TestData.Entity.testItemEntity1(bagEntity);
		underTest.save(itemEntity);

		itemEntity.setDescription("DESCRIPTION UPDATED");
		underTest.save(itemEntity);

		Optional<ItemEntity> foundItem = underTest.findById(itemEntity.getId());
		assertThat(foundItem).isPresent();
		assertThat(foundItem.get()).isEqualTo(itemEntity);
	}

	@Test
	public void testThatItemCanBeDeleted() {
		BagEntity bagEntity = TestData.Entity.testBagEntity1();
		ItemEntity itemEntity = TestData.Entity.testItemEntity1(bagEntity);
		underTest.save(itemEntity);
		underTest.deleteById(itemEntity.getId());

		Optional<ItemEntity> foundItem = underTest.findById(itemEntity.getId());
		assertThat(foundItem).isEmpty();
	}

	@Test
	public void testThatGetItemsWithStoreNameEqualsTo() {
		BagEntity bagEntity = TestData.Entity.testBagEntity1();
		ItemEntity itemEntity1 = TestData.Entity.testItemEntity1(bagEntity);
		itemEntity1.setStoreName("GAP");
		underTest.save(itemEntity1);
		ItemEntity itemEntity2 = TestData.Entity.testItemEntity1(bagEntity);
		itemEntity2.setStoreName("TJ-MAX");
		underTest.save(itemEntity2);
		ItemEntity itemEntity3 = TestData.Entity.testItemEntity1(bagEntity);
		itemEntity3.setStoreName("GAP");
		underTest.save(itemEntity3);

		List<ItemEntity> result = underTest.storeNameEquals("GAP");
		assertThat(result).containsExactly(itemEntity1, itemEntity3);
	}


}
