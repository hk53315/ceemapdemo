package com.ustc.ccmap;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MKOLSearchRecord;
import com.baidu.mapapi.map.MKOLUpdateElement;
import com.baidu.mapapi.map.MKOfflineMap;
import com.baidu.mapapi.map.MKOfflineMapListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;

public class OfflineDemo extends Activity implements MKOfflineMapListener {
	
	private MapView mMapView = null;
	private MKOfflineMap mOffline = null;
	private TextView cidView;
	private TextView stateView;
	private EditText cityNameView;
	private MapController mMapController = null;
	/**
	 * �����ص����ߵ�ͼ��Ϣ�б�
	 */
	private ArrayList<MKOLUpdateElement>localMapList =null;
	private LocalMapAdapter lAdapter = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**
         * ʹ�õ�ͼsdkǰ���ȳ�ʼ��BMapManager.
         * BMapManager��ȫ�ֵģ���Ϊ���MapView���ã�����Ҫ��ͼģ�鴴��ǰ������
         * ���ڵ�ͼ��ͼģ�����ٺ����٣�ֻҪ���е�ͼģ����ʹ�ã�BMapManager�Ͳ�Ӧ������
         */
        DemoApplication app = (DemoApplication)this.getApplication();
        if (app.mBMapManager == null) {
            app.mBMapManager = new BMapManager(getApplicationContext());
            /**
             * ���BMapManagerû�г�ʼ�����ʼ��BMapManager
             */
            app.mBMapManager.init(new DemoApplication.MyGeneralListener());
        }
        setContentView(R.layout.activity_offline);
        mMapView = new MapView(this);
        mMapController = mMapView.getController();
        
        mOffline = new MKOfflineMap();    
        /**
         * ��ʼ�����ߵ�ͼģ��,MapControler�ɴ�MapView.getController()��ȡ
         */
        mOffline.init(mMapController, this);
        initView();
                
	}
	
	private void initView(){
		
		cidView       = (TextView)findViewById(R.id.cityid);
		cityNameView  = (EditText)findViewById(R.id.city);
		stateView     = (TextView)findViewById(R.id.state);
		
		ListView hotCityList = (ListView)findViewById(R.id.hotcitylist);
        ArrayList<String> hotCities = new ArrayList<String>();
        //��ȡ���ֳ����б�
        ArrayList<MKOLSearchRecord> records1 = mOffline.getHotCityList();
        if (records1 != null) {
        	for (MKOLSearchRecord r : records1 ){
        		 hotCities.add(r.cityName+"("+r.cityID+")"+"   --"+ this.formatDataSize(r.size));
        	}
        }
        ListAdapter hAdapter = (ListAdapter)new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,hotCities); 
        hotCityList.setAdapter(hAdapter);
        
        ListView allCityList = (ListView)findViewById(R.id.allcitylist);
        //��ȡ����֧�����ߵ�ͼ�ĳ���
        ArrayList<String> allCities = new ArrayList<String>();
        ArrayList<MKOLSearchRecord> records2 = mOffline.getOfflineCityList();
        if (records1 != null) {
        	for (MKOLSearchRecord r : records2 ){
        		 allCities.add(r.cityName+"("+r.cityID+")"+"   --"+ this.formatDataSize(r.size));
        	}
        }
        ListAdapter aAdapter = (ListAdapter)new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,allCities); 
        allCityList.setAdapter(aAdapter);
        
        LinearLayout cl = (LinearLayout)findViewById(R.id.citylist_layout);
		LinearLayout lm = (LinearLayout)findViewById(R.id.localmap_layout);
		lm.setVisibility(View.GONE);
		cl.setVisibility(View.VISIBLE);
		
		//��ȡ���¹������ߵ�ͼ��Ϣ
        localMapList = mOffline.getAllUpdateInfo();
        if ( localMapList == null ){
            localMapList = new ArrayList<MKOLUpdateElement>();	
        }
        
        ListView localMapListView = (ListView)findViewById(R.id.localmaplist);
        lAdapter = new LocalMapAdapter();
        localMapListView.setAdapter(lAdapter);
		
	}
	/**
	 * �л��������б�
	 * @param view
	 */
	public void clickCityListButton(View view){
		LinearLayout cl = (LinearLayout)findViewById(R.id.citylist_layout);
		LinearLayout lm = (LinearLayout)findViewById(R.id.localmap_layout);
		lm.setVisibility(View.GONE);
		cl.setVisibility(View.VISIBLE);
		
	}
	/**
	 * �л������ع����б�
	 * @param view
	 */
	public void clickLocalMapListButton(View view){
		LinearLayout cl = (LinearLayout)findViewById(R.id.citylist_layout);
		LinearLayout lm = (LinearLayout)findViewById(R.id.localmap_layout);
		lm.setVisibility(View.VISIBLE);
		cl.setVisibility(View.GONE);
	}
	/**
	 * ������������
	 * @param view
	 */
	public void search(View view){
		ArrayList<MKOLSearchRecord> records = mOffline.searchCity(cityNameView.getText().toString());
		if (records == null || records.size() != 1)
			return;
		cidView.setText(String.valueOf(records.get(0).cityID));
	}
	
	/**
	 * ��ʼ����
	 * @param view
	 */
	public void start(View view){
		int cityid = Integer.parseInt(cidView.getText().toString());
		mOffline.start(cityid);
		clickLocalMapListButton(null);
		Toast.makeText(this, "��ʼ�������ߵ�ͼ. cityid: "+cityid, Toast.LENGTH_SHORT)
		          .show();
	}
	/**
	 * ��ͣ����
	 * @param view
	 */
	public void stop(View view){
		int cityid = Integer.parseInt(cidView.getText().toString());
		mOffline.pause(cityid);
		Toast.makeText(this, "��ͣ�������ߵ�ͼ. cityid: "+cityid, Toast.LENGTH_SHORT)
		          .show();
	}
	/**
	 * ɾ�����ߵ�ͼ
	 * @param view
	 */
	public void remove(View view){
		int cityid = Integer.parseInt(cidView.getText().toString());
		mOffline.remove(cityid);
		Toast.makeText(this, "ɾ�����ߵ�ͼ. cityid: "+cityid, Toast.LENGTH_SHORT)
		          .show();
	}
	/**
	 * ��SD���������ߵ�ͼ��װ��
	 * @param view
	 */
    public void importFromSDCard(View view){
		int num = mOffline.scan();
		String msg = "";
		if ( num == 0){
			msg = "û�е������߰�������������߰�����λ�ò���ȷ�������߰��Ѿ������";
		}
		else{
		   msg = String.format("�ɹ����� %d �����߰������������ع����鿴",num);	
		}
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
    /**
     * ����״̬��ʾ 
     */
    public void updateView(){
    	localMapList = mOffline.getAllUpdateInfo();
        if ( localMapList == null ){
            localMapList = new ArrayList<MKOLUpdateElement>();	
        }
    	lAdapter.notifyDataSetChanged();
    }
	
	

	@Override
    protected void onPause() {
		int cityid = Integer.parseInt(cidView.getText().toString());
		mOffline.pause(cityid);
        mMapView.onPause();
        super.onPause();
    }
    
    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }
    
    
    public  String formatDataSize(int size) {
        String ret = "";
        if (size < (1024 * 1024)) {
            ret = String.format("%dK", size / 1024);
        } else {
            ret = String.format("%.1fM", size / (1024 * 1024.0));
        }
        return ret;
    }

    @Override
    protected void onDestroy() {
    	/**
    	 * �˳�ʱ���������ߵ�ͼģ��
    	 */
        mOffline.destroy();
        mMapView.destroy();
        super.onDestroy();
    }

	@Override
	public void onGetOfflineMapState(int type, int state) {
		switch (type) {
		case MKOfflineMap.TYPE_DOWNLOAD_UPDATE:
			{
				MKOLUpdateElement update = mOffline.getUpdateInfo(state);
				//�������ؽ��ȸ�����ʾ
				if ( update != null ){
				    stateView.setText(String.format("%s : %d%%", update.cityName, update.ratio));
				    updateView();
				}
			}
			break;
		case MKOfflineMap.TYPE_NEW_OFFLINE:
			//�������ߵ�ͼ��װ
			Log.d("OfflineDemo", String.format("add offlinemap num:%d", state));
			break;
		case MKOfflineMap.TYPE_VER_UPDATE:
		    // �汾������ʾ
            //	MKOLUpdateElement e = mOffline.getUpdateInfo(state);
			
			break;
		}
		 
	}
	/**
	 * ���ߵ�ͼ�����б�������
	 */
	public class LocalMapAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return localMapList.size();
		}

		@Override
		public Object getItem(int index) {
			return localMapList.get(index);
		}

		@Override
		public long getItemId(int index) {
			return index;
		}

		@Override
		public View getView(int index, View view, ViewGroup arg2) {
			MKOLUpdateElement e = (MKOLUpdateElement) getItem(index);
		    view = View.inflate(OfflineDemo.this, R.layout.offline_localmap_list,null);
		    initViewItem(view,e);
			return view;
		}
		
		void initViewItem(View view , final MKOLUpdateElement e){
			Button display = (Button)view.findViewById(R.id.display);
			Button remove  = (Button)view.findViewById(R.id.remove); 
			TextView title = (TextView)view.findViewById(R.id.title);
			TextView update = (TextView)view.findViewById(R.id.update);
			TextView ratio = (TextView)view.findViewById(R.id.ratio);
			ratio.setText(e.ratio+"%");
			title.setText(e.cityName);
			if ( e.update){
				update.setText("�ɸ���");
			}
			else{
				update.setText("����");
			}
			if ( e.ratio != 100 ){
				display.setEnabled(false);
			}
			else{
				display.setEnabled(true);
			}
			remove.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {				
					 mOffline.remove(e.cityID);
                     updateView();
				}
			});
			display.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.putExtra("x", e.geoPt.getLongitudeE6() );
					intent.putExtra("y", e.geoPt.getLatitudeE6());
					intent.setClass(OfflineDemo.this, BaseMapDemo.class);
					startActivity(intent);
				}
			});
		}
		
	}

}