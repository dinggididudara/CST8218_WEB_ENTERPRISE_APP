/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.lee00665.bouncer.soomin1.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * class Bouncer creating bouncer object 
 * @author Soomin 040899389
 */
@Entity
public class Bouncer implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public final static Random random = new Random();
    
    final static int FRAME_WIDTH = 500;
    final static int FRAME_HEIGHT = 500;
    final static int SIZE = 10;
    final static int MAX_SPEED = 3;
    final static int GRAVITY_ACCEL = 1;
    final static int DECAY_RATE = 1;
    static int x; static int y;static int yspeed;
    //int ySpeed = 3;//in pixels per animation frame
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column
    private int frameWidth; //frame width
    @Column
    private int frameHeight; //frame height
//    @Column
//    private int x;
//    @Column
//    private int y; //pixel grid position
    @Column
    private int dx;
    @Column
    private int dy;
   
    public Bouncer(){
        
    }
    
    public Bouncer(int width, int height){
        this.frameWidth = width;
        this.frameHeight = height;
        x = random.nextInt(width);
        y = random.nextInt(height);
        dx = random.nextInt(2*MAX_SPEED) - MAX_SPEED;
        dy = random.nextInt(2*MAX_SPEED) - MAX_SPEED;
    }
    
    public int getFrameWidth(){
        return frameWidth;
    }
   
    public void setFrameWidth(int frameWidth){
        this.frameWidth = frameWidth;
    }
    
    public int getFrameHeight(){
        return frameHeight;
    }
    
    public void setFrameHeight(int frameHeight){
        this.frameHeight = frameHeight;
    }
    
    public int getX(){
        return x;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public int getYspeed(){
        return yspeed;
    }
    
    public void setYspeed(int yspeed){
        this.yspeed = yspeed;
    }
    
    public int getDx(){
        return dx;
    }
    
    public void setDx(int dx){
        this.dx = dx;
    }
    
    public int getDy(){
        return dy;
    }
    
    public void setDy(int dy){
        this.dy = dy;
    }
    
   
    
//    public void draw(Graphics gra){
//        gra.fillOval(x,y,SIZE,SIZE);
//    }
//    
    /*
    advanceOneFrame
    */
    public void advanceOneFrame(){
        //calling number of times per second
        //hit left wall
       if(x < 0 && dx <0){
           x = 0;
           dx = -dx; //go opposit way!
       }
       // hit top wall
       if(y < 0 && dy < 0) {
           y = 0;
           dy = -dy; //go opposit way!
       }
       // hit right wall
       if(x > FRAME_WIDTH-SIZE && dx >0){
           x = FRAME_WIDTH - SIZE;
           dx = -dx; //go opposit way!
       }
        // hit bottom wall
        if(y > FRAME_WIDTH - SIZE && dy > 0){
            y = FRAME_WIDTH - SIZE;
            dy = -dy; //go opposit way!
        }
        //moving the bouncers
        x += dx;
        y += dy;
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
        return "cst8218.lee00665.bouncer.soomin1.entity.Bouncer[ id=" + id + " ]";
    }
    

    
}
