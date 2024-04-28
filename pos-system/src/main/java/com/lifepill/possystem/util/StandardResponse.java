package com.lifepill.possystem.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A standard response object containing a code, message, and data.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardResponse {
    /** The status code of the response. */
    private int code;
    /** The message associated with the response. */
    private String message;
    /** The data associated with the response. */
    private Object data;
}
