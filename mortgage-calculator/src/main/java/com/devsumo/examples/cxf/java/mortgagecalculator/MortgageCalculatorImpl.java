package com.devsumo.examples.cxf.java.mortgagecalculator;

public class MortgageCalculatorImpl implements MortgageCalculator {
	
	private static final Float ANSWER_TO_EVERYTHING = Float.valueOf(42f);

	@Override
	public Float calculatePayment(MortgageDetails dtls) {
		return ANSWER_TO_EVERYTHING;
	}
}
