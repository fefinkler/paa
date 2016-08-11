/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asimon
 */
@Entity
@Table(name = "log_acoes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogAcoes.findAll", query = "SELECT l FROM LogAcoes l"),
    @NamedQuery(name = "LogAcoes.findById", query = "SELECT l FROM LogAcoes l WHERE l.id = :id"),
    @NamedQuery(name = "LogAcoes.findByDataHora", query = "SELECT l FROM LogAcoes l WHERE l.dataHora = :dataHora"),
    @NamedQuery(name = "LogAcoes.findByTabela", query = "SELECT l FROM LogAcoes l WHERE l.tabela = :tabela"),
    @NamedQuery(name = "LogAcoes.findByAcao", query = "SELECT l FROM LogAcoes l WHERE l.acao = :acao"),
    @NamedQuery(name = "LogAcoes.findByCampo", query = "SELECT l FROM LogAcoes l WHERE l.campo = :campo"),
    @NamedQuery(name = "LogAcoes.findByValorAnterior", query = "SELECT l FROM LogAcoes l WHERE l.valorAnterior = :valorAnterior"),
    @NamedQuery(name = "LogAcoes.findByValorAtual", query = "SELECT l FROM LogAcoes l WHERE l.valorAtual = :valorAtual")})
public class LogAcoes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "data_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    @Column(name = "tabela")
    private String tabela;
    @Column(name = "acao")
    private Character acao;
    @Column(name = "campo")
    private String campo;
    @Column(name = "valor_anterior")
    private String valorAnterior;
    @Column(name = "valor_atual")
    private String valorAtual;
    @JoinColumn(name = "usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuarios usuariosId;

    public LogAcoes() {
    }

    public LogAcoes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public Character getAcao() {
        return acao;
    }

    public void setAcao(Character acao) {
        this.acao = acao;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(String valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public String getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(String valorAtual) {
        this.valorAtual = valorAtual;
    }

    public Usuarios getUsuariosId() {
        return usuariosId;
    }

    public void setUsuariosId(Usuarios usuariosId) {
        this.usuariosId = usuariosId;
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
        if (!(object instanceof LogAcoes)) {
            return false;
        }
        LogAcoes other = (LogAcoes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.LogAcoes[ id=" + id + " ]";
    }
    
}
