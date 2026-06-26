package com.vl.appholidatyrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vl.appholidatyrecyclerview.model.ListHolidaysInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAdapter.ListItemClickListener {

    public static final String TAG = "VL_MainActivity";
    public static final int SUCCESS = 1;
    public static final int ERROR = 2;
    private Toast toast;
    private ListHolidaysInfo listHolidaysInfo;

    private static String urlString = "https://ya.ru";
    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == SUCCESS) {
                String responseData = (String) msg.obj;
                parseData(responseData);
            } else {
                if(msg.what == ERROR) {
                    Log.e(TAG, "handleMessage: error on data getting");
                }
            }
        }
    };

    @Override
    public void onListItemClick(int clickedItemIndex) {
        CharSequence text = listHolidaysInfo.listHolidaysInfo[clickedItemIndex].getHoliday_name();
        int duration = Toast.LENGTH_SHORT;
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, text, duration);
        toast.show();
    }

    void parseData(String output) {
        try {
            JSONObject outputJSON = new JSONObject(output);
            JSONArray array = outputJSON.getJSONArray("arrHolidays"); // vl 4 text
            // VL orig: JSONObject responseJSON = outputJSON.getJSONObject("responce");
            // VL orig: JSONArray array = responseJSON.getJSONArray("holidays");
            int length = array.length();
            listHolidaysInfo = new ListHolidaysInfo(length);
            // VL orig: ArrayList<String> namesHoliday = new ArrayList<String>();
            for (int i = 0; i < length; i++) {
                JSONObject obj = array.getJSONObject(i);

                String name = obj.getString("name");
                // VL orig:     String desc = obj.getString("description");
                // VL orig:     JSONObject obj_data = obj.getJSONObject("date");
                // VL orig:     String data_iso = obj_data.getString("iso");
                String data_iso = obj.getString("date");

                // VL orig: namesHoliday.add(name);
                listHolidaysInfo.addHoliday(name, data_iso, "desc", i);
            }

            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new MyAdapter(listHolidaysInfo, length, this));

        } catch (JSONException e) {
           e.printStackTrace();
           // throw new RuntimeException(e);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "hello vl", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate: add holidaqInfo");
        DataFetcherThread thread = new DataFetcherThread(mHandler, urlString);
        thread.start();
    }
} // public class MainActivity extends AppCompatActivity {