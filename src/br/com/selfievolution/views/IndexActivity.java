package br.com.selfievolution.views;

import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import br.com.selfievolution.R;
import br.com.selfievolution.controllers.IndexController;
import br.com.selfievolution.models.IndexModel;
import br.com.selfievolution.objects.FacebookResponse;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.google.gson.Gson;

public class IndexActivity extends ActionBarActivity {

	private IndexController controller;
	private IndexModel model;
	IndexActivity activity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		
		this.activity = this;

	 // start Facebook Login
		
		//LoginButton facebook = (LoginButton) findViewById(R.id.authButton);
		//facebook.setReadPermissions(Arrays.asList("public_profile"));
		
	    Session.openActiveSession(this, true, new Session.StatusCallback() {

	      // callback when session changes state
	      @Override
	      public void call(Session session, SessionState state, Exception exception) {
	        if (session.isOpened()) {

	          // make request to the /me API
	          Request.newMeRequest(session, new Request.GraphUserCallback() {

	            // callback after Graph API response with user object
	            @Override
	            public void onCompleted(GraphUser user, Response response) {
	            	if (user != null) {
	            		
						/*JSONObject obj = response.getGraphObject().getInnerJSONObject();
						Gson gson = new Gson();
						FacebookResponse resp = gson.fromJson(obj.toString(), FacebookResponse.class);*/
						
						//salvo os dados na session
						SharedPreferences pref = activity.getApplicationContext().getSharedPreferences("SelfieSession", 0); // 0 - for private mode
						Editor editor = pref.edit();
						editor.putBoolean("logado", true);
						editor.putString("nome", user.getFirstName());
						editor.commit();	            		
	            		System.out.println(user.getFirstName());
	            		
	            		controller = new IndexController(model, activity);
	            		controller.startApp();

	              }
	            }
	          }).executeAsync();
	        }
	      }
	    });

	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}	 
	
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_action_bar, menu);               

        return super.onCreateOptionsMenu(menu);
    }
    
    public void cadastrar(View v){
    	
    	Intent i = new Intent(this, UsuarioActivity.class);
    	startActivity(i);
    }
    
    public void entrar(View v){
    	
    	Intent i = new Intent(this, UsuarioActivity.class);
    	startActivity(i);
    }    
	
}
