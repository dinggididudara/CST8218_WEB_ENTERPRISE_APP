/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.lee00665.bouncer.soomin2.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author lee00665
 */
@Entity
@Table(name = "BOUNCER")
@NamedQueries({
    @NamedQuery(name = "Bouncer.findAll", query = "SELECT b FROM Bouncer b"),
    @NamedQuery(name = "Bouncer.findByX", query = "SELECT b FROM Bouncer b WHERE b.x = :x"),
    @NamedQuery(name = "Bouncer.findByY", query = "SELECT b FROM Bouncer b WHERE b.y = :y"),
    @NamedQuery(name = "Bouncer.findByYspeed", query = "SELECT b FROM Bouncer b WHERE b.yspeed = :yspeed")})
public class Bouncer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "X")
    private Integer x;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Y")
    private int y;
    @Basic(optional = false)
    @NotNull
    @Column(name = "YSPEED")
    private int yspeed;

    public Bouncer() {
    }

    public Bouncer(Integer x) {
        this.x = x;
    }

    public Bouncer(Integer x, int y, int yspeed) {
        this.x = x;
        this.y = y;
        this.yspeed = yspeed;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getYspeed() {
        return yspeed;
    }

    public void setYspeed(int yspeed) {
        this.yspeed = yspeed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (x != null ? x.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bouncer)) {
            return false;
        }
        Bouncer other = (Bouncer) object;
        if ((this.x == null && other.x != null) || (this.x != null && !this.x.equals(other.x))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cst8218.lee00665.bouncer.soomin2.entity.Bouncer[ x=" + x + " ]";
    }
    
}
