package eu.comexis.napoleon.client.rpc.callback;

import java.util.List;

import eu.comexis.napoleon.shared.command.suggest.GetAllSuggestResponse;

public abstract class GotAllSuggest extends AbstractCallback<GetAllSuggestResponse>{

  public GotAllSuggest() {
    // TODO Auto-generated constructor stub
  }
  public abstract void got(List<String> suggestList);

  @Override
  public void onSuccess(GetAllSuggestResponse result) {
    got(result.getSuggestList());

  }
}
