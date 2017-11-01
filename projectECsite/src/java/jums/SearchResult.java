/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import com.fasterxml.jackson.databind.JsonNode;

/**
 *
 * @author guest1Day
 */
public class SearchResult extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        final String firstURL = "https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch?";
        final String appid = "dj00aiZpPXdramV0Y2lSUVRlbSZzPWNvbnN1bWVyc2VjcmV0Jng9ODU-";
        if(request.getParameter("query").equals("")){
            request.setAttribute("error", "検索ワードが未入力です。");
            request.getRequestDispatcher("/error.jsp").forward(request,response);
        }else{
            try {
                LinkedHashMap<String,String> params = new LinkedHashMap<String,String>();
                params.put("appid",appid);
                params.put("query", request.getParameter("query"));
                params.put("category_id", request.getParameter("category_id"));
                params.put("sort", request.getParameter("sort"));
                params.put("offset",request.getParameter("offset"));

                //parameterの埋め込み
                StringBuffer requestUrl = new StringBuffer();
                requestUrl.append(firstURL);
                for(Map.Entry<String,String> data:params.entrySet()){
                    String encodedResult = URLEncoder.encode(data.getValue(),"UTF-8");
                    //配列の最後のoffsetに来たら、”＆”じゃなくて空白を連結する
                    requestUrl.append(String.format( "%s=%s" ,data.getKey(), encodedResult )+(data.getKey().equals("offset") ? "":"&"));
                }

                //入力条件から検索結果をJsonNodeの形で取得し、各ParameterをarrayListに格納
                Json json = new Json();
                JsonNode root = json.getResult( requestUrl.toString());
                ArrayList<ItemDataBeans> itemList = new ArrayList<ItemDataBeans>();          
                itemList = json.jsonNodeIntoList(root);
                int totalResultsAvailable = json.totalResultsAvailable(root);

                HttpSession session = request.getSession();

                request.setAttribute("totalResultsAvailable",totalResultsAvailable);
                request.setAttribute("params",params);
                session.setAttribute("result",itemList);
                request.getRequestDispatcher("/searchresult.jsp").forward(request,response);
            }catch(Exception e){
                request.setAttribute("error",e.getMessage() );
                request.getRequestDispatcher("/error.jsp").forward(request,response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
