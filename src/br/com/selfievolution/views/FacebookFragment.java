package br.com.selfievolution.views;

import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.selfievolution.R;
import br.com.selfievolution.controllers.IndexController;
import br.com.selfievolution.models.IndexModel;
import br.com.selfievolution.objects.Usuario;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

public class FacebookFragment extends Fragment {

	private static final String TAG = "MainFragment";
	private UiLifecycleHelper uiHelper;
	private IndexActivity activity;
	private IndexModel model;
	private IndexController controller;
	SharedPreferences pref;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	    
	    this.activity = (IndexActivity) getActivity();
	}
	
	@Override
	public void onResume() {
	    super.onResume();

	    // For scenarios where the main activity is launched and user
	    // session is not null, the session state change notification
	    // may not be triggered. Trigger it if it's open/closed.
	    //Session session = Session.getActiveSession();
	    //if (session != null && (session.isOpened() || session.isClosed()) ) {
	      //  onSessionStateChange(session, session.getState(), null);
	    //}

	    uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    	View view = inflater.inflate(R.layout.activity_index, container, false);

	    	LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
	    	authButton.setReadPermissions(Arrays.asList("email"));
	    	authButton.setFragment(this);
	    	
	    return view;
	}	
	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {

		pref = activity.getSharedPreferences("SelfieSession", 0); // 0 - for private mode		
		LoginButton button = (LoginButton) activity.findViewById(R.id.authButton);

		if (state.isOpened()) {
	        Log.i(TAG, "Logged in...");
	        button.setText("Sair");
	        
	        final Session requestSession = session;
            Request request = Request.newMeRequest(session, new Request.GraphUserCallback(){
                @Override
                public void onCompleted(GraphUser user, Response response){
                    if(requestSession == Session.getActiveSession() && user != null){
            			System.out.println(user.toString());
                    	//salvo os dados na session
                		Editor editor = pref.edit();

            			editor.putBoolean("logado", true);
            			editor.putString("id_facebook", user.getId().toString());
            			editor.putString("nome", user.getFirstName().toString());
            			editor.commit();	            		

            			//System.out.println(Session.getActiveSession().getPermissions()); 
            			
            			//System.out.println(user.getProperty("email").toString());
            			
            			Usuario usuario = new Usuario();
            			usuario.setNome(user.getName().toString());
            			usuario.setSexo(user.getProperty("gender").toString());
            			usuario.setEmail(user.getProperty("email").toString());
            			
            			controller = new IndexController(model, activity);
            			controller.startApp();
            			
            			activity.finish();
            			
                    }else{
                        Log.e("facebook", "Error when making newMeRequest");
                    }
                }
            });

            request.executeAsync();	        
	        
	    } else if (state.isClosed()) {
	        Log.i(TAG, "Logged out...");
	    }
	}
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};	
		

}
