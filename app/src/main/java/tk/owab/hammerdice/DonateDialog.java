package tk.owab.hammerdice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class DonateDialog extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(R.string.donate_dialog_text)
		.setPositiveButton(R.string.donate, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://projects.serwin.tk/hammer"));
		        startActivity(browserIntent);
			}
		})
		.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {

			}
		});

		return builder.create();
	}
}
