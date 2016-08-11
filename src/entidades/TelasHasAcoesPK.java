/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author asimon
 */
@Embeddable
public class TelasHasAcoesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ref_telas")
    private int refTelas;
    @Basic(optional = false)
    @Column(name = "ref_acoes")
    private int refAcoes;

    public TelasHasAcoesPK() {
    }

    public TelasHasAcoesPK(int refTelas, int refAcoes) {
        this.refTelas = refTelas;
        this.refAcoes = refAcoes;
    }

    public int getRefTelas() {
        return refTelas;
    }

    public void setRefTelas(int refTelas) {
        this.refTelas = refTelas;
    }

    public int getRefAcoes() {
        return refAcoes;
    }

    public void setRefAcoes(int refAcoes) {
        this.refAcoes = refAcoes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) refTelas;
        hash += (int) refAcoes;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TelasHasAcoesPK)) {
            return false;
        }
        TelasHasAcoesPK other = (TelasHasAcoesPK) object;
        if (this.refTelas != other.refTelas) {
            return false;
        }
        if (this.refAcoes != other.refAcoes) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TelasHasAcoesPK[ refTelas=" + refTelas + ", refAcoes=" + refAcoes + " ]";
    }
    
}
