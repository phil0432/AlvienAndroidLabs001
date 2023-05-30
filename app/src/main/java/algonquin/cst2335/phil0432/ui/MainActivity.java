package algonquin.cst2335.phil0432.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import algonquin.cst2335.phil0432.R;
import algonquin.cst2335.phil0432.data.MainViewModel;
import algonquin.cst2335.phil0432.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel model;
    private ActivityMainBinding variableBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        TextView mytext = variableBinding.textview;
        EditText myedittext = variableBinding.myedittext;
        Button mybutton = variableBinding.mybutton;
        CheckBox mycheckbox = variableBinding.checkbox;
        Switch myswitch = variableBinding.switch1;
        RadioButton myradiobutton = variableBinding.radiobutton;
        ImageView myimageview = variableBinding.imageview;
        ImageButton myimagebutton = variableBinding.myimagebutton;

        model = new ViewModelProvider(this).get(MainViewModel.class);

        //variableBinding.myedittext.setText(model.editString);
        variableBinding.mybutton.setOnClickListener(click ->
                {
                    model.editString.postValue(variableBinding.myedittext.getText().toString());
                }
        );

        model.editString.observe(this, s -> {
            variableBinding.textview.setText("Your edit text has: "+ s);
        });
        model.check_coffee.observe(this, selected -> {
            variableBinding.checkbox.setChecked(selected);
            variableBinding.radiobutton.setChecked(selected);
            variableBinding.switch1.setChecked(selected);

            String toast_message = "The value is now: " + selected;
            Toast.makeText(MainActivity.this, toast_message, Toast.LENGTH_SHORT).show();
        });

        mycheckbox.setOnCheckedChangeListener( (btn, isChecked) -> {
            model.check_coffee.postValue(isChecked);
        } );
        myswitch.setOnCheckedChangeListener( (btn, isChecked) -> {
            model.check_coffee.postValue(isChecked);
        } );
        myradiobutton.setOnCheckedChangeListener( (btn, isChecked) -> {
            model.check_coffee.postValue(isChecked);
        } );

        myimagebutton.setOnClickListener(v -> {
            int height = v.getHeight();
            int width = v.getWidth();

            CharSequence text = "The width = " + width + " and height = " + height;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();

        });

    }

}