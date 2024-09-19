package org.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.task.model.Client;
import org.task.model.Contact;
import org.task.model.enums.ContactType;
import org.task.repository.ClientRepository;
import org.task.repository.ContactRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Transactional
    public Client addClient(String name) {
        Client client = new Client();
        client.setName(name);
        clientRepository.save(client);
        return client;
    }

    @Transactional
    public Contact addContact(Long clientId, ContactType type, String value) {
        Client client = clientRepository.findById(clientId);
        if (client == null) {
            throw new EntityNotFoundException("Client not found");
        }
        Contact contact = new Contact();
        contact.setClient(client);
        contact.setContactType(type);
        contact.setContactValue(value);
        contactRepository.save(contact);
        return contact;
    }

    @Transactional(readOnly = true)
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Client getClientById(Long clientId) {
        return clientRepository.findById(clientId);
    }

    @Transactional(readOnly = true)
    public List<Contact> getContacts(Long clientId) {
        return contactRepository.findByClientId(clientId);
    }

    @Transactional(readOnly = true)
    public List<Contact> getContactsByType(Long clientId, ContactType type) {
        return contactRepository.findByClientIdAndType(clientId, type);
    }
}
