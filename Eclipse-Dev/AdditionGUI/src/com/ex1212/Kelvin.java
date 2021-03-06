package com.ex1212;

public class Kelvin extends Temperature{

	double temp;
	
	public Kelvin(double temp) {
		super(temp);
	}
	
	public static double convertToCelcius(double temperature) {
		return temperature - 273.15;
	}
	
	public static double convertToFanhrenheit(double temperature) {
		return ((temperature * 9) / 5) - 459.67;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
