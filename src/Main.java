import java.net.ServerSocket;
import java.net.Socket;

public class Main {

  public static void main(String[] args) throws Exception {
    ServerSocket server = new ServerSocket(8080);
    for (;;) {
      Socket socket = server.accept();
      try {
        for (;;) {
          byte[] bytes = new byte[1024];
          int len = socket.getInputStream().read(bytes);
          if (len < 0) {
            break;
          }
          socket.getOutputStream().write(bytes, 0, len);
        }
      }
      catch (Throwable e) {
      }
    }
  }

}
