package Basic_Multi_threaded_design;

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

import Pool_based_Multi_threaded_Design.IProdConsBuffer;
import Pool_based_Multi_threaded_Design.ProdConsBuffer;

public class Worker extends Thread {
    Socket soc;
    String filesname;
    int id;
    public Worker(Socket soc, int id) {
        this.id=id;
        this.soc = soc;
    }
    public void run() {

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}