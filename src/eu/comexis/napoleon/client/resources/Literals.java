package eu.comexis.napoleon.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.ConstantsWithLookup;

import eu.comexis.napoleon.shared.model.Title;

public interface Literals extends ConstantsWithLookup {

  public Literals INSTANCE = GWT.create(Literals.class);

  @DefaultStringValue("Chargement des données...")
  public String dataLoading();

  // MaritalStatus enum
  @DefaultStringValue("Célibataire")
  public String MaritalStatus_SINGLE();

  @DefaultStringValue("Marié")
  public String MaritalStatus_MARRIED();

  @DefaultStringValue("Cohabitant")
  public String MaritalStatus_COHABITATION();

  // MatrimonialRegime enum
  @DefaultStringValue("Aucun")
  public String MatrimonialRegime_NONE();

  @DefaultStringValue("Communauté des biens")
  public String MatrimonialRegime_COMMUNITY();

  @DefaultStringValue("Séparation des biens")
  public String MatrimonialRegime_SEPARATION();
  
  
  @DefaultStringValue("Monsieur")
  public String Title_MR();
  
  @DefaultStringValue("Madame")
  public String Title_MRS();
  
  @DefaultStringValue("Mademoiselle")
  public String Title_MISS();
  
  @DefaultStringValue("M")
  public String Title_MR_short();
  
  @DefaultStringValue("Mme")
  public String Title_MRS_short();
  
  @DefaultStringValue("Mlle")
  public String Title_MISS_short();
  
  @DefaultStringValue("€")
  public String FeeUnit_LUMP_SUM();
  
  @DefaultStringValue("% du loyer")
  public String FeeUnit_RENT_PERCENTAGE();


}