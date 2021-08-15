package com.zacktcheng.signupsheet.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zacktcheng.signupsheet.api.Attendee;

public class AttendeeRowMapper implements RowMapper<Attendee> {

    @Override
    public Attendee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Attendee attendee = new Attendee();
        attendee.setId(rs.getInt("id"));
        attendee.setName(rs.getString("name"));
        attendee.setQuantity(rs.getInt("quantity"));
        attendee.setBringing(rs.getString("bringing"));
        attendee.setMobile(rs.getString("mobile"));
        attendee.setEmail(rs.getString("email"));
        attendee.setNote(rs.getString("note"));
        return attendee;
    }
}
