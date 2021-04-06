package Bista;
import java.awt.Color;	
import java.awt.Font;	
import java.awt.event.FocusAdapter;	
import java.awt.event.FocusEvent;	
import javax.swing.JTextField;	
	
public class HintTextField extends JTextField {	
	
	Font gainFont = new Font("Tahoma", Font.PLAIN, 11);	
	Font lostFont = new Font("Tahoma", Font.ITALIC, 11);
	String hint;
	
	public HintTextField(final String hint) {
		this.hint = hint;
		setText(hint);	
		setFont(lostFont);	
		setForeground(Color.GRAY);	
		this.addFocusListener(new FocusAdapter() {	
	
			@Override	
			public void focusGained(FocusEvent e) {	
				if (getText().equals(hint) || getText().equals("")) {	
					setText(null);	
					setFont(gainFont);	
				} else {	
					setText(getText());	
					setFont(gainFont);	
				}	
				setForeground(Color.BLACK);
			}	
	
			@Override	
			public void focusLost(FocusEvent e) {	
				if (getText().equals(hint)|| getText().length()==0) {	
					setText(hint);	
					setFont(lostFont);	
					setForeground(Color.GRAY);	
				} else {	
					setText(getText());	
					setFont(gainFont);	
					setForeground(Color.BLACK);	
				}	
			}	
		});	
	
	}
	
	@Override
	public String getText() {
		String text = super.getText();
		if (text.equals(hint)) {
			return "";
		} else {
			return text;
		}
	}

	@Override
	public void setText(String t) {
		if (t == null) {
			super.setText("");
		}
		else if (t.equals("")) {
			super.setText(hint);
			setFont(lostFont);	
			setForeground(Color.GRAY);
		} else {
			super.setText(t);		
		}
	}
	
	public void setHint(String pHint) {
		if (getText().equals(hint)|| getText().length()==0) {	
			setText(pHint);	
			setFont(lostFont);	
			setForeground(Color.GRAY);	
		} else {	
			setText(getText());	
			setFont(gainFont);	
			setForeground(Color.BLACK);	
		}
		this.hint = pHint;
	}
}