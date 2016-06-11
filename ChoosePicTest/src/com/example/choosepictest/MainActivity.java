package com.example.choosepictest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener{
	
	public static final int TAKE_PHOTO=1;
	public static final int CROP_PHOTO=2;
	
	private Button takePhoto;
	private ImageView picture;
	private Uri imageUri;
	
	
	private Button choosePhoto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ȡ�������
		takePhoto = (Button)findViewById(R.id.takePhone);
		picture = (ImageView)findViewById(R.id.picture);
		//������ť����¼�
		takePhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//����File����,���ڴ������պ����Ƭ
				File outputImage = new File(Environment.getExternalStorageDirectory(),"output_image.jpg");
				try {
					if(outputImage.exists()){
						outputImage.delete();
					}
					outputImage.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				imageUri = Uri.fromFile(outputImage);
				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent, TAKE_PHOTO);//�����������
			}
		});
		
		choosePhoto = (Button)findViewById(R.id.choosePhoto);
		choosePhoto.setOnClickListener(this);
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case TAKE_PHOTO:
			if(resultCode==RESULT_OK){
				Intent intent = new Intent("com.android.camera.action.CROP");
				intent.setDataAndType(imageUri, "/image/*");
				intent.putExtra("scale", true);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent, CROP_PHOTO);//�������г���
			}
			break;
		case CROP_PHOTO:
			if(resultCode==RESULT_OK){
				try {
					Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
					picture.setImageBitmap(bitmap);//�����к����Ƭ��ʾ����
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onClick(View v) {
		//����File�������ڴ洢ѡ�����Ƭ
		File file = new File(Environment.getExternalStorageDirectory(),"output_image.jpg");
		try {
			if(file.exists()){
				file.delete();
			}
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageUri = Uri.fromFile(file);
		Intent intent = new Intent("android.intent.action.GET_CONTENT");
		intent.setType("image/*");
		intent.putExtra("crop", true);
		intent.putExtra("scale", true);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(intent, CROP_PHOTO);
	}
	
	
}

















