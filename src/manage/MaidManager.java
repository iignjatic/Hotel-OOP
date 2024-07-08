package manage;

import entity.Maid;
import entity.Room;
import entity.Workers;
import enums.Function;
import enums.StatusRoom;

public class MaidManager{
	//za check outovanu sobu dodjeljuje se sobarici sa najmanje rasporedjenih soba za ciscenje
	//nakon sredjivanja omogucen je check in  
	WorkerManager workers;
	
	public MaidManager(WorkerManager workers) {
		this.workers = workers;
	}
	
	
	public void someoneClean(Room r) {
		int min = 100;
		String maid = null;
		for(Workers worker: workers.workersMap.values()) {
			if (worker.getFunction() == Function.maid) {
				if (((Maid)worker).getNumOfRoomsToClean()<min) {
					min = ((Maid)worker).getNumOfRoomsToClean();
					maid = worker.getUsername();
			}
		}
			
		}
		((Maid)workers.workersMap.get(maid)).setNumOfRoomsToClean(min+1);
		((Maid)workers.workersMap.get(maid)).getRoomsToClean().add(r);
		

}
	public void cleanRoom(Room r) {
		r.setStatusRoom(StatusRoom.slobodno);
	}
}