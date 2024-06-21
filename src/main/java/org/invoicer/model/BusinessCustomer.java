package org.invoicer.model;

import java.math.BigDecimal;

public class BusinessCustomer extends Customer{
    private final String siret;
    private final String socialReason;
    private final BigDecimal sales;
    public BusinessCustomer(String reference, String siret, String socialReason, BigDecimal sales) {
        super(reference);
        this.siret = siret;
        this.socialReason = socialReason;
        this.sales = sales;
    }

    public String getSiret() {
        return siret;
    }

    public String getSocialReason() {
        return socialReason;
    }

    public BigDecimal getSales() {
        return sales;
    }
}
