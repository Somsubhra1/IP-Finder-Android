package com.somsubhra.ipfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText urlText;
    private Button button;
    private TextView hostText, ipText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking components:

        urlText = findViewById(R.id.urlText);
        button = findViewById(R.id.button);
        hostText = findViewById(R.id.hostText);
        ipText = findViewById(R.id.ipText);

        button.setOnClickListener(this);  //adding listener to button
    }
    @Override
    public void onClick(View view) {

        String url = urlText.getText().toString(); // fetch URL

        //checks if URL field is empty:

        if( url.equals("") ) {
            ipText.setText(R.string.url_null);
            return;
        }

        //try/catch block to fetch Host & IP:

        try {

            String hostIP = new NetTask().execute(url).get(); // Gets value NetTask Class

            //Checks for error:

            if ( hostIP.equals("E") ) {
                hostText.setText("");
                ipText.setText(R.string.error_msg);
                return;
            }

            //Updating components:

            hostText.setText(new StringBuilder().append("Host URL: ").append(url).toString());
            ipText.setText(new StringBuilder().append("Host IP: ").append(hostIP).toString());

         } catch (Exception e) {

            hostText.setText("");
            ipText.setText(R.string.error_msg);

         }//end of try/catch
    }// end of onClick
}//end of class