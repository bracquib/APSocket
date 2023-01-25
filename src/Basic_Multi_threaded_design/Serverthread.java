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

public class Serverthread {

	static int serverPort =9999;
	static int backlog =3;
	public static void main(String[] args) throws UnknownHostException, IOException {
			//creates one thread per client request. For each client request,
		//the server creates a dedicated worker thread; each worker thread processes one client request and dies
		ServerSocket listenSoc = new ServerSocket(serverPort, backlog);

		while (true) {
			Socket soc = listenSoc.accept();
			Worker worker = new Worker(soc);
			worker.start();

		}
		
		}
			}

