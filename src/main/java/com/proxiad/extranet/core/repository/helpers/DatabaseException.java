package com.proxiad.extranet.core.repository.helpers;
/**
 * Generic exception for Database exceptions
 */
public class DatabaseException extends TrombinoscopeException {
	private static final long serialVersionUID = 7363656167240196220L;

	public DatabaseException(java.lang.String message) {
        super(message);
    }

    public DatabaseException(java.lang.Throwable cause) {
        super(cause);
    }

    public DatabaseException(java.lang.String message, java.lang.Throwable cause) {
        super(message, cause);
    }
}
