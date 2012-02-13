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

  // RealEstateState Enum
  @DefaultStringValue("Excellent")
  public String RealEstateState_EXCELLENT();

  @DefaultStringValue("Bon")
  public String RealEstateState_GOOD();

  @DefaultStringValue("Neuf")
  public String RealEstateState_NEW();

  @DefaultStringValue("A rafraichir")
  public String RealEstateState_REFRESH();

  @DefaultStringValue("A rénover")
  public String RealEstateState_RENEW();

  @DefaultStringValue("Renové")
  public String RealEstateState_RENEWED();

  // TypeOfRealEstate Enum
  @DefaultStringValue("Appartement 1 ch.")
  public String TypeOfRealEstate_A1();

  @DefaultStringValue("Appartement 2 ch.")
  public String TypeOfRealEstate_A2();

  @DefaultStringValue("Appartement 3 ch.")
  public String TypeOfRealEstate_A3();

  @DefaultStringValue("Atelier")
  public String TypeOfRealEstate_ATELIER();

  @DefaultStringValue("Commerce")
  public String TypeOfRealEstate_COMMERCE();

  @DefaultStringValue("Flat")
  public String TypeOfRealEstate_FLAT();

  @DefaultStringValue("Kot")
  public String TypeOfRealEstate_KOT();

  @DefaultStringValue("Bureaux")
  public String TypeOfRealEstate_OFFICE();

  @DefaultStringValue("Villa")
  public String TypeOfRealEstate_VILLA();
}