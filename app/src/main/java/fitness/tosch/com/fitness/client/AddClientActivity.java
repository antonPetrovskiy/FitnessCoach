package fitness.tosch.com.fitness.client;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import fitness.tosch.com.fitness.R;
import fitness.tosch.com.fitness.sql.Client;
import fitness.tosch.com.fitness.sql.DatabaseHandler;

public class AddClientActivity extends AppCompatActivity {

    EditText name;
    EditText age;
    EditText tall;
    EditText weight;
    EditText info;
    Button footer;
    TextView grafic;
    TextView inform;
    Button save;

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

    boolean d1;
    boolean d2;
    boolean d3;
    boolean d4;
    boolean d5;
    boolean d6;
    boolean d7;

    Button temp;
    public static final int REQ_CODE_PICK_PHOTO = 0;
    ImageView portrait;
    String uri;
    String oldName;
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        init();
        if(db.getClientCount()!=0 && !name.getText().toString().equals("") && db.findClient(name.getText().toString())!=null){

            oldName = getIntent().getStringExtra("name");
        }

        buttons();
    }

    public void init(){
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        tall = findViewById(R.id.tall);
        weight = findViewById(R.id.weight);
        info = findViewById(R.id.info);
        footer = findViewById(R.id.button);
        portrait = findViewById(R.id.imageProfile);
        save = findViewById(R.id.button9);
        uri = "";
        Typeface face = Typeface.createFromAsset(getAssets(), "first.otf");
        name.setTypeface(face);
        age.setTypeface(face);
        tall.setTypeface(face);
        weight.setTypeface(face);
        info.setTypeface(face);
        footer.setTypeface(face);
        grafic = findViewById(R.id.textView2);
        inform = findViewById(R.id.textView3);
        grafic.setTypeface(face);
        inform.setTypeface(face);
        save.setTypeface(face);

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

        name.setText(getIntent().getStringExtra("name"));
        age.setText(getIntent().getStringExtra("age"));
        tall.setText(getIntent().getStringExtra("tall"));
        weight.setText(getIntent().getStringExtra("weight"));
        info.setText(getIntent().getStringExtra("info"));

        if(getIntent().getStringExtra("photo")!=null && !getIntent().getStringExtra("photo").equals("")) {
            portrait.setImageURI(Uri.parse(getIntent().getStringExtra("photo")));
            uri = getIntent().getStringExtra("photo");
        }

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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String days = "";
                if(d1)
                    days = days + "ПН-" + t1.getText().toString() + " ";
                if(d2)
                    days = days + "ВТ-" + t2.getText().toString() + " ";
                if(d3)
                    days = days + "СР-" + t3.getText().toString() + " ";
                if(d4)
                    days = days + "ЧТ-" + t4.getText().toString() + " ";
                if(d5)
                    days = days + "ПТ-" + t5.getText().toString() + " ";
                if(d6)
                    days = days + "СБ-" + t6.getText().toString() + " ";
                if(d7)
                    days = days + "ВС-" + t7.getText().toString() + " ";
                if(!name.getText().toString().equals("") && !age.getText().toString().equals("")) {
                    if(uri == null){
                        uri = "";
                    }
                    if(oldName!=null){
                        db.deleteClient(db.findClient(oldName));
                    }

                    db.addClient(new Client(uri, name.getText().toString(), age.getText().toString(), tall.getText().toString(), weight.getText().toString(), days, info.getText().toString()));

                    Intent intent = new Intent(getApplicationContext(), ClientsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Клиент добавлен", Toast.LENGTH_LONG);
                    toast.show();
                    finish();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Введите данные клиента", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!d1){
                    b1.setBackground(getResources().getDrawable(R.drawable.day_used));
                    t1.setBackground(getResources().getDrawable(R.drawable.day_used));
                    d1 = true;
                    setTime(t1);
                }else{
                    b1.setBackground(getResources().getDrawable(R.drawable.day));
                    t1.setBackground(getResources().getDrawable(R.drawable.day));
                    d1 = false;
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!d2){
                    b2.setBackground(getResources().getDrawable(R.drawable.day_used));
                    t2.setBackground(getResources().getDrawable(R.drawable.day_used));
                    d2 = true;
                    setTime(t2);
                }else{
                    b2.setBackground(getResources().getDrawable(R.drawable.day));
                    t2.setBackground(getResources().getDrawable(R.drawable.day));
                    d2 = false;
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!d3){
                    b3.setBackground(getResources().getDrawable(R.drawable.day_used));
                    t3.setBackground(getResources().getDrawable(R.drawable.day_used));
                    d3 = true;
                    setTime(t3);
                }else{
                    b3.setBackground(getResources().getDrawable(R.drawable.day));
                    t3.setBackground(getResources().getDrawable(R.drawable.day));
                    d3 = false;
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!d4){
                    b4.setBackground(getResources().getDrawable(R.drawable.day_used));
                    t4.setBackground(getResources().getDrawable(R.drawable.day_used));
                    d4 = true;
                    setTime(t4);
                }else{
                    b4.setBackground(getResources().getDrawable(R.drawable.day));
                    t4.setBackground(getResources().getDrawable(R.drawable.day));
                    d4 = false;
                }
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!d5){
                    b5.setBackground(getResources().getDrawable(R.drawable.day_used));
                    t5.setBackground(getResources().getDrawable(R.drawable.day_used));
                    d5 = true;
                    setTime(t5);
                }else{
                    b5.setBackground(getResources().getDrawable(R.drawable.day));
                    t5.setBackground(getResources().getDrawable(R.drawable.day));
                    d5 = false;
                }
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!d6){
                    b6.setBackground(getResources().getDrawable(R.drawable.day_used));
                    t6.setBackground(getResources().getDrawable(R.drawable.day_used));
                    d6 = true;
                    setTime(t6);
                }else{
                    b6.setBackground(getResources().getDrawable(R.drawable.day));
                    t6.setBackground(getResources().getDrawable(R.drawable.day));
                    d6 = false;
                }
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!d7){
                    b7.setBackground(getResources().getDrawable(R.drawable.day_used));
                    t7.setBackground(getResources().getDrawable(R.drawable.day_used));
                    d7 = true;
                    setTime(t7);
                }else{
                    b7.setBackground(getResources().getDrawable(R.drawable.day));
                    t7.setBackground(getResources().getDrawable(R.drawable.day));
                    d7 = false;
                }
            }
        });


        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(d1){
                    setTime(t1);
                }
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(d2){
                    setTime(t2);
                }
            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(d3){
                    setTime(t3);
                }
            }
        });

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(d4){
                    setTime(t4);
                }
            }
        });

        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(d5){
                    setTime(t5);
                }
            }
        });

        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(d6){
                    setTime(t6);
                }
            }
        });

        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(d7){
                    setTime(t7);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_PICK_PHOTO && resultCode == Activity.RESULT_OK){
            if ((data != null) && (data.getData() != null)){
                Uri u = data.getData();
                uri = u.toString();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), u);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Bitmap resized = Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*0.05), (int)(bitmap.getHeight()*0.05), true);

                portrait.setImageBitmap(bitmap);


                    //ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                    //String path = MediaStore.Images.Media.insertImage(getContentResolver(), resized, "Title", null);
                    //u = Uri.parse(path);
                    //uri = u.toString();
            }
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder ad;

        String message;
        String button1String;
        String button2String;
            message = "Вернутся назад?";
        button1String = "да";
        button2String = "нет";

        ad = new AlertDialog.Builder(AddClientActivity.this);

        ad.setMessage(message); // сообщение
        ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                AddClientActivity.super.onBackPressed();
            }
        });
        ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

            }
        });
        ad.show();
    }

    public void setTime(Button b){
        AlertDialog.Builder alert = new AlertDialog.Builder(AddClientActivity.this);

        final EditText input1 = new EditText(AddClientActivity.this);
        input1.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);

        alert.setMessage("Введите время");
        temp = b;

        alert.setView(input1);

        alert.setPositiveButton("ок", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if(!input1.getText().toString().equals("")){
                    if(input1.getText().toString().length()==5){
                        temp.setText(input1.getText().toString());
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "неправельный формат времени", Toast.LENGTH_LONG);
                        toast.show();
                    }

                }
            }
        });
        alert.show();
    }
}
