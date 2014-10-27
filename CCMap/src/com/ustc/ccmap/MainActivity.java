package com.ustc.ccmap;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MKOLUpdateElement;
import com.baidu.mapapi.map.MKOfflineMap;
import com.baidu.mapapi.map.MKOfflineMapListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Overlay;

public class MainActivity extends Activity {

	private MapView mapView;
	private MapController mapCtrl;
	private List<Overlay> mapOverlays;           //地图所有图层
	
	BMapManager mapManager = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
       
//       mapManager = new BMapManager(getApplication());  
//       //init方法的第一个参数需填入申请的API Key  
//       mapManager.init("tSzVmebGzWQVn33I5thcDoja", null);  
//       super.initMapActivity(mapManager);  
//		
	       DemoApplication app = (DemoApplication)this.getApplication();
	        if (app.mBMapManager == null) {
	            app.mBMapManager = new BMapManager(getApplicationContext());
	            /**
	             * 如果BMapManager没有初始化则初始化BMapManager
	             */
	            app.mBMapManager.init(new DemoApplication.MyGeneralListener());
	        }
		
		
		setContentView(R.layout.activity_main);

		
		Looper.prepare();
		Looper.loop();
		Looper loop = Looper.getMainLooper();
		
		
		
		mapView = (MapView) findViewById(R.id.bmapView);
		mapView.setBuiltInZoomControls(true);
		mapCtrl = mapView.getController();
		mapCtrl.setZoom(14);
//		mapOverlays = mapView.getOverlays();
//		
//		Log.e("ddd", mapOverlays.size()+"");
//		mapOverlays.add(new LongPressOperation(this, mapView)); //长按地图响应图层
//		Log.e("ddd", mapOverlays.size()+"");
//		mapView.refresh();
//		
//		
//		mapView.setOnLongClickListener(new LongPressOperation(this, mapView));
//		mapView.setOnTouchListener(new LongPressOperation(this, mapView));
//		mapView.setOnLongClickListener(new View.OnLongClickListener() {
//			   
//			   @Override
//			   public boolean onLongClick(View v) {
//			    // TODO Auto-generated method stub
//			    Log.e("ddd", "ddddd");
//			    return true;
//			   }
//			  });
	
	
	final MKOfflineMap mOffline=new MKOfflineMap();;  //申明变量
	//写在onCreate函数里
	//offline 实始化方法用更改。
	mOffline.init(mapCtrl,new MKOfflineMapListener(){
	@Override
	public void onGetOfflineMapState(int type, int state){
		switch(type){
			case MKOfflineMap.TYPE_DOWNLOAD_UPDATE:
			{
				MKOLUpdateElement update =mOffline.getUpdateInfo(state);
				//mText.setText(String.format("%s : %d%%", update.cityName, update.ratio));
			}
				break;
			case MKOfflineMap.TYPE_NEW_OFFLINE:
				Log.d("OfflineDemo", String.format("add offlinemapnum:%d", state));
				break;
			case MKOfflineMap.TYPE_VER_UPDATE:
				Log.d("OfflineDemo", String.format("new offlinemapver"));
				break;
				}
			}
		});

	}
	
}
