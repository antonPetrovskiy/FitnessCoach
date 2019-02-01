package fitness.tosch.com.fitness;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import fitness.tosch.com.fitness.client.ClientsActivity;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final ArrayList<String> names;
    private final ArrayList<String> imageId;
    public CustomList(Activity context,
                      ArrayList<String> names, ArrayList<String> imageId) {
        super(context, R.layout.list_single, names);
        this.context = context;
        this.names = names;
        this.imageId = imageId;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {


        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP,35);
        txtTitle.setTextColor(Color.parseColor("#3AA6D0"));
        txtTitle.setTypeface(Typeface.createFromAsset(context.getAssets(), "first.otf"));



        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(names.get(position));


        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.context.getContentResolver(), Uri.parse(imageId.get(position)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(bitmap!=null) {
            double w = bitmap.getWidth();
            double h = bitmap.getHeight();
            double u = w / h;

            Bitmap resized = Bitmap.createScaledBitmap(bitmap, (int) (90 * u), (int) (90), true);


            //ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            //String path = MediaStore.Images.Media.insertImage(getContentResolver(), resized, "Title", null);
            //u = Uri.parse(path);
            //uri = u.toString();

            //imageView.setImageURI(Uri.parse(imageId.get(position)));
            imageView.setImageBitmap(resized);
            //imageView.setImageResource(R.drawable.photo);
        }

        return rowView;
    }
}