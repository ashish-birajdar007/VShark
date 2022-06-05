package com.syndicate.vshark;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class CreateMeetActivity extends AppCompatActivity {

    private TextView meetingCode;
    private String generatedMeetCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meet);

        Button joinBtn = findViewById(R.id.join_btn2);
        Button shareBtn = findViewById(R.id.share_code_btn);
        meetingCode = findViewById(R.id.meeting_code);
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