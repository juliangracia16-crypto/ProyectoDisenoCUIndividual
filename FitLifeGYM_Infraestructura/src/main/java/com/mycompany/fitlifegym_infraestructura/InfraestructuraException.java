
package com.mycompany.fitlifegym_infraestructura;

/**
 * Excepcion personalizada para manejar los errores
 * que puedan ocurrir al generar un reporte pdf
 * @author Julian
 */
public class InfraestructuraException extends Exception{

    public InfraestructuraException() {
    }

    public InfraestructuraException(String message) {
        super(message);
    }

    public InfraestructuraException(String message, Throwable cause) {
        super(message, cause);
    }

    public InfraestructuraException(Throwable cause) {
        super(cause);
    }

    public InfraestructuraException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
