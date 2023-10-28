package br.com.gerenciamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "logado")

public class Logado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long sessionid;

    @Size(max = 20)
    String online;

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public Long getSessionid() {
        return sessionid;
    }

    public void setSessionid(Long sessionid) {
        this.sessionid = sessionid;
    }
    
}
