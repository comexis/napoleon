package eu.comexis.napoleon.client.core.lease;

public interface LeaseDetailUiHandlers {

  void onButtonBackToListClick();

  void onButtonUpdateClick();
  
  void onButtonPaymentTenantClick();
  
  void onButtonPaymentOwnerClick();
  
  void onButtonPaymentClick();

  void showOwner();

  void showReference();

  void showTenant();
}
