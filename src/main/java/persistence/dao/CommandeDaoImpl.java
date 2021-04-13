package persistence.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.entities.Categorie;
import persistence.entities.Client;
import persistence.entities.Commande;
import persistence.entities.CommandeId;

public class CommandeDaoImpl implements CommandeDao {

	@Override
	public void add(Commande command) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(command);
		tx.commit();
		session.close();
		
	}

	@Override
	public void delete(Commande command) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction(); 
		s.delete(command);
		tx.commit();
		s.close();
		
	}

	@Override
	public void update(Commande command) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction(); 
		s.update(command);
		tx.commit();
		s.close();
	}

	@Override
	public List<Commande> findAll() {
		List<Commande> listeCommande = new ArrayList<Commande>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		listeCommande = s.createQuery("from Commande").list();
		s.close();
		return listeCommande;
	}

	@Override
	public Commande findById(Serializable commande) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Commande cmd = s.get(Commande.class,(CommandeId) commande);
		s.close();
		return cmd;
	}

}
