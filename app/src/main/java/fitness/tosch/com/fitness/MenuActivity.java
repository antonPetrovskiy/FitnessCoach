package fitness.tosch.com.fitness;

import android.app.Activity;
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

import fitness.tosch.com.fitness.client.ClientsActivity;
import fitness.tosch.com.fitness.sql.DatabaseHandler;

public class MenuActivity extends AppCompatActivity {

    public static final int REQ_CODE_PICK_PHOTO = 0;
    ImageView portrait;
    TextView nameView;
    Button schedule;
    Button clients;
    Button history;
    DatabaseHandler db;
    String name;
    String photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        init();
        buttons();

    }


    public void init(){
        portrait = findViewById(R.id.imageProfile);
        nameView = findViewById(R.id.nameView);
        schedule = findViewById(R.id.button);
        clients = findViewById(R.id.button2);
        history = findViewById(R.id.button3);
        Typeface face = Typeface.createFromAsset(getAssets(), "first.otf");
        nameView.setTypeface(face);
        schedule.setTypeface(face);
        clients.setTypeface(face);
        history.setTypeface(face);
        db = new DatabaseHandler(this);
        name = db.getName();
        photo = db.getPhoto();
        portrait.setImageURI(Uri.parse(photo));
        nameView.setText(name);
    }

    public void buttons(){
        portrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Выберите фото"), REQ_CODE_PICK_PHOTO);
            }
        });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
                startActivity(intent);
            }
        });

        clients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClientsActivity.class);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });

        nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MenuActivity.this);
                alert.setMessage("Введите имя");

                final EditText input = new EditText(MenuActivity.this);
                alert.setView(input);

                alert.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        if(!value.equals(""))
                            nameView.setText(value);
                        name = value;
                        db.editProfile(name,photo);
                    }
                });

                alert.setNegativeButton("Назад", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_PICK_PHOTO && resultCode == Activity.RESULT_OK){
            if ((data != null) && (data.getData() != null)){
                Uri u = data.getData();
                portrait.setImageURI(u);
                photo = u.toString();
                db.editProfile(name,photo);
            }
        }
    }
}
