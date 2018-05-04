import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;



class PCThreadFactory implements ThreadFactory {

	private String name = "DEFAULT";
	AtomicInteger i = new AtomicInteger(0);
	
	public PCThreadFactory( String name ) {
		this.name = name ;
	}

	public Thread newThread(Runnable r) {
		String TName = name +" : "+ i.incrementAndGet();
		return new Thread(r, TName);
	}
}

public class Driver {



	public static void main(String args[]) {

		int max_count_consumer_thread = 5;
		
		LinkedBlockingQueue< String > blockingQueue = new LinkedBlockingQueue<>();

		Producer p = new Producer(blockingQueue);
		Thread tP =  new Thread(p);
		tP.start();	
		
		DynamicProducer dp = new DynamicProducer(blockingQueue,max_count_consumer_thread);
		Thread dP =  new Thread(dp);
		dP.start();
		

		Consumer C = new Consumer(blockingQueue);

		PCThreadFactory PCThreadFactory = new PCThreadFactory("Arindom "); 
		ExecutorService executor = Executors.newFixedThreadPool(max_count_consumer_thread,PCThreadFactory);
		for(int i=0;i<max_count_consumer_thread;++i) {
			executor.submit(C);
		}	
		executor.shutdown();
	}
}
