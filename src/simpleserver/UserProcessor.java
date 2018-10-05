package simpleserver;

import com.google.gson.Gson;

public class UserProcessor implements ServerProcessor {

  @Override
  public String process(String query) {
    // All logic goes in here
    Response response = new Response();
    Database db = Database.getDatabase();
    response.setData(db.getAllUsers());
    response.setStatus("OK");
    Gson gson = new Gson();
    return gson.toJson(response);
  }
}
