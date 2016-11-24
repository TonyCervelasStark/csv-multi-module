package com.sopra.test;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/test")
public class TestWS {
	@EJB
	TestDao dao;

	@GET
	public String test() {
		return "hello " + dao.test();
	}
}
