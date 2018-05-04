import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class DynamicProducer implements Runnable{

	private LinkedBlockingQueue< String > blockingQueue = new LinkedBlockingQueue<>();
	private int  max_count_consumer_thread = 10000;

	public DynamicProducer(LinkedBlockingQueue<String> lb,int max_thread_count) {
		this.blockingQueue = lb;
		this.max_count_consumer_thread = max_thread_count;
	}

	@Override
	public void run() {
		while(true) {

			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Message to be sent : \n type over to exit sending msgs" );
			String msg = sc.nextLine();

			try {
				blockingQueue.put(send(msg));

				/* when over is typed, We need to exit from all consumers */
				if(msg.equals("over")) { 				
					for(int i=1;i<this.max_count_consumer_thread;++i) {
						blockingQueue.put(send(msg));
					}
					System.out.println("Stop Sendig !! " );
					break;
				}
			}catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
	private String send(String msg) {
		System.out.println( "Sending " + msg);
		return msg;
	}

}
