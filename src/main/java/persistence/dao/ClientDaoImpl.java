package persistence.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.entities.Categorie;
import persistence.entities.Client;

public class ClientDaoImpl implements ClientDao {

	@Override
	public void add(Client client) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(client);
		tx.commit();
		session.close();
	}

	@Override
	public void delete(Client client) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction(); 
		s.delete(client);
		tx.commit();
		s.close();
		
	}

	@Override
	public void update(Client client) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction(); 
		s.update(client);
		tx.commit();
		s.close();
		
	}

	@Override
	public List<Client> findAll() {
		List<Client> listeClient = new ArrayList<Client>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		listeClient = s.createQuery("from Client").list();
		s.close();
		return listeClient;
	}

	@Override
	public Client findById(Serializable idclient) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Client client = s.get(Client.class,(int) idclient);
		s.close();
		return client;
	}

}
