package com.example.namemangler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Name mName;
    private TextView mNameDisplay;
    private EditText mNameField;
    private Button mMangle;
    private boolean mEnteredName;
    private String mDisplayPrompt;

    private static final String KEY_NAMEDISPLAY = "namedisplay";
    private static final String KEY_NAMEENTERED = "entered";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mName = new Name();
        mEnteredName = false;

        mNameDisplay = findViewById(R.id.name_display);
        mNameDisplay.setText(R.string.name);



        mNameField = findViewById(R.id.name_entered);

        if(savedInstanceState != null){
            mEnteredName = savedInstanceState.getBoolean(KEY_NAMEENTERED, false);
            mDisplayPrompt = savedInstanceState.getString(KEY_NAMEDISPLAY, "");
            mNameDisplay.setText(mDisplayPrompt);
        }
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mName.setmName(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mName.setmName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                mEnteredName = true;
            }
        });

        mMangle = findViewById(R.id.mangle_button);
        mMangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEnteredName){
                    mDisplayPrompt = String.format("%s", getResources().getString(R.string.name) + " " + mName.getmName() + " " + getResources().getString(mName.mangle()));
                    mNameDisplay.setText(mDisplayPrompt);
                } else {
                    int messageResId = R.string.no_name_toast;
                    Toast.makeText(MainActivity.this, messageResId, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_NAMEDISPLAY, mDisplayPrompt);
        savedInstanceState.putBoolean(KEY_NAMEENTERED, mEnteredName);
    }
}
