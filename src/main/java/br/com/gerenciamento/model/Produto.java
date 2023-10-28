package br.com.gerenciamento.model;

import br.com.gerenciamento.enums.Estados;
import br.com.gerenciamento.enums.Status;
import com.sun.istack.NotNull;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodid;

    @Column(name = "prod")
    @Size(min = 5, max = 255, message = "Descreva seu produto!")
    @NotBlank(message = "O produto não pode ser vazio!")
    @NotNull
    private String prod;

    @Column(name = "nome")
    @NotNull
    @NotBlank(message = "Insira o nome do responsável pelo anúncio!")
    private String nome;


    @Column(name = "telefone")
    @NotNull
    @Size(min = 10, max = 11, message = "Insira o número de telefone corretamente!")
    private String telefone;

    @Column(name = "estados")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Estados estados;

    @Column(name = "cidade")
    @NotNull
    @Size(max = 50, message = "Insira sua cidade!")
    private String cidade;

    @Column(name = "status")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name="login")
    @Size(max = 20)
    private String login;

    public Produto() {
    }

    public Long getProdid() {
        return prodid;
    }

    public void setProdid(Long prodid) {
        this.prodid = prodid;
    }

    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Estados getEstados() {
        return estados;
    }

    public void setEstados(Estados estados) {
        this.estados = estados;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String usuario_userid) {
        this.login = usuario_userid;
    }

}