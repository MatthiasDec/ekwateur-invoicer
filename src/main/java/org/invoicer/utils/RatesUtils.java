package org.invoicer.utils;

import org.invoicer.Constants;
import org.invoicer.model.BusinessCustomer;
import org.invoicer.model.Customer;
import org.invoicer.model.IndividualCustomer;

import java.math.BigDecimal;

public class RatesUtils {

    public static BigDecimal getElectricityRate(Customer customer) {
        return customer instanceof IndividualCustomer ? getElectricityRate((IndividualCustomer) customer) : getElectricityRate((BusinessCustomer) customer);
    }

    public static BigDecimal getElectricityRate(IndividualCustomer customer) {
        return Constants.individualElectricalRate;
    }

    public static BigDecimal getElectricityRate(BusinessCustomer customer) {
        return Constants.bigBusinessSalesLimit.compareTo(customer.getSales()) > 0 ? Constants.smallBusinessElectricalRate: Constants.bigBusinessElectricalRate;
    }

    public static BigDecimal getGasRate(Customer customer) {
        return customer instanceof IndividualCustomer ? getGasRate((IndividualCustomer) customer) : getGasRate((BusinessCustomer) customer);
    }

    public static BigDecimal getGasRate(IndividualCustomer customer) {
        return Constants.individualGasRate;
    }

    public static BigDecimal getGasRate(BusinessCustomer customer) {
        return Constants.bigBusinessSalesLimit.compareTo(customer.getSales()) > 0 ? Constants.smallBusinessGasRate: Constants.bigBusinessGasRate;
    }
}
