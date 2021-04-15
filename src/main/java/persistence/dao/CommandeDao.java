package persistence.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import persistence.entities.Commande;


public interface CommandeDao extends GlobalDao<Commande> {
	
	List<Commande> findByEtat(BigDecimal etat);
	List<Commande> findByDate(Date dateDebut, Date dateFin);
	
}
