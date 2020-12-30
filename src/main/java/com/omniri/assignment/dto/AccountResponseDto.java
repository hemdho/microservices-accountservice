package com.omniri.assignment.dto;

import java.util.Date;

public class AccountResponseDto {
	private String accountId;
	private String accountType;
	private String customerName;
	private String branch;
	private String minorIndicator;
	private Date openDate;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getMinorIndicator() {
		return minorIndicator;
	}

	public void setMinorIndicator(String minorIndicator) {
		this.minorIndicator = minorIndicator;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

}
