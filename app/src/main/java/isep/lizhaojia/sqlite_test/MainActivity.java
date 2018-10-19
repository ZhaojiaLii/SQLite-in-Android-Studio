package isep.lizhaojia.sqlite_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amitshekhar.DebugDB;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    DatabaseHelper mDatabaseHelper;
    private Button btn_ADD, btn_SHOW;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,DebugDB.getAddressLog());
        editText = (EditText)findViewById(R.id.editText);
        btn_ADD = (Button) findViewById(R.id.btn_ADD);
        btn_SHOW = (Button) findViewById(R.id.btn_SHOW);
        mDatabaseHelper = new DatabaseHelper(this);

        btn_ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if(newEntry.length()!=0){
                    // add to db
                    AddData(newEntry);
                    editText.setText("");
                } else {
                    toastMessage("put sth in the field");
                }
            }
        });

        btn_SHOW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,ListDataActivity.class);
                startActivity(intent);

            }
        });
    }

    public void AddData(String newEntry){
        boolean insertData = mDatabaseHelper.addData(newEntry);
        if(insertData){
            toastMessage("Successfully!");
        } else {
            toastMessage("Failed!");
        }
    }
    private  void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
