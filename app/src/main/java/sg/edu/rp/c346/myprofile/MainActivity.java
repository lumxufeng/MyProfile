package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String name = prefs.getString("Name", "invalid name!");
        Float gpa = prefs.getFloat("GPA", 0);
        Integer gender = prefs.getInt("gender", 0);

        etName.setText(name);
        etGPA.setText(gpa.toString());
        rgGender.check(gender);


        Toast toast = Toast.makeText(getApplicationContext(), name + " " + gpa, Toast.LENGTH_LONG);
        toast.show();

    }

    @Override
    protected void onPause() {
        super.onPause();

        String strName = etName.getText().toString();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor preEdit = prefs.edit();


        preEdit.putString("Name", strName);
        preEdit.putFloat("GPA", Float.parseFloat(etGPA.getText().toString()));
        preEdit.putInt("gender", rgGender.getCheckedRadioButtonId());


        preEdit.commit();

    }
}
