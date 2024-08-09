package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.form.SigninForm;
import com.example.demo.form.SignupForm;
import com.example.demo.service.AdminService;
import com.example.demo.service.ContactService;
import java.util.Random;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/contacts")
    public String showContact(Model model) {
        List<Contact> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "admins/admin_list";
    }

    @GetMapping("/contacts/{id}")
    public String showContactDetails(@PathVariable Long id, Model model) {
        Contact contact = contactService.getContactById(id);
        model.addAttribute("contact", contact);
        return "admins/admin_detail";
    }

    @GetMapping("/contacts/{id}/edit")
    public String showeditContactForm(@PathVariable Long id, Model model) {
        Contact contact = contactService.getContactById(id);
        if (contact == null) {
            return "redirect:/admin/contacts";
        }
        ContactForm contactForm = new ContactForm();
        contactForm.setId(contact.getId());
        contactForm.setLastName(contact.getLastName());
        contactForm.setFirstName(contact.getFirstName());
        contactForm.setEmail(contact.getEmail());
        contactForm.setPhone(contact.getPhone());
        contactForm.setZipCode(contact.getZipCode());
        contactForm.setAddress(contact.getAddress());
        contactForm.setBuildingName(contact.getBuildingName());
        contactForm.setContactType(contact.getContactType());
        contactForm.setBody(contact.getBody());
        
        model.addAttribute("contactForm", contactForm);
        model.addAttribute("id", id);
        return "admins/admin_edit";
    }

    @PostMapping("/contacts/{id}/edit")
    public String editContact(@PathVariable Long id, @ModelAttribute ContactForm contactForm) {
        contactService.updateContact(id, contactForm);
        return "redirect:/admin/contacts";
    }

    @PostMapping("/contacts/{id}/delete")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/admin/contacts";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/admin/signin";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        SignupForm signupForm = new SignupForm();
        model.addAttribute("signupForm", signupForm);
        return "admins/admin_signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute SignupForm signupForm, Model model) {
        adminService.registerUser(signupForm.getLastName(), signupForm.getFirstName(), signupForm.getEmail(), signupForm.getPassword());
        return "redirect:/admin/signin";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signin(Model model) {
        SigninForm signinForm = new SigninForm();
        model.addAttribute("signinForm", signinForm);
        return "admins/admin_signin";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String login(@ModelAttribute SigninForm signinForm, Model model) {
        boolean successLogin = adminService.signin(signinForm.getEmail(), signinForm.getPassword());
        if (successLogin) {
            return "redirect:/admin/contacts";
        } else {
            model.addAttribute("message", "ログインに失敗しました。");
            return "admins/admin_signin";
        }
    }
    
    public class MakePassword {

    	  /** 実行用mainメソッド */
    	  public static void main(String[] args) {
    	    System.out.println(makePassword(10, true, true));
    	  }

    	  /** パスワードを作成 */
    	  private static StringBuilder makePassword(int length, boolean uppercaseFlg, boolean digitFlg) {
    	    StringBuilder lowercase = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
    	    StringBuilder uppercase = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    	    StringBuilder digit = new StringBuilder("0123456789");

    	    if (uppercaseFlg) {
    	      lowercase.append(uppercase);
    	    }
    	    if (digitFlg) {
    	      lowercase.append(digit);
    	    }

    	    StringBuilder password = new StringBuilder();

    	    Random rand = new Random();
    	    for (int i = 0; i < length; i++) {
    	      int num = rand.nextInt(lowercase.length());
    	      password.append(lowercase.charAt(num));
    	    }

    	    return password;
    	  }
    	}
}
