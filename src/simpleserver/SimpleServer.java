package simpleserver;

import com.google.gson.Gson;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class SimpleServer {

  public static void main(String[] args) throws IOException {
    ServerSocket ding;
    Socket dong = null;
    String mainLine = null;
    String resource = null;
    Response badResponse = new Response();
    badResponse.status = "ERROR";
    try {
      ding = new ServerSocket(1298);
      System.out.println("Opened socket " + 1299);
      while (true) {

        // keeps listening for new clients, one at a time
        try {
          dong = ding.accept(); // waits for client here
        } catch (IOException e) {
          System.out.println("Error opening socket");
          System.exit(1);
        }

        InputStream stream = dong.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        try {

          // read the first line to get the request method, URI and HTTP version
          String line = in.readLine();
          mainLine = line;
          System.out.println("----------REQUEST START---------");
          System.out.println(line);
          // read only headers
          line = in.readLine();
          while (line != null && line.trim().length() > 0) {
            int index = line.indexOf(": ");
            if (index > 0) {
              System.out.println(line);
            } else {
              break;
            }
            line = in.readLine();
          }
          System.out.println("----------REQUEST END---------\n\n");
        } catch (IOException e) {
          System.out.println("Error reading");
          System.exit(1);
        }

        BufferedOutputStream out = new BufferedOutputStream(dong.getOutputStream());
        PrintWriter writer = new PrintWriter(out, true);  // char output to the client

        // every response will always have the status-line, date, and server name
        writer.println("HTTP/1.1 200 OK");
        writer.println("Server: TEST");
        writer.println("Connection: close");
        writer.println("Content-type: text/plain");
        writer.println("");
//
        // Body of our response
        // put rest of logic here
        // sanitize url
        // separate query from resource
        // then use factory
        // processor.process()
        //gson.toJson(response

        // GET /hello HTTP/1.1

        String[] requestParts = mainLine.split(" "); // [GET , /hello, HTTP/1.1]
        String endpoint = requestParts[1];
        ServerProcessor serverProcessor = ProcessFactory.getProcessor(endpoint);
        writer.println(serverProcessor.process(endpoint));
        //Gson gson = new Gson();
        //writer.println(gson.toJson(badResponse));

        dong.close();
      }
    } catch (IOException e) {
      System.out.println("Error opening socket");
      System.exit(1);
    }
  }
}
