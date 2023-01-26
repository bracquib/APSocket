package Pool_based_Multi_threaded_Design;

import java.net.Socket;

public interface IProdConsBuffer {

	// put the message in the buffer
	public void put(Socket message) throws InterruptedException;

	// get the message from the buffer
	public Socket get() throws InterruptedException;

	// return the number of messages in the buffer
	public int nmsg();

	// return the total number of put operations
	public int totmsg();


	// return a boolean indicating if the buffer has occurred an error
	public boolean noErrors();

	// return the buffer size
	public int getSize();


}