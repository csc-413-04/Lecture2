package simpleserver;

public class ProcessFactory {

    static ServerProcessor getProcessor(String url) {
      String endpoint = "/test";
      String query = "?asd=hello";
      ServerProcessor processor = null;


      /*
      some switch statement
       */
      processor = new UserProcessor();

      return processor;
    }
}
