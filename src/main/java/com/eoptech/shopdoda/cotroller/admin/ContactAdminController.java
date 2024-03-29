package com.eoptech.shopdoda.cotroller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eoptech.shopdoda.entities.Contact;
import com.eoptech.shopdoda.repositories.ContactRepo;
import com.eoptech.shopdoda.services.ContactService;

@Controller
@RequestMapping("admin/contacts")
public class ContactAdminController {

    @Autowired
    private ContactRepo contactRepo;

    @Autowired
    private ContactService contactService;

    @GetMapping
    public String contacts(final ModelMap model) {
        model.addAttribute("contactList", contactService.getContacts());
        return "back-end/contacts";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(final ModelMap model, @PathVariable("id") int contactId) {

        Contact contact = contactRepo.findById(contactId).get();
        contact.setStatus(false);
        contactRepo.save(contact);

        return "redirect:/admin/contacts";
    }

}
