package fitness.tosch.com.fitness.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper implements IDatabaseHandler {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "clientsManager";

    private static final String TABLE_CLIENTS = "clients";
    private static final String KEY_ID = "id";
    private static final String KEY_PHOTO = "photo";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
    private static final String KEY_TALL = "tall";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_DAYS = "days";
    private static final String KEY_INFO = "info";

    private static final String TABLE_HISTORY = "history";
    private static final String KEY_ID_HISTORY = "id";
    private static final String KEY_DATE_HISTORY = "date";
    private static final String KEY_NAME_HISTORY = "name";

    private static final String TABLE_PROFILE= "profile";
    private static final String KEY_ID_PROFILE = "id";
    private static final String KEY_NAME_PROFILE = "name";
    private static final String KEY_PHOTO_PROFILE = "photo";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CLIENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"+ KEY_PHOTO + " TEXT," + KEY_NAME + " TEXT,"
                + KEY_AGE + " TEXT," + KEY_TALL + " TEXT," + KEY_WEIGHT + " TEXT," + KEY_DAYS + " TEXT," + KEY_INFO + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_HISTORY_TABLE = "CREATE TABLE " + TABLE_HISTORY + "("
                + KEY_ID_HISTORY + " INTEGER PRIMARY KEY,"+ KEY_DATE_HISTORY + " TEXT," + KEY_NAME_HISTORY + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_HISTORY_TABLE);

        String CREATE_PROFILE_TABLE = "CREATE TABLE " + TABLE_PROFILE + "("
                + KEY_ID_PROFILE + " INTEGER PRIMARY KEY,"+ KEY_NAME_PROFILE + " TEXT," + KEY_PHOTO_PROFILE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_PROFILE_TABLE);
        String ROW1 = "INSERT INTO " + TABLE_PROFILE + " Values (1,'Имя','фото');";
        sqLiteDatabase.execSQL(ROW1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);

        onCreate(sqLiteDatabase);
    }

    @Override
    public void addClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PHOTO, client.get_photo());
        values.put(KEY_NAME, client.get_name());
        values.put(KEY_AGE, client.get_age());
        values.put(KEY_TALL, client.get_tall());
        values.put(KEY_WEIGHT, client.get_weight());
        values.put(KEY_DAYS, client.get_days());
        values.put(KEY_INFO, client.get_info());

        db.insert(TABLE_CLIENTS, null, values);
        db.close();
    }

    @Override
    public Client getClient(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CLIENTS, new String[] { KEY_ID, KEY_PHOTO,
                        KEY_NAME, KEY_AGE, KEY_TALL, KEY_WEIGHT, KEY_DAYS, KEY_INFO, }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }


        Client client = new Client(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));

        db.close();
        cursor.close();
        return client;
    }

    @Override
    public List<Client> getAllClient() {
        List<Client> contactList = new ArrayList<Client>();
        String selectQuery = "SELECT  * FROM " + TABLE_CLIENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Client contact = new Client();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.set_photo(cursor.getString(1));
                contact.set_name(cursor.getString(2));
                contact.set_age(cursor.getString(3));
                contact.set_tall(cursor.getString(4));
                contact.set_weight(cursor.getString(5));
                contact.set_days(cursor.getString(6));
                contact.set_info(cursor.getString(7));

                contactList.add(contact);
            } while (cursor.moveToNext());

        }
        db.close();
        cursor.close();

        return contactList;
    }

    @Override
    public int getClientCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CLIENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        return cursor.getCount();
    }

    @Override
    public int updateClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PHOTO, client.get_photo());
        values.put(KEY_NAME, client.get_name());
        values.put(KEY_AGE, client.get_age());
        values.put(KEY_TALL, client.get_tall());
        values.put(KEY_WEIGHT, client.get_weight());
        values.put(KEY_DAYS, client.get_days());
        values.put(KEY_INFO, client.get_info());

        return db.update(TABLE_CLIENTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(client.get_id()) });
    }

    @Override
    public void deleteClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLIENTS, KEY_ID + " = ?", new String[] { String.valueOf(client.get_id()) });
        db.close();
    }

    @Override
    public Client findClient(String s) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CLIENTS, new String[] { KEY_ID, KEY_PHOTO,
                        KEY_NAME, KEY_AGE, KEY_TALL, KEY_WEIGHT, KEY_DAYS, KEY_INFO, }, KEY_NAME + "=?",
                new String[] { String.valueOf(s) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Client client = new Client(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));

        db.close();
        cursor.close();
        return client;
    }

    @Override
    public List<String> getHistory() {
        List<String> historyList = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_HISTORY;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                historyList.add(cursor.getString(1) + " " + cursor.getString(2));
            } while (cursor.moveToNext());

        }
        db.close();
        cursor.close();
        return historyList;
    }

    @Override
    public void addToHistory(String date, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE_HISTORY, date);
        values.put(KEY_NAME_HISTORY, name);
        db.insert(TABLE_HISTORY, null, values);
        db.close();
    }

    @Override
    public void deleteFromHistory(String s1, String s2) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_HISTORY, KEY_DATE_HISTORY + " = ? AND "+ KEY_NAME_HISTORY + "= ?",
                new String[]{s1, s2});
        db.close();
    }

    @Override
    public void editProfile(String s, String s1) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(KEY_NAME_PROFILE, s);
        values.put(KEY_PHOTO_PROFILE, s1);
        db.update(TABLE_PROFILE, values, KEY_ID + " = ?",
                new String[] { String.valueOf("1") });
        db.close();
    }

    @Override
    public void setProfile(String s, String s1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME_PROFILE, s);
        values.put(KEY_PHOTO_PROFILE, s1);
        db.insert(TABLE_PROFILE, null, values);
        db.close();
    }

    @Override
    public String getName() {
        String name = "";
        String selectQuery = "SELECT  * FROM " + TABLE_PROFILE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        name = cursor.getString(1);


        db.close();
        cursor.close();

        return name;
    }

    @Override
    public String getPhoto() {
        String photo = "";
        String selectQuery = "SELECT  * FROM " + TABLE_PROFILE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        photo = cursor.getString(2);



        db.close();
        cursor.close();

        return photo;
    }
}
