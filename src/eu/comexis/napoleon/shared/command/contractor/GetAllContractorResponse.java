
package eu.comexis.napoleon.shared.command.contractor;
import eu.comexis.napoleon.shared.command.Response;
import java.util.List;
import eu.comexis.napoleon.shared.model.Contractor;

public class GetAllContractorResponse implements Response{
  private List<Contractor> listContractor;
public void setListContractor(List<Contractor> listContractor){
	this.listContractor = listContractor;
}
public List<Contractor> getListContractor(){
	return this.listContractor;
}
	public GetAllContractorResponse (List<Contractor> listContractor){
this.listContractor=listContractor;
}

  
  public GetAllContractorResponse() {
    // TODO Auto-generated constructor stub
  }
}
