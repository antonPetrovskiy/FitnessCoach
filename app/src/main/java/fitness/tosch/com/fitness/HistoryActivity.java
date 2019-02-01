package fitness.tosch.com.fitness;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fitness.tosch.com.fitness.client.ClientsActivity;
import fitness.tosch.com.fitness.sql.Client;
import fitness.tosch.com.fitness.sql.DatabaseHandler;

public class HistoryActivity extends AppCompatActivity {

    Button header;
    ListView list;
    ArrayList<String> historyList;

    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        init();
        refresh();
        buttons();
    }

    public void init(){
        header = findViewById(R.id.button);
        list = findViewById(R.id.list);
        header.setText("Журнал");
        Typeface face = Typeface.createFromAsset(getAssets(), "first.otf");
        header.setTypeface(face);

        historyList = new ArrayList<>();
        db = new DatabaseHandler(this);
    }

    public void buttons(){
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder ad;
                String message;
                String button1String;
                String button2String;
                message = "Удалить с журнала?";
                button1String = "да";
                button2String = "нет";
                ad = new AlertDialog.Builder(HistoryActivity.this);
                final int ii = i;
                ad.setMessage(message); // сообщение
                ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        db.deleteFromHistory(historyList.get(ii).substring(0,11), historyList.get(ii).substring(12));
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "удалено", Toast.LENGTH_LONG);
                        toast.show();
                        Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
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

    public void refresh(){

        List<String> cli = db.getHistory();
        for (String cn : cli) {
            if(cn!=null&&!cn.equals(""))
                historyList.add(cn);
            Collections.sort(historyList, Collections.<String>reverseOrder());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_single1, R.id.txt, historyList);
        list.setAdapter(adapter);


    }
}
