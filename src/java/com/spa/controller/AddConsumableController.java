/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.spa.controller;

import com.spa.dao.ConsumableDAO;
import com.spa.dto.ConsumableDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nguye
 */
@WebServlet(name = "AddConsumableController", urlPatterns = {"/AddConsumableController"})
public class AddConsumableController extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String id = request.getParameter("consumableID");
            String name = request.getParameter("name");
            String unit = request.getParameter("unit");
            String stockRaw = request.getParameter("stock");
            int stock = 0;

            // 🎯 VALIDATE INPUTS
            // 1. Validate Consumable ID: Phải có dạng "cs" theo sau bởi các chữ số (ví dụ: cs001, cs123)
            Pattern idPattern = Pattern.compile("^(?i)^cs\\d+$");
            if (id == null || id.isEmpty() || !idPattern.matcher(id).matches()) {
                request.setAttribute("ERROR", "Consumable ID phải có dạng 'cs' theo sau bởi các chữ số (ví dụ: cs001)!");
                request.getRequestDispatcher("/jsp/addConsumable.jsp").forward(request, response);
                return;
            }

            // 2. Validate Name và Unit: Không được rỗng
            if (name == null || name.trim().isEmpty()) {
                request.setAttribute("ERROR", "Name không được để trống!");
                request.getRequestDispatcher("/jsp/addConsumable.jsp").forward(request, response);
                return;
            }
            if (unit == null || unit.trim().isEmpty()) {
                request.setAttribute("ERROR", "Unit không được để trống!");
                request.getRequestDispatcher("/jsp/addConsumable.jsp").forward(request, response);
                return;
            }

            // 3. Validate Stock: Phải là số nguyên > 0
            try {
                stock = Integer.parseInt(stockRaw);
            } catch (NumberFormatException e) {
                request.setAttribute("ERROR", "Stock phải là số nguyên hợp lệ!");
                request.getRequestDispatcher("/jsp/addConsumable.jsp").forward(request, response);
                return;
            }
            if (stock <= 0) {
                request.setAttribute("ERROR", "Stock phải lớn hơn 0!");
                request.getRequestDispatcher("/jsp/addConsumable.jsp").forward(request, response);
                return;
            }

            // 🛑 CHECK ID ĐÃ TỒN TẠI
            ConsumableDAO dao = new ConsumableDAO();
            if (dao.getConsumableByID(id)) {  // Giả sử DAO có method getConsumableByID(String id) trả về DTO nếu tồn tại, null nếu không
                request.setAttribute("ERROR", "Consumable ID đã tồn tại!");
                request.getRequestDispatcher("/jsp/addConsumable.jsp").forward(request, response);
                return;
            }

            

            boolean ok = dao.insertConsumable(id, name, unit, stock);

            if (ok) {
                request.setAttribute("SUCCESS", "Consumable added successfully!");
            } else {
                request.setAttribute("ERROR", "Failed to add consumable!");
            }
            request.getRequestDispatcher("/jsp/addConsumable.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddConsumableController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddConsumableController.class.getName()).log(Level.SEVERE, null, ex);
        }
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