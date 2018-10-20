package ss.com.bannerslidersample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProgressBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_empty_view);
        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(2000); // set Waktu Pending selama 2 detik
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }finally {
                    startActivity(new Intent(ProgressBar.this, Login.class));
                    finish(); // Menutup Activity
                }
            }
        };
        thread.start();
    }
}