package com.example.retos345.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "message")
public class Message implements Serializable{

    //***** ATRIBUTOS *****
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "messageText")
    private String messageText;

    //***** RELACIONES *****
    //Relacion Muchos a uno. El Message tiene enlazados un Client y Un Farm
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "client_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Client client;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "farm_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Farm farm;

    // @ManyToOne(fetch = FetchType.LAZY, optional = false)
	// @JoinColumn(name = "ortopedic_id")
	// @JsonProperty(access = Access.WRITE_ONLY)
	// private Ortopedic ortopedic;

    //***** METODOS *****
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    // public Ortopedic getOrtopedic() {
    //     return ortopedic;
    // }

    // public void setOrtopedic(Ortopedic ortopedic) {
    //     this.ortopedic = ortopedic;
    // }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    
}
