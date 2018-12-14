package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.opensymphony.xwork2.ActionSupport;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import util.BaseUtil;
import util.CRUDUtil;
import util.DBUtil;

public class AdminAction extends ActionSupport{
	private String OBJECT="/contest";
	private String message;
	private String path;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
    //添加赛事
    public void contestAdd() throws Exception{
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		JSONObject jo=new JSONObject();
    	String name=request.getParameter("name");
    	String logo=request.getParameter("logo");
    	String contestStartDate=request.getParameter("contestStartDate");
    	String contestEndDate=request.getParameter("contestEndDate");
    	String content=request.getParameter("content");
    	CRUDUtil.insert("game", "name,logo,contestStartDate,contestEndDate,registrationStartDate,registrationEndDate,content", "?,?,?,?,?,?,?", new Object[]{name,logo,contestStartDate,contestEndDate,contestStartDate,contestEndDate,content});
    	jo.put("code", 0);
    	BaseUtil.returnJSONObject(request, response, jo);

    }
    //审核列表
    public void contestMana() throws Exception{
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		JSONArray ja=new JSONArray();
		String k=request.getParameter("key");
		String sql="select * from game where del=? and name like ?";
		Object params[]={0,"%"+k+"%"};
		DBUtil db=new DBUtil();
		db.doPstm(sql, params);
		ResultSet rs=db.getRs();
		while(rs.next()){
			JSONObject jo=new JSONObject();
			jo.put("id", rs.getInt("id"));
			jo.put("name", rs.getString("name"));
			jo.put("logo", rs.getString("logo"));
			jo.put("conteststartdate", rs.getString("contestStartDate"));
			jo.put("contestenddate", rs.getString("contestEndDate"));
			jo.put("registrationstartdate", rs.getString("registrationStartDate"));
			jo.put("registrationenddate", rs.getString("registrationEndDate"));
			jo.put("content", rs.getString("content")==null?"暂无":rs.getString("content"));
			jo.put("claim", rs.getString("claim")==null?"暂无":rs.getString("claim"));
			jo.put("reward", rs.getString("reward")==null?"暂无":rs.getString("reward"));
			jo.put("platform", rs.getString("platform")==null?"暂无":rs.getString("platform"));
			ja.add(jo);
		}
		rs.close();
		db.closed();
		BaseUtil.returnJSONArray(request, response, ja);
    }
    //删除赛事
    public void contestDel() throws Exception{
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		JSONObject jo=new JSONObject();
		String id=request.getParameter("id");
		CRUDUtil.update("game", "del=?", "id=?", new Object[]{1,id});
		jo.put("code", 0);
		BaseUtil.returnJSONObject(request, response, jo);
    }
    //赛事编辑
    public void contestEdit() throws Exception{
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		JSONObject jo=new JSONObject();
		String id=request.getParameter("id");
		String claim=request.getParameter("claim");
		String reward=request.getParameter("reward");
		String platform=request.getParameter("platform");
		CRUDUtil.update("game", "claim=?,reward=?,platform=?", "id=?", new Object[]{claim,reward,platform,id});
		jo.put("code", 0);
		BaseUtil.returnJSONObject(request, response, jo);
    }
    //删除列表
    public void contestMana2() throws Exception{
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		JSONArray ja=new JSONArray();
		String k=request.getParameter("key");
		String sql="select * from game where del=? and name like ?";
		Object params[]={1,"%"+k+"%"};
		DBUtil db=new DBUtil();
		db.doPstm(sql, params);
		ResultSet rs=db.getRs();
		while(rs.next()){
			JSONObject jo=new JSONObject();
			jo.put("id", rs.getInt("id"));
			jo.put("name", rs.getString("name"));
			jo.put("logo", rs.getString("logo"));
			jo.put("conteststartdate", rs.getString("contestStartDate"));
			jo.put("contestenddate", rs.getString("contestEndDate"));
			jo.put("registrationstartdate", rs.getString("registrationStartDate"));
			jo.put("registrationenddate", rs.getString("registrationEndDate"));
			jo.put("content", rs.getString("content")==null?"暂无":rs.getString("content"));
			jo.put("claim", rs.getString("claim")==null?"暂无":rs.getString("claim"));
			jo.put("reward", rs.getString("reward")==null?"暂无":rs.getString("reward"));
			jo.put("platform", rs.getString("platform")==null?"暂无":rs.getString("platform"));
			ja.add(jo);
		}
		rs.close();
		db.closed();
		BaseUtil.returnJSONArray(request, response, ja);
    }
    

}
