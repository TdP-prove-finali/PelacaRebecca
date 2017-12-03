package tesi.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Row {
	
	private SimpleStringProperty title;
	private SimpleIntegerProperty tb1;
	private SimpleIntegerProperty tb2;
	private SimpleIntegerProperty tb3;
	private SimpleIntegerProperty tb4;
	private SimpleIntegerProperty tb5;
	private SimpleIntegerProperty tb6;
	
	public Row(String title, int tb1, int tb2, int tb3, int tb4, int tb5, int tb6) {
		
		this.title = new SimpleStringProperty(title);
		this.tb1 = new SimpleIntegerProperty(tb1);
		this.tb2 = new SimpleIntegerProperty(tb2);
		this.tb3 = new SimpleIntegerProperty(tb3);
		this.tb4 = new SimpleIntegerProperty(tb4);
		this.tb5 = new SimpleIntegerProperty(tb5);
		this.tb6 = new SimpleIntegerProperty(tb6);
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitle(SimpleStringProperty title) {
		this.title = title;
	}

	public int getTb1() {
		return tb1.get();
	}

	public void setTb1(SimpleIntegerProperty tb1) {
		this.tb1 = tb1;
	}

	public int getTb2() {
		return tb2.get();
	}

	public void setTb2(SimpleIntegerProperty tb2) {
		this.tb2 = tb2;
	}

	public int getTb3() {
		return tb3.get();
	}

	public void setTb3(SimpleIntegerProperty tb3) {
		this.tb3 = tb3;
	}

	public int getTb4() {
		return tb4.get();
	}

	public void setTb4(SimpleIntegerProperty tb4) {
		this.tb4 = tb4;
	}

	public int getTb5() {
		return tb5.get();
	}

	public void setTb5(SimpleIntegerProperty tb5) {
		this.tb5 = tb5;
	}

	public int getTb6() {
		return tb6.get();
	}

	public void setTb6(SimpleIntegerProperty tb6) {
		this.tb6 = tb6;
	}

	@Override
	public String toString() {
		return "Row [title=" + title + ", tb1=" + tb1 + ", tb2=" + tb2 + ", tb3=" + tb3 + ", tb4=" + tb4 + ", tb5="
				+ tb5 + ", tb6=" + tb6 + "]";
	}	
}
