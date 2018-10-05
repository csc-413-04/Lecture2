package simpleserver;

public class Response {
  public String status;
  public int entries;
  iData[] data;

  void doStuff(iData datatype) {

  }

  public void setStatus(String status){
    this.status = status;
  }

  public void setData(iData[] data) {
    this.entries = data.length;
    this.data = data;
  }
}
