package com.syndicate.vshark;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.giphy.sdk.ui.themes.Theme;
import com.google.firebase.auth.FirebaseAuth;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class CreateMeetActivity extends AppCompatActivity {

    private TextView meetingCode;
    private String generatedMeetCode;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meet);

        Button joinBtn = findViewById(R.id.join_btn2);
        Button shareBtn = findViewById(R.id.share_code_btn);
        ImageView share = findViewById(R.id.share_app1);
        ImageView options = findViewById(R.id.more_options1);
        meetingCode = findViewById(R.id.meeting_code);
        mAuth = FirebaseAuth.getInstance();
        generatedMeetCode = getMeetingCode();
        meetingCode.setText(generatedMeetCode);

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMeet();
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareMeetCode(generatedMeetCode);
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
                PopupMenu menu = new PopupMenu(CreateMeetActivity.this, options);
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

    private void startMeet() {
        URL serverURL = null;

        try {
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(serverURL).build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                .setRoom(meetingCode.getText().toString()).build();
        JitsiMeetActivity.launch(CreateMeetActivity.this,options);
    }

    private String getMeetingCode() {
        String codes = "qwertyuiopasdfghjklzxcvbnm1234567890";
        StringBuilder meetCode = new StringBuilder();
        for(int i=0;i<6;i++){
            char c = codes.charAt((int) (Math.random() * (36)));
            meetCode.append(c);
        }
        return meetCode.toString();
    }

    public void optionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.idLogOut:
                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent i = new Intent(CreateMeetActivity.this, LoginActivity.class);
                startActivity(i);
                this.finish();
                break;
            case R.id.addUserProfile:
                startActivity(new Intent(CreateMeetActivity.this, AddUserProfileActivity.class));
                break;
            case R.id.userProfile:
                startActivity(new Intent(CreateMeetActivity.this, UserProfileActivity.class));
                break;
            case R.id.viewPitcherProfile:
                startActivity(new Intent(CreateMeetActivity.this, viewPitcherProfile.class));
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

    private void shareMeetCode(String code)
    {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Join Meeting with Your Shark at vShark App\nJoin With following code\n"+code);
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, "Share Notes");
        startActivity(shareIntent);
    }

}