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
@Table(name = "servicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicos.findAll", query = "SELECT s FROM Servicos s"),
    @NamedQuery(name = "Servicos.findById", query = "SELECT s FROM Servicos s WHERE s.id = :id"),
    @NamedQuery(name = "Servicos.findByDescricao", query = "SELECT s FROM Servicos s WHERE s.descricao = :descricao"),
    @NamedQuery(name = "Servicos.findByDelete", query = "SELECT s FROM Servicos s WHERE s.delete = :delete")})
public class Servicos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "delete")
    private Character delete;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refServicos")
    private List<ServicosHasPrestadores> servicosHasPrestadoresList;

    public Servicos() {
    }

    public Servicos(Integer id) {
        this.id = id;
    }

    public Servicos(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicos)) {
            return false;
        }
        Servicos other = (Servicos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}
