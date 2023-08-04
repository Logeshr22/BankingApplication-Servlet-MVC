package com.digit.MVC_Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;

public class BankApp {
	int bank_id;
	String bank_name;
	String ifsc;
	int acc_no;
	int pin;
	int customer_id;
	String customer_name;
	int balance;
	String email;
	Long phone;

	private PreparedStatement pstmt;
	public static Connection con;

	public BankApp() {
		String url = "jdbc:mysql://localhost:3306/BankingApplication";
		String user = "root";
		String pass = "Logesh88823";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getBank_id() {
		return bank_id;
	}

	public void setBank_id(int bank_id) {
		this.bank_id = bank_id;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getIfsc_code() {
		return ifsc;
	}

	public void setIfsc_code(String ifsc) {
		this.ifsc = ifsc;
	}

	public int getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(int acc_no) {
		this.acc_no = acc_no;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public boolean Register() {
		try {
			pstmt = con.prepareStatement("insert into Register values (?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, bank_id);
			pstmt.setString(2, bank_name);
			pstmt.setString(3, ifsc);
			pstmt.setInt(4, acc_no);
			pstmt.setInt(5, pin);
			pstmt.setInt(6, customer_id);
			pstmt.setString(7, customer_name);
			pstmt.setInt(8, balance);
			pstmt.setString(9, email);
			pstmt.setLong(10, phone);
			int x = pstmt.executeUpdate();

			if (x > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean login() {
		try {
			pstmt = con.prepareStatement("select * from register where customer_id = ? and pin = ?");
			pstmt.setInt(1, customer_id);
			pstmt.setInt(2, pin);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next() == true) {
				bank_id = rs.getInt("bank_id");
				bank_name = rs.getString("bank_name");
				ifsc = rs.getString("ifsc");
				acc_no = rs.getInt("acc_no");
				pin = rs.getInt("pin");
				customer_id = rs.getInt("customer_id");
				customer_name = rs.getString("customer_name");
				balance = rs.getInt("balance");
				email = rs.getString("email");
				phone = rs.getLong("phone");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean ChangePassword(int new_password, int confirmNew_pass) {
		try {
			if (new_password == confirmNew_pass) {
				pstmt = con.prepareStatement("update register set pin = ? where acc_no = ?");
				pstmt.setInt(1, new_password);
				pstmt.setInt(2, acc_no);
				int x = pstmt.executeUpdate();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkBalance() {
		try {
			pstmt = con.prepareStatement("Select balance from register where acc_no = ?");
			pstmt.setInt(1, acc_no);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next() == true) {
				balance = rs.getInt("balance");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
