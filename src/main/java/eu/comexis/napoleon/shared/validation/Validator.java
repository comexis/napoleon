package eu.comexis.napoleon.shared.validation;

import java.util.List;

/**
 * General contract for validation
 * 
 * @author jDramaix
 *
 * @param <T>
 */
public interface Validator<T> {

  public List<ValidationMessage> validate(T toValidate);
}
