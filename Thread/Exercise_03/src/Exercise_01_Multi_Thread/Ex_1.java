package Exercise_01_Multi_Thread;

import java.util.Scanner;

public class Ex_1 implements Runnable {
	int n;

	public Ex_1(int n) {
		this.n = n;
	}

	public void run() {
		if(n > 0) {
			System.out.println("Thread: " + Thread.currentThread().getId() + " đang đếm ước số từ 1 đến " + n);
		} else {
			System.out.println("Thread: " + Thread.currentThread().getId() + " không thể đếm ước số vì " + n + " < 0");
		}
		for (int i = 1; i <= n; i++) {
			int count = count(i);
			System.out.println("Số thứ " + i + ": " + count + " là ước số.");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int count(int num) {
		int count = 0;
		if(num > 0) {
			for (int i = 1; i <= num; i++) {
				if (num % i == 0) {
					count++;
				}
			}
			return count;
		} else {
			return count;
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		int n1 = 40;
		int n2 = 20;

		Scanner sc = new Scanner(System.in);

		int n3;

		System.out.print("\nNhập vào số cần tìm ước số: ");
		n3 = sc.nextInt();

		Thread thread1 = new Thread(new Ex_1(n1));
		Thread thread2 = new Thread(new Ex_1(n2));
		Thread thread3 = new Thread(new Ex_1(n3));

		thread1.start();
		thread1.join();
		
		thread2.start();
		thread3.start();
		
		thread2.join();
		thread3.join();
	}
}
