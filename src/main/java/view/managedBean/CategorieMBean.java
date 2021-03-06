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
import persistence.entities.Categorie;
import persistence.entities.Client;

@ManagedBean
@SessionScoped
public class CategorieMBean {
	 
	private Categorie categorie = new Categorie();
	private Categorie selectedCategorie = new Categorie();
	CategorieDao catedao = new CategorieDaoImpl();
	private List<Categorie> listCategorie = new ArrayList<Categorie>();
	
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public Categorie getSelectedCategorie() {
		return selectedCategorie;
	}
	public void setSelectedCategorie(Categorie selectedCategorie) {
		this.selectedCategorie = selectedCategorie;
	}
	public List<Categorie> getListCategorie() {
		listCategorie = catedao.findAll();
		return listCategorie;
	}
	public void setListCategorie(List<Categorie> listCategorie) {
		this.listCategorie = listCategorie;
	}
	public void addCategorie(ActionEvent e) {
		catedao.add(categorie);
		categorie = new Categorie();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout effectu? avec succ?s"));
	}
	public void deleteCategorie(ActionEvent e) {
		if(selectedCategorie==null  || selectedCategorie.getIdcateg()== new BigDecimal(0))
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Attention", "Aucune categorie s?lectionn?"));
		else {
			catedao.delete(selectedCategorie);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression effectu? avec succ?s"));
		}
	}
	public String editCategorie() {
		return "editCategorie.xhtml";
	}
	public void updateCategorie(ActionEvent e) {
		catedao.update(selectedCategorie);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modification effectu? avec succ?s"));
	}
}
