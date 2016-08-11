/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author asimon
 */
@Entity
@Table(name = "telas_has_acoes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TelasHasAcoes.findAll", query = "SELECT t FROM TelasHasAcoes t"),
    @NamedQuery(name = "TelasHasAcoes.findByRefTelas", query = "SELECT t FROM TelasHasAcoes t WHERE t.telasHasAcoesPK.refTelas = :refTelas"),
    @NamedQuery(name = "TelasHasAcoes.findByRefAcoes", query = "SELECT t FROM TelasHasAcoes t WHERE t.telasHasAcoesPK.refAcoes = :refAcoes")})
public class TelasHasAcoes implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TelasHasAcoesPK telasHasAcoesPK;
    @JoinTable(name = "telas_has_acoes_has_usuarios", joinColumns = {
        @JoinColumn(name = "ref_telas", referencedColumnName = "ref_telas"),
        @JoinColumn(name = "ref_acoes", referencedColumnName = "ref_acoes")}, inverseJoinColumns = {
        @JoinColumn(name = "ref_usuarios", referencedColumnName = "id")})
    @ManyToMany
    private List<Usuarios> usuariosList;
    @JoinColumn(name = "ref_acoes", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Acoes acoes;
    @JoinColumn(name = "ref_telas", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Telas telas;

    public TelasHasAcoes() {
    }

    public TelasHasAcoes(TelasHasAcoesPK telasHasAcoesPK) {
        this.telasHasAcoesPK = telasHasAcoesPK;
    }

    public TelasHasAcoes(int refTelas, int refAcoes) {
        this.telasHasAcoesPK = new TelasHasAcoesPK(refTelas, refAcoes);
    }

    public TelasHasAcoesPK getTelasHasAcoesPK() {
        return telasHasAcoesPK;
    }

    public void setTelasHasAcoesPK(TelasHasAcoesPK telasHasAcoesPK) {
        this.telasHasAcoesPK = telasHasAcoesPK;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public Acoes getAcoes() {
        return acoes;
    }

    public void setAcoes(Acoes acoes) {
        this.acoes = acoes;
    }

    public Telas getTelas() {
        return telas;
    }

    public void setTelas(Telas telas) {
        this.telas = telas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (telasHasAcoesPK != null ? telasHasAcoesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TelasHasAcoes)) {
            return false;
        }
        TelasHasAcoes other = (TelasHasAcoes) object;
        if ((this.telasHasAcoesPK == null && other.telasHasAcoesPK != null) || (this.telasHasAcoesPK != null && !this.telasHasAcoesPK.equals(other.telasHasAcoesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TelasHasAcoes[ telasHasAcoesPK=" + telasHasAcoesPK + " ]";
    }
    
}
