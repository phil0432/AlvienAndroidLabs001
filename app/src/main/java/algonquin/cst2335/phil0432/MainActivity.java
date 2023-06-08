package algonquin.cst2335.phil0432;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w( "MainActivity", "In onCreate() - Loading Widgets" );

        Button btn = findViewById(R.id.button);
        EditText emailEditText =  findViewById(R.id.email);

        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String emailAddress = prefs.getString("LoginName", "");



        Intent nextPage = new Intent( MainActivity.this, SecondActivity.class);

        emailEditText.setText(emailAddress);


        btn.setOnClickListener( clk-> {
            Intent: nextPage.putExtra( "EmailAddress", emailEditText.getText().toString() );
            startActivity(nextPage);
            editor.putString("LoginName", emailEditText.getText().toString());
            editor.apply();

        } );


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w( TAG, "onStart() - The application is now visible on screen." );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w( TAG, "onResume() - The application is now responding to user input" );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w( TAG, "onStop() - The application is no longer visible." );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w( TAG, "onDestroy() - Any memory used by the application is freed." );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w( TAG, "In onCreate() - Loading Widgets");
    }



}