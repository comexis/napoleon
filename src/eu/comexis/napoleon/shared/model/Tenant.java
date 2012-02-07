package eu.comexis.napoleon.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.Unindexed;

/**
 * Represent the Tenant (Proprio) of a RealEstate (Bien immo) (It's considered to be a single
 * person)
 * 
 * @author xavier
 * 
 */
@Unindexed
public class Tenant extends Party implements IsSerializable {
}
