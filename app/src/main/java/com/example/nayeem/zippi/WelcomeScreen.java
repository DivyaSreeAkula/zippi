package com.example.nayeem.zippi;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.widget.TextView;

        import java.lang.reflect.AccessibleObject;

public class WelcomeScreen extends Activity {
    TextView welcome;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomescreen);
        welcome=(TextView)findViewById(R.id.welcome);
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                Intent intent=getIntent();
                String name = intent.getStringExtra("key1");
                String phn = intent.getStringExtra("key2");
                welcome.setText("Logging In As \n"+ name );
                Intent i=new Intent(WelcomeScreen.this,EventplannerView.class);
                i.putExtra("phone",phn);
                i.putExtra("name",name);
                startActivity(i);
                finish();
            }
        },secondsDelayed * 1000);
    }

}


