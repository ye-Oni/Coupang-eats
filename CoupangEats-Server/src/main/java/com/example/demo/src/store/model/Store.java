package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Store {
    private int storeID;
    private int categoryID;
    private int reviewID;
    private int menuID;
    private String storeName;
    private String imgUrl;
    private int minPrice;
    private double storeLat;
    private double storeLng;
    private int isCheetah;
    private String storeAddress;
    private String status;
    private String createAt;
    private String updateAt;
}
