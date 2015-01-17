package com.devsumo.examples.cxf.java.mortgagecalculator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/")
public interface MortgageCalculator {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/payments")
	Float calculatePayment(MortgageDetails dtls);
}
