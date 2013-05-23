package utils;



public class DAOException extends Exception {

	
	private static final long serialVersionUID = 1L;
	public static final int PRIMARY_KEY_WAS_VIOLATED = 0;
	public static final int NOT_FOUND_ROW = 1;
	public static final int IMPOSIBLE_CREATE_STATEMENT = 2;
	public static final int IMPOSIBLE_ESTABLISH_CONNECTION = 3;
	public static final int IMPOSIBLE_CLOSE_CONNECTION = 4;
	public static final int IMPOSIBLE_FIND_DRIVER = 5;
	public static final int IMPOSIBLE_MAKE_QUERY = 6;
	public static final int IMPOSIBLE_DO_VECTOR = 7;
	public static final int IMPOSIBLE_CLOSE_STATEMENT =8;
	public static final int IMPOSIBLE_CLOSE_RESULSET =9;
	
	private static final String[] errors = {"La clave primaria fue violada",
												"No se encontro la fila",
												"Imposible crear el estamento",
												"Imposible establecer coneccion",
												"Imposible cerrar la coneccion",
												"Imposible en contrar el Driver",
												"Imposible hacer la consulta",
												"Imposible llenar vector de transferencia",
												"Imposible cerrar la consulta",
												"Imposible cerrar resulset"};
	/**
	 * @uml.property  name="error"
	 */
	private int error = -1;

	/**
	 * @param arg0
	 */
	public DAOException(int error) {
		this.error = error;
	}

	/**
	 * @return  Returns the error.
	 * @uml.property  name="error"
	 */
	public int getError() {
		return error;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return errors[error];
	}
	
}
