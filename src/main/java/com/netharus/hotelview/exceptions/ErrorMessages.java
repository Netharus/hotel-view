package com.netharus.hotelview.exceptions;

public final class ErrorMessages {
    private ErrorMessages() {
    }

    public static final String HOTEL_NOT_FOUND = "Hotel with id: %d, not found";

    public static final String ILLEGAL_PARAM = "Unsupported histogram parameter: ";

    public static final String ILLEGAL_HISTOGRAM_PARAM_VALIDATOR = "Invalid histogram parameter: '%s'. "
            + "Allowed values: %s";
}
