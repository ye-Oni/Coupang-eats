package com.example.demo.src.event;

import com.example.demo.config.BaseException;
import com.example.demo.src.event.model.GetEventRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class EventProvider {
    private final EventDao eventDao;

    @Autowired
    public EventProvider(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public List<GetEventRes> getEvents() throws BaseException {
        try {
            List<GetEventRes> getEventRes = eventDao.getEvents();
            return getEventRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetEventRes getEvent(int eventID) throws BaseException {
        try {
            GetEventRes getEventRes = eventDao.getEvent(eventID);
            return getEventRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
