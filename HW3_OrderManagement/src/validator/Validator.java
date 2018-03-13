package validator;

/**
 * interface with isValid method used to validate user input
 * @author Istvan
 *
 * @param <T>
 */
public interface Validator<T> {
	public boolean isValid(T t);
}
