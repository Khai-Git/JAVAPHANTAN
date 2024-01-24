package Exercise_01_Multi_Thread;

import java.util.Scanner;

public class Ex_2 implements Runnable {
	int x;
	int n;

	public Ex_2(int x, int n) {
		this.x = x;
		this.n = n;
	}

	public void run() {
		System.out.println("x= " + x + ", n= " + n);
		System.out.println("Tổng S(n)= " + sum(x, n));
	}

	public long sum(int x, int n) {
		long sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += Math.pow(x,i);
		}
		return sum;
	}

	public static void main(String[] args) throws InterruptedException {
		int x1 = 2;
		int n1 = 3;
		int x2 = 4;
		int n2 = 6;
		Thread thread1 = new Thread(new Ex_2(x1, n1));
		Thread thread2 = new Thread(new Ex_2(x2, n2));
		
		thread1.start();
		thread1.join();
		thread2.start();
		thread2.join();
		
		
	}
}
