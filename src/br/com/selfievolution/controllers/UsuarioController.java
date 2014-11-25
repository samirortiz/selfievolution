package br.com.selfievolution.controllers;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.EditText;
import android.widget.RadioGroup;
import br.com.selfievolution.R;
import br.com.selfievolution.models.AlunoModel;
import br.com.selfievolution.models.TreinadorModel;
import br.com.selfievolution.objects.Usuario;
import br.com.selfievolution.utils.UnixCrypt;
import br.com.selfievolution.views.HomeActivity;
import br.com.selfievolution.views.UsuarioActivity;

public class UsuarioController{
    
	private UsuarioActivity activity;
    private TreinadorModel modelTreinador;
    private AlunoModel modelAluno;
    
    public UsuarioController(TreinadorModel modelTreinador, AlunoModel modelAluno, UsuarioActivity activity){
        this.activity = activity;
        this.modelTreinador = modelTreinador;
        this.modelAluno = modelAluno;
    }
 
    public UsuarioActivity getActivity() {
        return activity;
    }
 
    public TreinadorModel getModelProfessor() {
        return modelTreinador;
    }    

    public AlunoModel getModelAluno() {
        return modelAluno;
    }        
    
    public void salvarUsuario(){
    	
    	Usuario usuario = new Usuario();
    	modelTreinador = new TreinadorModel(activity);
    	
    	final EditText nome = (EditText) activity.findViewById(R.id.nomeUsuario);
    	RadioGroup sexo = (RadioGroup) activity.findViewById(R.id.radioGroupSexo);
    	final EditText email = (EditText) activity.findViewById(R.id.emailUsuario);
    	EditText senha = (EditText) activity.findViewById(R.id.senhaUsuario);
    	
    	if(!checkEmail(email.getText().toString())){
    		
    		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setTitle("Erro!");
			builder.setMessage("O formato de email é inválido");
			builder.create().show();

    	} else{

        	final SharedPreferences pref = getActivity().getSharedPreferences("SelfieSession", 0); // 0 - for private mode
    		
	    	usuario.setNome(nome.getText().toString());
	    	usuario.setEmail(email.getText().toString());
	    	
	    	//encriptação da senha
	    	String crypt = UnixCrypt.crypt(email.getText().toString(),senha.getText().toString());
	    	usuario.setSenha(crypt);

			switch (sexo.getCheckedRadioButtonId()) {

			    case R.id.radioM:
		    		usuario.setSexo("Masculino");
			        break;
			    case R.id.radioF:
			    	usuario.setSexo("Feminino");
			        break;
			}    	
			
			final long idUsuario = modelTreinador.insert(usuario);
			
	    	if(idUsuario > 0){

	    		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				builder.setTitle("Sucesso!");
				builder.setMessage("Treinador cadastrado com sucesso!");
				builder.setPositiveButton("Entrar", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						//salvo os dados na session
			        	Editor editor = pref.edit();
			        	editor.putBoolean("logado", true);
			        	editor.putInt("id", (int)idUsuario);
			        	editor.putString("nome", nome.getText().toString());
			        	editor.putString("email", email.getText().toString());

			        	editor.commit();
			        	
			        	getActivity().finish();		
			        	
			        	Intent i = new Intent(getActivity(), HomeActivity.class);
			        	getActivity().startActivity(i);

					}
				});
				
				builder.create().show();

	    	}else{
	    		
	    		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				builder.setTitle("Erro!");
				builder.setMessage("Email já está sendo utilizado!!");
				
				builder.create().show();
	    	}
    	}
    }
    
    
    public void salvarAluno(){
    	
    	Usuario aluno = new Usuario();
    	modelAluno = new AlunoModel(activity);
    	
    	SharedPreferences pref = getActivity().getSharedPreferences("SelfieSession", 0); // 0 - for private mode

    	EditText nome = (EditText) activity.findViewById(R.id.nomeAluno);
    	RadioGroup sexo = (RadioGroup) activity.findViewById(R.id.sexoAluno);
    	EditText email = (EditText) activity.findViewById(R.id.emailALuno);
    	
    	if(!checkEmail(email.getText().toString())){
    		
    		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setTitle("Erro!");
			builder.setMessage("O formato de email é inválido");
			builder.create().show();

    	} else{

    		aluno.setNome(nome.getText().toString());
    		aluno.setEmail(email.getText().toString());
    		aluno.setEmailTreinador(pref.getString("email", null));
	    	
    		aluno.setIdRole(2);

			switch (sexo.getCheckedRadioButtonId()) {

			    case R.id.radioM:
			    	aluno.setSexo("male");
			        break;
			    case R.id.radioF:
			    	aluno.setSexo("female");
			        break;
			}
			
			long idAluno = modelAluno.insert(aluno);
			
	    	if(idAluno > 0){

	    		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				builder.setTitle("Sucesso!");
				builder.setMessage("Aluno cadastrado com sucesso!");
				builder.setNeutralButton("OK", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						builder.create().dismiss();
						
						getActivity().finish();
						
						Intent i = new Intent(getActivity(), HomeActivity.class);
						getActivity().startActivity(i);
						
					}
				});
				
				builder.create().show();
	        	
				
	    	}else{
	    		
	    		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				builder.setTitle("Erro!");
				builder.setMessage("Email já está sendo utilizado!!");
				
				builder.create().show();
	    	}
    	}
    }
    
    public boolean checkEmail(String email){
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }
    
}
