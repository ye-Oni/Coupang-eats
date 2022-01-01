package com.example.demo.src.event;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.event.model.GetEventRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private final EventProvider eventProvider;

    public EventController(EventProvider eventProvider) {
        this.eventProvider = eventProvider;
    }

    @ResponseBody
    @GetMapping("")
    public BaseResponse<List<GetEventRes>> getEvents() {
        try {
            List<GetEventRes> getEventRes = eventProvider.getEvents();
            return new BaseResponse<>(getEventRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/{eventID}")
    public BaseResponse<GetEventRes> getStore(@PathVariable("eventID") int eventID) {
        try {
            GetEventRes getEventRes = eventProvider.getEvent(eventID);
            return new BaseResponse<>(getEventRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
