package org.task.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.task.model.Contact;
import org.task.model.enums.ContactType;

import java.util.List;

@Repository
public class ContactRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Contact contact) {
        sessionFactory.getCurrentSession().save(contact);
    }

    public List<Contact> findByClientId(Long clientId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Contact where client.id = :clientId", Contact.class)
                .setParameter("clientId", clientId)
                .list();
    }

    public List<Contact> findByClientIdAndType(Long clientId, ContactType type) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Contact where client.id = :clientId and contactType = :type", Contact.class)
                .setParameter("clientId", clientId)
                .setParameter("type", type)
                .list();
    }
}
