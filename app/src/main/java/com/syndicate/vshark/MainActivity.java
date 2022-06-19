package com.syndicate.vshark;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import com.google.firebase.auth.FirebaseAuth;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        Button createMeetBtn = findViewById(R.id.create_meet_btn);
        Button joinMeetBtn = findViewById(R.id.join_btn1);
        EditText meetingCode = findViewById(R.id.meeting_code_box);
        ImageView share = findViewById(R.id.share_app);
        ImageView options = findViewById(R.id.more_options);

        URL serverURL = null;


        try {
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(serverURL).build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        createMeetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateMeetActivity.class));
            }
        });

        joinMeetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(meetingCode.getText().toString()).build();
                JitsiMeetActivity.launch(MainActivity.this,options);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareApp();
            }
        });

        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(MainActivity.this, options);
                menu.getMenuInflater().inflate(R.menu.menu_main, menu.getMenu());

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        optionsItemSelected(menuItem);
                        return true;
                    }
                });
                menu.show();
            }
        });

    }


    private void optionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.idLogOut:
                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                this.finish();
                break;
            case R.id.addUserProfile:
                startActivity(new Intent(MainActivity.this, AddUserProfileActivity.class));
                break;
            case R.id.userProfile:
                startActivity(new Intent(MainActivity.this, UserProfileActivity.class));
                break;
            case R.id.viewPitcherProfile:
                startActivity(new Intent(MainActivity.this, viewPitcherProfile.class));
                break;
        }
    }

    private void shareApp() {
        Intent ip = new Intent(Intent.ACTION_SEND);
        ip.setType("text/plain");
        ip.putExtra(Intent.EXTRA_SUBJECT,"Share vShark");
        String share_Url="https://vshark220906537.wordpress.com/";
        ip.putExtra(Intent.EXTRA_TEXT,share_Url);
        startActivity(Intent.createChooser(ip,"Share Using"));
    }
}