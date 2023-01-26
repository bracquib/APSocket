package Pool_based_Multi_threaded_Design;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Worker extends Thread {
    String filesname;
	   Socket soc;
        ProdConsBuffer prodcons;
      int id;
	private boolean running=true;
    public Worker(ProdConsBuffer prodcons, int id) {
        this.id=id;
        this.prodcons = prodcons;
    }
	public  void run(){
		while (this.running) {
		try {
			this.soc=prodcons.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String filesname = "";
	        try {
	            InputStream is = soc.getInputStream();
	            DataInputStream dis = new DataInputStream(is);
	            int length = dis.readInt();
	            byte[] c = new byte[length];
	            dis.readFully(c);
	            filesname = new String(c, "UTF-8");
	            System.out.println("serveur recoit la requete" + id);
	            
	            
	            //récupérer le fichier appelé filesname dans ses dossier et l'envoyer au client
	            OutputStream os = soc.getOutputStream();
	            DataOutputStream dos = new DataOutputStream(os);
	            File file = new File("./src/Babyfile/" + filesname);
	            if (file.exists()) {
	                FileInputStream fileIn = new FileInputStream(file);
	                byte[] buffer = new byte[4096];
	                int len;
	                int i = 0;
	                while ((len = fileIn.read(buffer, i*512, 512)) != -1) {
	                    dos.write(buffer, i*512, len);
	                    System.out.println("Envoie bloc " + i+1);
	                    i++;
	                }
	                System.out.println("envoie le fichier");

	                fileIn.close();
	            }
	            dos.close();
	            os.close();
	            soc.close();
	            //listenSoc.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
		}
}
	
	
}
