/**
 * @author ����4+1
 * @version 1.0.0 ����ʱ�䣺2012-08-08
 * ����Ϊ����Ŀ���������ͼ�������¼�ͼ��ͼ����Ͳ��ִ����ܡ�
 * �������н��棬������ͼ�ᵯ�����ݴ��ڣ�POPVIEW����
 * ����Ե�����ݴ�����Ӧ��ѡ��Ӷ�ִ�в�ͬ�Ĳ�����
 * ���ͼѡ���ѯ���ġ����õ��������㡢�ղصص���Ϣ�ȡ�
 */
package com.ustc.ccmap;


import java.util.List;

import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayItem;

public class LongPressOperation extends ItemizedOverlay<OverlayItem> implements OnLongClickListener,
		OnGestureListener, OnTouchListener{

	private GestureDetector gestureScanner = new GestureDetector(this);


	public LongPressOperation(MainActivity context, MapView mapVie) {
		super(null, mapVie);
		Log.e("LongPressOperation", "init");

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Log.e("eeeeeeeeee", "�����¼���Ӧ");
		return gestureScanner.onTouchEvent(event);//onTouchEvent
	}

//	@Override
//	public boolean onSingleTapConfirmed(MotionEvent e) {
//		//�����ݿ�ݲ˵���ʾʱ���������λ�ã�ʹ����ʧ��
//		Log.e("LongPressOperation", "onSingleTapConfirmed");
//		mHandler.sendEmptyMessage(mContext.MSG_VIEW_TAPOTHERAREA);
//		return true;
//	}

//	@Override
//	public boolean onDoubleTap(MotionEvent e) {
//		return false;
//	}

//	@Override
//	public boolean onDoubleTapEvent(MotionEvent e) {
//		//�����������ͼʱ���Ŵ��ͼ��
//		if (++level % 3 == 0) {
//			mMapCtrl.zoomIn();
//			level = 0;
//		}
//		return false;
//	}

	@Override
	public boolean onDown(MotionEvent e) {
		Log.e("eeeeeeeeee", "�����¼���Ӧ");
		//do nothing
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		Log.e("eeeeeeeeee", "�����¼���Ӧ");
		//do nothing
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		//do nothing
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		Log.e("eeeeeeeeee", "�����¼���Ӧ");
		return true;
	}

	@Override
	public void onLongPress(MotionEvent event) {
		Log.e("eeeeeeeeee", "�����¼���Ӧ");

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		Log.e("eeeeeeeeee", "�����¼���Ӧ");
		return true;
	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		Log.e("lomng", "ddd");
		return false;
	}



}
