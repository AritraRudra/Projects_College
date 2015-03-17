
public class StartHere {
	public static void main(String[] args) {
		int buffer_size, channel_size;
		long call_duration, waiting_time;
		int length = args.length;
        if (length !=3) {
            System.out.println("usage : java startHere "+"<buffer_size> <channel_size> <waiting_time>");
            System.exit(1);
        }
        
        buffer_size = Integer.parseInt(args[0]);
        channel_size = Integer.parseInt(args[1]);
        waiting_time =Long.parseLong(args[2]);
		Schedule schdl= new Schedule(buffer_size, channel_size, waiting_time);
		schdl.begin();
		
	}

}
