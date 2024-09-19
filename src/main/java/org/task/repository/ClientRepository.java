package org.task.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.task.model.Client;

import java.util.List;

@Repository
public class ClientRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Client client) {
        sessionFactory.getCurrentSession().save(client);
    }

    public Client findById(Long id) {
        return sessionFactory.getCurrentSession().get(Client.class, id);
    }

    public List<Client> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Client", Client.class).list();
    }

}
