package eu.comexis.napoleon.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.ConstantsWithLookup;

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
  
  // MR, MRS, MISS,MRS_MR,MR_MRS,PHYS,MORALEPRIV,MORALEPUB,GESTION,PUB,PRIV,IE,COM,SCI,SCRI,SC,SPRLU, SPRL,SCRL,SA,ASBL;

  @DefaultStringValue("Monsieur")
  public String Title_MR();

  @DefaultStringValue("Madame")
  public String Title_MRS();

  @DefaultStringValue("Mademoiselle")
  public String Title_MISS();
  
  @DefaultStringValue("Monsieur et Madame")
  public String Title_MR_MRS();
  
  @DefaultStringValue("Madame et Monsieur")
  public String Title_MRS_MR();
  
  @DefaultStringValue("Personne physique")
  public String Title_PHYS();
  
  @DefaultStringValue("Personne morale de droit privé")
  public String Title_MORALEPRIV();
  
  @DefaultStringValue("Personne morale de droit public")
  public String Title_MORALEPUB();
  
  @DefaultStringValue("Gestionnaire")
  public String Title_GESTION();
  
  @DefaultStringValue("Société publique")
  public String Title_PUB();
  
  @DefaultStringValue("Société privée")
  public String Title_PRIV();
  
  @DefaultStringValue("Institution européenne")
  public String Title_IE();
  
  @DefaultStringValue("Commune")
  public String Title_COM();
  
  @DefaultStringValue("S.C.I.")
  public String Title_SCI();
  
  @DefaultStringValue("S.C.R.I.")
  public String Title_SCRI();
  
  @DefaultStringValue("S.A.")
  public String Title_SA();
  
  @DefaultStringValue("S.C.")
  public String Title_SC();
  
  @DefaultStringValue("S.P.R.L.")
  public String Title_SPRL();
  
  @DefaultStringValue("S.P.R.L.U.")
  public String Title_SPRLU();
  
  @DefaultStringValue("S.C.R.L.")
  public String Title_SCRL();
  
  @DefaultStringValue("A.S.B.L.")
  public String Title_ASBL();

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
  
  @DefaultStringValue("-")
  public String RealEstateState_NONE();

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
  
  @DefaultStringValue("-")
  public String TypeOfRealEstate_NONE();
  
  //TypeOfRent
  @DefaultStringValue("-")
  public String TypeOfRent_NONE();
  
  @DefaultStringValue("Kot")
  public String TypeOfRent_KOT();
  
  @DefaultStringValue("Bureau")
  public String TypeOfRent_OFFICE();
  
  @DefaultStringValue("Meublé")
  public String TypeOfRent_MEUBLE();
  
  @DefaultStringValue("Résidence principale")
  public String TypeOfRent_PRINCIPAL();
  
  @DefaultStringValue("Résidence secondaire")
  public String TypeOfRent_SECONDAIRE();
  
  @DefaultStringValue("Commercial")
  public String TypeOfRent_COMMERCIAL();
  
  //AmountOfTimeUnit
  @DefaultStringValue("-")
  public String AmountOfTimeUnit_NONE();
  
  @DefaultStringValue("Jour(s)")
  public String AmountOfTimeUnit_DAY();
  
  @DefaultStringValue("Mois")
  public String AmountOfTimeUnit_MONTH();

  @DefaultStringValue("Veuillez corriger les erreurs suivantes :")
  public String validationMessageDisplayIntro();

  @DefaultStringValue("Message d'information")
  public String informationDialogDefaultTitle();

  @DefaultStringValue("Erreur de validation")
  public String validationMessageDialogTitle();

  @DefaultStringValue("Chargement du fichier...")
  public String fileUpload();
  
  @DefaultStringValue("Biens immobiliers")
  public String menuRealEstate();
  
  @DefaultStringValue("Locataires")
  public String menuTenant();
  
  @DefaultStringValue("Propriétaires")
  public String menuOwner();
  
  @DefaultStringValue("Locations")
  public String menuLease();
  
  @DefaultStringValue("Paiements")
  public String menuPayment();

  @DefaultStringValue("Mes propriétaires")
  public String menuOwnerList();

  @DefaultStringValue("Créer un propriétaire")
  public String menuOwnerNew();

  @DefaultStringValue("Mes locations")
  public String menuLeaseList();
  
  @DefaultStringValue("Créer une location")
  public String menuLeaseNew();

  @DefaultStringValue("Mes biens")
  public String menuRealEstateList();

  @DefaultStringValue("Créer un bien")
  public String menuRealEstateNew();

  @DefaultStringValue("Mes locataires")
  public String menuTenantList();

  @DefaultStringValue("Créer un locataire")
  public String menuTenantNew();

  @DefaultStringValue("Dashboard")
  public String dashboardTitle();

  @DefaultStringValue("Edition d'un locataire")
  public String tenantUpdateTitle();

  @DefaultStringValue("Détails d'un locataire")
  public String tenantDetailsTitle();

  @DefaultStringValue("Liste des locataires")
  public String tenantListTitle();
  
  @DefaultStringValue("Création d'un locataire")
  public String tenantNewTitle();
  
  @DefaultStringValue("Liste des propriétaires")
  public String ownerListTitle();
  
  @DefaultStringValue("Création d'un propriétaire")
  public String ownerNewTitle();
  
  @DefaultStringValue("Edition d'un propriétaire")
  public String ownerUpdateTitle();

  @DefaultStringValue("Détails d'un propriétaire")
  public String ownerDetailsTitle();

  @DefaultStringValue("Liste des paiements")
  public String paymentListTitle();

  @DefaultStringValue("Liste des locations")
  public String leaseListTitle();

  @DefaultStringValue("Liste des biens immobiliers")
  public String realEstateListTitle();

  @DefaultStringValue("Détails d'un bien immobilier")
  public String realEstateDetailsTitle();

  @DefaultStringValue("Création d'un bien immobilier")
  public String realEstateNewTitle();

  @DefaultStringValue("Edition d'un bien immobilier")
  public String realEstateUpdateTitle();

  @DefaultStringValue("Détails d'une location")
  public String leaseDetailsTitle();

  @DefaultStringValue("Edition d'une location")
  public String leaseUpdateTitle();
  
  @DefaultStringValue("Création d'une location")
  public String leaseNewTitle();

  @DefaultStringValue("Edition d'un paiement")
  public String paymentOwnerUpdateTitle();
  
  @DefaultStringValue("Edition d'un paiement")
  public String paymentTenantUpdateTitle();
  
  
  
}