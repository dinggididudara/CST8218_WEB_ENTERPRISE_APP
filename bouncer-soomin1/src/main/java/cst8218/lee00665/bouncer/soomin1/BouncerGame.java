/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package cst8218.lee00665.bouncer.soomin1;

import cst8218.lee00665.bouncer.soomin1.entity.Bouncer;
import cst8218.lee00665.bouncer.soomin1.presentation.BouncerFacade;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author lee00665
 */
@Startup
@Singleton
public class BouncerGame {
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public static final int CHANGE_RATE = 1;
    public static final int HEIGHT = 500;
    public static final int WIDTH = 500;
   
    
    List<Bouncer> bouncers;
    
    @EJB
    private BouncerFacade bouncerFacade;
    

    public List<Bouncer> getBouncerList(){
        return bouncers;
    }
    
    public void newBouncer(MouseEvent event, Color color){
        Bouncer newBouncer = new Bouncer(HEIGHT, WIDTH);
        bouncerFacade.create(newBouncer);
        System.out.println("New Bouncer just Created!");
    }
    
    @PostConstruct
    public void go(){
        new Thread(new Runnable(){
            @Override
            public void run(){
                //the game runs indefinitely
                while(true){
                    //update all the bouncers and save changes to the database
                    bouncers = bouncerFacade.findAll();
                    for(Bouncer bouncer : bouncers){
                        bouncer.advanceOneFrame();
                        bouncerFacade.edit(bouncer);
                    }
                    //sleep while waitinh to process the next frame of the animation
                    try{
                        //wake up roughly CHANGE_RATE times per second
                        Thread.sleep((long) (1.0/CHANGE_RATE*1000));
                    
                    } catch(InterruptedException exception){
                        exception.printStackTrace();
                    }
                }
                
            }
        
        }).start();
    }
}
