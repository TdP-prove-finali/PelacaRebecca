package tesi.model;

import javafx.beans.property.SimpleIntegerProperty;

public class Forecast {
	
	private SimpleIntegerProperty one;
	private SimpleIntegerProperty two;
	private SimpleIntegerProperty three;
	private SimpleIntegerProperty four;
	private SimpleIntegerProperty five;
	private SimpleIntegerProperty six;
	private SimpleIntegerProperty seven;
	private SimpleIntegerProperty eight;
	private SimpleIntegerProperty nine;
	
	public Forecast(int one) {
		super();
		this.one = new SimpleIntegerProperty(one);
	}
	
	public Forecast(int one, int two) {
		super();
		this.one = new SimpleIntegerProperty(one);
		this.two = new SimpleIntegerProperty(two);
	}
	
	public Forecast(int one, int two, int three) {
		super();
		this.one = new SimpleIntegerProperty(one);
		this.two = new SimpleIntegerProperty(two);
		this.three = new SimpleIntegerProperty(three);
	}
	
	public Forecast(int one, int two, int three, int four) {
		super();
		this.one = new SimpleIntegerProperty(one);
		this.two = new SimpleIntegerProperty(two);
		this.three = new SimpleIntegerProperty(three);
		this.four = new SimpleIntegerProperty(four);
	}
	
	public Forecast(int one, int two, int three, int four, int five) {
		super();
		this.one = new SimpleIntegerProperty(one);
		this.two = new SimpleIntegerProperty(two);
		this.three = new SimpleIntegerProperty(three);
		this.four = new SimpleIntegerProperty(four);
		this.five = new SimpleIntegerProperty(five);
	}
	
	public Forecast(int one, int two, int three, int four, int five, int six) {
		super();
		this.one = new SimpleIntegerProperty(one);
		this.two = new SimpleIntegerProperty(two);
		this.three = new SimpleIntegerProperty(three);
		this.four = new SimpleIntegerProperty(four);
		this.five = new SimpleIntegerProperty(five);
		this.six = new SimpleIntegerProperty(six);
	}
	
	public Forecast(int one, int two, int three, int four, int five, int six, int seven) {
		super();
		this.one = new SimpleIntegerProperty(one);
		this.two = new SimpleIntegerProperty(two);
		this.three = new SimpleIntegerProperty(three);
		this.four = new SimpleIntegerProperty(four);
		this.five = new SimpleIntegerProperty(five);
		this.six = new SimpleIntegerProperty(six);
		this.seven = new SimpleIntegerProperty(seven);
	}
	
	public Forecast(int one, int two, int three, int four, int five, int six, int seven, int eight) {
		super();
		this.one = new SimpleIntegerProperty(one);
		this.two = new SimpleIntegerProperty(two);
		this.three = new SimpleIntegerProperty(three);
		this.four = new SimpleIntegerProperty(four);
		this.five = new SimpleIntegerProperty(five);
		this.six = new SimpleIntegerProperty(six);
		this.seven = new SimpleIntegerProperty(seven);
		this.eight = new SimpleIntegerProperty(eight);
	}

	public Forecast(int one, int two, int three, int four, int five, int six, int seven, int eight, int nine) {
		super();
		this.one = new SimpleIntegerProperty(one);
		this.two = new SimpleIntegerProperty(two);
		this.three = new SimpleIntegerProperty(three);
		this.four = new SimpleIntegerProperty(four);
		this.five = new SimpleIntegerProperty(five);
		this.six = new SimpleIntegerProperty(six);
		this.seven = new SimpleIntegerProperty(seven);
		this.eight = new SimpleIntegerProperty(eight);
		this.nine = new SimpleIntegerProperty(nine);
	}

	public int getOne() {
		if(one!=null)
			return one.get();
		else
			return 0;
	}

	public int getTwo() {
		if(two!=null)
			return two.get();
		else
			return 0;
	}

	public int getThree() {
		if(three!=null)
			return three.get();
		else
			return 0;
	}

	public int getFour() {
		if(four!=null)
			return four.get();
		else
			return 0;
	}

	public int getFive() {
		if(five!=null)
			return five.get();
		else
			return 0;
	}

	public int getSix() {
		if(six!=null)
			return six.get();
		else
			return 0;
	}

	public int getSeven() {
		if(seven!=null)
			return seven.get();
		else
			return 0;
	}

	public int getEight() {
		if(eight!=null)
			return eight.get();
		else
			return 0;
	}

	public int getNine() {
		if(nine!=null)
			return nine.get();
		else
			return 0;
	}

	@Override
	public String toString() {
		return "Forecast [one=" + one + ", two=" + two + ", three=" + three + ", four=" + four + ", five=" + five
				+ ", six=" + six + ", seven=" + seven + ", eight=" + eight + ", nine=" + nine + "]";
	}
}
