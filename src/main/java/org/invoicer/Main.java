package org.invoicer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.invoicer.model.BusinessCustomer;
import org.invoicer.model.Customer;
import org.invoicer.model.IndividualCustomer;
import org.invoicer.model.Invoice;
import org.invoicer.utils.Validator;

import java.math.BigDecimal;

import static org.invoicer.Constants.NUMBER_FORMAT;

public class Main {
    public static final Logger logger = LogManager.getLogger(Main.class);

    public static final String INDIVIDUAL_ARGUMENT_MODE = "Individual";

    public static final String BUSINESS_ARGUMENT_MODE = "Business";

    public static void main(String[] args) {

        if(args.length == 0) {
            logger.error("No data inputted. Terminating the invoice calculation operation");
            showExpectedArguments();
        }else if(args.length < 7){
            logger.error("Not enough data inputted. Terminating the invoice calculation operation");
            showExpectedArguments();
        }else{
            logger.info("Arguments detected. Triggering the invoice calculation");

            boolean areArgsValid = validateArguments(args);
            if(!areArgsValid){
                logger.info("The arguments inputted are not valids : ");
                showExpectedArguments();
            }

            else{
                Customer customer = getCustomer(args);
                if(customer == null){
                    logger.error("An error occurred while fetching the customer info. Terminating the invoice calculation operation");
                    showExpectedArguments();
                }else{
                    Invoice invoice = new Invoice(customer, new BigDecimal(args[5]), new BigDecimal(args[6]));
                    invoice.printInvoice(logger);
                    logger.info("Invoice was calculated successfully");
                }
            }

        }
    }

    private static Customer getCustomer(String[] args) {
        if (INDIVIDUAL_ARGUMENT_MODE.equalsIgnoreCase(args[0])) {
            return new IndividualCustomer(args[1], args[2], args[3], args[4]);
        } else if (BUSINESS_ARGUMENT_MODE.equalsIgnoreCase(args[0])) {
            return new BusinessCustomer(args[1], args[2], args[3], new BigDecimal(args[4]));
        }
        else{
            showExpectedArguments();
            return null;
        }
    }


    public static void showExpectedArguments(){
        logger.warn("The following arguments are expected : ");
        logger.warn("1. The type of customer : 'Individual' or 'Business'");
        logger.warn("2. The reference customer matching the expected format EKW + 9 digits (String)");
        logger.warn("3. If the customer is an individual, its name (String). If the customer is a business, its SIRET number (String)");
        logger.warn("4. If the customer is an individual, its surname (String). If the customer is a business, its social reason (String)");
        logger.warn("5. If the customer is an individual, its gender (String). If the customer is a business, its sales (Numerical)");
        logger.warn("6. The customer gas consumption (Numerical), input 0 if no gas consumption");
        logger.warn("7. The customer electrical consumption (Numerical), input 0 if no electrical consumption");
    }

    public static boolean validateArguments(String[] args){
        boolean arg1Validation = args[0] != null && (INDIVIDUAL_ARGUMENT_MODE.equals(args[0]) || BUSINESS_ARGUMENT_MODE.equals(args[0]));
        boolean arg2Validation = args[1] != null && Validator.isReferenceValid(args[1]);
        boolean arg3Validation = args[2] != null ;
        boolean arg4Validation = args[3] != null ;
        boolean arg5Validation = args[4] != null && (INDIVIDUAL_ARGUMENT_MODE.equals(args[0]) || (BUSINESS_ARGUMENT_MODE.equals(args[0]) && args[4].matches(NUMBER_FORMAT)));
        boolean arg6Validation = args[5] != null && args[5].matches(NUMBER_FORMAT);
        boolean arg7Validation = args[6] != null && args[6].matches(NUMBER_FORMAT);
        return arg1Validation && arg2Validation && arg3Validation && arg4Validation && arg5Validation && arg6Validation && arg7Validation;
    }

}