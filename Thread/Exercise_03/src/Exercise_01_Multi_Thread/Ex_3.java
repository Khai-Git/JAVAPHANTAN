package Exercise_01_Multi_Thread;

public class Ex_3 implements Runnable {
	int n;

	public Ex_3(int n) {
		this.n = n;
	}

	public void run() {
		System.out.println("n= " + n);
		if (n > 1) {
			System.out.println("Có " + count(n) + " số chia hết cho 2 hoặc chia hết cho 3 nhưng không chia hết cho 6");
		} else {
			System.out.println("Vì n= " + count(n) + " < 1 nên không có số chia hết cho 2 hoặc chia hết cho 3 nhưng không chia hết cho 6");
		}
	}

	public long count(int n) {
		int count = 0;
		if (n > 1) {
			for (int i = 1; i <= n; i++) {
				if(i % 2 == 0 || (i % 3 == 0 && i % 6 !=0)) {
					count++;
				}
			}
			return count;
		} else {
			return count;
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		int n1 = 3;
		int n2 = 6;
		int n3 = -20;
		int n4 = 50;
		Thread thread1 = new Thread(new Ex_3(n1));
		Thread thread2 = new Thread(new Ex_3(n2));
		Thread thread3 = new Thread(new Ex_3(n3));
		Thread thread4 = new Thread(new Ex_3(n4));
		
		thread1.start();
		thread1.join();
		thread2.start();
		thread2.join();
		
		thread3.start();
		thread4.start();
		thread3.join();
		thread4.join();
		
		
	}
}