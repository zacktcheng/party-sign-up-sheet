package com.zacktcheng.signupsheet.DAO;

import java.util.List;

import com.zacktcheng.signupsheet.api.Attendee;

public interface AttendeeDAO {

	List<Attendee> loadAttendees();
	
    void saveAttendee(Attendee attendee);
    
    Attendee getAttendee(int id);

	void updateAttendee(Attendee attendee);

	void deleteAttendee(int id);
}
