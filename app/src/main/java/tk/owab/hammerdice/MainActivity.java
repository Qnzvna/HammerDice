package tk.owab.hammerdice;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.EditText;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

    public final static String DICES = "tk.owab.hammerdice.DICES";
    public final static String HIT = "tk.owab.hammerdice.HIT";
    public final static String WOUND = "tk.owab.hammerdice.WOUND";
    public final static String ASAVE = "tk.owab.hammerdice.ASAVE";

    private int dices = 1;
    private double chanceToHit = 1;
    private double chanceToWound = 1;
    private double chanceToSave = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

    private double setProbFromDice(int index) {
        switch(index) {
            case 0: // 2+
                return 5.0 / 6;
            case 1: // 3+
                return 4.0 / 6;
            case 2: // 4+
                return 3.0 / 6;
            case 3: // 5+
                return 2.0 / 6;
            case 4: // 6+
                return 1.0 / 6;
            default:
                return 0.0;
        }
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
		Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://hammer.serwin.tk/donate"));
        startActivity(browserIntent);
	}

    public void chartClick(View v) {
        RadioGroup toHit = (RadioGroup)findViewById(R.id.radioGroup0);
        RadioGroup toWound = (RadioGroup)findViewById(R.id.radioGroup1);
        ToggleButton saveButton = (ToggleButton)findViewById(R.id.button2);
        RadioGroup toSave = (RadioGroup)findViewById(R.id.radioGroup2);
        EditText numberDiceText = (EditText)findViewById(R.id.editText1);

        String text;
        try {
            text = numberDiceText.getText().toString();
            this.dices = Integer.parseInt(text);
        } catch (Exception e)
        {
            numberDiceText.setText(this.dices+"");
        }

        int index = toHit.indexOfChild(findViewById(toHit.getCheckedRadioButtonId()));
        this.chanceToHit = this.setProbFromDice(index);

        index = toWound.indexOfChild(findViewById(toWound.getCheckedRadioButtonId()));
        this.chanceToWound = this.setProbFromDice(index);

        if (saveButton.isChecked())
        {
            index = toSave.indexOfChild(findViewById(toSave.getCheckedRadioButtonId()));
            this.chanceToSave = this.setProbFromDice(index);
        } else {
            this.chanceToSave = 0;
        }

        Intent intent = new Intent(this, ChartActivity.class);
        intent.putExtra(MainActivity.DICES, this.dices);
        intent.putExtra(MainActivity.HIT, this.chanceToHit);
        intent.putExtra(MainActivity.WOUND, this.chanceToWound);
        intent.putExtra(MainActivity.ASAVE, this.chanceToSave);

        startActivity(intent);
    }
}
