package Babyfile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class FileServer {
	
	
	static int serverPort =9999;
	static int backlog =3;
	public static void main(String[] args) throws UnknownHostException, IOException {
			ServerSocket listenSoc = new ServerSocket(serverPort, backlog);
				while (true) {
					Socket soc = listenSoc.accept();
					String filesname = "";

					InputStream is = soc.getInputStream();
					DataInputStream dis = new DataInputStream(is);
					int length = dis.readInt();
					byte[] c = new byte[length];
					dis.readFully(c);
					filesname = new String(c,"UTF-8");
					System.out.println("serveur recoit la requete");

					//récupérer le fichier appelé filesname dans ses dossier et l'envoyer au client
					OutputStream os = soc.getOutputStream();
					DataOutputStream dos = new DataOutputStream(os);
					File file = new File("./src/Babyfile/"+filesname);
					if (file.exists()) {
						FileInputStream fileIn = new FileInputStream(file);
						byte[] buffer = new byte[4096];
						int len;
						while ((len = fileIn.read(buffer)) != -1) {
							dos.write(buffer, 0, len);
						}
						System.out.println("reçoie le fichier");

						fileIn.close();
					}
					dos.close();
					os.close();
					soc.close();
					//listenSoc.close();




					
					}
			}
		}