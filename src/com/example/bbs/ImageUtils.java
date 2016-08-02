package com.example.bbs;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public class ImageUtils {	
	/**
	 * drawableToBitmap with white background
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
        // ȡ drawable �ĳ���
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        
        // ȡ drawable ����ɫ��ʽ
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        // ������Ӧ bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // ������Ӧ bitmap �Ļ���
        Canvas canvas = new Canvas(bitmap);
        // ��ɫ��ɫ   Ӧ��͸��ͼ
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, w, h, paint);
        drawable.setBounds(0, 0, w, h);
        // �� drawable ���ݻ���������
        drawable.draw(canvas);
        return bitmap;
    }
	
	/**
	 * YUV420sp
	 * 
	 * @param inputWidth
	 * @param inputHeight
	 * @param scaled
	 * @return
	 */
	public static byte[] getYUV420sp(int inputWidth, int inputHeight,
			Bitmap scaled) {
		int[] argb = new int[inputWidth * inputHeight];

		scaled.getPixels(argb, 0, inputWidth, 0, 0, inputWidth, inputHeight);

		byte[] yuv = new byte[inputWidth * inputHeight * 3 / 2];
		
		encodeYUV420SP(yuv, argb, inputWidth, inputHeight);

		scaled.recycle();

		return yuv;
	}

	/**
	 * RGBתYUV420sp
	 * 
	 * @param yuv420sp
	 *            inputWidth * inputHeight * 3 / 2
	 * @param argb
	 *            inputWidth * inputHeight
	 * @param width
	 * @param height
	 */
	private static void encodeYUV420SP(byte[] yuv420sp, int[] argb, int width,
			int height) {
		// ֡ͼƬ�����ش�С
		final int frameSize = width * height;
		// ---YUV����---
		int Y, U, V;
		// Y��index��0��ʼ
		int yIndex = 0;
		// UV��index��frameSize��ʼ
		int uvIndex = frameSize;

		// ---��ɫ����---
//		int a, R, G, B;
		int R, G, B;
		//
		int argbIndex = 0;
		//

		// ---ѭ���������ص㣬RGBתYUV---
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {

				// a is not used obviously
//				a = (argb[argbIndex] & 0xff000000) >> 24;
				R = (argb[argbIndex] & 0xff0000) >> 16;
				G = (argb[argbIndex] & 0xff00) >> 8;
				B = (argb[argbIndex] & 0xff);
				//
				argbIndex++;

				// well known RGB to YUV algorithm
				Y = ((66 * R + 129 * G + 25 * B + 128) >> 8) + 16;
				U = ((-38 * R - 74 * G + 112 * B + 128) >> 8) + 128;
				V = ((112 * R - 94 * G - 18 * B + 128) >> 8) + 128;

				//
				Y = Math.max(0, Math.min(Y, 255));
				U = Math.max(0, Math.min(U, 255));
				V = Math.max(0, Math.min(V, 255));

				// NV21 has a plane of Y and interleaved planes of VU each
				// sampled by a factor of 2
				// meaning for every 4 Y pixels there are 1 V and 1 U. Note the
				// sampling is every other
				// pixel AND every other scanline.
				// ---Y---
				yuv420sp[yIndex++] = (byte) Y;
				// ---UV---
				if ((j % 2 == 0) && (i % 2 == 0)) {
					//
					yuv420sp[uvIndex++] = (byte) V;
					//
					yuv420sp[uvIndex++] = (byte) U;
				}
			}
		}
	}
}