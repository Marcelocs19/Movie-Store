package com.moviestore.error;

public class ServiceException extends RuntimeException {

		private static final long serialVersionUID = 4244881451243023367L;

		public ServiceException(String message) {
			super(message);
		}

		public ServiceException(String message, Throwable cause) {
			super(message, cause);
		}
			
}
