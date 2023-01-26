package Pool_based_Multi_threaded_Design;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class PoolServer {

	static int serverPort =9999;
	static int backlog =3 ;
	static int poolSize = 3;
	
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
			//creates one thread per client request. For each client request,
		//the server creates a dedicated worker thread; each worker thread processes one client request and dies
		ServerSocket listenSoc = new ServerSocket(serverPort, backlog);
		ProdConsBuffer clientsBuffer = new ProdConsBuffer();
		
		Worker[] workers = new Worker[poolSize];
		for (int i = 0; i < poolSize; i++) {
			workers[i] = new Worker(clientsBuffer, i);
			workers[i].start();
		}

		Thread.sleep(10000);
		while (true) {
	        Socket soc = listenSoc.accept();
	        clientsBuffer.put(soc);
    }
	}

	
}
