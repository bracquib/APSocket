package Pool_based_Multi_threaded_Design;



import java.net.Socket;
import java.util.concurrent.Semaphore;


public class ProdConsBuffer implements IProdConsBuffer {

	private Socket[] buffer;
	private int in;
	private int out;

	private Semaphore notFull = new Semaphore(10);
	private Semaphore notEmpty = new Semaphore(0);
	private Semaphore mutex = new Semaphore(1);
	private int count;

	private int totalMessages;

	private static boolean print = false;

	public ProdConsBuffer() {
		buffer = new Socket[10];
		in = 0;
		out = 0;
		count = 0;
	}

	public ProdConsBuffer(int size) {
		buffer = new Socket[size];
		in = 0;
		out = 0;
		count = 0;
		notFull = new Semaphore(size);
	}
	@Override
	public void put(Socket soc) throws InterruptedException {
		notFull.acquire();
		mutex.acquire();
		buffer[in] = soc;
		in = (in + 1) % buffer.length;
		count++;
		totalMessages++;
		mutex.release();
		notEmpty.release();
	}

	@Override
	public Socket get() throws InterruptedException {
		notEmpty.acquire();
		mutex.acquire();
		Socket message = buffer[out];
		out = (out + 1) % buffer.length;
		count--;
		mutex.release();
		notFull.release();
		return message;
	}

	@Override
	public int nmsg() {
		return (in - out) % buffer.length;
	}

	@Override
	public int totmsg() {
		return totalMessages;
	}

	public boolean noErrors() {
		return count == 0 && in == out;
	}

	@Override
	public int getSize() {
		return buffer.length;
	}

	

	
}
