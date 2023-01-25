package Babystep;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class HelloServer {
	static int serverPort =9999;
	static int backlog =3;
		public static void main(String[] args) throws UnknownHostException, IOException {
			ServerSocket listenSoc = new ServerSocket(serverPort, backlog);
				while (true) {
					Socket soc = listenSoc.accept();
					String name = "";

					InputStream is = soc.getInputStream();
					DataInputStream dis = new DataInputStream(is);
					int length = dis.readInt();
					byte[] c = new byte[length];
					dis.readFully(c);
					name = new String(c,"UTF-8");
					System.out.println("serveur recoit ");

					
					
					
					
					OutputStream os = soc.getOutputStream();
					DataOutputStream dos = new DataOutputStream(os);
					String name2 ="réponse ";
					byte[] b = name2.getBytes("UTF-8");
					dos.writeInt(b.length);
					dos.write(b);
					System.out.println("server repond ");

					
					}
			}
		}

