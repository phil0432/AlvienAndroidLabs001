package algonquin.cst2335.phil0432;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This is the main activity java file
 *
 * @author alvien
 * @version 1.0
 *
 */
public class MainActivity extends AppCompatActivity {

    /** This holds the text at the centre of the screen */
    private TextView tv = null;
    /** This Allows the user to enter text */
    private EditText et = null;
    /** This acts as a button that allows user to login */
    private Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textView);
        EditText et = findViewById(R.id.editText);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(clk ->{
            String pw = et.getText().toString();

            checkPasswordComplexity( pw);

            if (checkPasswordComplexity(pw)) {
                tv.setText("Your password meets the requirements");
            } else {
                tv.setText("You shall not pass!");
            }
        });

    }


    /**This function checks the complexity of the password if it has an Upper Case letter, a lower case letter, a number, and a special symbol (#$%^&*!@?)
     *
     * @param pw The string object that we are checking
     * @return Returns true if the password is complex enough and false its not
     */
    private boolean checkPasswordComplexity(String pw) {

        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;

        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;

        for (int i = 0; i < pw.length(); i++) {
            char c = pw.charAt(i);

            if (Character.isUpperCase(c)) {
                foundUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                foundLowerCase = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            }else if (isSpecialCharacter(c)) {
                foundSpecial = true;
            }

        }


        if(!foundUpperCase)
        {
            Toast.makeText(this, "Your password does not have an upper case letter", Toast.LENGTH_SHORT).show();

            return false;
        }

        else if( ! foundLowerCase)
        {
            Toast.makeText(this, "Your password does not have an lower case letter", Toast.LENGTH_SHORT).show();

            return false;

        }

        else if( ! foundNumber) {

            Toast.makeText(this, "Your password does not have a number", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if(! foundSpecial) {
            Toast.makeText(this, "Your password does not have a special character", Toast.LENGTH_SHORT).show();
            return false;

        }

        else

            return true; //only get here if they're all true
    }

    /**
     * Checks if a character is a special character.
     *
     * @param c The character to be checked.
     * @return {@code true} if the character is a special character, {@code false} otherwise.
     */
    boolean isSpecialCharacter(char c)
    {
        switch (c)
        {
            case '#':
            case '?':
            case '*':
            case '$':
            case '%':
            case '^':
            case '&':
            case '!':
            case '@':
                return true;
            default:
                return false;
        }
    }
}