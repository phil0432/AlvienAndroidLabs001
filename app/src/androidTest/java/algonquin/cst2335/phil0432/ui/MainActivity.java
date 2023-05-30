package algonquin.cst2335.phil0432.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import algonquin.cst2335.phil0432.data.MainViewModel;
import algonquin.cst2335.phil0432.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;
    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        EditText myedit = variableBinding.myedittext;
        TextView mytext = variableBinding.textview;
        Button mybtn = variableBinding.mybutton;
        CheckBox mycheck = variableBinding.checkbox;
        Switch myswitch = variableBinding.switch1;
        RadioButton myradio = variableBinding.radiobutton;
        ImageButton imageButton = variableBinding.myimagebutton;

        mybtn.setOnClickListener(click ->
                {
                    model.editString.postValue(myedit.getText().toString());
                    mytext.setText("Your edit text has: " + model.editString);
                }
        );

        model.editString.observe(this, s -> mytext.setText("Your edit text has: " + s));

        model.check_coffee.observe(this, selected -> {
            variableBinding.checkbox.setSelected(selected);
            variableBinding.switch1.setSelected(selected);
            variableBinding.radiobutton.setSelected(selected);

            String toast_message = "The value is now: " + selected;
            Toast.makeText(MainActivity.this, toast_message, Toast.LENGTH_SHORT).show();
        });

        mycheck.setOnCheckedChangeListener( (btn, isChecked) -> model.check_coffee.postValue(isChecked));

        myswitch.setOnCheckedChangeListener( (btn, isChecked) -> model.check_coffee.postValue(isChecked));

        myradio.setOnCheckedChangeListener( (btn, isChecked) -> model.check_coffee.postValue(isChecked));

        imageButton.setOnClickListener(v -> {
            int width = v.getWidth();
            int height = v.getHeight();

            String message = "The width = " + width + " and height = " + height;
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
     });


}
}
