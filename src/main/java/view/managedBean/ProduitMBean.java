package view.managedBean;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import persistence.dao.CategorieDao;
import persistence.dao.CategorieDaoImpl;
import persistence.dao.ProduitDao;
import persistence.dao.ProduitDaoImpl;
import persistence.entities.Categorie;
import persistence.entities.Produit;

@ManagedBean
@SessionScoped
public class ProduitMBean {
	
	private Produit produit = new Produit();
	private Produit selectedProduit = new Produit();
	ProduitDao produitdao = new ProduitDaoImpl();
	private List<Produit> listProduit = new ArrayList<Produit>();
	
	
	public Produit getProduit() {
		return produit;
	}


	public void setProduit(Produit produit) {
		this.produit = produit;
	}


	public Produit getSelectedProduit() {
		return selectedProduit;
	}


	public void setSelectedProduit(Produit selectedProduit) {
		this.selectedProduit = selectedProduit;
	}


	public List<Produit> getListProduit() {
		listProduit = produitdao.findAll();
		return listProduit;
	}


	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}


	public void addProduit(ActionEvent e) {
		produitdao.add(produit);
		produit = new Produit();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout effectu� avec succ�s"));
	}
	public void deleteProduit(ActionEvent e) {
		if(selectedProduit==null  || selectedProduit.getIdproduit()== new BigDecimal(0))
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Attention", "Aucun produit s�lectionn�"));
		else {
			produitdao.delete(selectedProduit);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression effectu� avec succ�s"));
		}
	}
	
	public String editProduit() {
		return "editProduit.xhtml";
	}
	public void updateProduit(ActionEvent e) {
		produitdao.update(selectedProduit);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modification effectu� avec succ�s"));
	}
}
