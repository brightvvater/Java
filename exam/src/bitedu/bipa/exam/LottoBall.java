package bitedu.bipa.exam;

public class LottoBall {
	
	private int number;
	private boolean isSelected;
	
	public LottoBall(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	@Override
	public String toString() {
		return  String.valueOf(this.number) ;
	}
	
	
	
	
}
