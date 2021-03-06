package com.fig2324;

import javax.swing.SwingWorker;
import javax.swing.JLabel;
import java.util.concurrent.ExecutionException;


/**
 * StringWorker subclass for calculating fibonacci numbers in a 
 * background thread
 * @author superlamer
 */
public class BackgroundCalculator extends SwingWorker<Long, Object> {

	private final int n; // Fibonacci number to calculate
	private final JLabel resultLabel; // JLabel to display the result
	
	/**
	 * Default constructor
	 * @param n the fibonacci number to calculate
	 * @param JLabelResultLabel the label where to display the result
	 */
	public BackgroundCalculator(int n, JLabel resultLabel) {
		this.n = n;
		this.resultLabel = resultLabel;
	}
	
	@Override
	protected Long doInBackground() throws Exception {
		long nthFib;
		return nthFib = fibonacci(n);
	}
	
	// code to run in the event dispatch thread when doInBackground returns
	protected void done() {
		try {
			// get the result of doInBackground and display it
			resultLabel.setText(get().toString());
		} catch (InterruptedException ie) {
			resultLabel.setText("Interrupted while waiting for results.");
		} catch (ExecutionException ex) {
			resultLabel.setText("Error encountered, while performing calculation.");
		}
	}

	// recursive method fibonacci; calculates nth Fibonaccci number
	public long fibonacci(long number) {
		if (number == 0 || number == 1)
			return number;
		else
			return fibonacci(number - 1) + fibonacci(number - 2);
	}
	
}
