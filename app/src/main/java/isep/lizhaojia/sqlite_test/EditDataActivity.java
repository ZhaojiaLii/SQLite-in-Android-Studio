package isep.lizhaojia.sqlite_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDataActivity extends Activity {

    private static final String TAG = "EditDataActivity";

    private Button btn_save, btn_delete;
    private EditText editText_item;

    DatabaseHelper mDatabaseHelper;

    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R. layout.edit_data_layout);

        btn_save = (Button) findViewById(R.id.btn_SAVE);
        btn_delete =(Button) findViewById(R.id.btn_DELETE);
        editText_item = (EditText) findViewById(R.id.editText_item);
        mDatabaseHelper = new DatabaseHelper(this);

        Intent receivecIntent =  getIntent();

        selectedID = receivecIntent.getIntExtra("id", -1);
        selectedName = receivecIntent.getStringExtra("name");
        editText_item.setText(selectedName);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = editText_item.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateName(item,selectedID,selectedName);
                }
                else {
                    toastMessage("you must type in a name");
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.deleteName(selectedID,selectedName);
                editText_item.setText("");
                toastMessage("removed from database");
            }
        });

    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
