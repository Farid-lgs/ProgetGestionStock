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
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout effectu� avec succ�s"));
	}
	public void deleteClient(ActionEvent e) {
		if(selectedClient==null  || selectedClient.getIdclient()== new BigDecimal(0))
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Attention", "Aucun  client s�lectionn�"));
		else {
			clidao.delete(selectedClient);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression effectu� avec succ�s"));
		}
	}
	public String editClient() {
		return "editClient.xhtml";
	}
	public void updateClient(ActionEvent e) {
		clidao.update(selectedClient);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modification effectu� avec succ�s"));
	}
}
