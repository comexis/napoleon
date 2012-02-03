package eu.comexis.napoleon.shared.command.estate;

import java.util.ArrayList;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;

public class GetAllRealEstateResponse implements Response {
	
	private ArrayList<SimpleRealEstate> realEstates;
	
	public GetAllRealEstateResponse() {}
	
	public ArrayList<SimpleRealEstate> getRealEstates() {
		return realEstates;
	}
	
	public void setRealEstates(ArrayList<SimpleRealEstate> realEstates) {
		this.realEstates = realEstates;
	}

}
