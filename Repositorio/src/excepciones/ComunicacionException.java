package excepciones;

public class ComunicacionException extends Exception {

	private static final long serialVersionUID = 5565329992155619820L;

	public ComunicacionException(String mensaje){
		super(mensaje);
	}
}
