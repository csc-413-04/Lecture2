package simpleserver;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class Database {
  User[] users = null;
  private  HashMap <String, User> userMap = new HashMap<>();

  boolean isLoaded = false;
  public static Database database = new Database();

  private Database () {

  }

  public static Database getDatabase() {
    if (!database.isLoaded) database.connect();
    return database;
  }

  public User[] getAllUsers() {
    return this.users;
  }

  void connect() {
    isLoaded = true;
    Gson gson = new Gson();
    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader("src/data.json"));
      JsonParser jsonParser = new JsonParser();
      JsonObject obj = jsonParser.parse(br).getAsJsonObject();

      users = gson.fromJson(obj.get("users"), User[].class);
      //.loadAll();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
