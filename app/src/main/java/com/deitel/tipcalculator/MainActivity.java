package com.deitel.tipcalculator;

import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.view.Menu;
//import android.view.MenuItem;

public class MainActivity extends Activity {

	
	//currency and percent formatters
	private static final NumberFormat currencyFormat = 
			NumberFormat.getCurrencyInstance();
	private static final NumberFormat percentFormat = 
			NumberFormat.getPercentInstance();
	
	private double billAmount = 0.0;
	private double customPercent = 0.18;
	private TextView amountDisplayTextView;
	private TextView percentCustomTextView;
	private TextView tip15TextView;
	private TextView total15TextView;
	private TextView tipCustomTextView;
	private TextView totalCustomTextView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//get references to the TextViews
		//that MainActivity interacts with programmatically
		amountDisplayTextView = 
				(TextView) findViewById(R.id.amountDisplayTextView);
		percentCustomTextView = 
				(TextView) findViewById(R.id.percentCustomTextView);
		tip15TextView = (TextView) findViewById(R.id.tip15TextView);
		total15TextView = (TextView) findViewById(R.id.total15TextView);
		tipCustomTextView = (TextView) findViewById(R.id.tipCustomTextView);
		totalCustomTextView = (TextView) findViewById(R.id.totalCustomTextView);
		
		//update GUI based on billAmount and customPercent
		amountDisplayTextView.setText(
				currencyFormat.format(billAmount));
		updateStandard();
		updateCustom();
		
		//set amountEditText's TextWatcher
		EditText amountEditText = 
				(EditText) findViewById(R.id.amountEditText);
		amountEditText.addTextChangedListener(amountEditTextWatcher);
		
		//set customTipSeekBar's OnSeekBarChangeListener
		SeekBar customTipSeekBar = 
				(SeekBar) findViewById(R.id.customTipSeekBar);
		customTipSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
	} // end method OnCreate

	//updates 15% tip TextViews
	private void updateStandard()
	{
		//calculate 15% tip and total
		double fifteenPercentTip = billAmount * 0.15;
		double fifteenPercentTotal = billAmount + fifteenPercentTip;
		
		//display 15% tip and total formatted as currency
		tip15TextView.setText(currencyFormat.format(fifteenPercentTip));
		total15TextView.setText(currencyFormat.format(fifteenPercentTotal));
	}// end method updateStandard
	
	//updates the custom tip and total TextViews
	private void updateCustom()
	{
		//show customPercent in percentCustomTextView formatted as %
		double customTip = billAmount * customPercent;
		double customTotal = billAmount + customTip;
		
		//display custom tip and total formatted as curenct
		tipCustomTextView.setText(currencyFormat.format(customTip));
		totalCustomTextView.setText(currencyFormat.format(customTotal));
	}// end method updateCustom
	
	//called when the user changes the position of SeekBar
	private OnSeekBarChangeListener customSeekBarListener = 
			new OnSeekBarChangeListener()
			{
				//update customPercent, then call updateCustom
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser)
				{
					//sets customPercent to position of the SeekBar's thumb
					customPercent = progress / 100.0;
					updateCustom();
				}// end method onStartTrackingTouch
				
				public void OnStartTrackingTouch(SeekBar seekBar)
				{
				} // end method onStartTrackingTouch
				
				@Override
				public void onStopTrackingTouch(SeekBar seekBar)
				{
				}//end method onStopTrackingTouch

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
				
				
			};//end OnSeekBarChangeListener
			
	//event-handling object that responds to amountEditText's events
	private TextWatcher amountEditTextWatcher = new TextWatcher()
	{
		//called when the user enters a number
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count)
		{
				//convert amountEditText's text to a double
				try
				{
					billAmount = Double.parseDouble(s.toString())/100.0;
				}// end try
				catch (NumberFormatException e)
				{
						billAmount = 0.0;
				}// end catch
				
				//display currency formatted bill amount
				amountDisplayTextView.setText(currencyFormat.format(billAmount));
				updateStandard();
				updateCustom();
		} //end method onTextChanged
		
		@Override
		public void afterTextChanged(Editable s)
		{	
		}// end method afterTextChanged
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after)
		{
		} // end method beforeTextChanged
	};

}// end classMainActivity
