package com.digit.MVC_Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

public class Transfer {

	public static Connection con;

	int transaction_id;
	int cust_id;
	String bank_name;
	String IFSC;
	int Sender_accno;
	String receiver_IFSC;
	int receiver_accno;
	int amount;
	int pin;

	private PreparedStatement pstmt;
	private ResultSet res;

	public Transfer() {
		String url = "jdbc:mysql://localhost:3306/BankingApplication";
		String user = "root";
		String pwd = "Logesh88823";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Transfer(int customerID, String senderBankName, String senderIFSC, int senderAccountNumber,
			String receiverIFSC, int receiverAccountNumber, int amountOfTransfer, int transactionID) {
		super();
		this.cust_id = customerID;
		this.bank_name = senderBankName;
		this.IFSC = senderIFSC;
		this.Sender_accno = senderAccountNumber;
		this.receiver_IFSC = receiverIFSC;
		this.receiver_accno = receiverAccountNumber;
		this.amount = amountOfTransfer;
		this.transaction_id = transactionID;
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getIFSC() {
		return IFSC;
	}

	public void setIFSC(String iFSC) {
		IFSC = iFSC;
	}

	public int getSender_accno() {
		return Sender_accno;
	}

	public void setSender_accno(int sender_accno) {
		Sender_accno = sender_accno;
	}

	public String getReceiver_IFSC() {
		return receiver_IFSC;
	}

	public void setReceiver_IFSC(String receiver_IFSC) {
		this.receiver_IFSC = receiver_IFSC;
	}

	public int getReceiver_accno() {
		return receiver_accno;
	}

	public void setReceiver_accno(int receiver_accno) {
		this.receiver_accno = receiver_accno;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public boolean transferAmount() {
		try {

			pstmt = con
					.prepareStatement("select * from register where customer_id =? and ifsc = ? and acc_no = ? and pin = ?");
			pstmt.setInt(1, cust_id);
			pstmt.setString(2, IFSC);
			pstmt.setInt(3, Sender_accno);
			pstmt.setInt(4, pin);
			res = pstmt.executeQuery();
			if (res.next() == true) {
				pstmt = con.prepareStatement("select * from register where ifsc = ? and acc_no =?");
				pstmt.setString(1, receiver_IFSC);
				pstmt.setInt(2, receiver_accno);
				res = pstmt.executeQuery();
				if (res.next() == true) {
					pstmt = con.prepareStatement("select balance from register where acc_no = ?");
					pstmt.setInt(1, Sender_accno);
					res = pstmt.executeQuery();
					res.first();
					int bal = res.getInt(1);
					if (bal > amount) {
						pstmt = con.prepareStatement("update register set balance = balance - ? where acc_no = ?");
						pstmt.setInt(1, amount);
						pstmt.setInt(2, Sender_accno);
						int x = pstmt.executeUpdate();
						if (x > 0) {
							pstmt = con.prepareStatement("update register set balance = balance + ? where acc_no=?");
							pstmt.setInt(1, amount);
							pstmt.setInt(2, receiver_accno);
							int x1 = pstmt.executeUpdate();
							if (x1 > 0) {
								Random r = new Random();
								int transaction_id = (100000 + r.nextInt(900000));

								pstmt = con.prepareStatement("insert into transfer_status values(?,?,?,?,?,?,?,?)");
								pstmt.setInt(1, cust_id);
								pstmt.setString(2, bank_name);
								pstmt.setString(3, IFSC);
								pstmt.setInt(4, Sender_accno);
								pstmt.setString(5, receiver_IFSC);
								pstmt.setInt(6, receiver_accno);
								pstmt.setInt(7, amount);
								pstmt.setInt(8, transaction_id);
								int x2 = pstmt.executeUpdate();
								if (x2 > 0) {
									return true;
								} else {
									return false;
								}
							} else {
								return false;
							}
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Transfer> viewAllTransactions(int curAccountNumber) {
		ArrayList<Transfer> receiveTransactionsList = new ArrayList<Transfer>();

		try {
			String query = "SELECT * FROM transfer_status WHERE sender_accno = ? OR receiver_accno = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, curAccountNumber);
			ps.setInt(2, curAccountNumber);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int customerID = rs.getInt("cust_id");
				String senderBankName = rs.getString("bank_name");
				String senderIFSC = rs.getString("IFSC");
				int senderAccountNumber = rs.getInt("Sender_accno");
				String receiverIFSC = rs.getString("receiver_IFSC");
				int receiverAccountNumber = rs.getInt("receiver_accno");
				int amountOfTransfer = rs.getInt("amount");
				int transactionID = rs.getInt("transaction_id");
				Transfer curTransaction = new Transfer(customerID, senderBankName, senderIFSC, senderAccountNumber,
						receiverIFSC, receiverAccountNumber, amountOfTransfer, transactionID);
				receiveTransactionsList.add(curTransaction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return receiveTransactionsList;
	}

}