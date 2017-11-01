/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.net.URL;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;

/**
 *
 * @author guest1Day
 */
public class Jason {
    
    public JsonNode getResult(String request) throws IOException{
        //URL変数を設定
     try{
         //yahooサーバーへの接続
         //urlの中に入力フォームから入手したParameterをつける
        URL url = new URL(request);
        HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
        urlcon.setRequestMethod("GET");
        urlcon.setInstanceFollowRedirects(false);
        urlcon.connect();
        
        //BufferedReader、StringBufferの変数を設定
        BufferedReader reader = new BufferedReader(new InputStreamReader( urlcon.getInputStream()) );
        StringBuffer responseBuffer = new StringBuffer();
        
        //取得結果を1行ずつ取り出して、responsebufferに入れる
        while(true){
            String line = reader.readLine();
            if(line==null){
                break;
            }
            responseBuffer.append(line);
        }
        reader.close();
        urlcon.disconnect();
        
        //Json文字列をUTF-8文字列へ変換する
        JsonNode response = getJsonNode( responseBuffer.toString() );
        
        //戻り値としてJsonNodeを返す
        return response;
     }catch(SocketTimeoutException e){
         throw new IllegalArgumentException("ソケットの読み込みもしくは受け入れでタイムアウトが発生しました。");
     }catch(UnknownServiceException e){
         throw new IllegalArgumentException("プロトコルが入力をサポートしていません。");
     }
    }
 /**
 * URLconnecitonでgetした文字列をUTF-8の文字列へ変換する(?)
 * @author guest1Day
 */
    public JsonNode getJsonNode(String jsonString){
        JsonNode head = null;
        try{
            JsonFactory jf = new JsonFactory();
            JsonParser jp = jf.createParser(jsonString);
            ObjectMapper mapper = new ObjectMapper();
            head = mapper.readTree(jp);
        return head;
        }catch(Exception e){
            throw new IllegalArgumentException("メソッドJsonNodeでエラーが発生しました。");
        }
    }
}
