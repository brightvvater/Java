package bitedu.bipa.librarysystem;

public class BookSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RentalHistoryService service = new RentalHistoryService();
		service.findRentalHistoriesByUserNo(8);
		
	}

}
