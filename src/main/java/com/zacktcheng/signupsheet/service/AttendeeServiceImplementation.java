package com.zacktcheng.signupsheet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zacktcheng.signupsheet.DAO.AttendeeDAO;
import com.zacktcheng.signupsheet.api.Attendee;

@Service
public class AttendeeServiceImplementation implements AttendeeService {

	@Autowired
	private AttendeeDAO attendeeDAO;
	
	@Override
	public List<Attendee> loadAttendees() {
		List<Attendee> attendees = attendeeDAO.loadAttendees();
		return attendees;
	}

	@Override
	public void saveAttendee(Attendee attendee) {
		attendeeDAO.saveAttendee(attendee);
	}

	@Override
	public Attendee getAttendee(int id) {
		return attendeeDAO.getAttendee(id);
	}

	@Override
	public void updateAttendee(Attendee attendee) {
		attendeeDAO.updateAttendee(attendee);
	}

	@Override
	public void deleteAttendee(int id) {
		attendeeDAO.deleteAttendee(id);
	}
}
