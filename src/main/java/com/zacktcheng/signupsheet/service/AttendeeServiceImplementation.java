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
    public boolean isAttendeeInfoValid(Attendee attendee, User user, String previousMobile) {
        if (!isNameValid(attendee, user)) return false;
        if (!isMobileValid(attendee, user, previousMobile)) return false;
        if (!isQuantityValid(attendee, user)) return false;
        user.setErrorMsg("");
        return true;
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
    
    private boolean isNameValid(Attendee attendee, User user) {
        if (attendee.getName() == null || attendee.getName().trim().isEmpty()) {
            user.setErrorMsg("Attendee's name cannot be empty.");
            return false;
        }
        return true;
    }
    
    private boolean isMobileValid(Attendee attendee, User user, String previousMobile) {
        if (attendee.getMobile() == null) {
            user.setErrorMsg("Attendee's phone number cannot be empty.");
            return false; 
        }
        
        String number = attendee.getMobile();
        
        if (!number.matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")) {
            user.setErrorMsg("Attendee's phone number is not valid in USA.");
            return false;
        }
        if (previousMobile != null && !number.equals(previousMobile) && attendeeDAO.hasMobileExisted(number)) {
            user.setErrorMsg("This number is currently used by another attendee, please try another number.");
            return false;
        } 
        return true;
    }
    
    private boolean isQuantityValid(Attendee attendee, User user) {
        if (attendee.getQuantity() == null) return false;
        
        int quantity = Integer.parseInt(attendee.getQuantity());
        
        if (1 > quantity || quantity > 5) {
            user.setErrorMsg("Please enter a valid quantity of people.");
        	return false;
        }
        return true;
    }
}
