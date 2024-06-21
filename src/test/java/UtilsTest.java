import org.invoicer.Constants;
import org.invoicer.model.BusinessCustomer;
import org.invoicer.model.Customer;
import org.invoicer.model.IndividualCustomer;
import org.invoicer.utils.RatesUtils;
import org.invoicer.utils.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UtilsTest {

    @Test
    void testIsReferenceValidCase1(){
        // check that isReferenceValid for null case
        boolean result = Validator.isReferenceValid(null);
        Assertions.assertFalse(result);
    }

    @Test
    void testIsReferenceValidCase2(){
        // check that isReferenceValid for nominal case
        boolean result = Validator.isReferenceValid("EKW123456789");
        Assertions.assertTrue(result);
    }

    @Test
    void testIsReferenceValidCase3(){
        // check that isReferenceValid for wrong nominal case
        boolean result = Validator.isReferenceValid("thisIsAReference");
        Assertions.assertFalse(result);
    }

    @Test
    void testElectricityRatesCase1(){
        // check that electricity is right for IndividualCustomer
        Customer customer = mock(IndividualCustomer.class);
        BigDecimal result = RatesUtils.getElectricityRate(customer);
        Assertions.assertEquals(Constants.individualElectricalRate, result);
    }

    @Test
    void testGasRatesCase1(){
        // check that gas is right for IndividualCustomer
        Customer customer = mock(IndividualCustomer.class);
        BigDecimal result = RatesUtils.getGasRate(customer);
        Assertions.assertEquals(Constants.individualGasRate, result);
    }

    @Test
    void testElectricityRatesCase2(){
        // check that electricity is right for a small business
        BusinessCustomer customer = mock(BusinessCustomer.class);
        when(customer.getSales()).thenReturn(new BigDecimal(10));
        BigDecimal result = RatesUtils.getElectricityRate(customer);
        Assertions.assertEquals(Constants.smallBusinessElectricalRate, result);
    }

    @Test
    void testGasRatesCase2(){
        // check that gas is right for a small business
        BusinessCustomer customer = mock(BusinessCustomer.class);
        when(customer.getSales()).thenReturn(new BigDecimal(10));
        BigDecimal result = RatesUtils.getGasRate(customer);
        Assertions.assertEquals(Constants.smallBusinessGasRate, result);
    }

    @Test
    void testElectricityRatesCase3(){
        // check that electricity is right for a big business
        BusinessCustomer customer = mock(BusinessCustomer.class);
        when(customer.getSales()).thenReturn(new BigDecimal(15000000));
        BigDecimal result = RatesUtils.getElectricityRate(customer);
        Assertions.assertEquals(Constants.bigBusinessElectricalRate, result);
    }

    @Test
    void testGasRatesCase3(){
        // check that gas is right for a big business
        BusinessCustomer customer = mock(BusinessCustomer.class);
        when(customer.getSales()).thenReturn(new BigDecimal(15000000));
        BigDecimal result = RatesUtils.getGasRate(customer);
        Assertions.assertEquals(Constants.bigBusinessGasRate, result);
    }

    @Test
    void testElectricityRatesCase4(){
        // check that electricity is right for a business with limit sales
        BusinessCustomer customer = mock(BusinessCustomer.class);
        when(customer.getSales()).thenReturn(Constants.bigBusinessSalesLimit);
        BigDecimal result = RatesUtils.getElectricityRate(customer);
        Assertions.assertEquals(Constants.bigBusinessElectricalRate, result);
    }

    @Test
    void testGasRatesCase4(){
        // check that gas is right for a  business with limit sales
        BusinessCustomer customer = mock(BusinessCustomer.class);
        when(customer.getSales()).thenReturn(Constants.bigBusinessSalesLimit);
        BigDecimal result = RatesUtils.getGasRate(customer);
        Assertions.assertEquals(Constants.bigBusinessGasRate, result);
    }

}
