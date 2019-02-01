package fitness.tosch.com.fitness;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class ScheduleActivity extends AppCompatActivity {

    Button b;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        init();
        buttons();
        setDay();

    }

    public void init(){
        b = findViewById(R.id.button);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        Typeface face = Typeface.createFromAsset(getAssets(), "first.otf");
        b.setTypeface(face);
        b1.setTypeface(face);
        b2.setTypeface(face);
        b3.setTypeface(face);
        b4.setTypeface(face);
        b5.setTypeface(face);
        b6.setTypeface(face);
        b7.setTypeface(face);
    }

    public void buttons(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DayscheduleActivity.class);
                intent.putExtra("day", "ПН");
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DayscheduleActivity.class);
                intent.putExtra("day", "ВТ");
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DayscheduleActivity.class);
                intent.putExtra("day", "СР");
                startActivity(intent);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DayscheduleActivity.class);
                intent.putExtra("day", "ЧТ");
                startActivity(intent);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DayscheduleActivity.class);
                intent.putExtra("day", "ПТ");
                startActivity(intent);
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DayscheduleActivity.class);
                intent.putExtra("day", "СБ");
                startActivity(intent);
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DayscheduleActivity.class);
                intent.putExtra("day", "ВС");
                startActivity(intent);
            }
        });
    }

    public void setDay(){
        Calendar c = Calendar.getInstance();
        //c.setTime(yourDate);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek){
            case 1:
                b7.setBackgroundResource(R.drawable.day_button_current);
                break;
            case 2:
                b1.setBackgroundResource(R.drawable.day_button_current);
                break;
            case 3:
                b2.setBackgroundResource(R.drawable.day_button_current);
                break;
            case 4:
                b3.setBackgroundResource(R.drawable.day_button_current);
                break;
            case 5:
                b4.setBackgroundResource(R.drawable.day_button_current);
                break;
            case 6:
                b5.setBackgroundResource(R.drawable.day_button_current);
                break;
            case 7:
                b6.setBackgroundResource(R.drawable.day_button_current);
                break;
        }
    }

}
