/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.lee00665.bouncer.soomin3;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Bouncer.java
 * 
 * Bouncer class represent a bouncing object
 * This contains properties and methods related to bouncer
 * This is used to manage bouncer's movement in Bouncer application
 * 
 * @author lee00665
 */
@Entity
@Table(name = "BOUNCER")
@NamedQueries({
    @NamedQuery(name = "Bouncer.findAll", query = "SELECT b FROM Bouncer b"),
    @NamedQuery(name = "Bouncer.findById", query = "SELECT b FROM Bouncer b WHERE b.id = :id"),
    @NamedQuery(name = "Bouncer.findByX", query = "SELECT b FROM Bouncer b WHERE b.x = :x"),
    @NamedQuery(name = "Bouncer.findByY", query = "SELECT b FROM Bouncer b WHERE b.y = :y"),
    @NamedQuery(name = "Bouncer.findByYspeed", query = "SELECT b FROM Bouncer b WHERE b.yspeed = :yspeed")})
public class Bouncer implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "X")
    private int x;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Y")
    private int y;
    @Basic(optional = false)
    @NotNull
    @Column(name = "YSPEED")
    private int yspeed;

    public static final int FRAME_HEIGHT = 500;
    public static final int FRAME_WIDTH = 500;
    public static final int CHANGE_RATE = 1;
    public static final int GRAVITY_ACCEL = 2;
    public static final int DECAY_RATE = 1;
    
    
    public Bouncer() {
    }

    public Bouncer(Long id) {
        this.id = id;
    }

    public Bouncer(Long id, int x, int y, int yspeed) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.yspeed = yspeed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bouncer)) {
            return false;
        }
        Bouncer other = (Bouncer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cst8218.lee00665.bouncer.soomin3.Bouncer[ id=" + id + " ]";
    }
    
    public void updateBouncer(Bouncer newBouncer){
        if(newBouncer.getX() != 0){
            this.setX(newBouncer.getX());
        }
        if(newBouncer.getY() != 0){
            this.setY(newBouncer.getY());
        }
        if(newBouncer.getYspeed() != 0){
            this.setYspeed(newBouncer.getYspeed());
        }
        
    }
    
    public void replace(Bouncer newBouncer){
        if (newBouncer.getId() != null && this.getId().equals(newBouncer.getId())) {
            this.updateBouncer(newBouncer);
        }
    }
    
    public void advanceOneFrame(){
        //if hit the right wall, bounce 
        if(x >= FRAME_HEIGHT || x <= 0){
            x = FRAME_HEIGHT; //place at the right wall
        }
       
        //if hit the bottom or top wall, bounce
        if(y <= 0 || y >= FRAME_HEIGHT){
           yspeed = -yspeed; //speed decrease
        }
        //moving until hit somewhere
        y += yspeed;
        yspeed += GRAVITY_ACCEL;
        
        if(yspeed < 0){ //going up
            yspeed *= DECAY_RATE; //decrease speed
        } else {
            yspeed += GRAVITY_ACCEL; //going down, increase speed with gravity acceleration
        }
        
    }
}
