package ctect.textingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;
import android.graphics.Color;
import android.telephony.SmsManager;
import android.widget.Toast;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.w3c.dom.Text;


import java.util.ArrayList;



/**
 * @author Cody Henrichsen  - Helped OH so much.
 */

public class TextingActivity extends AppCompatActivity {

    private Button startButton;
    private Button dadContact;
    private Button momContact;
    private TextView titleBox;
    private EditText phoneNumber;
    private EditText TextMessage;
    private RelativeLayout backgroundLayout;
    private Spinner  textSpinner;
    private ArrayList<String> messageList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texting);

        //Init for the variables startButton and titleBox
        startButton = (Button) findViewById(R.id.StartButton);
        dadContact = (Button) findViewById(R.id.dadContact);
        momContact = (Button) findViewById(R.id.momContact);
        titleBox = (TextView) findViewById(R.id.titleBox);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        TextMessage = (EditText) findViewById(R.id.TextMessage);
        backgroundLayout = (RelativeLayout) findViewById(R.id.backgroundLayout);
        textSpinner = (Spinner) findViewById(R.id.spinner);

        //ArrayList with warm message greetings
        messageList = new ArrayList<String>();
        messageList.add("Hi, how are you doing");
        messageList.add("Hi mom, I leanred how to create a Text Message app today!");
        messageList.add("Just wanted to say hi...:)");

        setUpListeners();
    }

    private void loadSpinner() {
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, messageList);
        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textSpinner.setAdapter(listAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_texting, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void changeBackgroundColor() {
        int redColor;
        int greenColor;
        int blueColor;

        redColor = (int) (Math.random() * 256);
        greenColor = (int) (Math.random() * 256);
        blueColor = (int) (Math.random() * 256);

        backgroundLayout.setBackgroundColor(Color.rgb(redColor, greenColor, blueColor));

    }

    protected void sendSMSMessage() {

        String phoneNo = phoneNumber.getText().toString();
        String message = TextMessage.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    private void setUpListeners() {
        momContact.setOnClickListener(new View.OnClickListener(){
            public void onClick(View buttonView) {
                phoneNumber.setText("18016666661");
            }
        });
        dadContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View buttonView) {
                phoneNumber.setText("8018799954");
            }
        });
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View buttonView) {
                sendSMSMessage();
                changeBackgroundColor();
            }
        });
    }
}
