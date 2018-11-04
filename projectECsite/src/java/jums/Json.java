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
import java.util.ArrayList;

/**
 *
 * @author guest1Day
 */
public class Json {
    
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
    
    /*
    ** jsonノードの中身を配列に格納する
    */
    public ArrayList<ItemDataBeans> jsonNodeIntoList(JsonNode jsonNode){
        ArrayList<ItemDataBeans> list = new ArrayList<ItemDataBeans>();
        String totalResultsReturned = jsonNode.get("ResultSet").get("totalResultsReturned").asText();
        for(int i = 0; i<Integer.parseInt( totalResultsReturned ); i++){
        ItemDataBeans ib = new ItemDataBeans();
        ib.setName( jsonNode.get("ResultSet").get("0").get("Result").get(String.valueOf(i)).get("Name").asText() );
        ib.setImage( jsonNode.get("ResultSet").get("0").get("Result").get(String.valueOf(i)).get("Image").get("Medium").asText() );
        ib.setBrand( jsonNode.get("ResultSet").get("0").get("Result").get(String.valueOf(i)).get("Brands").get("Name").asText() );
        ib.setPrice( jsonNode.get("ResultSet").get("0").get("Result").get(String.valueOf(i)).get("Price").get("_value").asText() );
        ib.setReviewRate( jsonNode.get("ResultSet").get("0").get("Result").get(String.valueOf(i)).get("Review").get("Rate").asText() );
        ib.setReviewCount( jsonNode.get("ResultSet").get("0").get("Result").get(String.valueOf(i)).get("Review").get("Count").asText() );
        list.add(ib);        
        }
        return list;
    }

    /*
    ** jsonノードの中身の数を調べる
    */
    public int totalResultsAvailable(JsonNode jsonNode){
        return Integer.parseInt( jsonNode.get("ResultSet").get("totalResultsAvailable").asText() );
    }
    
}

