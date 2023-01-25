package Babystep;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
public class HelloClient {
	static String serverHost ="127.0.0.1";
	static int serverPort =9999;
	static int id;
		public static void run() throws UnknownHostException, IOException {
			String name2 = "";
			Socket soc = new Socket(serverHost, serverPort);
			
			OutputStream os = soc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			String name ="Hello";
			System.out.println("Hello " + id);
			byte[] b = name.getBytes("UTF-8");
			dos.writeInt(b.length);
			dos.write(b);
			
			
			
			InputStream is = soc.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			int length = dis.readInt();
			byte[] c = new byte[length];
			dis.readFully(c);
			System.out.println("client " + id + "recoit");
			name = new String(c,"UTF-8");
			
		}
		public static void main(String[] args) throws UnknownHostException, IOException {
			HelloClient.run();
			id++;
		}
}
