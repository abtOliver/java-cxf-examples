package com.devsumo.examples.cxf.java.userservice.okay;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 * Simple JAX-RS service class with accepts a PUT of a user's details and writes that object's
 * properties to the console.
 */
@Path("/")
public class UserService {

	@PUT
    @Consumes({"application/json"})
    @Path("/users")
	public boolean storeUserDetails(UserDetails userDetails) {
		System.out.println(userDetails);
		return true;
	}
}
