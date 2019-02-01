package fitness.tosch.com.fitness.client;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fitness.tosch.com.fitness.CustomList;
import fitness.tosch.com.fitness.R;
import fitness.tosch.com.fitness.sql.Client;
import fitness.tosch.com.fitness.sql.DatabaseHandler;

public class ClientsActivity extends AppCompatActivity {

    Button header;
    Button add;
    ArrayList<String> clientsList;
    ArrayList<String> photosList;
    ListView list;


    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        db = new DatabaseHandler(this);

        init();
        buttons();
        refresh();
    }

    public void init(){
        header = findViewById(R.id.button);
        add = findViewById(R.id.add);
        list = findViewById(R.id.list);
        clientsList = new ArrayList<>();
        photosList = new ArrayList<>();

        //LinearLayoutManager llm = new LinearLayoutManager(this);
        //list.setLayoutManager(llm);


        Typeface face = Typeface.createFromAsset(getAssets(), "first.otf");
        header.setTypeface(face);
        add.setTypeface(face);

    }

    public void buttons(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddClientActivity.class);
                startActivity(intent);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);




                intent.putExtra("photo",db.findClient(clientsList.get(i)).get_photo());
                intent.putExtra("name",db.findClient(clientsList.get(i)).get_name());
                intent.putExtra("age",db.findClient(clientsList.get(i)).get_age());
                intent.putExtra("tall",db.findClient(clientsList.get(i)).get_tall());
                intent.putExtra("weight",db.findClient(clientsList.get(i)).get_weight());
                intent.putExtra("days",db.findClient(clientsList.get(i)).get_days());
                intent.putExtra("info",db.findClient(clientsList.get(i)).get_info());
                startActivity(intent);
            }
        });
    }

    public void refresh(){

        List<Client> cli = db.getAllClient();
        for (Client cn : cli) {
            String log = cn.get_name()+"   "+cn.get_id();
            if(log!=null)
                clientsList.add(cn.get_name());
            String log1 = cn.get_photo();
            if(log1!=null)
                photosList.add(log1);
            //persons.add(new Person(log, log1));

        }


        CustomList adapter = new CustomList(ClientsActivity.this, clientsList, photosList);
        list=findViewById(R.id.list);
        list.setAdapter(adapter);
    }


}
