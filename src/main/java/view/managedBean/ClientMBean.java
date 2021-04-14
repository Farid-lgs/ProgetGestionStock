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
import persistence.entities.Client;

@ManagedBean
@SessionScoped
public class ClientMBean {
	
	private Client client = new Client();
	private Client selectedClient = new Client();
	ClientDao clidao = new ClientDaoImpl();
	private List<Client> listClient = new ArrayList<Client>();
	private List<Client> listClientNom = new ArrayList<Client>();
	private List<Client> listClientPrenom = new ArrayList<Client>();
	private String valeurRecherche;
	private String critereRecherche;

    public String getCritereRecherche() {
        return critereRecherche;
    }

    public void setCritereRecherche(String text2) {
        this.critereRecherche = text2;
    }
    
    public String renvoi() {
    	if (critereRecherche.equalsIgnoreCase("1")) {
    		return "showClientNom.xhtml";
    		}
    	else if(critereRecherche.equalsIgnoreCase("2")) {
    		return "showClientPrenom.xhtml";
    		}
    	else
    		return "showClient.xhtml";
    }
	
	
	public String getValeurRecherche() {
		return valeurRecherche;
	}

	public void setValeurRecherche(String valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}

	public List<Client> getListClientNom() {
		listClientNom = clidao.findByNom(valeurRecherche);
		return listClientNom;
	}
	public void setListClientNom(List<Client> listClientNom) {
		this.listClientNom = listClientNom;
	}
	public List<Client> getListClientPrenom() {
		listClientPrenom = clidao.findByPrenom(valeurRecherche);
		return listClientPrenom;
	}
	public void setListClientPrenom(List<Client> listClientPrenom) {
		this.listClientPrenom = listClientPrenom;
	}
	public Client getSelectedClient() {
		return selectedClient;
	}
	public void setSelectedClient(Client selectedClient) {
		this.selectedClient = selectedClient;
	}
	
	public List<Client> getListClient() {
		this.listClient = clidao.findAll();
		return listClient;
	}
	public void setListClient(List<Client> listClient) {
		this.listClient = listClient;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public void addClient(ActionEvent e) {
		clidao.add(client);
		client = new Client();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout effectué avec succés"));
	}
	public void deleteClient(ActionEvent e) {
		if(selectedClient==null  || selectedClient.getIdclient()== new BigDecimal(0))
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Attention", "Aucun  client sélectionné"));
		else {
			clidao.delete(selectedClient);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression effectué avec succés"));
		}
	}
	public String editClient() {
		return "editClient.xhtml";
	}
	public void updateClient(ActionEvent e) {
		clidao.update(selectedClient);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modification effectué avec succés"));
	}
}
