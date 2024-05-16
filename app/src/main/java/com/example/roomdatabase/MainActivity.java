package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    EditText nameedt, useridedt, emailedt, edtid;
    Button savebtn, readbtn, updatebtn, deletebtn;
    static MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        useridedt = findViewById(R.id.edt_userid);
        nameedt = findViewById(R.id.edt_name);
        emailedt = findViewById(R.id.edt_email);
        edtid = findViewById(R.id.edt_id);


        savebtn = findViewById(R.id.btn_save);
        readbtn = findViewById(R.id.btn_read);
        updatebtn = findViewById(R.id.btn_update);
        deletebtn = findViewById(R.id.btn_delete);

        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "userdb").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();

            }
        });

        readbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });
    }

    public void saveData() {
        int userid = Integer.parseInt(useridedt.getText().toString());
        String username = nameedt.getText().toString();
        String useremail = emailedt.getText().toString();

        Users user = new Users();
        user.setId(userid);
        user.setName(username);
        user.setEmail(useremail);

        myDatabase.myDao().addUser(user);
        Toast.makeText(MainActivity.this, " Data Added Successfully", Toast.LENGTH_SHORT).show();

        useridedt.setText("");
        nameedt.setText("");
        emailedt.setText("");

    }

    public void getData() {
        List<Users> users = myDatabase.myDao().getUser();
        String info = " ";

        for (Users usr : users) {
            int id = usr.getId();
            String name = usr.getName();
            String email = usr.getEmail();
            info = info + "\n\n" + "Id : " + id + "\n Name :" + name + "\n Email :" + email;

            Toast.makeText(this, "" + info, Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteData() {
        int id = Integer.parseInt(edtid.getText().toString());
        Users user = new Users();
        user.setId(id);

        myDatabase.myDao().deleteUser(user);

        Toast.makeText(this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
    }

    public void updateData() {
        int id = Integer.parseInt(useridedt.getText().toString());
        String name = nameedt.getText().toString();
        String email = emailedt.getText().toString();

        Users use = new Users();
        use.setId(id);
        use.setName(name);
        use.setEmail(email);

        myDatabase.myDao().updateUser(use);
        Toast.makeText(this, "Data Updated Succefully", Toast.LENGTH_SHORT).show();
    }

}