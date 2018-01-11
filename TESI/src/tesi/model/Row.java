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
	private SimpleIntegerProperty tb7;
	private SimpleIntegerProperty tb8;
	private SimpleIntegerProperty tb9;
	
	public Row(String title, int tb1) {
		
		this.title = new SimpleStringProperty(title);
		this.tb1 = new SimpleIntegerProperty(tb1);
	}
	
	public Row(String title, int tb1, int tb2) {
		
		this.title = new SimpleStringProperty(title);
		this.tb1 = new SimpleIntegerProperty(tb1);
		this.tb2 = new SimpleIntegerProperty(tb2);
	}
	
	public Row(String title, int tb1, int tb2, int tb3) {
		
		this.title = new SimpleStringProperty(title);
		this.tb1 = new SimpleIntegerProperty(tb1);
		this.tb2 = new SimpleIntegerProperty(tb2);
		this.tb3 = new SimpleIntegerProperty(tb3);
	}
	
	public Row(String title, int tb1, int tb2, int tb3, int tb4) {
		
		this.title = new SimpleStringProperty(title);
		this.tb1 = new SimpleIntegerProperty(tb1);
		this.tb2 = new SimpleIntegerProperty(tb2);
		this.tb3 = new SimpleIntegerProperty(tb3);
		this.tb4 = new SimpleIntegerProperty(tb4);
	}
	
	public Row(String title, int tb1, int tb2, int tb3, int tb4, int tb5) {
		
		this.title = new SimpleStringProperty(title);
		this.tb1 = new SimpleIntegerProperty(tb1);
		this.tb2 = new SimpleIntegerProperty(tb2);
		this.tb3 = new SimpleIntegerProperty(tb3);
		this.tb4 = new SimpleIntegerProperty(tb4);
		this.tb5 = new SimpleIntegerProperty(tb5);
	}
	
	public Row(String title, int tb1, int tb2, int tb3, int tb4, int tb5, int tb6) {
		
		this.title = new SimpleStringProperty(title);
		this.tb1 = new SimpleIntegerProperty(tb1);
		this.tb2 = new SimpleIntegerProperty(tb2);
		this.tb3 = new SimpleIntegerProperty(tb3);
		this.tb4 = new SimpleIntegerProperty(tb4);
		this.tb5 = new SimpleIntegerProperty(tb5);
		this.tb6 = new SimpleIntegerProperty(tb6);
	}
	
	public Row(String title, int tb1, int tb2, int tb3, int tb4, int tb5, int tb6, int tb7) {
		
		this.title = new SimpleStringProperty(title);
		this.tb1 = new SimpleIntegerProperty(tb1);
		this.tb2 = new SimpleIntegerProperty(tb2);
		this.tb3 = new SimpleIntegerProperty(tb3);
		this.tb4 = new SimpleIntegerProperty(tb4);
		this.tb5 = new SimpleIntegerProperty(tb5);
		this.tb6 = new SimpleIntegerProperty(tb6);
		this.tb7 = new SimpleIntegerProperty(tb7);
	}
	
	public Row(String title, int tb1, int tb2, int tb3, int tb4, int tb5, int tb6, int tb7, int tb8) {
		
		this.title = new SimpleStringProperty(title);
		this.tb1 = new SimpleIntegerProperty(tb1);
		this.tb2 = new SimpleIntegerProperty(tb2);
		this.tb3 = new SimpleIntegerProperty(tb3);
		this.tb4 = new SimpleIntegerProperty(tb4);
		this.tb5 = new SimpleIntegerProperty(tb5);
		this.tb6 = new SimpleIntegerProperty(tb6);
		this.tb7 = new SimpleIntegerProperty(tb7);
		this.tb8 = new SimpleIntegerProperty(tb8);
	}

	public Row(String title, int tb1, int tb2, int tb3, int tb4, int tb5, int tb6, int tb7, int tb8, int tb9) {
		
		this.title = new SimpleStringProperty(title);
		this.tb1 = new SimpleIntegerProperty(tb1);
		this.tb2 = new SimpleIntegerProperty(tb2);
		this.tb3 = new SimpleIntegerProperty(tb3);
		this.tb4 = new SimpleIntegerProperty(tb4);
		this.tb5 = new SimpleIntegerProperty(tb5);
		this.tb6 = new SimpleIntegerProperty(tb6);
		this.tb7 = new SimpleIntegerProperty(tb7);
		this.tb8 = new SimpleIntegerProperty(tb8);
		this.tb9 = new SimpleIntegerProperty(tb9);
	}

	public String getTitle() {
		return title.get();
	}

	public int getTb1() {
		return tb1.get();
	}

	public int getTb2() {
		return tb2.get();
	}

	public int getTb3() {
		return tb3.get();
	}

	public int getTb4() {
		return tb4.get();
	}

	public int getTb5() {
		return tb5.get();
	}

	public int getTb6() {
		return tb6.get();
	}
	
	public int getTb7() {
		return tb7.get();
	}
	
	public int getTb8() {
		return tb8.get();
	}
	
	public int getTb9() {
		return tb9.get();
	}

	@Override
	public String toString() {
		return "Row [title=" + title + ", tb1=" + tb1 + ", tb2=" + tb2 + ", tb3=" + tb3 + ", tb4=" + tb4 + ", tb5="
				+ tb5 + ", tb6=" + tb6 + ", tb7=" + tb7 + ", tb8=" + tb8 + ", tb9=" + tb9 + "]";
	}
}
