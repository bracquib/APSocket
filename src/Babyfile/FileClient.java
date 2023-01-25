package Babyfile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class FileClient {
	static String serverHost ="127.0.0.1";
	static int serverPort =9999;
	static int id;
		public static void run() throws UnknownHostException, IOException {
			Socket soc = new Socket(serverHost, serverPort);
			
			OutputStream os = soc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			String filesname ="Hello.txt";
			System.out.println("demande le fichier " + id);
			byte[] b = filesname.getBytes("UTF-8");
			dos.writeInt(b.length);
			dos.write(b);

			InputStream is = soc.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			try (FileOutputStream fileOut = new FileOutputStream(filesname)) {
				byte[] buffer = new byte[4096];
				int len;
				while ((len = dis.read(buffer)) != -1) {
					fileOut.write(buffer, 0, len);
				}
				//affiche  l'intérieur du fichier
				System.out.println("le contenu du fichier est : " + new String(buffer));



			}
				System.out.println("reçoie le fichier");
			soc.close();



			
		}
		public static void main(String[] args) throws UnknownHostException, IOException {
			FileClient.run();
			id++;
		}
}