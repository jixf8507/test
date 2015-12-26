package com.qx.eakay.model.monitor;

public class CarContorlCmd extends BaseModel {

	private String CheckOut;

	private String BookID;
	
	public CarContorlCmd (){
		super();
		this.CheckOut = "" ;
		this.BookID = "" ;
	}

	public String getCheckOut() {
		return CheckOut;
	}

	public void setCheckOut(String checkOut) {
		CheckOut = checkOut;
	}

	public String getBookID() {
		return BookID;
	}

	public void setBookID(String bookID) {
		BookID = bookID;
	}

}
