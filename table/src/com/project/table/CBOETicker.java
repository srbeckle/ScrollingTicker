package com.project.table;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CBOETicker extends JFrame 
{
	private JLabel aaplTicker;
	private JLabel googTicker;
	private StringBuilder aaplSb;
	private StringBuilder googSb;
	private final String[] aaplQuotes = {"AAPL 70.5 Jan 16 Call 84.45","AAPL 70.5 Jan 16 Call 83.85","AAPL 70.5 Jan 16 Call 90.55","AAPL 70.5 Jan 16 Call 93.45"};
	private final String[] googQuotes = {"GOOG 70.5 Jan 16 Call 84.45","GOOG 70.5 Jan 16 Call 83.85","GOOG 70.5 Jan 16 Call 90.55","GOOG 70.5 Jan 16 Call 93.45"};
	private StringBuilder googSb1;
	private JLabel googTicker1;
	private StringBuilder googSb2;
	private JLabel googTicker2;
	private StringBuilder googSb3;
	private JLabel googTicker3;
	private StringBuilder googSb4;
	private JLabel googTicker4;
	private StringBuilder googSb5;
	private JLabel googTicker5;
	private StringBuilder googSb6;
	private JLabel googTicker6;

	public CBOETicker()
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel contentPane = new JPanel(new GridLayout(10,1));
		aaplSb = new StringBuilder("AAPL 70.5 Jan 16 Call 85.45");
		aaplTicker = new JLabel(aaplSb.toString());
		aaplTicker.setFont(new Font("Serif", Font.ITALIC, 18));
		contentPane.add(aaplTicker);
		googSb = new StringBuilder("GOOG 70.5 Feb 16 Put 85.45");
		googTicker = new JLabel(googSb.toString());
		googTicker.setFont(new Font("Serif", Font.ITALIC, 18));
		
		googSb = new StringBuilder("GOOG 70.5 Feb 16 Put 85.45");
		googTicker = new JLabel(googSb.toString());
		googTicker.setFont(new Font("Serif", Font.ITALIC, 18));
		
		googSb1 = new StringBuilder("GOOG 70.5 Feb 16 Put 85.45");
		googTicker1 = new JLabel(googSb1.toString());
		googTicker1.setFont(new Font("Serif", Font.ITALIC, 18));
		
		googSb2 = new StringBuilder("GOOG 70.5 Feb 16 Put 85.45");
		googTicker2 = new JLabel(googSb2.toString());
		googTicker2.setFont(new Font("Serif", Font.ITALIC, 18));
		
		googSb3 = new StringBuilder("GOOG 70.5 Feb 16 Put 85.45");
		googTicker3 = new JLabel(googSb3.toString());
		googTicker3.setFont(new Font("Serif", Font.ITALIC, 18));
		
		googSb4 = new StringBuilder("GOOG 70.5 Feb 16 Put 85.45");
		googTicker4 = new JLabel(googSb4.toString());
		googTicker4.setFont(new Font("Serif", Font.ITALIC, 18));
		
		googSb5 = new StringBuilder("GOOG 70.5 Feb 16 Put 85.45");
		googTicker5 = new JLabel(googSb5.toString());
		googTicker5.setFont(new Font("Serif", Font.ITALIC, 18));
		
		googSb6 = new StringBuilder("GOOG 70.5 Feb 16 Put 85.45");
		googTicker6 = new JLabel(googSb6.toString());
		googTicker6.setFont(new Font("Serif", Font.ITALIC, 18));
		
		contentPane.add(aaplTicker);
		contentPane.add(googTicker);
		contentPane.add(googTicker1);
		contentPane.add(googTicker2);
		contentPane.add(googTicker3);
		contentPane.add(googTicker4);
		contentPane.add(googTicker5);
		contentPane.add(googTicker6);
		this.setContentPane(contentPane);
		pack();
		setVisible(true);
		this.setSize(new Dimension(1200,200));
		new Timer(5500, new FeedTimerListener(aaplTicker, aaplQuotes, aaplSb)).start();
		new Timer(3500, new FeedTimerListener(googTicker, googQuotes, googSb)).start();
		new Timer(10000, new FeedTimerListener(googTicker1, googQuotes, googSb1)).start();
		new Timer(3000, new FeedTimerListener(googTicker2, googQuotes, googSb2)).start();
		new Timer(4300, new FeedTimerListener(googTicker3, googQuotes, googSb3)).start();
		new Timer(3800, new FeedTimerListener(googTicker4, googQuotes, googSb4)).start();
		new Timer(7500, new FeedTimerListener(googTicker5, googQuotes, googSb5)).start();
		new Timer(6400, new FeedTimerListener(googTicker6, googQuotes, googSb6)).start();

	}
	public static void main(String[] args) {
		new CBOETicker();

	}
	
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
		private JLabel ticker;
		private String[] quotes;
		private StringBuilder sb;
		
		public FeedTimerListener(JLabel ticker, String[] quotes, StringBuilder sb)
		{
			this.ticker = ticker;
			this.quotes = quotes;
			this.sb = sb;
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Random random = new Random();
			int i = random.nextInt(4);
			sb.append("   " + quotes[i]);
			ticker.setText(sb.toString());
			//System.out.println(ticker.getText().length());
			//System.out.println("String pixel length = " + ticker.getGraphics().getFontMetrics().stringWidth(ticker.getText()));
			//System.out.println("Label length = " + ticker.getWidth());

			//does string length exceed label length?
			if(ticker.getGraphics().getFontMetrics().stringWidth(ticker.getText()) > ticker.getWidth())
			{
				new Timer(50, new ScrollTimerListener(sb, ticker)).start();
			}
		}
	}



}
