package todo.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.TodoDAO;
import todo.dto.Todo;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/todo/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String title =request.getParameter("title");
		String task =request.getParameter("task");
		String inputLimitdate=request.getParameter("limitdate");
		String userid=request.getParameter("userid");
		int status=Integer.parseInt(request.getParameter("status"));

		Todo dto = new Todo();
		dto.setId(id);
		dto.setTitle(title);
		dto.setTask(task);
		dto.setInputLimitdate(inputLimitdate);
		dto.setUserid(userid);
	    dto.setStatus(status);

        boolean checkResult = dto.valueCheck();

        // もし入力チェックエラーがあった場合は、エラーメッセージを表示し、再入力させるため元の詳細画面へ戻る
        if (! checkResult ) {
            request.setAttribute("errorMessages", dto.getErrorMessages());
            // タスク１件のvoをリクエスト属性へバインド
            request.setAttribute("dto", dto);

            // 詳細画面を表示する
            RequestDispatcher rd =
                request.getRequestDispatcher("/detail.jsp");
            rd.forward(request, response);
            return;
        }



	    String message="";


	    try(TodoDAO dao =new TodoDAO()){

	    	if(id ==0) {

	    	dao.registerInsert(dto);
	    	  message="タスクの新規登録処理が完了しました";
	    	}else{
	    	   dao.registerUpdate(dto);
	    	   message ="タスク[ "+id+"]の更新処理が完了しました。 ";
	    	}
	    	setMessage(request,message);

	    }catch (Exception e) {
	    	throw new ServletException(e);
	    }

	    RequestDispatcher rd =request.getRequestDispatcher("/todo/search");
	    rd.forward(request,response);
	}

	    protected void setMessage(HttpServletRequest request, String message) {
	        request.setAttribute("message", message);

	}



}


