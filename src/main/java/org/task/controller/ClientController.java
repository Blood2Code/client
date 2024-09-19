package org.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.task.model.Client;
import org.task.model.enums.ContactType;
import org.task.service.ClientService;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public String addClient(@RequestParam String name) {
        clientService.addClient(name);
        return "redirect:/clients";
    }

    @PostMapping("/{clientId}/contacts")
    public String addContact(
            @PathVariable Long clientId,
            @RequestParam ContactType type,
            @RequestParam String value) {
        clientService.addContact(clientId, type, value);
        return "redirect:/clients/" + clientId;
    }

    @GetMapping
    public String getClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients";
    }

    @GetMapping("/{clientId}")
    public String getClient(@PathVariable Long clientId, Model model) {
        Client client = clientService.getClientById(clientId);
        model.addAttribute("client", client);
        return "client";
    }

    @GetMapping("/{clientId}/contacts")
    public String getContacts(@PathVariable Long clientId, Model model) {
        model.addAttribute("contacts", clientService.getContacts(clientId));
        return "contacts";
    }

    @GetMapping("/{clientId}/contacts/{type}")
    public String getContactsByType(
            @PathVariable Long clientId,
            @PathVariable ContactType type,
            Model model) {
        model.addAttribute("contacts", clientService.getContactsByType(clientId, type));
        return "contacts";
    }
}
