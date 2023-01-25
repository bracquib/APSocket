package Pool_based_Multi_threaded_Design;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class PoolServer {

	static int serverPort =9999;
	static int backlog =3 ;
	public static void main(String[] args) throws UnknownHostException, IOException {
			//creates one thread per client request. For each client request,
		//the server creates a dedicated worker thread; each worker thread processes one client request and dies
		ServerSocket listenSoc = new ServerSocket(serverPort, backlog);
		ProdCons clientsBuffer = new ProdCons(..);
		while (true) {
		Socket soc= socListen.accept();
		clientsBuffer.put(soc);
		}
}
	
}
