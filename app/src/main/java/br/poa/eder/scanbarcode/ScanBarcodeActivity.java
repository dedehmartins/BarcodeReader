package br.poa.eder.scanbarcode;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.List;

public class ScanBarcodeActivity extends AppCompatActivity {
    DecoratedBarcodeView barcodereader;
    TextView tvBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);
        //getSupportActionBar().hide();

        ActivityCompat.requestPermissions(ScanBarcodeActivity.this,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
        tvBottom = findViewById(R.id.tvBottom);

        barcodereader = findViewById(R.id.barcodereader);
        barcodereader.setStatusText("");
        barcodereader.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult rawResult) {
                tvBottom.setText(rawResult.getText());
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> list) {
                String str ="";
            }
        });
    }



    @Override
    public void onResume() {
        barcodereader.resume();
        super.onResume();
    }

    @Override
    public void onPause() {
        barcodereader.pause();
        super.onPause();
    }
}
