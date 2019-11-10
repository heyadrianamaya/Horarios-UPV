package upv.poo.basededatos;

import java.sql.SQLDataException;

public class NotExecuteQuerySQLException extends SQLDataException {

    /**
     * Constructs a <code>SQLDataException</code> object with a given
     * <code>reason</code>.
     * The <code>SQLState</code> is initialized
     * to <code>null</code> and the vendor code is initialized to 0.
     * <p>
     * The <code>cause</code> is not initialized, and may subsequently be
     * initialized by a call to
     * {@link Throwable#initCause(Throwable)} method.
     *
     * @param reason a description of the exception
     * @since 1.6
     */
    public NotExecuteQuerySQLException(String reason) {
        super(reason);
    }

    /**
     * Constructs a <code>SQLDataException</code> object with a given
     * <code>reason</code> and <code>SQLState</code>. The
     * vendor code is initialized to 0.
     * <p>
     * The <code>cause</code> is not initialized, and may subsequently be
     * initialized by a call to
     * {@link Throwable#initCause(Throwable)} method.
     *
     * @param reason   a description of the exception
     * @param SQLState an XOPEN or SQL:2003 code identifying the exception
     * @since 1.6
     */
    public NotExecuteQuerySQLException(String reason, String SQLState) {
        super(reason, SQLState);
    }
}
