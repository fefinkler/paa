/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "servicos_has_prestadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicosHasPrestadores.findAll", query = "SELECT s FROM ServicosHasPrestadores s"),
    @NamedQuery(name = "ServicosHasPrestadores.findById", query = "SELECT s FROM ServicosHasPrestadores s WHERE s.id = :id"),
    @NamedQuery(name = "ServicosHasPrestadores.findByValorHora", query = "SELECT s FROM ServicosHasPrestadores s WHERE s.valorHora = :valorHora")})
public class ServicosHasPrestadores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "valor_hora")
    private BigInteger valorHora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refServicosHasPrestadores")
    private List<Agendas> agendasList;
    @JoinColumn(name = "ref_prestadores", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Prestadores refPrestadores;
    @JoinColumn(name = "ref_servicos", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Servicos refServicos;

    public ServicosHasPrestadores() {
    }

    public ServicosHasPrestadores(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigInteger valorHora) {
        this.valorHora = valorHora;
    }

    @XmlTransient
    public List<Agendas> getAgendasList() {
        return agendasList;
    }

    public void setAgendasList(List<Agendas> agendasList) {
        this.agendasList = agendasList;
    }

    public Prestadores getRefPrestadores() {
        return refPrestadores;
    }

    public void setRefPrestadores(Prestadores refPrestadores) {
        this.refPrestadores = refPrestadores;
    }

    public Servicos getRefServicos() {
        return refServicos;
    }

    public void setRefServicos(Servicos refServicos) {
        this.refServicos = refServicos;
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
        if (!(object instanceof ServicosHasPrestadores)) {
            return false;
        }
        ServicosHasPrestadores other = (ServicosHasPrestadores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ServicosHasPrestadores[ id=" + id + " ]";
    }
    
}
