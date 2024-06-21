package org.invoicer.utils;

import org.invoicer.Constants;

public class Validator {

    public static boolean isReferenceValid(String reference){
        if(reference != null){
            return reference.matches(Constants.REFERENCE_FORMAT);
        }
        return false;
    }

}
