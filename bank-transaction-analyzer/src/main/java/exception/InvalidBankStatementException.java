package exception;

public class InvalidBankStatementException extends RuntimeException {

  public InvalidBankStatementException(final String message) {
    super(message);
  }
}
