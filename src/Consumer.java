import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable{

	private LinkedBlockingQueue< String > blockingQueue = new LinkedBlockingQueue<>();
	

	public Consumer(LinkedBlockingQueue<String> lb) {
		this.blockingQueue = lb;	
	}

	@Override
	public void run() {
		while (true) {
			String taken = "";
			try {
				/* Increase the timer in case of dynamic producer*/
				taken = blockingQueue.take();
				//taken = blockingQueue.poll(10,TimeUnit.SECONDS); 

				System.out.println("Receivig .. " +taken + " by "+ Thread.currentThread().getName() );	

				if(taken == null || taken.equals("end"))
				{
					break;
				}
				if(taken.equals("over")/* this is from dynamic producer*/) {
					System.out.println("Over is received .. timie to exit");
					break;
				}
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

		}
	}

}
