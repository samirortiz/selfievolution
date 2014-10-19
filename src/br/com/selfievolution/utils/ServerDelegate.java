package br.com.selfievolution.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ServerDelegate {

	//private static String urlServidor = "http://agronet.agrobrasilseguros.com.br/rotinas/agrows.php";
	//private static String urlServidor = "http://agronet.samir.dev.com:81/agrows.php";
	private static String urlServidor = "http://10.0.0.13/selfie/ws.php";

	private static InputStream getWebData(String url) {

		HttpClient client = new DefaultHttpClient();
		HttpResponse response;
		try {
			HttpGet get = new HttpGet(url);

			response = client.execute(get);

			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			if (response != null) {
				InputStream in = response.getEntity().getContent();
				return in;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	private static InputStream postWebData(String url, String json) {

		HttpClient client = new DefaultHttpClient();
		HttpResponse response;
		try {
			HttpPost post = new HttpPost(url);
			post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json;charset=UTF-8");
			post.getParams().setParameter("jsonpost", json);            
            
			StringEntity se = new StringEntity(json);
			se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			se.setContentType(new BasicHeader(HTTP.CHARSET_PARAM, "utf-8"));

			post.setEntity(se);

			response = client.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			if (response != null) {
				InputStream in = response.getEntity().getContent();
				return in;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <E> String sincronizarProfessores(ArrayList<E> dados) {

		try {

			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			String json = gson.toJson(dados);

			InputStream is = postWebData(urlServidor, json);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			StringBuilder str = new StringBuilder();
			String line = null;

			while ((line = reader.readLine()) != null) {
				str.append(line);
			}

			System.out.println(str);
			return str.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return null;

	}	
	
	public static <E> String sincronizarAlunos(ArrayList<E> dados) {

		try {

			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			String json = gson.toJson(dados);

			InputStream is = postWebData(urlServidor, json);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			StringBuilder str = new StringBuilder();
			String line = null;

			while ((line = reader.readLine()) != null) {
				str.append(line);
			}

			System.out.println(str);
			return str.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return null;

	}		
	
	
/*
 * 
	//valida a empresa no WS
	public static Empresa getEmpresa(String idEmpresa, String codAcesso) {

		try {
			Log.d("ServerDelegate", "Comunicando com o servidor...");

			InputStream is = getWebData(urlServidor + "?get=empresa&id="+idEmpresa+"&cod="+codAcesso);

			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			StringBuilder str = new StringBuilder();
			String line = null;
			
			while ((line = reader.readLine()) != null ) {
				str.append(line);
			}

			Empresa empresa = new Empresa();

			JSONObject obj = new JSONObject(str.toString());
			JSONArray emp = obj.getJSONArray("empresa");

			for (int i = 0; i < emp.length(); i++) {
				Gson gson = new Gson();
				JSONObject child = emp.getJSONObject(i);

				empresa = gson.fromJson(child.toString(), Empresa.class);
				Log.d("ServerDelegate", empresa.getNome());
			}
			
			return empresa;

		} catch (IOException e) {
			Log.d("ServerDelegate", "Erro!");
			e.printStackTrace();
		}catch (JSONException e) {
			Log.d("ServerDelegate", "Erro!");
			e.printStackTrace();			
		}catch (NullPointerException e) {
			Log.d("ServerDelegate", "Erro!");
			e.printStackTrace();	
		}

		return null;
	}


	//busca a lista de usuários
	public static ArrayList<Usuario> getUsuarios(Integer idEmpresa) {

		try {
			InputStream is = getWebData(urlServidor + "?get=usuarios&empresa="+idEmpresa);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			StringBuilder str = new StringBuilder();
			String line = null;

			while ((line = reader.readLine()) != null ) {
				str.append(line);
			}

			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

			JSONObject obj = new JSONObject(str.toString());
			JSONArray usu = obj.getJSONArray("usuarios");

			for (int i = 0; i < usu.length(); i++) {
				Gson gson = new Gson();
				JSONObject child = usu.getJSONObject(i);

				Usuario usuario = gson.fromJson(child.toString(), Usuario.class);
				usuarios.add(usuario);
			}

			return usuarios;

		} catch (IOException e) {
			Log.d("Teste", "Erro!");
			e.printStackTrace();
		}catch (JSONException e) {
			Log.d("Teste", "Erro!");
			e.printStackTrace();			
		}

		return null;
	}	

	//busca a lista de vistorias
	public static ArrayList<Vistoria> getVistorias(Integer idEmpresa) {

		try {
			InputStream is = getWebData(urlServidor + "?get=vistorias&empresa="+idEmpresa);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			StringBuilder str = new StringBuilder();
			String line = null;

			while ((line = reader.readLine()) != null) {
				str.append(line);
			}

			ArrayList<Vistoria> vistorias = new ArrayList<Vistoria>();
			
			if(!str.toString().equals("null")){

				JSONObject obj = new JSONObject(str.toString());
			
				JSONArray usu = obj.getJSONArray("vistorias");

				for (int i = 0; i < usu.length(); i++) {
					Gson gson = new Gson();
					JSONObject child = usu.getJSONObject(i);

					Vistoria vistoria = gson.fromJson(child.toString(), Vistoria.class);
					vistorias.add(vistoria);
				}
			}
			
			return vistorias;

		} catch (IOException e) {
			Log.d("Teste", "Erro!");
			e.printStackTrace();
		}catch (JSONException e) {
			Log.d("Teste", "Erro!");
			e.printStackTrace();			
		}

		return null;
	}
	
	//busca a lista de variedades
	public static ArrayList<Variedade> getVariedades() {

		try {
			InputStream is = getWebData(urlServidor + "?get=variedades");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			StringBuilder str = new StringBuilder();
			String line = null;

			while ((line = reader.readLine()) != null) {
				str.append(line);
			}

			ArrayList<Variedade> variedades = new ArrayList<Variedade>();
			
			if(!str.toString().equals("null")){

				JSONObject obj = new JSONObject(str.toString());
			
				JSONArray usu = obj.getJSONArray("variedades");

				for (int i = 0; i < usu.length(); i++) {
					Gson gson = new Gson();
					JSONObject child = usu.getJSONObject(i);

					Variedade variedade = gson.fromJson(child.toString(), Variedade.class);
					variedades.add(variedade);
				}
			}
			
			return variedades;

		} catch (IOException e) {
			Log.d("Teste", "Erro!");
			e.printStackTrace();
		}catch (JSONException e) {
			Log.d("Teste", "Erro!");
			e.printStackTrace();			
		}

		return null;
	}	
	
	//busca a lista de produtos
	public static ArrayList<Produto> getProdutos() {

		try {
			InputStream is = getWebData(urlServidor + "?get=produtos");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			StringBuilder str = new StringBuilder();
			String line = null;

			while ((line = reader.readLine()) != null) {
				str.append(line);
			}

			ArrayList<Produto> produtos = new ArrayList<Produto>();
			
			if(!str.toString().equals("null")){

				JSONObject obj = new JSONObject(str.toString());
			
				JSONArray usu = obj.getJSONArray("produtos");

				for (int i = 0; i < usu.length(); i++) {
					Gson gson = new Gson();
					JSONObject child = usu.getJSONObject(i);

					Produto produto = gson.fromJson(child.toString(), Produto.class);
					produtos.add(produto);
				}
			}
			
			return produtos;

		} catch (IOException e) {
			Log.d("Teste", "Erro!");
			e.printStackTrace();
		}catch (JSONException e) {
			Log.d("Teste", "Erro!");
			e.printStackTrace();			
		}

		return null;
	}	

	//busca a lista de produtos variedades
	public static ArrayList<ProdutoVariedade> getProdutosVariedades() {

		try {
			InputStream is = getWebData(urlServidor + "?get=produtos_variedades");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			StringBuilder str = new StringBuilder();
			String line = null;

			while ((line = reader.readLine()) != null) {
				str.append(line);
			}

			ArrayList<ProdutoVariedade> produtosVariedades = new ArrayList<ProdutoVariedade>();
			
			if(!str.toString().equals("null")){

				JSONObject obj = new JSONObject(str.toString());

				JSONArray usu = obj.getJSONArray("produtos_variedades");

				for (int i = 0; i < usu.length(); i++) {
					Gson gson = new Gson();
					JSONObject child = usu.getJSONObject(i);

					ProdutoVariedade produtoVariedade = gson.fromJson(child.toString(), ProdutoVariedade.class);						
					produtosVariedades.add(produtoVariedade);
				}
			}
			
			return produtosVariedades;

		} catch (IOException e) {
			Log.d("Teste", "Erro!");
			e.printStackTrace();
		}catch (JSONException e) {
			Log.d("Teste", "Erro!");
			e.printStackTrace();			
		}

		return null;
	}		
	
	

	public static String transmitirLaudo(Lvp001 laudo) {
		
		try {

			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			String json = gson.toJson(laudo);

			InputStream is = postWebData(urlServidor, json);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			StringBuilder str = new StringBuilder();
			String line = null;

			while ((line = reader.readLine()) != null) {
				str.append(line);
			}

			//System.out.println(str);
			return str.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return null;

	}		
*/
}
