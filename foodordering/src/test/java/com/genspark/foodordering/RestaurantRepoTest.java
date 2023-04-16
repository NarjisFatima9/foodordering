package com.genspark.foodordering;

import com.genspark.foodordering.entity.Restaurant;
import com.genspark.foodordering.serrepository.RestaurantRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestaurantRepoTest {
    @Autowired
    private RestaurantRepo restaurantRepo;

    // JUnit test for saveEmployee
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveRestaurantTest(){

        Restaurant restaurant= Restaurant.builder()
                .name("Dominos")
                .address("Chicago")
                .emails("nargsimba@outlook.com")
                .build();

        restaurantRepo.save(restaurant);

       // Assertions.assertThat(restaurant.getRegId().isGreaterThan(0);
    }

}
