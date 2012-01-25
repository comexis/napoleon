package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.Iterator;

import com.googlecode.objectify.Key;

import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class OwnerDao extends NapoleonDao<Owner>{
  
  public Owner create(){
    Owner owner = new Owner();
    return owner;
  }
  @Override
  public Owner update(Owner owner){
    try{
      // if this is the first time the owner is stored in the db, check that there is not a owner with exactly the same names (first + last)
      // should raise a NapoleonDaoUpdateFailed exception with text explaining the duplicate names problem
      Key<Owner> ownerKey = ofy().put(owner);
      return ofy().get(ownerKey);
    }catch (Exception e){
      // should raise a NapoleonDaoUpdateFailed exception
      e.printStackTrace();
      return null;
    }
  }
  /**
   * returns the owner (full) corresponding to the given last name
   * 
   * @param name: the owner's last name
   * @return
   */
  public Owner findByName(String name){
    try{
      Owner owner = ofy().query(Owner.class).filter("lastName", name).get();
      return owner;
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }
  /**
   * Retrieves the list of all owners stored in the database (with the minimum of data to be shown in UI)
   * 
   * @return The list of owners
   */
  public ArrayList<SimpleOwner> getListSimpleOwners(){
    Iterator<Owner> iterator =  this.listAll().iterator();
    ArrayList<SimpleOwner> owners = new ArrayList<SimpleOwner>();
    while (iterator.hasNext()) {
      Owner owner = iterator.next();
      SimpleOwner o = new SimpleOwner();
      o.setId(owner.getId().toString());
      o.setName(owner.getLastName());
      o.setPostalCode(owner.getPostalCode());
      o.setCity(owner.getCity());
      o.setAddress(owner.getStreet());
      o.setMobile(owner.getMobilePhoneNumber());
      owners.add(o);
    }
    return owners;
  }
}
