package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/sql_test")
public class SqlTestServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (UserDao userDao = new UserDao(getConnection(req.getServletContext()))) {

            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date chosen = formatter.parse("01/01/2019");
            User user = userDao.getUserByName("Jancsi");
            boolean attended = userDao.hasAttended(user, chosen);

            req.setAttribute("msg", attended);
            req.getRequestDispatcher("sql_test.jsp").forward(req, resp);

        } catch (SQLException e) {
            handleError(e, req, resp);
        } catch (ParseException e) {
            handleError(e, req, resp);
        }
    }
}
