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
	private User user = new User();
	private int attendeeId;
	private String attendeeMobile;
	
	@GetMapping("/")
	public String showWaitlist(Model model) {	
		if (!model.containsAttribute("user")) {
			model.addAttribute("user", user);
		}
		List<Attendee> attendees = attendeeService.loadAttendees();
		for (Attendee attendee : attendees) {
			if (!user.isInputPasswordValid()) {
				attendee.setMobilePreview("(xxx)-xxx-" + attendee.getMobile().substring(6));
			} else {
				attendee.setMobilePreview(attendee.getMobile());
			}
		}
		model.addAttribute("attendees", attendees);
		return "showSignupSheet";
	}
	
	@GetMapping("/addAttendee")
	public String addAttendee(Model model) {
		Attendee attendee = new Attendee();
		model.addAttribute("attendee", attendee);
		return "updateSignupSheet";
	}
	
	@GetMapping("/adminSignin")
	public String adminSignin(Model model) {
		model.addAttribute("user", user);
		return "adminSignin";
	}
	
	@PostMapping("/validateAdminSignin")
	public String validateAdminSignin(User tempUser, Model model) {
		if (tempUser.isInputPasswordValid()) {
			user.setPassword(tempUser.getPassword());
			user.setValidInBinary();
			return "redirect:/";
		}
		return "adminSignin";
	}
	
	@GetMapping("/adminSignout")
	public String adminSignout() {
		user = new User();
		return "redirect:/";
	}
	
	@PostMapping("/updateSignupSheet")
	public String updateSignupSheet(Attendee attendee, Model model) {
		System.out.println(attendee);
		if (attendee.getMobile() == null 
				|| attendee.getMobile().trim().length() != 10 
				|| !attendee.getMobile().matches("[0-9]+")
				|| attendee.getName() == null 
				|| attendee.getName().trim().isEmpty()) {
			return "updateSignupSheet";
		}
		if (attendee.getId() == 0) {
			attendeeService.saveAttendee(attendee);
		} else {
			attendeeService.updateAttendee(attendee);
		}
		return "redirect:/";
	}
	
	@GetMapping("/verifyAttendee")
	public String verifyAttendee(@RequestParam("AttendeeId") int id, @RequestParam("AttendeeMobile") String mobile, Model model) {
		attendeeId = id;
		attendeeMobile = mobile;
		if (user.isInputPasswordValid()) {
			Attendee attendeeToUpdate = attendeeService.getAttendee(id);
			model.addAttribute("attendee", attendeeToUpdate);
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
			return "updateSignupSheet";
		}
		return "verifyAttendee";
	}
	
	@GetMapping("/deleteAttendee")
	public String deleteAttendee(@RequestParam("AttendeeId") int id) {
		attendeeService.deleteAttendee(id);
		return "redirect:/";
	}
}
