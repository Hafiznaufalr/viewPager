package ss.com.bannerslidersample;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.events.OnBannerClickListener;
import ss.com.bannerslider.views.BannerSlider;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener {
    private BannerSlider bannerSlider;
    private ImageView btn;
    GridView gridview;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

    }




    private void setupViews() {
        setupToolbar();
        setupBannerSlider();

        setupSettingsUi();
    }

    private void setupSettingsUi() {

    }
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Apakah Anda Ingin Keluar?")
                .setCancelable(false)
                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }




    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn = (ImageView) findViewById(R.id.set);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });

        ImageView githubSourceImageView = (ImageView) findViewById(R.id.image_github);
        githubSourceImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://malangkota.go.id/");

                if (Build.VERSION.SDK_INT>15) {
                    CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
                    intentBuilder.setToolbarColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                    intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));
                    intentBuilder.setStartAnimations(MainActivity.this, android.R.anim.fade_in, android.R.anim.fade_out);
                    intentBuilder.setExitAnimations(MainActivity.this, android.R.anim.fade_in, android.R.anim.fade_out);
                    CustomTabsIntent customTabsIntent = intentBuilder.build();
                    customTabsIntent.launchUrl(MainActivity.this, uri);
                }else {
                    startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW,uri),"Choose Browser..."));
                }

            }
        });
    }

    private void setupBannerSlider(){
        bannerSlider = (BannerSlider) findViewById(R.id.banner_slider1);
        addBanners();

        bannerSlider.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this, "Banner with position " + String.valueOf(position) + " clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void addBanners(){
        List<Banner> remoteBanners=new ArrayList<>();
        //Add banners using image urls
        remoteBanners.add(new RemoteBanner(
                "https://sakinahmuslimah.000webhostapp.com/img/imalang.png"
        ));
        remoteBanners.add(new RemoteBanner(
                "https://sakinahmuslimah.000webhostapp.com/img/IMGSD2.jpg"
        ));
        remoteBanners.add(new RemoteBanner(
                "https://sakinahmuslimah.000webhostapp.com/img/IMGSD3.jpg"
        ));
        remoteBanners.add(new RemoteBanner(
                "https://sakinahmuslimah.000webhostapp.com/img/IMGSD4.jpg"
        ));
        bannerSlider.setBanners(remoteBanners);

    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }

}
