package com.zacktcheng.signupsheet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zacktcheng.signupsheet.api.Attendee;
import com.zacktcheng.signupsheet.api.User;
import com.zacktcheng.signupsheet.service.AttendeeService;

@Controller
public class SignupSheetController {

    @Autowired
    private AttendeeService attendeeService;
    
    @Autowired
    private User user;
    
    private int attendeeId;
    private String attendeeMobile;
    
    @GetMapping("/")
    public String showWaitlist(Model model) {    
    	user.setErrorMsg("");
    	if (!model.containsAttribute("user")) {
            model.addAttribute("user", user);
        }
        List<Attendee> attendees = attendeeService.loadAttendees();
        attendeeService.setMobilePreviews(attendees, user.isInputPasswordValid());
        model.addAttribute("attendees", attendees);
        return "showSignupSheet";
    }
    
    @GetMapping("/addAttendee")
    public String addAttendee(Model model) {
        Attendee attendee = new Attendee();
        model.addAttribute("attendee", attendee);
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", user);
        }
        return "updateSignupSheet";
    }
    
    @GetMapping("/adminSignin")
    public String adminSignin(Model model) {
    	if (!model.containsAttribute("user")) {
            model.addAttribute("user", user);
        }
        return "adminSignin";
    }
    
    @PostMapping("/validateAdminSignin")
    public String validateAdminSignin(User tempUser, Model model) {
        if (tempUser.isInputPasswordValid()) {
            user.setPassword(tempUser.getPassword());
            user.setValidInBinary();
            user.setErrorMsg("");
            return "redirect:/";
        }
        user.setErrorMsg("Incorrect password. Please try again.");
        return "redirect:/adminSignin";
    }
    
    @GetMapping("/adminSignout")
    public String adminSignout() {
        user = new User();
        return "redirect:/";
    }
    
    @PostMapping("/updateSignupSheet")
    public String updateSignupSheet(Attendee attendee, Model model) {
        System.out.println(attendee);
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", user);
        }
        if (attendee.getId() == 0) {
            if (attendeeService.isAttendeeInfoValid(attendee, user, attendeeMobile)) {
                attendeeService.saveAttendee(attendee);
                user.setErrorMsg("");
                return "redirect:/";
            }
            return "redirect:/addAttendee";
        }
        if (attendeeService.isAttendeeInfoValid(attendee, user, attendeeMobile)) {
            attendeeService.updateAttendee(attendee);
            user.setErrorMsg("");
            return "redirect:/";
        }
        model.addAttribute("attendee", attendee);
        return "updateSignupSheet";
    }
    
    @GetMapping("/verifyAttendee")
    public String verifyAttendee(@RequestParam("AttendeeId") int id, @RequestParam("AttendeeMobile") String mobile, Model model) {
        attendeeId = id;
        attendeeMobile = mobile;
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", user);
        }
        if (user.isInputPasswordValid()) {
            Attendee attendeeToUpdate = attendeeService.getAttendee(id);
            model.addAttribute("attendee", attendeeToUpdate);
            user.setErrorMsg("");
            return "updateSignupSheet";
        }
        model.addAttribute("attendee", new Attendee());
        return "verifyAttendee";
    }
    
    @PostMapping("/verifyAttendeeMobile") 
    public String verifyAttendeeMobile(Attendee attendee, Model model) {
        if (attendee.getMobile().equals(attendeeMobile)) {
            Attendee attendeeToUpdate = attendeeService.getAttendee(attendeeId);
            model.addAttribute("attendee", attendeeToUpdate);
            user.setErrorMsg("");
            return "updateSignupSheet";
        }
        user.setErrorMsg("Incorrect phone number. Please try again.");
        return "redirect:/verifyAttendee?AttendeeId=" + attendeeId + "&AttendeeMobile=" + attendeeMobile;
    }
    
    @GetMapping("/deleteAttendee")
    public String deleteAttendee(@RequestParam("AttendeeId") int id) {
        attendeeService.deleteAttendee(id);
        return "redirect:/";
    }
}
