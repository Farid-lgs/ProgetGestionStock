package persistence.entities;
// Generated 24 mars 2021 � 11:44:39 by Hibernate Tools 4.0.1.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Commande generated by hbm2java
 */
@Entity
@Table(name = "COMMANDE", schema = "GESTIONSTOCK")
public class Commande implements java.io.Serializable {


	private CommandeId id = new CommandeId();
	private Client client = new Client();
	private Produit produit = new Produit();
	private BigDecimal qtecommande;
	private Date datecommande;
	private BigDecimal etatcommande;

	public Commande() {
	}

	public Commande(CommandeId id, Client client, Produit produit) {
		this.id = id;
		this.client = client;
		this.produit = produit;
	}

	public Commande(CommandeId id, Client client, Produit produit, BigDecimal qtecommande, Date datecommande,
			BigDecimal etatcommande) {
		this.id = id;
		this.client = client;
		this.produit = produit;
		this.qtecommande = qtecommande;
		this.datecommande = datecommande;
		this.etatcommande = etatcommande;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "idproduit", column = @Column(name = "IDPRODUIT", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "idclient", column = @Column(name = "IDCLIENT", nullable = false, precision = 22, scale = 0)) })
	public CommandeId getId() {
		this.id = new CommandeId(this.produit.getIdproduit(), this.client.getIdclient());
		return this.id;
	}

	public void setId(CommandeId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDCLIENT", nullable = false, insertable = false, updatable = false)
	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDPRODUIT", nullable = false, insertable = false, updatable = false)
	public Produit getProduit() {
		return this.produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	@Column(name = "QTECOMMANDE", precision = 22, scale = 0)
	public BigDecimal getQtecommande() {
		return this.qtecommande;
	}

	public void setQtecommande(BigDecimal qtecommande) {
		this.qtecommande = qtecommande;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATECOMMANDE", length = 7)
	public Date getDatecommande() {
		return this.datecommande;
	}

	public void setDatecommande(Date datecommande) {
		this.datecommande = datecommande;
	}

	@Column(name = "ETATCOMMANDE", precision = 22, scale = 0)
	public BigDecimal getEtatcommande() {
		return this.etatcommande;
	}

	public void setEtatcommande(BigDecimal etatcommande) {
		this.etatcommande = etatcommande;
	}

}
