package com.zacktcheng.signupsheet.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zacktcheng.signupsheet.api.Attendee;
import com.zacktcheng.signupsheet.rowmapper.AttendeeRowMapper;

@Repository
public class AttendeeDAOImplementation implements AttendeeDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Attendee> loadAttendees() {
        String sql = "SELECT * FROM attendees";
        List<Attendee> attendees = jdbcTemplate.query(sql, new AttendeeRowMapper());
        return attendees;
    }

    @Override
    public void saveAttendee(Attendee attendee) {
        Object[] sqlParameters = {attendee.getName(), attendee.getQuantity(), attendee.getBringing(), attendee.getMobile(), attendee.getNote()};
        System.out.println("id: " + attendee.getId());
        String sql = "INSERT INTO attendees (name, quantity, bringing, mobile, note) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, sqlParameters);
        System.out.println("1 record inserted.");
    }

    @Override
    public Attendee getAttendee(int id) {
        String sql = "SELECT * FROM attendees WHERE id=?";
        Attendee attendee = jdbcTemplate.queryForObject(sql, new AttendeeRowMapper(), id);
        return attendee;
    }

    @Override
    public void updateAttendee(Attendee attendee) {
        String sql = "UPDATE attendees SET name=?, quantity=?, bringing=?, mobile=?, note=? WHERE id=?";
        jdbcTemplate.update(sql, attendee.getName(), attendee.getQuantity(), attendee.getBringing(), attendee.getMobile(), attendee.getNote(), attendee.getId());
        System.out.println("1 record updated.");
    }

    @Override
    public void deleteAttendee(int id) {
        String sql = "DELETE FROM attendees WHERE id=?";
        jdbcTemplate.update(sql, id);
        System.out.println("1 record deleted.");
    }
    
    @Override
    public boolean hasMobileExisted(String mobile) {
        String sql = "SELECT * FROM attendees WHERE mobile=?";
    	try {
    	    jdbcTemplate.queryForObject(sql, new AttendeeRowMapper(), mobile);
        } catch (EmptyResultDataAccessException e) {
    	    return false;
        }
        return true;
    }
}
