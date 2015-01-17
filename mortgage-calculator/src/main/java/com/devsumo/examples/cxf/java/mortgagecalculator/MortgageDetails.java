package com.devsumo.examples.cxf.java.mortgagecalculator;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace="http://devsumo.com/mortgagecalculator")
public class MortgageDetails {
	
	private Float interestRate;
	private Integer totalAmount;
	private Integer depositAmount;
	private Integer termInYears;

	public MortgageDetails() {
	}

	public Float getInterestRate() {
		return interestRate;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public Integer getDepositAmount() {
		return depositAmount;
	}

	public Integer getTermInYears() {
		return termInYears;
	}

	public void setInterestRate(Float interestRate) {
		this.interestRate = interestRate;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setDepositAmount(Integer depositAmount) {
		this.depositAmount = depositAmount;
	}

	public void setTermInYears(Integer termInYears) {
		this.termInYears = termInYears;
	}

}
