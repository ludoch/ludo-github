package com.google.ludo.appengine.jpademo.ludoengine;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.ludo.appengine.jpademo.javasejpa.Player;
import javax.persistence.Persistence;

/**
 *
 * @author ludo@google.com
 */
public class LudoengineServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("javaseJPAPU");
        EntityManager em = emf.createEntityManager();

        if (action == null) {
            listPlayers(em, out);
        } else if (action.equals("add")) {
            addPlayers(em, out);
        } else if (action.equals("delete")) {
            deletePlayers(em, out);
        }
    }

    private void addPlayers(EntityManager em, PrintWriter out) {
        em.getTransaction().begin();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        em.persist(new Player("Devoxx Player " + dateFormat.format(date)));
        em.getTransaction().commit();
        out.write("new Player added.");

    }

    private void deletePlayers(EntityManager em, PrintWriter out) {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Player m").executeUpdate();
        em.getTransaction().commit();
        out.write("Players deleted.");
    }

    private void listPlayers(EntityManager em, PrintWriter out) {
        List<Player> players = em.createQuery("Select p from Player p").getResultList();
        try {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LudoengineServlet</title>");
            out.println("</head>");
            out.println("<body>");
            if (players.isEmpty()) {
                out.println("No Player to list... ");
            } else {
                out.println("<h1>Current players are " + "</h1>");
                for (Player p : players) {
                    out.println("<li>Player: " + p.getName() + "</li>");
                }
            }
            out.println("</body>");
            out.println("</html>");

        } finally {
            out.close();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
