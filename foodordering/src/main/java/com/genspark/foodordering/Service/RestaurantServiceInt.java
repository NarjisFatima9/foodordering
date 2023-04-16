package com.genspark.foodordering.Service;

import com.genspark.foodordering.entity.MenuItem;
import com.genspark.foodordering.entity.Restaurant;

import java.util.List;

public interface RestaurantServiceInt {

    Restaurant saveRestaurant(Restaurant restaurant);

    MenuItem saveMenuItems(MenuItem restaurant);

    Restaurant getRestaurant(Long id);

    List<Restaurant> getRestaurants();


    //String deleteItemById(long itemId);
    //List<Restaurant> findByRegId(Long restId);
    //List<Restaurant> findByName(String name);
    //Restaurant regRestaurant(Restaurant restaurant);

  //  List<MenuItems> findByMenuItems(Long id);
   // void deleteOrder(long parseLong);


}
