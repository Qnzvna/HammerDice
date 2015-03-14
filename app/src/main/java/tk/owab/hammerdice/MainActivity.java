package tk.owab.hammerdice;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DialogFragment newFragment = new DonateDialog();
	    newFragment.show(getFragmentManager(), "missiles");
	}

	public void countProb(View v) {
		RadioGroup toHit = (RadioGroup)findViewById(R.id.radioGroup0);
		RadioGroup toWound = (RadioGroup)findViewById(R.id.radioGroup1);
		ToggleButton saveButton = (ToggleButton)findViewById(R.id.button2);
		RadioGroup toSave = (RadioGroup)findViewById(R.id.radioGroup2);
		EditText numberDiceText = (EditText)findViewById(R.id.editText1);
		EditText minimumKillsText = (EditText)findViewById(R.id.editText2);
		TextView chanceView = (TextView)findViewById(R.id.chance);

		double chanceToHit = 1;
		double chanceToWound = 1;
		double chanceToSave = 0;
		int numberDice = 1;
		int minimumKills = 1;
		String text;
		try {
			text = minimumKillsText.getText().toString();
			minimumKills = Integer.parseInt(text);
			text = numberDiceText.getText().toString();
			numberDice = Integer.parseInt(text);
		} catch (Exception e)
		{
			minimumKillsText.setText(minimumKills+"");
			numberDiceText.setText(numberDice+"");
		}
		
		int index = toHit.indexOfChild(findViewById(toHit.getCheckedRadioButtonId()));
		switch(index) {
		case 0: // 3+
			chanceToHit = 4.0 / 6;
			break;
		case 1: // 4+
			chanceToHit = 3.0 / 6;
			break;
		case 2: // 5+
			chanceToHit = 2.0 / 6;
			break;
		}

		index = toWound.indexOfChild(findViewById(toWound.getCheckedRadioButtonId()));
		switch(index) {
		case 0: // 2+
			chanceToWound = 5.0 / 6;
			break;
		case 1: // 3+
			chanceToWound = 4.0 / 6;
			break;
		case 2: // 4+
			chanceToWound = 3.0 / 6;
			break;
		case 3: // 5+
			chanceToWound = 2.0 / 6;
			break;
		case 4: // 6+
			chanceToWound = 1.0 / 6;
			break;
		}
		
		if (saveButton.isChecked())
		{
			index = toSave.indexOfChild(findViewById(toSave.getCheckedRadioButtonId()));
			switch(index) {
			case 0: // 2+
				chanceToSave = 5.0 / 6;
				break;
			case 1: // 3+
				chanceToSave = 4.0 / 6;
				break;
			case 2: // 4+
				chanceToSave = 3.0 / 6;
				break;
			case 3: // 5+
				chanceToSave = 2.0 / 6;
				break;
			case 4: // 6+
				chanceToSave = 1.0 / 6;
				break;
			}
		} else {
			chanceToSave = 0;
		}
		
		Log.d("Hammer", numberDice+" "+minimumKills+" "+chanceToSave+" "+chanceToWound+" "+chanceToHit);
		double chance = DiceProb.fightSimulator(numberDice, minimumKills, chanceToSave, chanceToWound, chanceToHit);
		chanceView.setText("Chance to Waaaagh!!! "+Math.round(chance*100.0)+"%");
	}

	public void showSave(View v) {
		ToggleButton saveButton = (ToggleButton)findViewById(R.id.button2);
		RadioGroup toShow = (RadioGroup)findViewById(R.id.radioGroup2);
		
		if (!saveButton.isChecked())
		{
			toShow.setVisibility(View.GONE);
		} else {
			toShow.setVisibility(View.VISIBLE);
		}
	}
	
	public void donateClick(View v) {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://projects.serwin.tk/hammer"));
        startActivity(browserIntent);
	}
}
