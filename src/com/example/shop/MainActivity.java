package com.example.shop;

import java.util.ArrayList;
import android.R;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listplaceholder);
        
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
      
       
        JSONObject json = JSONfunctions.getJSONfromURL("http://10.0.2.2/articlesJson.php");
        Log.e("log_tag", "jsojnn"); 
        try{
        	
        	JSONArray  Article = json.getJSONArray("Article");
            Log.i("log_info", "aaaaaaaaa"); 

	        for(int i=0;i<Article.length();i++){						
				HashMap<String, String> map = new HashMap<String, String>();	
				JSONObject e = Article.getJSONObject(i);
				
				//map.put("id",  String.valueOf(i));
	        	map.put("idArticle", "idArticle :" + e.getString("idArticle"));
	        	map.put("libelle", "libelle: " +  e.getString("libelle"));
	        	mylist.add(map);			
			}		
        }catch(JSONException e)        {
        	 Log.e("log_tag", "Error parsing data "+e.toString());
        }
        
        ListAdapter adapter = new SimpleAdapter(this, mylist , R.layout.activity_main, 
                        new String[] { "idArticle", "idArticle" }, 
                        new int[] { R.id.item_title, R.id.item_subtitle });
        
        setListAdapter(adapter);
        
        final ListView lv = getListView();
        lv.setTextFilterEnabled(true);	
        lv.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {        		
        		@SuppressWarnings("unchecked")
				HashMap<String, String> o = (HashMap<String, String>) lv.getItemAtPosition(position);	        		
        		Toast.makeText(MainActivity.this, "ID '" + o.get("idArticle") + "' was clicked.", Toast.LENGTH_SHORT).show(); 

			}
		});
    }
}