package com.lidegou.lideshangmeng.mobile.scancodedemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.lidegou.lideshangmeng.mobile.R;


public class CodeShowActivity extends Activity {
	private ImageView mImgCodeShow;
	CreateQRImage createQRImageTest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.code_show_activity);
		mImgCodeShow=(ImageView)findViewById(R.id.img_code_show);
		createQRImageTest=new CreateQRImage();
		Bitmap bitmap=createQRImageTest.createQRImage("http://www.baidu.com");
		mImgCodeShow.setImageBitmap(bitmap);
		
	}

}
