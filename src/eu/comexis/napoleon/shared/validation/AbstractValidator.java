package eu.comexis.napoleon.shared.validation;

import java.math.BigDecimal;

import com.google.gwt.query.client.js.JsRegexp;



/**
 * Common class gathering all useful functions for validations
 * 
 * @author jDramaix
 * 
 * @param <T>
 */
public abstract class AbstractValidator<T> implements Validator<T> {
  
  private JsRegexp EMAIL_REGEX = new JsRegexp("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");

  /**
   * Test is a {@link String} is empty. A string is considered as empty if it is null or its length is equal
   * to 0
   * 
   * @param s
   * @return
   */
  protected boolean isEmpty(String s) {
    return s == null || s.length() == 0;
  }

  /**
   * Test if a {@link BigDecimal} is smaller than an integer upper bound
   * @param toCompare
   * @param upperBound the upper bound 
   * @return
   */
  protected boolean isLessThan(BigDecimal toCompare, int upperBound) {
    return isLessThan(toCompare, new BigDecimal(upperBound));
  }

  /**
   * Test if a {@link BigDecimal} is smaller than an float upper bound
   * @param toCompare
   * @param upperBound the upper bound 
   * @return
   */
  protected boolean isLessThan(BigDecimal toCompare, float upperBound) {
    return isLessThan(toCompare, new BigDecimal(upperBound));
  }

  /**
   * Test if a {@link BigDecimal} is smaller than an long upper bound
   * @param toCompare
   * @param upperBound the upper bound 
   * @return
   */
  protected boolean isLessThan(BigDecimal toCompare, long upperBound) {
    return isLessThan(toCompare, new BigDecimal(upperBound));
  }

  /**
   * Test if a {@link BigDecimal} is smaller than an other {@link BigDecimal}
   * @param toCompare
   * @param upperBound the upper bound 
   * @return
   */
  protected boolean isLessThan(BigDecimal toCompare, BigDecimal upperBound) {
    return toCompare.compareTo(upperBound) < 0;
  }

  /**
   * Test if a {@link BigDecimal} is smaller or equal to an integer upper bound
   * @param toCompare
   * @param upperBound the upper bound 
   * @return
   */
  protected boolean isLessOrEqualThan(BigDecimal toCompare, int upperBound) {
    return isLessOrEqualThan(toCompare, new BigDecimal(upperBound));
  }

  /**
   * Test if a {@link BigDecimal} is smaller or equal to an float upper bound
   * @param toCompare
   * @param upperBound the upper bound 
   * @return
   */
  protected boolean isLessOrEqualThan(BigDecimal toCompare, float upperBound) {
    return isLessOrEqualThan(toCompare, new BigDecimal(upperBound));
  }

  /**
   * Test if a {@link BigDecimal} is smaller or equal to an long upper bound
   * @param toCompare
   * @param upperBound the upper bound 
   * @return
   */
  protected boolean isLessOrEqualThan(BigDecimal toCompare, long upperBound) {
    return isLessOrEqualThan(toCompare, new BigDecimal(upperBound));
  }

  /**
   * Test if a {@link BigDecimal} is smaller or equal to another {@link BigDecimal}
   * @param toCompare
   * @param upperBound the upper bound 
   * @return
   */
  protected boolean isLessOrEqualThan(BigDecimal toCompare, BigDecimal upperBound) {
    return toCompare.compareTo(upperBound) <= 0;
  }

  /**
   * Test if a {@link BigDecimal} is greater to an integer lower bound
   * @param toCompare
   * @param lowerBound the lower bound 
   * @return
   */
  protected boolean isGreaterThan(BigDecimal toCompare, int lowerBound) {
    return isGreaterThan(toCompare, new BigDecimal(lowerBound));
  }

  /**
   * Test if a {@link BigDecimal} is greater to an float lower bound
   * @param toCompare
   * @param lowerBound the lower bound 
   * @return
   */
  protected boolean isGreaterThan(BigDecimal toCompare, float lowerBound) {
    return isGreaterThan(toCompare, new BigDecimal(lowerBound));
  }

  /**
   * Test if a {@link BigDecimal} is greater to a long lower bound
   * @param toCompare
   * @param lowerBound the lower bound 
   * @return
   */
  protected boolean isGreaterThan(BigDecimal toCompare, long lowerBound) {
    return isGreaterThan(toCompare, new BigDecimal(lowerBound));
  }

  /**
   * Test if a {@link BigDecimal} is greater to another {@link BigDecimal}
   * @param toCompare
   * @param lowerBound the lower bound 
   * @return
   */
  protected boolean isGreaterThan(BigDecimal toCompare, BigDecimal lowerBound) {
    return toCompare.compareTo(lowerBound) > 0;
  }

  /**
   * Test if a {@link BigDecimal} is greater or equal to an integer lower bound
   * @param toCompare
   * @param lowerBound the lower bound 
   * @return
   */
  protected boolean isGreaterOrEqualThan(BigDecimal toCompare, int lowerBound) {
    return isGreaterOrEqualThan(toCompare, new BigDecimal(lowerBound));
  }

  /**
   * Test if a {@link BigDecimal} is greater or equal to a float lower bound
   * @param toCompare
   * @param upperBound the lower bound 
   * @return
   */
  protected boolean isGreaterOrEqualThan(BigDecimal toCompare, float lowerBound) {
    return isGreaterOrEqualThan(toCompare, new BigDecimal(lowerBound));
  }

  /**
   * Test if a {@link BigDecimal} is greater or equal to a long lower bound
   * @param toCompare
   * @param upperBound the lower bound 
   * @return
   */
  protected boolean isGreaterOrEqualThan(BigDecimal toCompare, long lowerBound) {
    return isGreaterOrEqualThan(toCompare, new BigDecimal(lowerBound));
  }

  /**
   * Test if a {@link BigDecimal} is greater or equal to another {@link BigDecimal}
   * @param toCompare
   * @param upperBound the lower bound 
   * @return
   */
  protected boolean isGreaterOrEqualThan(BigDecimal toCompare, BigDecimal lowerBound) {
    return toCompare.compareTo(lowerBound) >= 0;
  }
  
  protected boolean isValidEmail(String email) {
    if (email == null || email.length() == 0){
      //cannot valid a empty string
      return true;
    }
    return EMAIL_REGEX.test(email);
  }

}
