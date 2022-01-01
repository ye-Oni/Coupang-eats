package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class GetAllStoreRes {
    private int storeID;
    private String imgUrl;
    private String storeName;
    private int deliveryFee;
    private String deliveryTime;
    private String packTime;
    private int isCheetah;
    private double rate;
    private int reviewNum;
    private double distance;
}
