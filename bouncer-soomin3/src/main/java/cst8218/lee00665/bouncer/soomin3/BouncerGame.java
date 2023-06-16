/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package cst8218.lee00665.bouncer.soomin3;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * BouncerGame.java
 * 
 * This is the main class for Bouncer game application
 * It handles the game initialization and execution
 * 
 * @author lee00665
 */
@Startup
@Singleton
public class BouncerGame {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     List<Bouncer> bouncers;
    @EJB
    private BouncerFacade bouncerFacade;
    public List<Bouncer> getBouncerList(){
        return bouncers;
    }
    @PostConstruct
    public void go(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    bouncers = bouncerFacade.findAll();
                    for(Bouncer bouncer : bouncers){
                        bouncer.advanceOneFrame();
                        bouncerFacade.edit(bouncer);
                    }
                    try{
                        Thread.sleep(100);
                    } catch(InterruptedException exception){
                        exception.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
