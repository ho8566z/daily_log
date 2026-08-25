package com.office.toypjt.memo;

public class MemoDto {

	private int memoNo;
	private String memId;
	private String memoTitle;
	private String memoComent;
	private String memoRegDate;
	private String memoModDate;
	
	
	
	
	
	public MemoDto() {}
	
	
	public MemoDto(String memoTitle, String memoComent) {
		super();
		this.memoTitle = memoTitle;
		this.memoComent = memoComent;
	}

	public MemoDto(int memoNo, String memId, String memoTitle, String memoComent) {
		super();
		this.memoNo = memoNo;
		this.memId = memId;
		this.memoTitle = memoTitle;
		this.memoComent = memoComent;
	}
	
	public MemoDto(int memoNo, String memId, String memoTitle, String memoComent, String memoRegDate,
			String memoModDate) {
		super();
		this.memoNo = memoNo;
		this.memId = memId;
		this.memoTitle = memoTitle;
		this.memoComent = memoComent;
		this.memoRegDate = memoRegDate;
		this.memoModDate = memoModDate;
	}
	
	
	
	
	public int getMemoNo() {
		return memoNo;
	}
	public void setMemoNo(int memoNo) {
		this.memoNo = memoNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemoTitle() {
		return memoTitle;
	}
	public void setMemoTitle(String memoTitle) {
		this.memoTitle = memoTitle;
	}
	public String getMemoComent() {
		return memoComent;
	}
	public void setMemoComent(String memoComent) {
		this.memoComent = memoComent;
	}
	public String getMemoRegDate() {
		return memoRegDate;
	}
	public void setMemoRegDate(String memoRegDate) {
		this.memoRegDate = memoRegDate;
	}
	public String getMemoModDate() {
		return memoModDate;
	}
	public void setMemoModDate(String memoModDate) {
		this.memoModDate = memoModDate;
	}
	
	
	
	
	
	
	
	
}

