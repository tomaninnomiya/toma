package todo.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * Servlet implementation class Todo
 */
public class Todo{
	private int id;
	private String title;
	private String task;
	private Timestamp limitdate;
	private Timestamp lastupdate;
	private String userid;
	private int status;
	private String label;
	private String inputLimitdate;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Timestamp getLimitdate() {
		return limitdate;
	}
	public void setLimitdate(Timestamp limitdate) {
		this.limitdate = limitdate;
	}
	public Timestamp getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(Timestamp lastupdate) {
		this.lastupdate = lastupdate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getInputLimitdate() {
		return inputLimitdate;
	}
	public void setInputLimitdate(String inputLimitdate) {
		this.inputLimitdate = inputLimitdate;
	}
	 Date dt = new Date(); //現在日時の取得






	    public boolean valueCheck() {
	        // エラーメッセージの初期化
	        errorMessages = new ArrayList<String>();

	        // id
	        if (id < 0) {
	            errorMessages.add("不正な入力を検出しました");
	        }

	        // title
	        if (title == null || title.isEmpty()) {
	            errorMessages.add("入力したタイトルが空です");
	        } else if (title.length() > 256) {
	            errorMessages.add("入力したタイトルが長すぎます");
	        }

	        // task
	        if (task == null || task.isEmpty()) {
	            errorMessages.add("入力したタスクが空です");
	        } else if (title.length() > 512) {
	            errorMessages.add("入力したタスクが長すぎます");
	        }

	        // limitdate

	        if (inputLimitdate == null || inputLimitdate.isEmpty()) {
	            errorMessages.add("入力したタスク期限が空です");
	        } else if (!inputLimitdate.matches("\\d{4}-\\d{2}-\\d{2}")) {
	            errorMessages.add("入力したタスク期限のフォーマットが違います");

	        }




	        // userid
	        if (userid == null || userid.isEmpty()) {
	            errorMessages.add("入力したユーザーIDが空です");
	        } else if (userid.length() > 64) {
	            errorMessages.add("入力したユーザーIDが長すぎます");
	        }

	        // status
	        if (status < 0 || status > 3) {
	            errorMessages.add("入力したステータスの値が不正です");
	        }

	        return (errorMessages.size() == 0);
	    }
	    private List<String> errorMessages;

	    public List<String> getErrorMessages() {
	        return errorMessages;
	    }

	    public void setErrorMessages(List<String> errorMessages) {
	        this.errorMessages = errorMessages;
	    }

}