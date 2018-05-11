package todo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.TodoDAO;
import todo.dto.Todo;

/**
 * 検索機能。タスク一覧を取得し、一覧結果へフォワードする。
 */
@WebServlet("/todo/search")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
          HttpServletResponse response) throws ServletException, IOException {

        // DAOの取得
        try(TodoDAO dao = new TodoDAO()) {
            // タスクのリストを一覧で取得し、リクエスト属性へ格納する
            List<Todo> list = dao.todoList();

            request.setAttribute("todoList", list);
        } catch (Exception e) {
            throw new ServletException(e);
        }

        // 検索一覧を表示する
        RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
