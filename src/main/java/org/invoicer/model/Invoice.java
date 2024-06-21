package org.invoicer.model;

import org.apache.logging.log4j.Logger;
import org.invoicer.utils.RatesUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Invoice {

    private final Customer customer;
    private final BigDecimal electricityRate;
    private final BigDecimal gasRate;
    private final BigDecimal electricityPrice;
    private final BigDecimal gasPrice;
    private final BigDecimal electricityConsumption;
    private final BigDecimal gasConsumption;
    private final BigDecimal totalPrice;

    public Invoice(Customer client, BigDecimal electricityConsumption, BigDecimal gasConsumption) {
        this.customer = client;
        this.electricityConsumption = electricityConsumption;
        this.gasConsumption = gasConsumption;
        this.electricityRate = RatesUtils.getElectricityRate(this.customer);
        this.gasRate = RatesUtils.getGasRate(this.customer);
        this.electricityPrice = this.electricityRate.multiply(electricityConsumption).setScale(3, RoundingMode.UP);
        this.gasPrice = this.gasRate.multiply(gasConsumption).setScale(3, RoundingMode.UP);
        this.totalPrice = this.electricityPrice.add(this.gasPrice).setScale(3, RoundingMode.UP);
    }

    public void printInvoice(Logger logger){
        logger.info("Invoice for customer {}", this.customer.getReference());
        logger.info("Electricity rates : {}EUR/kwh", this.electricityRate);
        logger.info("Electricity consumption : {}kwh", this.electricityConsumption);
        logger.info("_ Subtotal for electricity bill : {} EUR", electricityPrice);
        logger.info("Gas rates : {}EUR/kwh", this.gasRate);
        logger.info("Gas consumption : {}kwh", this.gasConsumption);
        logger.info("_ Subtotal for gas bill : {} EUR", gasPrice);
        logger.info("TOTAL : {}EUR", totalPrice);
    };
}
