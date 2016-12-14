/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;
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
@Table(name = "agendas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agendas.findAll", query = "SELECT a FROM Agendas a"),
    @NamedQuery(name = "Agendas.findById", query = "SELECT a FROM Agendas a WHERE a.id = :id"),
    @NamedQuery(name = "Agendas.findBySituacao", query = "SELECT a FROM Agendas a WHERE a.situacao = :situacao"),
    @NamedQuery(name = "Agendas.findByDescricao", query = "SELECT a FROM Agendas a WHERE a.descricao = :descricao"),
    @NamedQuery(name = "Agendas.findByInicioEstimado", query = "SELECT a FROM Agendas a WHERE a.inicioEstimado = :inicioEstimado"),
    @NamedQuery(name = "Agendas.findByFimEstimado", query = "SELECT a FROM Agendas a WHERE a.fimEstimado = :fimEstimado"),
    @NamedQuery(name = "Agendas.findByTempoRealizado", query = "SELECT a FROM Agendas a WHERE a.tempoRealizado = :tempoRealizado"),
    @NamedQuery(name = "Agendas.findByValor", query = "SELECT a FROM Agendas a WHERE a.valor = :valor"),
    @NamedQuery(name = "Agendas.findByObsPrestador", query = "SELECT a FROM Agendas a WHERE a.obsPrestador = :obsPrestador"),
    @NamedQuery(name = "Agendas.findByObsCliente", query = "SELECT a FROM Agendas a WHERE a.obsCliente = :obsCliente"),
    @NamedQuery(name = "Agendas.findByNota", query = "SELECT a FROM Agendas a WHERE a.nota = :nota"),
    @NamedQuery(name = "Agendas.findByDelete", query = "SELECT a FROM Agendas a WHERE a.delete = :delete")})
public class Agendas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "situacao")
    private Character situacao;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "inicio_estimado")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar inicioEstimado;
    @Column(name = "fim_estimado")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fimEstimado;
    @Column(name = "tempo_realizado")
    private BigInteger tempoRealizado;
    @Column(name = "valor")
    private BigInteger valor;
    @Column(name = "obs_prestador")
    private String obsPrestador;
    @Column(name = "obs_cliente")
    private String obsCliente;
    @Column(name = "nota")
    private Integer nota;
    @Column(name = "delete")
    private Character delete;
    @JoinColumn(name = "ref_clientes", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Clientes refClientes;
    @JoinColumn(name = "ref_servicos_has_prestadores", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ServicosHasPrestadores refServicosHasPrestadores;

    public Agendas() {
    }

    public Agendas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getSituacao() {
        return situacao;
    }

    public void setSituacao(Character situacao) {
        this.situacao = situacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getInicioEstimado() {
        return inicioEstimado != null ? inicioEstimado.getTime() : null;
    }

    public void setInicioEstimado(Calendar inicioEstimado) {
        this.inicioEstimado = inicioEstimado;
    }

    public Date getFimEstimado() {
        return fimEstimado != null ? fimEstimado.getTime() : null;
    }

    public void setFimEstimado(Calendar fimEstimado) {
        this.fimEstimado = fimEstimado;
    }

    public BigInteger getTempoRealizado() {
        return tempoRealizado;
    }

    public void setTempoRealizado(BigInteger tempoRealizado) {
        this.tempoRealizado = tempoRealizado;
    }

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }

    public String getObsPrestador() {
        return obsPrestador;
    }

    public void setObsPrestador(String obsPrestador) {
        this.obsPrestador = obsPrestador;
    }

    public String getObsCliente() {
        return obsCliente;
    }

    public void setObsCliente(String obsCliente) {
        this.obsCliente = obsCliente;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Character getDelete() {
        return delete;
    }

    public void setDelete(Character delete) {
        this.delete = delete;
    }

    public Clientes getRefClientes() {
        return refClientes;
    }

    public void setRefClientes(Clientes refClientes) {
        this.refClientes = refClientes;
    }

    public ServicosHasPrestadores getRefServicosHasPrestadores() {
        return refServicosHasPrestadores;
    }

    public void setRefServicosHasPrestadores(ServicosHasPrestadores refServicosHasPrestadores) {
        this.refServicosHasPrestadores = refServicosHasPrestadores;
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
        if (!(object instanceof Agendas)) {
            return false;
        }
        Agendas other = (Agendas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Agendas[ id=" + id + " ]";
    }
    
}
