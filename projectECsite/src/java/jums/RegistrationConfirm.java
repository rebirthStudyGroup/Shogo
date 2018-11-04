/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author guest1Day
 */
public class RegistrationConfirm extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
        
        HttpSession hs = request.getSession();
        String ac = (String)( request.getParameter("ac") );
        if(ac==null || Integer.parseInt(ac)!=(Integer)hs.getAttribute("ac")){
                request.setAttribute("error", "不正アクセスです");
                request.getRequestDispatcher("/error.jsp").forward(request,response);
            }
        
        try{
            String name = (String)request.getParameter("name").trim();
            String pass = (String)request.getParameter("pass").trim();
            String address = (String)request.getParameter("address").trim();
            String mail = (String)request.getParameter("mail").trim();
            String check = "";
            
            UserDataBeans udb = new UserDataBeans();
            udb.setName(name);
            udb.setPass(pass);
            udb.setAddress(address);
            udb.setMail(mail);
            //入力内容に不備がないか確認
            check = udb.check();           
            
            //入力したアドレスが既に使用済みでないか確認
            UserDataBeans checkUdb = new UserDataDAO().searchByMail(udb.getMail());
            if( checkUdb != null && checkUdb.getMail().equals(mail)){
                check +=udb.getMail()+"は既に登録済みのメールアドレスです。" ;
            }
            
            request.setAttribute("check",check);
            hs.setAttribute("mode", "reinput");
            hs.setAttribute("udb",udb);
            request.getRequestDispatcher("/registrationconfirm.jsp").forward(request,response);
        }catch(Exception e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
