package org.invoicer.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.invoicer.utils.Validator.isReferenceValid;

public abstract class Customer {

    public static final Logger logger = LogManager.getLogger();
    private final String reference;

    public Customer(String reference) {
        if(isReferenceValid(reference)){
            this.reference = reference;
        }else{
            logger.error("{} is not a valid customer reference. Setting it to null", reference);
            this.reference = null;
        }
    }

    public String getReference() {
        return reference;
    }
}
