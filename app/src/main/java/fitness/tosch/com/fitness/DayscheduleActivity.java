package fitness.tosch.com.fitness;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import fitness.tosch.com.fitness.client.AddClientActivity;
import fitness.tosch.com.fitness.client.ClientsActivity;
import fitness.tosch.com.fitness.sql.Client;
import fitness.tosch.com.fitness.sql.DatabaseHandler;

public class DayscheduleActivity extends AppCompatActivity {

    String day;
    Button header;
    Button add;
    ListView list;
    ArrayList<String> clientsList;
    ArrayList<String> imgList;

    ArrayList<String> historyList;

    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dayschedule);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        day = getIntent().getStringExtra("day");

        init();
        buttons();
        refresh();
    }

    public void init(){
        header = findViewById(R.id.button);
        add = findViewById(R.id.add);
        list = findViewById(R.id.list);
        header.setText(day);
        Typeface face = Typeface.createFromAsset(getAssets(), "first.otf");
        header.setTypeface(face);
        add.setTypeface(face);

        clientsList = new ArrayList<>();
        historyList = new ArrayList<>();
        imgList = new ArrayList<>();
        db = new DatabaseHandler(this);
    }

    public void buttons(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");
                String currentDateandTime = sdf.format(new Date());
                for(String s : historyList){
                    db.addToHistory(s.substring(0,11),s.substring(12));
                }
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Тренировки сохранены в журнал", Toast.LENGTH_LONG);
                toast.show();

            }
        });
    }

    public void refresh(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");
        String currentDateandTime = sdf.format(new Date());

        List<Client> cli = db.getAllClient();
        for (Client cn : cli) {
            String s = cn.get_days();
            if(s!=null&&!s.equals("")&&s.contains(day))
                clientsList.add(s.substring(s.indexOf(day) + 3, s.indexOf(day) + 8) + " " + cn.get_name());
            Collections.sort(clientsList);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_single1, R.id.txt, clientsList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder ad;
                String message;
                String button1String;
                String button2String;
                message = "Отметьте тренировку";
                button1String = "была";
                button2String = "небыло";
                ad = new AlertDialog.Builder(DayscheduleActivity.this);
                final View v = view;
                final int ii = i;
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");
                final String currentDateandTime = sdf.format(new Date());
                ad.setMessage(message); // сообщение
                ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        v.setBackgroundColor(Color.parseColor("#5539E639"));
                        historyList.add(currentDateandTime + " " + clientsList.get(ii).substring(0,6) + "\n" + clientsList.get(ii).substring(6));
                    }
                });
                ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        v.setBackgroundColor(Color.parseColor("#55FF4040"));
                        historyList.remove(historyList.indexOf(currentDateandTime + " " + clientsList.get(ii)));
                    }
                });
                ad.show();

            }
        });
    }





}
