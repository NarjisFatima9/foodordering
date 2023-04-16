package com.genspark.foodordering.serrepository;

import com.genspark.foodordering.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
   // List<Restaurant> findByRegId(Long restId);
    //List<Restaurant> findByName(String name);
    //Restaurant regRestaurant(Restaurant restaurant);

    //List<MenuItems> findByMenuItems(Long id);





}
