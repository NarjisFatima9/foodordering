package com.genspark.foodordering.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_menuItems")
public class MenuItem {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private long itemId;
    private String itemName;
    private double itemPrice;



}
