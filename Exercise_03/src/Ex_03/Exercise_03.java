package Ex_03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
	private int balance = 0;
	private Lock lock = new ReentrantLock();

	public void deposits(int n) {
		lock.lock();

		try {
			balance += n;
			System.out.println("Deposit: " + n + ", Balance: " + balance);
		} finally {
			lock.unlock();
		}
	}

	public void withdraws(int n) {
		lock.lock();

		try {
			balance -= n;
			System.out.println("Withdraw: " + n + ", Balance: " + balance);
		} finally {
			lock.unlock();
		}
	}
}

class DepositsThread extends Thread {
	private BankAccount account;

	public DepositsThread(BankAccount account) {
		super();
		this.account = account;
	}

	public synchronized void run() {
		account.deposits(100);

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class WithDrawsThread extends Thread {
	private BankAccount account;

	public WithDrawsThread(BankAccount account) {
		super();
		this.account = account;
	}

	public synchronized void run() {
		account.withdraws(100);

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class Exercise_03 {
	public static void main(String[] args) throws InterruptedException {
		BankAccount account = new BankAccount();

		for (int i = 0; i < 5; i++) {
			new DepositsThread(account).start();
			Thread.sleep(1000);
		}
		for (int i = 0; i < 5; i++) {
			new WithDrawsThread(account).start();
			Thread.sleep(1000);
		}
	}
}