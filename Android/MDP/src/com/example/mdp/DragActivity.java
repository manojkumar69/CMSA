package com.example.mdp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;





import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
//Device,Voice,photo,preview,video,v preview,contact,mail,enc,dec,gps,winfo,msg-s,msg-g
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi") 
public class DragActivity extends Activity implements services{
        /** Called when the activity is first created. */
	TableLayout allServiceLayout;
	SharedPreferences pref;
	TextView name;
	List<String> serviceList;
	String services,smstype = null;;
        @SuppressLint("NewApi")
		@Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.dragactivity);
          
                findViewById(R.id.email1).setOnTouchListener(new MyTouchListener());
                findViewById(R.id.encrypt1).setOnTouchListener(new MyTouchListener());
                findViewById(R.id.voice1).setOnTouchListener(new MyTouchListener());
                findViewById(R.id.videopreview1).setOnTouchListener(new MyTouchListener());
                findViewById(R.id.photopreview1).setOnTouchListener(new MyTouchListener());
                findViewById(R.id.takephoto1).setOnTouchListener(new MyTouchListener());
                findViewById(R.id.contact1).setOnTouchListener(new MyTouchListener());
                findViewById(R.id.decrypt1).setOnTouchListener(new MyTouchListener());
                findViewById(R.id.gps1).setOnTouchListener(new MyTouchListener());
                findViewById(R.id.sms1).setOnTouchListener(new MyTouchListener());
                findViewById(R.id.winfo1).setOnTouchListener(new MyTouchListener());
                findViewById(R.id.takevideo1).setOnTouchListener(new MyTouchListener());
                findViewById(R.id.status1).setOnTouchListener(new MyTouchListener());
                findViewById(R.id.groupmsg1).setOnTouchListener(new MyTouchListener());
//                findViewById(R.id.topleft).setOnDragListener(new MyDragListener());
                findViewById(R.id.topright).setOnDragListener(new MyDragListener());
//                findViewById(R.id.bottomleft).setOnDragListener(new MyDragListener());
                findViewById(R.id.bottomright).setOnDragListener(new MyDragListener());
                allServiceLayout= (TableLayout) findViewById(R.id.bottomright);
                pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
    			name = (TextView) findViewById(R.id.username1);
    			name.setText("Welcome  " + pref.getString("username", null));
    			servMap.put("email1","Email" );
    			servMap.put("groupmsg1","SMS" );
    			servMap.put("status1","DS" );
    			servMap.put("takevideo1", "RV");
    			servMap.put("winfo1", "WInfo");
    			servMap.put("sms1","SMS" );
    			servMap.put("gps1", "GPSLoc");
    			servMap.put("decrypt1","SD" );
    			servMap.put("contact1", "CL");
    			servMap.put("takephoto1","TP" );
    			servMap.put("photopreview1","PP" );
    			servMap.put("videopreview1","VP" );
    			servMap.put("voice1","VR" );
    			servMap.put("encrypt1", "SE");
                allServiceLayout.setOnClickListener(new OnClickListener() {
					
                	
					public void onClick(View v) { 
						// TODO Auto-generated method stub
						gettingSelectedServices();
						}
				}); 
        }
        public void gettingSelectedServices(){
        	serviceList=new ArrayList<String>();
			for (int i=0;allServiceLayout.getChildCount()>i;i++){
				 if (allServiceLayout.getChildAt(i) instanceof ImageView) {
					
					 ImageView view=(ImageView) allServiceLayout.getChildAt(i);
						serviceList.add(view.getResources().getResourceEntryName(view.getId()));	
				}
			 }
			
			
			List<String> checkservice=new ArrayList<String>();
			for(String ser:serviceList){
				checkservice.add(servMap.get(ser));	
			}
			Toast.makeText(getApplicationContext(),"myservicelist==>"+ checkservice.toString(), Toast.LENGTH_LONG).show();
			
			Boolean status = AvailableCombinationsActivity.checkCombinations(checkservice);
			Toast.makeText(getApplicationContext(),"status==>" +status, Toast.LENGTH_LONG).show();
			if(status)
			nextActivity();
			else
				Toast.makeText(getApplicationContext(), "Plz Select Other Combinations" + checkservice,
						Toast.LENGTH_LONG).show();
        }
        public void nextActivity(){
        	Bundle b=new Bundle();
			
			b.putStringArrayList("ser", (ArrayList<String>) serviceList); 
			Intent intent=new Intent(getApplicationContext(), SelectedServices.class);
			intent.putExtras(b);
			startActivity(intent); 
			b.clear();
        }
        private final class MyTouchListener implements OnTouchListener {
        	          
                @SuppressLint("NewApi")
				public boolean onTouch(View view, MotionEvent motionEvent) {
                	

//                	Toast.makeText(getApplicationContext(),"ID===>"+ 
//                        	view.getId(), Toast.LENGTH_SHORT).show();
//                	Toast.makeText(getApplicationContext(),motionEvent.getAction()+"==="+
//                			MotionEvent.ACTION_DOWN, Toast.LENGTH_SHORT).show();
                	
                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                ClipData data = ClipData.newPlainText("prabaharan", "javaprofessional");                                
                                 DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                                                view);
                                view.startDrag(data, shadowBuilder, view, 0);
//                                view.setVisibility(View.INVISIBLE);
                                return true;
                        } 
                        else {
                                return false;
                        }
                       
                }
        }

        class MyDragListener implements OnDragListener {
                Drawable enterShape = getResources().getDrawable(
                                R.drawable.shape_droptarget);
                Drawable normalShape = getResources().getDrawable(R.drawable.shape);

                @Override
                public boolean onDrag(View v, DragEvent event) {
//                	Toast.makeText(getApplicationContext(), "getclipdata===>"+event.getClipData().toString(), Toast.LENGTH_SHORT).show();
//                	Toast.makeText(getApplicationContext(), "getclipdescription===>"+event.getClipDescription().toString(), Toast.LENGTH_SHORT).show();
                        int action = event.getAction();
                        switch (event.getAction()) {
                        case DragEvent.ACTION_DRAG_STARTED:
                        	
                                // do nothing
                                break;
                        case DragEvent.ACTION_DRAG_ENTERED:
                       
                                v.setBackgroundDrawable(enterShape);
                                break;
                        case DragEvent.ACTION_DRAG_EXITED:
//                        	Toast.makeText(getApplicationContext(), "exited", 0).show();
//                        	View view1 = (View) event.getLocalState();  
//                        	view1.setVisibility(View.INVISIBLE);
                                v.setBackgroundDrawable(normalShape);
                                break;
                        case DragEvent.ACTION_DROP:
                                 // Dropped, reassign View to ViewGroup
//                        	 LayoutInflater layoutInflater = 
//                             (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                           final View addView = layoutInflater.inflate(R.layout.row, null);
                                View view = (View) event.getLocalState();
                                
                               
                                ViewGroup owner = (ViewGroup) view.getParent();
                               
                                owner.removeView(view);
//                                Toast.makeText(getApplicationContext(), v.getParent().toString(),1).show();
                                TableLayout container = (TableLayout) v;
//                                view.setTag(view.getId(), "settag");
//                                Toast.makeText(getApplicationContext(),(String)view.getTag(view.getId()), 0).show();
//                                Toast.makeText(getApplicationContext(), 
//                                v.getResources().getResourceEntryName(R.id.table1)+"=="+
//                                v.getResources().getResourceName(R.id.table1)+"=="+
//                                v.getResources().getResourcePackageName(R.id.table1)+"=="+
//                                v.getResources().getResourceTypeName(R.id.table1), 1).show();
                   
                                container.addView(view);
                                view.setVisibility(View.VISIBLE);
                                break;
                        case DragEvent.ACTION_DRAG_ENDED:
//                        	Toast.makeText(getApplicationContext(), "ended", 0).show();
                                v.setBackgroundDrawable(normalShape);
                        default:
                                break;
                        }
                        return true;
                }
        }
        @Override
    	public boolean onCreateOptionsMenu(Menu menu) {

    		// Inflate the menu; this adds items to the action bar if it is present.
    		getMenuInflater().inflate(R.menu.main, menu);
    		return true;
    	}
        @Override
    	public void onBackPressed() {
    		// TODO Auto-generated method stub
    		
    		Toast.makeText(getApplicationContext(), "you need to Logout your account",
    				Toast.LENGTH_LONG).show();
    	}
        @Override
    	public boolean onOptionsItemSelected(MenuItem item) {
    		// Handle action bar item clicks here. The action bar will
    		// automatically handle clicks on the Home/Up button, so long
    		// as you specify a parent activity in AndroidManifest.xml.
    		int id = item.getItemId();
    		switch (id) {
    		case R.id.action_avail_comb:

    			Intent intent1 = new Intent(this, AvailableCombinationsActivity.class);
    			startActivity(intent1);
    			return true;
    		case R.id.action_check_comb:
    			try {
    				gettingSelectedServices();
    			} catch (Exception e) {
    				Toast.makeText(getApplicationContext(), "Ser-Ex==>" + e, Toast.LENGTH_LONG).show();
    			}
    			return true;
    		case R.id.action_scan:
    			Intent intent = new Intent(this, ScanQRCodeActivity.class);
    			startActivity(intent);
    			return true;
    		case R.id.action_logout:
    			SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
    			pref.edit().clear().commit();
    			Intent inten = new Intent(this, SigninActivity.class);
    			startActivity(inten);
    			return true;

    		default:
    			return super.onOptionsItemSelected(item);
    		}
    	}
}