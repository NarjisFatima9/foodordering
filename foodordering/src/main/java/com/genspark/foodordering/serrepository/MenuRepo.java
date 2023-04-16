package com.genspark.foodordering.serrepository;


import com.genspark.foodordering.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepo extends JpaRepository<MenuItem, Long> {
    //List<MenuItems> findByItemId(Long id);
   // @Transactional
    //void deleteByItemId(Long regId);

    //MenuItems addItem(MenuItems menuItem);
}
