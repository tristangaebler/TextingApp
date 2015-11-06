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
import android.widget.AdapterView;


import org.w3c.dom.Text;

import java.util.ArrayList;



/**
 * @author Tristan Gaebler, Bodie Shane, Colm Laro, Ashton Brown, Braden Mabey, and tyler Jarrard.
 */

public class TextingActivity extends AppCompatActivity {

    private Button startButton;
    private TextView titleBox;
    private EditText phoneNumber;
    private EditText TextMessage;
    private RelativeLayout backgroundLayout;
    private Spinner  textSpinner;
    private Spinner numberSpinner;
    private ArrayList<String> messageList;
    private ArrayList<String> phoneList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texting);

        //Init for the variables startButton and titleBox
        startButton = (Button) findViewById(R.id.StartButton);
        titleBox = (TextView) findViewById(R.id.titleBox);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        TextMessage = (EditText) findViewById(R.id.TextMessage);
        backgroundLayout = (RelativeLayout) findViewById(R.id.backgroundLayout);
        textSpinner = (Spinner) findViewById(R.id.spinner);
        numberSpinner = (Spinner) findViewById(R.id.numberSpinner);


        //ArrayList with warm message greetings
        messageList = new ArrayList<String>();
        messageList.add("Enter a message or select from the list.");
        messageList.add("Hi, how are you doing");
        messageList.add("Hi mom, I learned how to create a text message app today!");
        messageList.add("Text me like one of your French girls ;)");
        messageList.add("Error your message could not be sent. Please try again later. Error number: 362950");
        messageList.add("I'm learning about important dates in history. Wanna be one of them?");
        messageList.add("Just wanted to say hi...;)");
        messageList.add("Tristan Gaebler");
        messageList.add("Bodie Shane");
        messageList.add("Colm Laro");
        messageList.add("Ashton Brown");
        messageList.add("Braden Mabey");
        messageList.add("Tyler Jarrard");
        messageList.add("");

        phoneList = new ArrayList<String>();
        phoneList.add("Enter a number, or select from the list.");
        phoneList.add("8012441454");
        phoneList.add("8015583942");
        phoneList.add("3852889199");
        phoneList.add("8017507515");
        phoneList.add("");

        loadSpinner();
        loadSpinnerP();
        setUpListeners();
    }

    private void loadSpinner() {
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, messageList);
        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textSpinner.setAdapter(listAdapter);

    }

    private void loadSpinnerP() {
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, phoneList);
        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberSpinner.setAdapter(listAdapter);

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
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View buttonView) {
                sendSMSMessage();
                changeBackgroundColor();
            }
        });

        textSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                TextMessage.setText(textSpinner.getSelectedItem().toString());
            }

            public void onNothingSelected(AdapterView<?> parent) {
                TextMessage.setText("");
            }
        });

        numberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                phoneNumber.setText(numberSpinner.getSelectedItem().toString());
            }

            public void onNothingSelected(AdapterView<?> parent) {
                phoneNumber.setText("");
            }
        });
    }
}
