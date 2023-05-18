package exam;



public class Lotto {

	
	public static void main(String[] args) {
		int[] arr = new int[6];
		for(int i=0;i<6;i++) {
			arr[i] = (int)(Math.random()*45+1);
			
			for(int j=0;j<i;j++) {
				if(arr[i] ==arr[j]) {
					i--;
				}
			}
		}
		for(int i=0;i<6;i++) {
			System.out.print(arr[i]+ " ");
		}
		
	
		
	}

}
