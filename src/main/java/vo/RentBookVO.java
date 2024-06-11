package vo;

public class RentBookVO {
	private String rent_ymd;
	private String rent_no;
	private String book_code;
	private String book_name;
	private String cust_no;
	private String cust_name;
	private String close_ymd;
	
	public String getRent_ymd() {
		return rent_ymd;
	}
	public void setRent_ymd(String rent_ymd) {
		this.rent_ymd = rent_ymd;
	}
	public String getRent_no() {
		return rent_no;
	}
	public void setRent_no(String rent_no) {
		this.rent_no = rent_no;
	}
	public String getBook_code() {
		return book_code;
	}
	public void setBook_code(String book_code) {
		this.book_code = book_code;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getCust_no() {
		return cust_no;
	}
	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getClose_ymd() {
		return close_ymd;
	}
	public void setClose_ymd(String close_ymd) {
		this.close_ymd = close_ymd;
	}
}
