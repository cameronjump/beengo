package cameronjump.beengo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.camer.beengocheet.R;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    static ArrayList<String> cardNames;
    static HashMap<String, Card> cards;

    String selectedCard;
    int selectedCardPosition;

    Spinner spinner;
    static ArrayAdapter<String> dataAdapter;

    EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this, "hey dude", Toast.LENGTH_LONG).show();

        final Button button = (Button)findViewById(R.id.viewCard);

        cards = new HashMap<String, Card>();

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(selectedCard == null) {
                    Toast.makeText(MainActivity.this, "No card selected", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent i = new Intent(getBaseContext(), BingoActivity.class);
                i.putExtra("Card", selectedCard);
                startActivityForResult(i, 1);
            }
        });

        final Button newCard = (Button)findViewById(R.id.newCard);

        newCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), NewCard.class);
                startActivityForResult(i, 1);
            }
        });

        final Button delete = (Button)findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(spinner.getSelectedItem() == null) {
                    Toast.makeText(MainActivity.this, "No card selected", Toast.LENGTH_LONG).show();
                    return;
                }
                cards.remove(selectedCard);
                cardNames.remove(selectedCardPosition);
                dataAdapter.remove(selectedCard);
                selectedCard = (String) spinner.getSelectedItem();
                selectedCardPosition = spinner.getFirstVisiblePosition();
            }
        });

        final Button reset = (Button)findViewById(R.id.reset);

        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i=0; i<cardNames.size(); i++) {
                    cards.get(cardNames.get(i)).resetCard();
                }
                Toast.makeText(MainActivity.this, "Cards have been reset", Toast.LENGTH_LONG).show();
            }
        });

        number = (EditText) findViewById(R.id.number);

        final Button play = (Button)findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(TextUtils.isEmpty(number.getText().toString())) {
                    Toast.makeText(MainActivity.this, "You must enter a number", Toast.LENGTH_LONG).show();
                    return;
                }
                int tempNumber = Integer.parseInt(number.getText().toString());
                for(int i=0; i<cardNames.size(); i++) {
                    String tempName = cardNames.get(i);
                    Card tempCard = cards.get(tempName);
                    tempCard.checkCard(tempNumber);
                    cards.put(tempName, tempCard);
                    if(tempCard.checkWinner()) {
                        Toast.makeText(MainActivity.this, tempName + " is a winner!!!", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });


        spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                selectedCard = parent.getItemAtPosition(position).toString();
                selectedCardPosition = position;

                // Showing selected spinner item
               // Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        cardNames = new ArrayList<String>();

        // Creating adapter for spinner
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cardNames);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    protected void onStart()
    {
        super.onStart();
        dataAdapter.notifyDataSetChanged();
    }

}
