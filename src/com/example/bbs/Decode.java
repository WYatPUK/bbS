package com.example.bbs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Decode extends Activity implements View.OnClickListener {

	TextView text;
	ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_decode);
		findViewById(R.id.decode_open).setOnClickListener(this);
		text = (TextView)this.findViewById(R.id.decode_text);
		imageView = (ImageView)this.findViewById(R.id.decode_image);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.decode_open:
			showFileChooser();
			break;
		}
	}
	
	private void showFileChooser() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		try {
			startActivityForResult(Intent.createChooser(intent, "请选择一张带二维码的图片"), 1);
		} catch (android.content.ActivityNotFoundException ex) {
			// Potentially direct the user to the Market with a Dialog
			Toast.makeText(this, "请安装文件管理器", Toast.LENGTH_SHORT)
					.show();
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == Activity.RESULT_OK) {
			// Get the Uri of the selected file
			Uri uri = data.getData();
			String url;
			url = uri.getPath();
			Log.i("ht", "url" + url);
			String fileName = url.substring(url.lastIndexOf("/") + 1);
			Toast.makeText(this, url, Toast.LENGTH_LONG).show();
			
			//获取bitmap并显示图像
			Bitmap bm = null;
			ContentResolver resolver = getContentResolver();  
			try {
				bm = MediaStore.Images.Media.getBitmap(resolver, uri);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}  
			imageView.setImageBitmap(ThumbnailUtils.extractThumbnail(bm, 100, 100));  
			
			//解析图片中的二维码
			/*
			//Reader reader = new MultiFormatReader();
			//Reader reader = new DataMatrixReader();
			QRCodeReader reader = new QRCodeReader();
			Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();  
		    hints.put(DecodeHintType.CHARACTER_SET, "UTF-8"); 
		    int[] intArray = new int[bm.getWidth() * bm.getHeight()]; 
		    bm.getPixels(intArray, 0, bm.getWidth(), 0, 0, bm.getWidth(), bm.getHeight());  
		    LuminanceSource source = new RGBLuminanceSource(bm.getWidth(), bm.getHeight(),intArray);
		    BinaryBitmap Bbitmap = new BinaryBitmap(new HybridBinarizer(source));
		    Result result = null;
		    try {
				result = reader.decode(Bbitmap, hints);
				String Decoded = result.getText();
			    text.setText(Decoded);
			} catch (NotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ChecksumException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		    byte[] data2 = ImageUtils.getYUV420sp(bm.getWidth(), bm.getHeight(), bm);  
			
		    */
			String http = QRCodeUtils.getStringFromQRCode(bm);
			text.setText(http);

		}
		//super.onActivityResult(requestCode, resultCode, data);
	}

}
