package com.zacktcheng.signupsheet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zacktcheng.signupsheet.DAO.AttendeeDAO;
import com.zacktcheng.signupsheet.api.Attendee;
import com.zacktcheng.signupsheet.api.User;

@Service
public class AttendeeServiceImplementation implements AttendeeService {

    @Autowired
    private AttendeeDAO attendeeDAO;
    
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
    
    @Override
    public boolean isAttendeeInfoValid(Attendee attendee, User user) {
        if (attendee.getName() == null || attendee.getName().trim().isEmpty()) {
            user.setErrorMsg("Attendee's name cannot be empty.");
            return false;
        }
        if (attendee.getMobile() != null) {
            String number = attendee.getMobile();
            if (!number.matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")) {
            	user.setErrorMsg("Attendee's phone number is not valid in USA.");
            	return false;
            } else if (attendeeDAO.hasMobileExisted(number)) {
            	user.setErrorMsg("This phone number is currently used on the sign-up sheet, please try another one.");
            	return false;	
            }
        } 
        else {
            user.setErrorMsg("Attendee's phone number cannot be empty.");
            return false;
        }
        
        if (attendee.getQuantity() != null) {
            int quantity = Integer.parseInt(attendee.getQuantity());
            if (1 <= quantity && quantity <= 5) {
            	user.setErrorMsg("");
            	return true;
            }
        }
        user.setErrorMsg("Please enter a valid quantity of people.");
        return false;
    }
    
    @Override
    public void setMobilePreviews(List<Attendee> attendees, boolean isAdmin) {
        if (isAdmin) {
            for (Attendee attendee : attendees) {
                attendee.setMobilePreview(attendee.getMobile());
            }
        } else {
            for (Attendee attendee : attendees) {
                try {
                    String number = attendee.getMobile();
                    String coveredNumber = "*-xxx-" + number.substring(number.length() - 4);
                    attendee.setMobilePreview(coveredNumber);
                } catch (NullPointerException e) {
                    System.out.println("WARNING: " + attendee + " mobile number length is too short.");
                    attendee.setMobilePreview("No Preview");
                }
            }
        }
    }
}
