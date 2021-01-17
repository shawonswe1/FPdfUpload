package com.example.fpdfupload;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.barteksc.pdfviewer.PDFView;

import java.net.URLEncoder;

public class ViewPdf extends AppCompatActivity {

    WebView pdfView;
//    PDFView pdfView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
        pdfView = findViewById(R.id.viewPdf);
        pdfView.getSettings().setJavaScriptEnabled(true);
        String name = getIntent().getStringExtra("name");
        String fileUrl = getIntent().getStringExtra("url");
        Uri path = Uri.parse(fileUrl);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(name);
        progressDialog.setMessage("Opening..............!!!");
//        progressDialog.show();

        pdfView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialog.dismiss();
            }
        });

        String url = "";
        try {
            url = URLEncoder.encode(fileUrl,"UTF-8");

        }catch (Exception ex)
        {
            Log.e("Error",ex.getLocalizedMessage());
        }
        Log.e("Url: ",url);
        pdfView.loadUrl("http://docs.google.com/gview?embedded=true&url="+url);

//        pdfView= (PDFView)findViewById(R.id.pdfView);
//        pdfView.fromUri(path)
//                .enableSwipe(true)
//                .enableAnnotationRendering(true)
//                .load();
    }
}