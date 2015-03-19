package com.example.ui.wizards;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;

/**
 * verify listener to verify text widget in wizard
 * 
 * @author user
 * 
 */
public class TextPatternVerifyListener implements VerifyListener {

	public static Pattern FirstVarPattern = Pattern.compile("[a-zA-Z\b\r\0]+");
	public static Pattern VarPattern = Pattern.compile("[a-zA-Z0-9_\b\r\0]+");

	public static int TYPE_VAR = 5;

	private Pattern pattern;
	private int type = 0;

	public TextPatternVerifyListener(Pattern pattern) {
		super();
		this.pattern = pattern;
	}

	public TextPatternVerifyListener(Pattern pattern, int type) {
		super();
		this.pattern = pattern;
		this.type = type;
	}

	@Override
	public void verifyText(VerifyEvent e) {
		// TODO Auto-generated method stub
		if (e.keyCode == 8) {
			e.doit = true;
			return;
		} else if (e.keyCode == 0) {
			e.doit = true;
			return;
		}

		if (type == TYPE_VAR) {
			String str = ((Text) e.widget).getText();
			String inStr = e.text;
			Matcher m;

			if (str.length() == 0)
				m = FirstVarPattern.matcher(inStr);
			else
				m = pattern.matcher(inStr);

			if (m.find())
				e.doit = true;
			else
				e.doit = false;
		}
	}

}
