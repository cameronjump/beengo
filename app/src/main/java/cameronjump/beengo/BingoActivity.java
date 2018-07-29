package cameronjump.beengo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.camer.beengocheet.R;


public class BingoActivity extends AppCompatActivity {

    Bundle extras;
    Card card;
    TextView[][] textViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingo);

        extras = getIntent().getExtras();
        String selectedCard = (String) extras.get("Card");
        card = MainActivity.cards.get(selectedCard);

        TextView name = (TextView) findViewById(R.id.cardName);
        name.setText(card.getName());
        textViews = new TextView[5][5];
        for(int i=0; i<textViews.length; i++) {
            for(int j=0; j<textViews.length; j++) {
                String viewName = "c" + i + j;
                int resID = getResources().getIdentifier(viewName, "id", getPackageName());
                textViews[i][j] = (TextView)findViewById(resID);
                textViews[i][j].setText(""+card.getValue()[i][j]);
                boolean state = card.getState()[i][j];
                if(state) textViews[i][j].setBackgroundColor(R.color.colorAccent);
            }
        }
        textViews[2][2].setText("Free");
    }
}
