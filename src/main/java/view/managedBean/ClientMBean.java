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
	private String valeurRecherche;
	private String critereRecherche;
	
	public ClientMBean() {
		this.listClient = clidao.findAll();
	}
	
    public String getCritereRecherche() {
        return critereRecherche;
    }

    public void setCritereRecherche(String text2) {
        this.critereRecherche = text2;
    }
    
    public void renvoi(ActionEvent e) {
    	if (critereRecherche.equalsIgnoreCase("0")||critereRecherche==null)
    		this.listClient = clidao.findAll();
    	else if (critereRecherche.equalsIgnoreCase("1")) 
    		this.listClient = clidao.findByNom(valeurRecherche);
    	else if (critereRecherche.equalsIgnoreCase("2")) 
    		this.listClient = clidao.findByPrenom(valeurRecherche);
    }
	
	
	public String getValeurRecherche() {
		return valeurRecherche;
	}

	public void setValeurRecherche(String valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}

	public Client getSelectedClient() {
		return selectedClient;
	}
	public void setSelectedClient(Client selectedClient) {
		this.selectedClient = selectedClient;
	}
	
	public List<Client> getListClient() {
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
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout effectu? avec succ?s"));
	}
	public void deleteClient(ActionEvent e) {
		if(selectedClient==null  || selectedClient.getIdclient()== new BigDecimal(0))
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Attention", "Aucun  client s?lectionn?"));
		else {
			clidao.delete(selectedClient);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression effectu? avec succ?s"));
		}
	}
	public String editClient() {
		return "editClient.xhtml";
	}
	public void updateClient(ActionEvent e) {
		clidao.update(selectedClient);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modification effectu? avec succ?s"));
	}
}
