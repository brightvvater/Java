package exam;


public class Practice {
	
	public static void main(String[] args) {
		WorkThread workThreadA = new WorkThread("workThreadA");
		WorkThread workThreadB = new WorkThread("workThreadB");
		workThreadA.start();
		workThreadB.start();
		
		try {       
			Thread.sleep(5000);
		} catch (Exception e) {	}
		workThreadA.work= false;
		
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		workThreadA.work = true;
	}
	
	
}


class WorkThread extends Thread {

	public boolean work = true;
	
	public WorkThread(String name) {
		setName(name);
	}
	

	@Override
	public void run() {
		while(true) {
			if(work) {
				System.out.println(getName() +": 작업처리");
			}else {
				Thread.yield();
			}
		}
	}
	
}







