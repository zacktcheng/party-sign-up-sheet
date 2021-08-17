package com.zacktcheng.signupsheet.service;

import java.util.List;

import com.zacktcheng.signupsheet.api.Attendee;
import com.zacktcheng.signupsheet.api.User;

public interface AttendeeService {

    List<Attendee> loadAttendees();
    
    void saveAttendee(Attendee attendee);
    
    Attendee getAttendee(int id);

    void updateAttendee(Attendee attendee);

    void deleteAttendee(int id);
    
    boolean isAttendeeInfoValid(Attendee attendee, User user);
    
    void setMobilePreviews(List<Attendee> attendees, boolean isAdmin);
}
