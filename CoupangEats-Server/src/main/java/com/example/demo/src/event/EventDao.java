package com.example.demo.src.event;

import com.example.demo.src.event.model.GetEventRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class EventDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetEventRes> getEvents() {
        String getEventsQuery = "select * from Event";
        return this.jdbcTemplate.query(getEventsQuery,
                (rs, rowNum) -> new GetEventRes(
                        rs.getInt("eventID"),
                        rs.getString("eventName"),
                        rs.getString("imgUrl")
                )
        );
    }

    public GetEventRes getEvent(int eventID) {
        String getEventQuery = "select * from Event where eventID = ?";
        int getEventParams = eventID;
        return this.jdbcTemplate.queryForObject(getEventQuery,
                (rs, rowNum) -> new GetEventRes(
                        rs.getInt("eventID"),
                        rs.getString("eventName"),
                        rs.getString("imgUrl")),
                getEventParams);
    }
}
