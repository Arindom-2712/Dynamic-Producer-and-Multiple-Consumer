
import java.util.concurrent.LinkedBlockingQueue;

public class Producer implements Runnable{

	private LinkedBlockingQueue< String > blockingQueue = new LinkedBlockingQueue<>();
	private String[] Messages = new String[] {"Hey","Hi","Lol","ROFL","Love", "Good Morning"};


	public Producer(LinkedBlockingQueue<String> lb) {
		this.blockingQueue = lb;
	}

	@Override
	public void run() {
		try {
			for(int i=0;i<Messages.length;++i) {
				blockingQueue.put(send(i));
				Thread.sleep(100);
			}
			/* Do not put this in case of dynamic producer case;*/
			//blockingQueue.put("end"); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private String send(int i) {
		String s = "";
		s= Messages[i];
		System.out.println("Seding .. " + s);
	    return s;
	}
}
