package vo;

import java.sql.Date;

public class BookVO {
	private String book_code;
	private String book_name;
	private String book_type;
	private String book_author;
	private Date in_date;
	private String stat_fg;
	
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
	public String getBook_type() {
		return book_type;
	}
	public void setBook_type(String book_type) {
		this.book_type = book_type;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	public Date getIn_date() {
		return in_date;
	}
	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}
	public String getStat_fg() {
		return stat_fg;
	}
	public void setStat_fg(String stat_fg) {
		this.stat_fg = stat_fg;
	}
}
