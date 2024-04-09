package Ex_03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
	private int balance = 0;
	private int insuranceLimit = 100000;
	private Lock lock = new ReentrantLock();

	public void deposits(int n) throws InterruptedException {
		lock.lock();

		try {
			while (balance + n > insuranceLimit) {
				System.out.println("Deposit is limited, need to Withdraw. Balance: " + balance);
				lock.wait();
			}
			balance += n;
			System.out.println("Deposit: " + n + ", Balance: " + balance);
		} finally {
			lock.unlock();
		}
	}

	public void withdraws(int n) throws InterruptedException {
		lock.lock();

		try {
			while (balance - n < 0) {
				System.out.println("Balance now is: " + balance + ", can't Withdraw need to Deposit");
				lock.wait();
			}
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
		for (int i = 0; i < 5; i++) {
			try {
				account.deposits(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		for (int i = 0; i < 5; i++) {
			try {
				account.withdraws(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

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

		Thread deposit1 = new DepositsThread(account);
		Thread withdraw1 = new WithDrawsThread(account);

		deposit1.start();
		withdraw1.start();

		try {
			deposit1.join();
			withdraw1.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}