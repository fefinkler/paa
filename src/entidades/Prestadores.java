/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author asimon
 */
@Entity
@Table(name = "prestadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestadores.findAll", query = "SELECT p FROM Prestadores p"),
    @NamedQuery(name = "Prestadores.findById", query = "SELECT p FROM Prestadores p WHERE p.id = :id"),
    @NamedQuery(name = "Prestadores.findByNome", query = "SELECT p FROM Prestadores p WHERE p.nome = :nome"),
    @NamedQuery(name = "Prestadores.findByCpf", query = "SELECT p FROM Prestadores p WHERE p.cpf = :cpf"),
    @NamedQuery(name = "Prestadores.findByRg", query = "SELECT p FROM Prestadores p WHERE p.rg = :rg"),
    @NamedQuery(name = "Prestadores.findByTelefone", query = "SELECT p FROM Prestadores p WHERE p.telefone = :telefone"),
    @NamedQuery(name = "Prestadores.findByCelular", query = "SELECT p FROM Prestadores p WHERE p.celular = :celular"),
    @NamedQuery(name = "Prestadores.findByEmail", query = "SELECT p FROM Prestadores p WHERE p.email = :email"),
    @NamedQuery(name = "Prestadores.findByEndereco", query = "SELECT p FROM Prestadores p WHERE p.endereco = :endereco"),
    @NamedQuery(name = "Prestadores.findByCep", query = "SELECT p FROM Prestadores p WHERE p.cep = :cep"),
    @NamedQuery(name = "Prestadores.findByDelete", query = "SELECT p FROM Prestadores p WHERE p.delete = :delete")})
public class Prestadores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "rg")
    private String rg;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "celular")
    private String celular;
    @Column(name = "email")
    private String email;
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "cep")
    private String cep;
    @Column(name = "delete")
    private Character delete;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refPrestadores")
    private List<ServicosHasPrestadores> servicosHasPrestadoresList;
    @JoinColumn(name = "ref_cidades", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cidades refCidades;

    public Prestadores() {
    }

    public Prestadores(Integer id) {
        this.id = id;
    }

    public Prestadores(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Character getDelete() {
        return delete;
    }

    public void setDelete(Character delete) {
        this.delete = delete;
    }

    @XmlTransient
    public List<ServicosHasPrestadores> getServicosHasPrestadoresList() {
        return servicosHasPrestadoresList;
    }

    public void setServicosHasPrestadoresList(List<ServicosHasPrestadores> servicosHasPrestadoresList) {
        this.servicosHasPrestadoresList = servicosHasPrestadoresList;
    }

    public Cidades getRefCidades() {
        return refCidades;
    }

    public void setRefCidades(Cidades refCidades) {
        this.refCidades = refCidades;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestadores)) {
            return false;
        }
        Prestadores other = (Prestadores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Prestadores[ id=" + id + " ]";
    }
    
}
