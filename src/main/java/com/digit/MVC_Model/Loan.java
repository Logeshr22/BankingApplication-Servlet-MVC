package com.digit.MVC_Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Loan {
	int loanID;
	String loanType;
	int tenure;
	float interest;
	String description;

	private PreparedStatement pstmt;
	public static Connection con;

	public Loan() {
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

	public int getLoanID() {
		return loanID;
	}

	public void setLoanID(int loanID) {
		this.loanID = loanID;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public float getInterest() {
		return interest;
	}

	public void setInterest(float interest) {
		this.interest = interest;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean LoanApply() {
		try {
			pstmt = con.prepareStatement("select * from LoanDetails where LoanType = ?");
			pstmt.setString(1, loanType);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				this.loanID = rs.getInt("LoanID");
				this.tenure = rs.getInt("Tenure");
				interest = rs.getFloat("Interest");
				description = rs.getString("Description");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}