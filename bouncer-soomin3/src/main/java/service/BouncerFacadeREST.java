/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import cst8218.lee00665.bouncer.soomin3.Bouncer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * BouncerFacadeREST.java
 * 
 * This is RESTful web service class for Bouncer entity
 * It provides endpoints for CRUD operations on bouncers using REST architecture
 * 
 * @author lee00665
 */
@Stateless
@Path("cst8218.lee00665.bouncer.soomin3.bouncer")
public class BouncerFacadeREST extends AbstractFacade<Bouncer> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public BouncerFacadeREST() {
        super(Bouncer.class);
    }

    @POST
    @Path("create/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(@PathParam("id") Long id, Bouncer entity) {
        if(entity.getId() == null){
            //Initialize non-id null values
            entity.setX(0);
            entity.setY(100);
            entity.setYspeed(2);
            
            super.create(entity);
            
            return Response.status(Response.Status.CREATED).build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    @POST
    @Path("update/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") Long id, Bouncer entity){
        Bouncer isExist = super.find(id);
        if(isExist == null){ //if not found
            return Response.status(Response.Status.NOT_FOUND).build();
        } else if (!isExist.getId().equals(entity.getId())){ //if non matching non null id
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            isExist.updateBouncer(entity); //update the existing bouncer with new values
            return Response.status(Response.Status.OK).build();
        }
    }
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response replace(@PathParam("id") Long id, Bouncer entity){
        Bouncer isExist = super.find(id);
        if(isExist == null){ //if not found
            return Response.status(Response.Status.NOT_FOUND).build();
        } else if (!isExist.getId().equals(entity.getId())){ //if non matching non null id
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            isExist.replace(entity); //replace the existing bouncer with new values
            return Response.status(Response.Status.OK).build();
        }
    }
//    
//    @PUT
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Response edit(@PathParam("id") Long id, Bouncer entity) {
//         if(entity.getId() == null){
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        }else {
//            super.create(entity);
//            return Response.status(Response.Status.CREATED).build();
//        }
//    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Bouncer find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bouncer> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bouncer> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
