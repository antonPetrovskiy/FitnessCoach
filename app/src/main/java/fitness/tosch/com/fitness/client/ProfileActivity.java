package fitness.tosch.com.fitness.client;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import fitness.tosch.com.fitness.R;
import fitness.tosch.com.fitness.sql.DatabaseHandler;

public class ProfileActivity extends AppCompatActivity {

    public String photo;
    public String name;
    public String age;
    public String tall;
    public String weight;
    public String days;
    public String info;

    public ImageView photoView;
    public TextView nameView;
    public EditText infoView;
    public TextView infoText;
    public Button btn;
    public Button dlt;

    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;

    Button t1;
    Button t2;
    Button t3;
    Button t4;
    Button t5;
    Button t6;
    Button t7;

    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent i = getIntent();
        photo = i.getStringExtra("photo");
        name = i.getStringExtra("name");
        age = i.getStringExtra("age");
        tall = i.getStringExtra("tall");
        weight = i.getStringExtra("weight");
        days = i.getStringExtra("days");
        info = i.getStringExtra("info");

        init();
        buttons();
        fill();
    }

    public void init(){
        photoView = findViewById(R.id.imageProfile);
        nameView = findViewById(R.id.nameView);
        infoView = findViewById(R.id.info);
        infoView.setEnabled(false);
        infoText = findViewById(R.id.textView3);
        btn = findViewById(R.id.button9);
        dlt = findViewById(R.id.button10);

        photoView.setImageURI(Uri.parse(photo));
        nameView.setText(name);

        Typeface face = Typeface.createFromAsset(getAssets(), "first.otf");

        nameView.setTypeface(face);
        infoView.setTypeface(face);
        infoText.setTypeface(face);
        btn.setTypeface(face);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b1.setTypeface(face);
        b2.setTypeface(face);
        b3.setTypeface(face);
        b4.setTypeface(face);
        b5.setTypeface(face);
        b6.setTypeface(face);
        b7.setTypeface(face);

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);
        t7 = findViewById(R.id.t7);
        t1.setTypeface(face);
        t2.setTypeface(face);
        t3.setTypeface(face);
        t4.setTypeface(face);
        t5.setTypeface(face);
        t6.setTypeface(face);
        t7.setTypeface(face);
    }

    public void buttons(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddClientActivity.class);
                intent.putExtra("photo", photo);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                intent.putExtra("tall", tall);
                intent.putExtra("weight", weight);
                intent.putExtra("days", days);
                intent.putExtra("info", info);
                intent.putExtra("number", info);
                startActivity(intent);
                finish();
            }
        });

        dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad;
                String message;
                String button1String;
                String button2String;
                message = "Удалить клиента?";
                button1String = "да";
                button2String = "нет";

                ad = new AlertDialog.Builder(ProfileActivity.this);

                ad.setMessage(message); // сообщение
                ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        db.deleteClient(db.findClient(name));
                        Intent intent = new Intent(getApplicationContext(), ClientsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Клиент удален", Toast.LENGTH_LONG);
                        toast.show();
                        startActivity(intent);
                        finish();
                    }
                });
                ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {

                    }
                });
                ad.show();
            }
        });
    }

    public void fill(){
        StringBuilder st = new StringBuilder("");
        if(!age.equals(""))
            st.append("Возраст - " + age + ". ");
        if(!tall.equals(""))
            st.append("Рост - " + tall + ". ");
        if(!weight.equals(""))
            st.append("Вес - " + weight + ". ");
        if(!info.equals(""))
            st.append("Информация - " + info + ". ");

        st.append(days);
        infoView.setText(st);

        if(days.contains("ПН")){
            String s = days.substring(days.indexOf("ПН")+3, days.indexOf("ПН")+8);
            b1.setBackground(getResources().getDrawable(R.drawable.day_used));
            t1.setBackground(getResources().getDrawable(R.drawable.day_used));
            t1.setText(s);
        }

        if(days.contains("ВТ")){
            String s = days.substring(days.indexOf("ВТ")+3, days.indexOf("ВТ")+8);
            b2.setBackground(getResources().getDrawable(R.drawable.day_used));
            t2.setBackground(getResources().getDrawable(R.drawable.day_used));
            t2.setText(s);
        }

        if(days.contains("СР")){
            String s = days.substring(days.indexOf("СР")+3, days.indexOf("СР")+8);
            b3.setBackground(getResources().getDrawable(R.drawable.day_used));
            t3.setBackground(getResources().getDrawable(R.drawable.day_used));
            t3.setText(s);
        }

        if(days.contains("ЧТ")){
            String s = days.substring(days.indexOf("ЧТ")+3, days.indexOf("ЧТ")+8);
            b4.setBackground(getResources().getDrawable(R.drawable.day_used));
            t4.setBackground(getResources().getDrawable(R.drawable.day_used));
            t4.setText(s);
        }

        if(days.contains("ПТ")){
            String s = days.substring(days.indexOf("ПТ")+3, days.indexOf("ПТ")+8);
            b5.setBackground(getResources().getDrawable(R.drawable.day_used));
            t5.setBackground(getResources().getDrawable(R.drawable.day_used));
            t5.setText(s);
        }

        if(days.contains("СБ")){
            String s = days.substring(days.indexOf("СБ")+3, days.indexOf("СБ")+8);
            b6.setBackground(getResources().getDrawable(R.drawable.day_used));
            t6.setBackground(getResources().getDrawable(R.drawable.day_used));
            t6.setText(s);
        }

        if(days.contains("ВС")){
            String s = days.substring(days.indexOf("ВС")+3, days.indexOf("ВС")+8);
            b7.setBackground(getResources().getDrawable(R.drawable.day_used));
            t7.setBackground(getResources().getDrawable(R.drawable.day_used));
            t7.setText(s);
        }
    }
}
