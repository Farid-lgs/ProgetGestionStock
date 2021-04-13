package persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.entities.Categorie;
import persistence.entities.Produit;

public class ProduitDaoImpl implements ProduitDao{
	
	@Override
    public void add(Produit produit) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(produit);
            tx.commit();
        }
    }

    @Override
    public void delete(Produit produit) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(produit);
            tx.commit();
        }
    }

    @Override
    public void update(Produit produit) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(produit);
            tx.commit();
        }
    }

    @Override
    public Produit findById(Serializable id) {
        Produit produit = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            produit = session.get(Produit.class,(int) id);
        }
        return produit;
    }

    @Override
    public List<Produit> findAll() {
        List<Produit> listProduits = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            listProduits = session.createQuery("from Produit").list();
        }
        return listProduits;
    }
    
}
