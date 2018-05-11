package todo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import todo.dto.Todo;

/**
 * Servlet implementation class TodoDAO
 */
@WebServlet("/TodoDAO")
public class TodoDAO extends DAO {
	public List<Todo> todoList() throws Exception{
		List<Todo> returnList= new ArrayList<Todo>();

		String sql ="SELECT id , title, task, limitdate,lastupdate,userid,label,td.status "
				+"FROM todo_list td LEFT JOIN status_list stts ON stts.status=td.status ORDER BY limitdate ASC";

		PreparedStatement statement =getPreparedStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			Todo dto=new Todo();
			dto.setId(rs.getInt("id"));
            dto.setTitle(rs.getString("title"));
            dto.setTask(rs.getString("task"));
            dto.setLimitdate(rs.getTimestamp("limitdate"));
            dto.setLastupdate(rs.getTimestamp("lastupdate"));
            dto.setUserid(rs.getString("userid"));
            dto.setLabel(rs.getString("label"));

            returnList.add(dto);


		}
		return returnList;
	}


	public Todo detail(int id) throws Exception{
		Todo dto = new Todo();
		String sql = "SELECT id , title , task , limitdate , lastupdate , userid , label , td.status " +
                "FROM todo_list td LEFT JOIN status_list stts ON stts.status = td.status WHERE id = ?";


		PreparedStatement statement = getPreparedStatement(sql);
		statement.setInt(1, id);

		ResultSet rs =statement.executeQuery();
		while(rs.next()) {
			dto.setId(rs.getInt("id"));
			dto.setTitle(rs.getString("title"));
			dto.setTask(rs.getString("task"));
			dto.setLimitdate(rs.getTimestamp("limitdate"));
			dto.setLastupdate(rs.getTimestamp("lastupdate"));
			dto.setUserid(rs.getString("userid"));
			dto.setLabel(rs.getString("status"));
			dto.setStatus(rs.getInt("status"));
		}
		return dto;


		}
	public int registerInsert(Todo dto) throws Exception{
		String sql=
			      "INSERT INTO todo_list (title,task,limitdate,lastupdate,userid,status) "
		                   + "VALUES (?,?,?,now(),?,?)";
		int result =0;
		try {
			PreparedStatement statement =getPreparedStatement(sql);
			statement.setString(1,dto.getTitle());
			statement.setString(2, dto.getTask());
			statement.setString(3, dto.getInputLimitdate());
			statement.setString(4,dto.getUserid());
			statement.setInt(5, dto.getStatus());

			result= statement.executeUpdate();
			super.commit();
		}catch (Exception e) {
			super.rollback();
			throw e;
		}
         return result;
	}
	public int registerUpdate(Todo dto) throws Exception{
		String sql = "UPDATE todo_list SET title = ? , task = ? , limitdate = ? , lastupdate=now() , userid = ? , status = ? WHERE id = ?";

		int result =0;
		try {
			PreparedStatement statement = getPreparedStatement(sql);
			statement.setString(1, dto.getTitle());
            statement.setString(2, dto.getTask());
            statement.setString(3, dto.getInputLimitdate() );
            statement.setString(4, dto.getUserid());
            statement.setInt(5, dto.getStatus());
            statement.setInt(6, dto.getId());

            result = statement.executeUpdate();

            super.commit();
		}catch (Exception e) {
			super.rollback();
			throw e;
		}
		return result;
	}
	public int delete(int id) throws Exception {
        String sql = "DELETE FROM todo_list WHERE id = ?";

        // SQLを実行してその結果を取得する。
        int result = 0;
        try {
            // プリペアステートメントを取得し、実行SQLを渡す
            PreparedStatement statement = getPreparedStatement(sql);
            statement.setInt(1, id);

            result = statement.executeUpdate();

            // コミットを行う
            super.commit();
        } catch (Exception e) {
            super.rollback();
            throw e;
        }

        return result;
	}


    /**
     * 入力チェックを行う。 もし入力チェックエラーがあった場合には自動的に
     * エラーメッセージが追加される。
     */



	}









