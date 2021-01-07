package com.example.ysh.recyclerview;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.net.Network;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.StringReader;
import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Dictionary> mArrayList;
    private CustomAdapter mAdapter;

    NetworkImageView symbolView;
    TextView temperatureView;

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperatureView = (TextView) findViewById(R.id.temp);
        symbolView = (NetworkImageView) findViewById(R.id.symbols);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        queue = Volley.newRequestQueue(this);

        StringRequest currentRequest = new StringRequest(Request.Method.POST, "http://api.openweathermap.org/data/2.5/weather?q=seoul&mode=xml&units=metric&appid=135073d7e063cc571b6adb9b22190876", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseXMLCurrent(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(currentRequest);


        mArrayList = new ArrayList<>();

        //mAdapter = new CustomAdapter(mArrayList);
        mAdapter = new CustomAdapter( this, mArrayList);

        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);


        Button buttonInsert = (Button)findViewById(R.id.button_main_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {


            // 1. 화면 아래쪽에 있는 데이터 추가 버튼을 클릭하면
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.edit_box, null, false);
                builder.setView(view);


                final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit);
                final EditText editTextID = (EditText) view.findViewById(R.id.edittext_id);
                final EditText editTextCity = (EditText) view.findViewById(R.id.edittext_city);
                final EditText editTextContents = (EditText) view.findViewById(R.id.edittext_contents);
                final EditText editTextbody = (EditText) view.findViewById(R.id.edittext_body);

                ButtonSubmit.setText("추가");

                final AlertDialog dialog = builder.create();

                ButtonSubmit.setOnClickListener(new View.OnClickListener() {


                    public void onClick(View v) {


                        String strID = editTextID.getText().toString();
                        String strCity = editTextCity.getText().toString();
                        String strContents = editTextContents.getText().toString();
                        String strBody = editTextbody.getText().toString();

                        Dictionary dict = new Dictionary(strID, strCity, strContents, strBody);
                        mArrayList.add(0, dict);

                        mAdapter.notifyItemInserted(0);
                        //mAdapter.notifyDataSetChanged();

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    private void parseXMLCurrent(String response) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(response)));
            doc.getDocumentElement().normalize();

            Element temElement = (Element)(doc.getElementsByTagName("temperature").item(0));
            String temperature = temElement.getAttribute("value");

            temperatureView.setText(temperature);

            Element weatherElement=(Element)(doc.getElementsByTagName("weather").item(0));
            String symbol = weatherElement.getAttribute("icon");

            ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
               @Override
                public Bitmap getBitmap(String url) {
                   return null;
                }

               @Override
                public void putBitmap(String url, Bitmap bitmap) {

               }
            });
            symbolView.setImageUrl("http://openweathermap.org/img/w/" + symbol + ".png", imageLoader);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}