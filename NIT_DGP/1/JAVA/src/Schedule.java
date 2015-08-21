import java.util.Random;

import org.omg.CORBA.PRIVATE_MEMBER;


public class Schedule {
	int buffer_size, channel_size,callsDropped=0;
	long call_duration, waiting_time,id=0;
	int call_check,qstat,qstat1;
	C_queue q;
	Call newCall;
	Random r_time = new Random(),r_call=new Random();

	public Schedule(int length, int channel_size, long waiting_time) {
		long range=1000; 	//Call duration 0-999
		q=new C_queue(length);
		this.buffer_size=length;
		this.call_duration=	(long)(r_time.nextDouble()*range);
		this.channel_size=channel_size;
		this.waiting_time=waiting_time;
	}

	void begin(){
		int i,len;
		long curr_time;
		while(true){
			call_check=r_call.nextInt();
			if(call_check%2 == 0){
				newCall=new Call(id++,call_duration);
				qstat=q.insert(newCall);
				if(qstat==1){
					try {
						Thread.currentThread().sleep(waiting_time);		// New call waits for certain time before rejected
						//Thread.sleep(waiting_time);		// New call waits for certain time before rejected
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					check_remove();
					qstat1=q.insert(newCall);
					if(qstat1==1){
						callsDropped++;
					}
				}

				check_remove();
				/*
				len=q.getSize();			
				for (i = 0; i<len; i++) {
					curr_time=System.currentTimeMillis();
					if(curr_time>q.a[i].endTime){
						q.delete(i);
					}
					//len=q.getSize();
				}
				*/
				//System.out.println("size : "+q.getSize());
				System.out.println("No of calls generated : "+id);
				System.out.println("No of calls dropped : "+callsDropped);
			}
		}
	}
	
	private void check_remove(){
		int i,len;
		long curr_time;
		len=q.getSize();			
		//for (i = 0; i<len; i++) {
		for (i = 0; i<q.rear; i++) {
			curr_time=System.currentTimeMillis();
			if(curr_time>q.a[i].endTime){
				q.delete(i);
			}
			//len=q.getSize();
		}
		//System.out.println("No of calls dropped : "+callsDropped);
	}

	//Generate a new call
	//Add it to Q
	//if full then drop++

	/*
	while(true){
		random
		if(rand%2 =0)
			Generate new call;
			Add2Q

	} 
	 */

}

/*
The standard method to generate a number (without a utility method) in a range is to just use the double with the range:

long range = 1234567L;
Random r = new Random()
long number = (long)(r.nextDouble()*range);

will give you a long between 0 (inclusive) and range (exclusive). Similarly if you want a number between x and y:

long x = 1234567L;
long y = 23456789L;
Random r = new Random()
long number = x+((long)(r.nextDouble()*(y-x)));
will give you a long from 1234567 (inclusive) through 123456789 (exclusive)

Note: check parentheses, because casting to long has higher priority than multiplication.
http://stackoverflow.com/questions/2546078/java-random-long-number-in-0-x-n-range

 */