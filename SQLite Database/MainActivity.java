package com.eminencetechnologies.studentrecordsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText editTextID, editTextName, editTextEmail, editTextCC;
    Button buttonAdd, buttonView, buttonUpdate, buttonDelete, buttonViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextID = findViewById(R.id.editText_id);
        editTextName = findViewById(R.id.editText_name);
        editTextEmail = findViewById(R.id.editText_email);
        editTextCC = findViewById(R.id.editText_CC);

        buttonAdd = findViewById(R.id.button_add);
        buttonView = findViewById(R.id.button_view);
        buttonUpdate = findViewById(R.id.button_update);
        buttonDelete = findViewById(R.id.button_delete);
        buttonViewAll = findViewById(R.id.button_viewAll);

        myDB = new DatabaseHelper(this);
        AddData();
        ViewData();
        UpdateData();
        DeleteData();
        ViewAllData();

    }

    public void AddData() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDB.insertData(editTextName.getText().toString(), editTextEmail.getText().toString(), editTextCC.getText().toString());
                if (isInserted) {
                    Toast.makeText(MainActivity.this, "Data Inserted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void ViewData() {
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editTextID.getText().toString();
                if (id.equals(String.valueOf(""))) {
                    editTextID.setError("Plase Enter ID");
                    return;
                }
                Cursor cursor = myDB.viewData(id);
                String data = null;
                if (cursor.moveToNext()) {
                    data = "ID: " + cursor.getString(0)+"\n"+
                            "Name: " + cursor.getString(1)+"\n"+
                            "Email: " + cursor.getString(2)+"\n"+
                            "Course Count: " + cursor.getString(3);
                }
                showMessage("Data", data);
            }
        });
    }

    public void UpdateData() {
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = myDB.updateData(editTextID.getText().toString(), editTextName.getText().toString(), editTextEmail.getText().toString(), editTextCC.getText().toString());
                if (isUpdated) {
                    Toast.makeText(MainActivity.this, "Updated!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void DeleteData() {
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer isDeleted = myDB.deleteData(editTextID.getText().toString());
                if (isDeleted > 0) {
                    Toast.makeText(MainActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void ViewAllData() {
        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = myDB.viewAllData();
                if (cursor.getCount() == 0) {
                    showMessage("Error", "Nothing Found!");
                }
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append("ID: " + cursor.getString(0) + "\n");
                    buffer.append("Name: " + cursor.getString(1) + "\n");
                    buffer.append("EMAIL: " + cursor.getString(2) + "\n");
                    buffer.append("Course Count: " + cursor.getString(3) + "\n");
                }
                showMessage("Data", buffer.toString());
            }
        });
    }


    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.create();
        builder.show();
    }

}