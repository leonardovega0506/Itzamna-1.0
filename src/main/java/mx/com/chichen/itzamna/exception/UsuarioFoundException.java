package mx.com.chichen.itzamna.exception;

public class UsuarioFoundException extends Exception{
    public UsuarioFoundException(){
        super("EL USUARIO CON ESE USERNAME YA EXISTE");
    }

    public UsuarioFoundException(String mensaje){
        super(mensaje);
    }
}
