package view.managedBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import persistence.dao.ClientDao;
import persistence.dao.ClientDaoImpl;
import persistence.dao.CommandeDao;
import persistence.dao.CommandeDaoImpl;
import persistence.entities.Client;
import persistence.entities.Commande;
import persistence.entities.CommandeId;

@ManagedBean
@SessionScoped
public class CommandeMBean {
	
	private Commande commande = new Commande();
	private Commande selectedCommande = new Commande();
	CommandeDao cmddao = new CommandeDaoImpl();
	private List<Commande> listCommande = new ArrayList<Commande>();

	public Commande getCommande() {
		return commande;
	}


	public void setClient(Commande commande) {
		this.commande = commande;
	}


	public Commande getSelectedCommande() {
		return selectedCommande;
	}


	public void setSelectedCommande(Commande selectedCommande) {
		this.selectedCommande = selectedCommande;
	}


	public List<Commande> getListCommande() {
		listCommande = cmddao.findAll();
		return listCommande;
	}

	public void setListClient(List<Commande> listCommande) {
		this.listCommande = listCommande;
	}


	public void addCommande(ActionEvent e) {
		cmddao.add(commande);
		commande = new Commande();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout effectué avec succés"));
	}
	
	public void deleteCommande(ActionEvent e) {
		if(selectedCommande == null  || selectedCommande.getId() == null)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Attention", "Aucun  commande sélectionné"));
		else {
			cmddao.delete(selectedCommande);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression effectué avec succés"));
		}
	}
	
	public String editCommande() {
		return "editCommande.xhtml";
	}
	
	public void updateCommande(ActionEvent e) {
		cmddao.update(selectedCommande);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modification effectué avec succés"));
	}
	
}
