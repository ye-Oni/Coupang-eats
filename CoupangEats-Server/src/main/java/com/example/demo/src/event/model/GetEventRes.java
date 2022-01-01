package com.example.demo.src.event.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetEventRes {
    private int eventID;
    private String eventName;
    private String imgUrl;
}
