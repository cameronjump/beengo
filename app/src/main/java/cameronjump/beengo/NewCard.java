package cameronjump.beengo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.camer.beengocheet.R;


public class NewCard extends AppCompatActivity {

    EditText[][] input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);

        input = new EditText[5][5];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                if (i == 2 & j == 2) continue;
                String viewName = "n" + i + j;
                int resID = getResources().getIdentifier(viewName, "id", getPackageName());
                input[i][j] = (EditText) findViewById(resID);
            }
        }

        final Button save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(createCard()) {
                    Toast.makeText(NewCard.this, "nice dude", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    private boolean createCard() {
        int[][] cardNumbers = new int[5][5];
        String name;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                if (i == 2 & j == 2) continue;
                try {
                    cardNumbers[i][j] = Integer.parseInt(input[i][j].getText().toString());
                } catch (Exception e) {
                    Toast.makeText(NewCard.this, "what idiot doesn't fill the numbers?", Toast.LENGTH_LONG).show();
                    return false;
                }
            }
        }
        try {
            name = ((EditText) findViewById(R.id.nameInput)).getText().toString();
        } catch (Exception e) {
            Toast.makeText(NewCard.this, "forgetting a name is just plain dumb", Toast.LENGTH_LONG).show();
            return false;
        }

        if (MainActivity.cardNames.contains(name)) {
            Toast.makeText(NewCard.this, "let me just type a name twice har har har", Toast.LENGTH_LONG).show();
            return false;
        }
        Card card = new Card(cardNumbers, name);
        MainActivity.cardNames.add(name);
        MainActivity.cards.put(name, card);
        return true;

    }


}
