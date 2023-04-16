package com.genspark.foodordering.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long regId;
    @NonNull
    private String name;
    @NonNull
    private String address;
    //@NonNull
    private String emails;

    @OneToOne (cascade = CascadeType.ALL)
    private Attachment attachment;

    private String idDownloadURL;

    @OneToMany(cascade =CascadeType.ALL)
    List<MenuItem> menuItems = new ArrayList<>();
}
