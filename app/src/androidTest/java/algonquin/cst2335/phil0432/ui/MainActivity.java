package ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import algonquin.cst2335.phil0432.R;
import algonquin.cst2335.phil0432.databinding.ActivityMainBinding;
import data.MainViewModel;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;

    private MainViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        TextView mytext = variableBinding.textview;
        Button btn = variableBinding.mybutton;
        EditText myedit = variableBinding.myedittext;

        model = new ViewModelProvider(this).get(MainViewModel.class);




//        btn.setOnClickListener(click ->
//                {
//                    model.editString = variableBinding.myedittext.getText().toString();
//                    variableBinding.textview.setText("Your edit text has: " + model.editString);
//                }
//        );




        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String editString = myedit.getText().toString();
                mytext.setText("Your edit text has: " + editString);



              //   Code here executes on main thread after user presses button
            }
        });

    }

}






