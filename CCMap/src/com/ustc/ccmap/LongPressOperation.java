/**
 * @author 神奇4+1
 * @version 1.0.0 创建时间：2012-08-08
 * 该类为本项目的主界面地图，长按事件图层和监听和部分处理功能。
 * 在主运行界面，长按地图会弹出气泡窗口（POPVIEW），
 * 你可以点击气泡窗口相应的选项，从而执行不同的操作，
 * 如地图选择查询中心、设置导航出发点、收藏地点信息等。
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
		Log.e("eeeeeeeeee", "长按事件响应");
		return gestureScanner.onTouchEvent(event);//onTouchEvent
	}

//	@Override
//	public boolean onSingleTapConfirmed(MotionEvent e) {
//		//当气泡快捷菜单显示时，点击其他位置，使其消失。
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
//		//当连续点击地图时，放大地图。
//		if (++level % 3 == 0) {
//			mMapCtrl.zoomIn();
//			level = 0;
//		}
//		return false;
//	}

	@Override
	public boolean onDown(MotionEvent e) {
		Log.e("eeeeeeeeee", "长按事件响应");
		//do nothing
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		Log.e("eeeeeeeeee", "长按事件响应");
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
		Log.e("eeeeeeeeee", "长按事件响应");
		return true;
	}

	@Override
	public void onLongPress(MotionEvent event) {
		Log.e("eeeeeeeeee", "长按事件响应");

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		Log.e("eeeeeeeeee", "长按事件响应");
		return true;
	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		Log.e("lomng", "ddd");
		return false;
	}



}
