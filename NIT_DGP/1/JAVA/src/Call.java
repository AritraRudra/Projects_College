
public class Call {
	
	long id,startTime,duration, endTime;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	
	public Call() {
		/*this.id=id;			//Default ID
		this.startTime= System.currentTimeMillis( );
		this.duration=5000; 	//Default Duration in mili sec.
		this.endTime=this.startTime+this.duration;*/
	}
	
	
	public Call(long id, long call_duration) {
		this.id=id;
		this.startTime= System.currentTimeMillis( );
		this.duration=call_duration;
		this.endTime=this.startTime+this.duration;
	}
		
}
