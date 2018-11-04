/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author guest1Day
 */
public class DeleteResult extends HttpServlet {

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
        HttpSession session = request.getSession();
        try  {
            UserDataBeans udb = (UserDataBeans)session.getAttribute("loginUser");
            
            //カートリストを削除する
            ArrayList<ItemDataDTO> deleteItemDatas = new ArrayList<ItemDataDTO>();
            deleteItemDatas = new ItemDataDAO().SearchByItemCode(udb.getUserID(),"");
            
            ArrayList<Integer> deleteItemIDs = new ArrayList<Integer>();
            for(ItemDataDTO deleteItemData : deleteItemDatas){
                deleteItemIDs.add(deleteItemData.getCartID());
            }
            
            new ItemDataDAO().Delete(deleteItemIDs);
            
            //購入履歴を削除する
            new BuyHistoryDataDAO().Delete(udb.getUserID());
            
            //ユーザー情報を削除する
            new UserDataDAO().Delete( udb.getUserID() );
            session.removeAttribute("loginUser");
            
            request.getRequestDispatcher("/deleteresult.jsp").forward(request,response);
        }catch(Exception e){
            request.setAttribute("error", e.getMessage() );
            request.getRequestDispatcher("/error.jsp").forward(request,response);
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
