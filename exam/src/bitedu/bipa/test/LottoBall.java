package bitedu.bipa.test;

//데이터 클래스(DTO, VO) 
public class LottoBall implements Comparable{
	private int number;
	private boolean isSelected;
	
	public LottoBall(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}

	/*
	 * private void setNumber(int number) { this.number = number; }
	 */
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	@Override
	public String toString() {
		return String.valueOf(number);
	}

	@Override
	public int compareTo(Object o) {
		
		return 0;
	}
	
	
	
	

}
