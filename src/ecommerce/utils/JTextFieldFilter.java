package ecommerce.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Estensione di PlainDocument per il filtraggio dei caratteri inseribili in una JTextFiled
 * @author Davide Malvezzi
 */
public class JTextFieldFilter extends PlainDocument {
	    
	/**
	 * Tipologie di caratteri inseribili
	 */
    public static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHA = LOWERCASE + UPPERCASE;
    public static final String NUMERIC = "0123456789";
    public static final String FLOAT = NUMERIC + ".";
    public static final String ALPHA_NUMERIC = ALPHA + NUMERIC;
     
    protected String acceptedChars = null;
    protected boolean negativeAccepted = false;
    
    /**
     * @brief Costruttore
     */
    public JTextFieldFilter() {
        this(ALPHA_NUMERIC);
    }
    
    /**
     * @brief Costruttore
     * @param acceptedchars Insieme di caratteri accettabili
     */
    public JTextFieldFilter(String acceptedchars) {
        acceptedChars = acceptedchars;
    }
    
    /**
     * @brief Setta se accettare o meno numeri negativi
     * @param negativeaccepted Accettare numeri negativi
     */
    public void setNegativeAccepted(boolean negativeaccepted) {
        if (acceptedChars.equals(NUMERIC) ||
                acceptedChars.equals(FLOAT) ||
                acceptedChars.equals(ALPHA_NUMERIC)){
            negativeAccepted = negativeaccepted;
            acceptedChars += "-";
        }
    }
     
    /**
     * @brief Controlla se i caratteri inseriti sono accettabili
     */
    public void insertString(int offset, String  str, AttributeSet attr)  throws BadLocationException {
        if (str == null) return;
         
        if (acceptedChars.equals(UPPERCASE))
            str = str.toUpperCase();
        else if (acceptedChars.equals(LOWERCASE))
            str = str.toLowerCase();
         
        for (int i = 0; i < str.length(); i++) {
            if (acceptedChars.indexOf(str.valueOf(str.charAt(i))) == -1)
                return;
        }
         
        if (acceptedChars.equals(FLOAT) || (acceptedChars.equals(FLOAT + "-") && negativeAccepted)) {
            if (str.indexOf(".") != -1) {
                if (getText(0, getLength()).indexOf(".") != -1) {
                    return;
                }
            }
        }
         
        if (negativeAccepted && str.indexOf("-") != -1) {
            if (str.indexOf("-") != 0 || offset != 0 ) {
                return;
            }
        }
         
        super.insertString(offset, str, attr);
    }
}
