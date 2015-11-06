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
 * @version 2.0 This is the final version of our texting app. It can send messages to any preset or custom number. You can send preset messages or custom ones.
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

    /**
     * This method initializes all of our class variables. It also adds text to our ArrayList. It calls the loadSpinner() method and the setUpListeners() method.
     * @param savedInstanceState
     */
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
        messageList.add("");
        messageList.add("Hi, how are you doing");
        messageList.add("Hi mom, I learned how to create a text message app today!");
        messageList.add("Text me like one of your French girls ;)");
        messageList.add("Error your message could not be sent. Please try again later. Error number: 362950");
        messageList.add("I'm learning about important dates in history. Wanna be one of them?");
        messageList.add("Just wanted to say hi...;)");
        messageList.add("Spam");
        messageList.add("You may fall from the sky, you may fall from a tree, but the best way to fall... is in love with me.");
        messageList.add("Can I have your picture so I can show Santa what I want for Christmas?");
        messageList.add("Can I tie your shoe? Because I can't have you fall for anyone else.");
        messageList.add("Do you have a name or can I call you mine?");
        messageList.add("Are you Google? Because I've just found what I've been searching for.");
        messageList.add("If you stood in front of a mirror and held up 11 roses, you would see 12 of the most beautiful things in the world.");
        messageList.add("Guess what I'm wearing? The smile you gave me.");
        messageList.add("Even if there wasn't any gravity on earth, I would still fall for you!");
        messageList.add("Your hand looks heavy. Let me hold it for you.");
        messageList.add("I need some answers for my math homework. Quick. What's your number?");
        messageList.add("I want our love to be like pi, irrational and never ending.");
        messageList.add("Do you believe in love at first sight, or should I walk by again?");
        messageList.add("Kissing burns 5 calories a minute. How about a workout?");
        messageList.add("A boy gives a girl 12 roses. 11 real, 1 fake and he says to her  I will stop loving you when all the roses die");
        messageList.add("Let's commit the perfect crime: I'll steal you're heart, and you'll steal mine.");
        messageList.add("I've got skittles in my mouth, wanna taste the rainbow?");
        messageList.add("I hope you know CPR, because you take my breath away!");
        messageList.add("Tristan Gaebler");
        messageList.add("Bodie Shane");
        messageList.add("Colm Laro");
        messageList.add("Ashton Brown");
        messageList.add("Braden Mabey");
        messageList.add("Tyler Jarrard");


        loadSpinner();
        setUpListeners();
    }

    /**
     * This method loads our spinner with messages that we can send.
     */
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

    /**
     * This method randomly changes the background color of our app.
     */
    private void changeBackgroundColor() {
        int redColor;
        int greenColor;
        int blueColor;

        redColor = (int) (Math.random() * 256);
        greenColor = (int) (Math.random() * 256);
        blueColor = (int) (Math.random() * 256);

        backgroundLayout.setBackgroundColor(Color.rgb(redColor, greenColor, blueColor));

    }

    /**
     * This is the method that actually sends the SMS message. We declared the phoneNo and message variables. We used a try-catch control statement to send the message and watches for failures.
     */
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
                phoneNumber.setText("8014626010");
            }
        });
        dadContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View buttonView) {
                phoneNumber.setText("16502530000");
            }
        });
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
    }
}
