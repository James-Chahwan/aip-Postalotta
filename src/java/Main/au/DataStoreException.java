package Main.au;

/**
 * Thrown by the Account Data Access Object if a problem occurs in communicating
 * with the underlying persistence mechanism (i.e., an SQL exception or a JNDI 
 * naming exception).
 * from the labs
 * HELPS ALOT 
 */
public class DataStoreException extends Exception {
    
    public DataStoreException(String message) {
        super(message);
    }
    
    public DataStoreException(Throwable cause) {
        super(cause);
    }
    
    public DataStoreException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
