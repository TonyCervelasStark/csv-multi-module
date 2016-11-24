package com.sopra.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sopra.modele.Employee;


@Path("employee")
public class FormationWs {
	
	FService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getAll() {
		return service.findAll();
	}
	
	@GET
	@Path( "{id}" )
	@Produces( MediaType.APPLICATION_JSON )
	public Employee getOne( @PathParam( "id" ) int id )
	{
		return service.findEmployeeById(id);
	}
	
	@PUT
	@Consumes( MediaType.APPLICATION_JSON )
	@Produces( MediaType.APPLICATION_JSON )
	public Employee create( Employee employee )
	{
		return service.createOrUpdate( employee );
	}

	@POST
	@Consumes( MediaType.APPLICATION_JSON )
	@Produces( MediaType.APPLICATION_JSON )
	public Employee update( Employee employee )
	{
		return service.createOrUpdate( employee );
	}
	
	@DELETE
	@Path( "{id}" )
	public void delete( @PathParam( "id" ) int id )
	{
		service.delete( id );
	}
	
	@Path( "search" )
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public List<Employee> search( @QueryParam( "name" ) String name, @QueryParam( "firstname" ) String firstname )
	{
		return service.search( name, firstname );
	}
	
}
