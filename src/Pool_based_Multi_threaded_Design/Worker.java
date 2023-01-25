package Pool_based_Multi_threaded_Design;

public class Worker {
	Message m;
	Worker (ProdCons clientsBuffer) {this.clientsBuffer = clientsBuffer;}
	public void run(){
	while (true){
	Socket soc= clientsBuffer.get();
	// receive request from soc, process it and reply to client
	// do this as many times as required (session-oriented communication)
	// at the end, close soc
	}
}
}
