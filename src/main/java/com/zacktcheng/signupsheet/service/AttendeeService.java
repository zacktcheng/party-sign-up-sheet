package com.zacktcheng.signupsheet.service;

import java.util.List;

import com.zacktcheng.signupsheet.api.Attendee;

public interface AttendeeService {

    List<Attendee> loadAttendees();
    
    void saveAttendee(Attendee attendee);
    
    Attendee getAttendee(int id);

    void updateAttendee(Attendee attendee);

    void deleteAttendee(int id);
}
