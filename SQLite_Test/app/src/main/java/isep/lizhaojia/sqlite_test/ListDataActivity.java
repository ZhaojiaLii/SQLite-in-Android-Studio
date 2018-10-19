package isep.lizhaojia.sqlite_test;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity extends Activity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    private ListView mListview;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        mListview = (ListView)findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();

    }

    private void populateListView() {

        Log.d(TAG,"populateListView: Display data in the ListView");

        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
        }
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        mListview.setAdapter(adapter);

        //set onItemClickListener to the listView
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = mListview.getItemAtPosition(position).toString();
                Log.d(TAG,"onItemClick: you have clicked on" + name);

                Cursor data = mDatabaseHelper.getItemID(name);
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Log.d(TAG, "onItenClick: the ID is "+ itemID);
                    Intent editScreenintent = new Intent(ListDataActivity.this,EditDataActivity.class);
                    editScreenintent.putExtra("id",itemID);
                    editScreenintent.putExtra("name",name);
                    startActivity(editScreenintent);
                } else {
                    toastMessage("No ID associated with this name");
                }
            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
