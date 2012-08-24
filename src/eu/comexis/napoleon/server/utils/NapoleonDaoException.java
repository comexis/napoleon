package eu.comexis.napoleon.server.utils;

@SuppressWarnings("serial")
public class NapoleonDaoException extends Exception{
  String errMsg;
  public NapoleonDaoException(){
    super();
    errMsg = "Oups, problème";
  }
  public NapoleonDaoException(String errMsg){
    super(errMsg);
    this.errMsg=errMsg;
  }
  public String getError()
  {
    return errMsg;
  }
}
