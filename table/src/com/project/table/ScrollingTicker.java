package com.project.table;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class ScrollTimerListener implements ActionListener
{
	private StringBuilder sb;
	private JLabel ticker;
	
	public ScrollTimerListener(StringBuilder sb, JLabel ticker)
	{
		this.sb = sb;
		this.ticker = ticker;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(ticker.getGraphics().getFontMetrics().stringWidth(ticker.getText()) > ticker.getWidth())
		{
			sb.deleteCharAt(0);
			ticker.setText(sb.toString());
		}
		else
		{
			((Timer) e.getSource()).stop();
		}
	}
}

class FeedTimerListener implements ActionListener
{
	private Ticker ticker;
	private String[] quotes;
	private StringBuilder sb;
	
	public FeedTimerListener(Ticker ticker, String[] quotes, StringBuilder sb)
	{
		this.ticker = ticker;
		this.quotes = quotes;
		this.sb = sb;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Random random = new Random();
		int i = random.nextInt(Ticker.QUOTE_SIZE);
		sb.append("   " + quotes[i] + "   ");
		ticker.setText(sb.toString());
		
		//does string length exceed label length?
		if(ticker.getGraphics().getFontMetrics().stringWidth(ticker.getText()) > ticker.getWidth())
		{
			new Timer(50, new ScrollTimerListener(sb, ticker)).start();
		}
	}
}

class Ticker extends JLabel
{
	public static int QUOTE_SIZE = 10;
	private StringBuilder sb;
	private String symbol;
	private String[] quoteStream = new String[QUOTE_SIZE];
	
	public Ticker(String symbol)
	{
		this.symbol = symbol;
		sb = new StringBuilder();
		createQuotes();
		setFont(new Font("Serif", Font.ITALIC, 18));
		start();
	}
	
	//simulates real time feed data. For simulation only.
	private void createQuotes()
	{
		Random random = new Random();
		int dollars = random.nextInt(50) + 40;
		for(int i = 0; i < QUOTE_SIZE; i++)
		{
			int cents = random.nextInt(100) / 5;
			quoteStream[i] = symbol + " Jan 16 Call " + dollars + "." + cents;
		}
	}
	
	public void start()
	{
		Random random = new Random();
		int i = random.nextInt(2000) + 2500;
		new Timer(i, new FeedTimerListener(this, quoteStream, sb)).start();
	}
}

public class  ScrollingTicker extends JFrame 
{
	
	public ScrollingTicker()
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel contentPane = new JPanel(new GridLayout(10,1));
		contentPane.add(new Ticker("AAPL"));
		contentPane.add(new Ticker("CSCO"));
		contentPane.add(new Ticker("GOOG"));
		contentPane.add(new Ticker("ALU"));
		contentPane.add(new Ticker("MSFT"));
		contentPane.add(new Ticker("JNJ"));
		contentPane.add(new Ticker("CVX"));
		contentPane.add(new Ticker("KLM"));
		
		this.setContentPane(contentPane);
		pack();
		setVisible(true);
		
		this.setSize(new Dimension(1200,200));
		

	}
	public static void main(String[] args) {
		new ScrollingTicker();

	}
	
	
	
	



}
