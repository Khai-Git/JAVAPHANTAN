package Ex_03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Bank {
	private int balance = 0;
	private Lock lock = new ReentrantLock();
	
	public void deposits(int n) {
		lock.lock();
		
		balance += n;
		
		lock.unlock();
	}
	
	public void withdraws(int n) {
		lock.lock();
		
		balance -= n;
		
		lock.unlock();
	}
}

class DepositThread extends Thread {
	
}

public class Exercise_03 {
	public static void main(String[] args) {
		System.out.println();
	}
}