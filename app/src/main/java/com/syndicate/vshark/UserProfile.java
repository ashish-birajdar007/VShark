package com.syndicate.vshark;

import androidx.annotation.NonNull;

public class UserProfile {
    private String email = "NA";
    private String companyName = "NA";
    private String productDetails = "NA";
    private String ask = "NA";
    private String equity = "NA";
    private String companyValuation = "NA";
    private String lastYearSales = "NA";
    private String priorInvestorName = "NA";
    private String priorInvestorInvestment = "NA";
    private String priorInvestorStake = "NA";
    private String netProfit = "NA";

    public UserProfile() {

    }

    public UserProfile(String Email) {
        this.email = Email;
    }

    public UserProfile(String companyName, String productDetails, String ask, String equity, String companyValuation, String lastYearSales, String priorInvestorName, String priorInvestorInvestment, String priorInvestorStake, String netProfit) {
        this.companyName = companyName;
        this.productDetails = productDetails;
        this.ask = ask;
        this.equity = equity;
        this.companyValuation = companyValuation;
        this.lastYearSales = lastYearSales;
        this.priorInvestorName = priorInvestorName;
        this.priorInvestorInvestment = priorInvestorInvestment;
        this.priorInvestorStake = priorInvestorStake;
        this.netProfit = netProfit;
    }

    public UserProfile(String email, String companyName, String productDetails, String ask, String equity, String companyValuation, String lastYearSales, String priorInvestorName, String priorInvestorInvestment, String priorInvestorStake, String netProfit) {
        this.email = email;
        this.companyName = companyName;
        this.productDetails = productDetails;
        this.ask = ask;
        this.equity = equity;
        this.companyValuation = companyValuation;
        this.lastYearSales = lastYearSales;
        this.priorInvestorName = priorInvestorName;
        this.priorInvestorInvestment = priorInvestorInvestment;
        this.priorInvestorStake = priorInvestorStake;
        this.netProfit = netProfit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getEquity() {
        return equity;
    }

    public void setEquity(String equity) {
        this.equity = equity;
    }

    public String getCompanyValuation() {
        return companyValuation;
    }

    public void setCompanyValuation(String companyValuation) {
        this.companyValuation = companyValuation;
    }

    public String getLastYearSales() {
        return lastYearSales;
    }

    public void setLastYearSales(String lastYearSales) {
        this.lastYearSales = lastYearSales;
    }

    public String getPriorInvestorName() {
        return priorInvestorName;
    }

    public void setPriorInvestorName(String priorInvestorName) {
        this.priorInvestorName = priorInvestorName;
    }

    public String getPriorInvestorInvestment() {
        return priorInvestorInvestment;
    }

    public void setPriorInvestorInvestment(String priorInvestorInvestment) {
        this.priorInvestorInvestment = priorInvestorInvestment;
    }

    public String getPriorInvestorStake() {
        return priorInvestorStake;
    }

    public void setPriorInvestorStake(String priorInvestorStake) {
        this.priorInvestorStake = priorInvestorStake;
    }

    public String getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(String netProfit) {
        this.netProfit = netProfit;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "Company Name : " + companyName + '\n' +
                " Product Details : " + productDetails + '\n' +
                " Ask : " + ask + '\n' +
                " Equity : " + equity + '\n' +
                " Company Valuation : " + companyValuation + '\n' +
                " Last Year Sales : " + lastYearSales + '\n' +
                " Prior Investor Name : " + priorInvestorName + '\n' +
                " Prior Investor Investment : " + priorInvestorInvestment + '\n' +
                " Prior Investor Stake : " + priorInvestorStake + '\n' +
                " Net Profit : " + netProfit;
    }
}
