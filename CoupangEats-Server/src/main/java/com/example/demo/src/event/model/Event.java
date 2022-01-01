package com.example.demo.src.event.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Event {
    private int eventID;
    private String eventName;
    private String imgUrl;
    private String status;
    private String createAt;
    private String updateAt;
}
