package com.somsubhra.ipfinder;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText urlText;
    private Button button;
    private TextView hostText, ipText, host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking components:

        urlText = findViewById(R.id.urlText);
        button = findViewById(R.id.button);
        host = findViewById(R.id.host);
        hostText = findViewById(R.id.hostText);
        ipText = findViewById(R.id.ipText);

        Toast.makeText(this, "Created by SOMSUBHRA DAS", Toast.LENGTH_LONG).show();

        button.setOnClickListener(this);  //adding listener to button
    }
    @Override
    public void onClick(View view) {

        final String url = urlText.getText().toString(); // fetch URL

        //checks if URL field is empty:

        if( url.equals("") ) {
            host.setText("");
            hostText.setText("");
            ipText.setText(R.string.url_null);
            return;
        }

        //try/catch block to fetch Host & IP:

        try {

            String hostIP = new NetTask().execute(url).get(); // Gets value NetTask Class

            //Checks for error:

            if ( hostIP.equals("E") ) {
                host.setText("");
                hostText.setText("");
                ipText.setText(R.string.error_msg);
                return;
            }

            //Updating components:

            Toast.makeText(this, "IP Found Successfully", Toast.LENGTH_SHORT).show();
            host.setText(R.string.host);
            hostText.setPaintFlags(hostText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            hostText.setText(url);
            ipText.setText(new StringBuilder().append("Host IP: ").append(hostIP).toString());

            // Setting hyperlink to open the URL in WebView:

            hostText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Opening "+urlText.getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Openweb.class); // WebView Intent
                    intent.putExtra("URL", urlText.getText().toString());
                    startActivity(intent);
                }
            });

         } catch (Exception e) {
            host.setText("");
            hostText.setText("");
            ipText.setText(R.string.error_msg);

         }//end of try/catch
    }// end of onClick
}//end of class