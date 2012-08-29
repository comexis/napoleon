package eu.comexis.napoleon.shared.command.suggest;

import java.util.List;

import eu.comexis.napoleon.shared.command.Response;

public class GetAllSuggestResponse implements Response {

  private List<String> suggestList;

  public GetAllSuggestResponse() {
    // TODO Auto-generated constructor stub
  }

  public List<String> getSuggestList() {
    return suggestList;
  }

  public void setSuggestList(List<String> suggestList) {
    this.suggestList = suggestList;
  }

}
