/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guest1Day
 */
public class Common extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            
        LinkedHashMap<String,String> category = new LinkedHashMap<String,String>();
        category.put("1","すべてのカテゴリから");
        category.put("13457","ファッション");
        category.put("2498", "食品");
        category.put("2500", "ダイエット、健康");
        category.put("2501", "コスメ、香水");
        category.put("2502", "パソコン、周辺機器");
        category.put("2504", "AV機器、カメラ");
        category.put("2505", "家電");
        category.put("2506", "家具、インテリア");
        category.put("2507", "花、ガーデニング");
        category.put("2508", "キッチン、生活雑貨、日用品");
        category.put("2503", "DIY、工具、文具");
        category.put("2509", "ペット用品、生き物");
        category.put("2510", "楽器、趣味、学習");
        category.put("2511", "ゲーム、おもちゃ");
        category.put("2497", "ベビー、キッズ、マタニティ");
        category.put("2512", "スポーツ");
        category.put("2513", "レジャー、アウトドア");
        category.put("2514", "自転車、車、バイク用品");
        category.put("2516", "CD、音楽ソフト");
        category.put("2517", "DVD、映像ソフト");
        category.put("10002", "本、雑誌、コミック");
            
        LinkedHashMap<String,String> sort = new LinkedHashMap<String,String>();
        sort.put("-score" , "おすすめ順");
        sort.put("+price" , "商品価格が安い順");
        sort.put("-price" , "商品価格が高い順");
        sort.put("+name" , "ストア名昇順");
        sort.put("-name" , "ストア名降順");
        sort.put("-sold" , "売れ筋順");
        
        String appid="sennseikou_0916";
        
        request.setAttribute("category",category);
        request.setAttribute("sort",sort);
        request.setAttribute("appid",appid);
        request.getRequestDispatcher("/search.jsp").forward(request,response);
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
