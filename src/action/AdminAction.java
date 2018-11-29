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
	private String OBJECT="/saishi";
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
    public void saishiAdd() throws Exception{
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		JSONObject jo=new JSONObject();
    	String name=request.getParameter("name");
    	String time=request.getParameter("time");
    	String content=request.getParameter("content");
    	CRUDUtil.insert("bisai", "name,time,content", "?,?,?", new Object[]{name,time,content});
    	jo.put("code", 0);
    	BaseUtil.returnJSONObject(request, response, jo);

    }
    //审核列表
    public void saishiMana() throws Exception{
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		JSONArray ja=new JSONArray();
		String k=request.getParameter("key");
		String sql="select * from bisai where del=? and name like ?";
		Object params[]={0,"%"+k+"%"};
		DBUtil db=new DBUtil();
		db.doPstm(sql, params);
		ResultSet rs=db.getRs();
		while(rs.next()){
			JSONObject jo=new JSONObject();
			jo.put("id", rs.getInt("id"));
			jo.put("name", rs.getString("name"));
			jo.put("time", rs.getString("time"));
			jo.put("content", rs.getString("content")==null?"暂无":rs.getString("content"));
			jo.put("yaoqiu", rs.getString("yaoqiu")==null?"暂无":rs.getString("yaoqiu"));
			jo.put("jiangli", rs.getString("jiangli")==null?"暂无":rs.getString("jiangli"));
			jo.put("pingtai", rs.getString("pingtai")==null?"暂无":rs.getString("pingtai"));
			ja.add(jo);
		}
		rs.close();
		db.closed();
		BaseUtil.returnJSONArray(request, response, ja);
    }
    //删除赛事
    public void saishiDel() throws Exception{
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		JSONObject jo=new JSONObject();
		String id=request.getParameter("id");
		CRUDUtil.update("bisai", "del=?", "id=?", new Object[]{1,id});
		jo.put("code", 0);
		BaseUtil.returnJSONObject(request, response, jo);
    }
    //赛事编辑
    public void saishiEdit() throws Exception{
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		JSONObject jo=new JSONObject();
		String id=request.getParameter("id");
		String yaoqiu=request.getParameter("yaoqiu");
		String jiangli=request.getParameter("jiangli");
		String pingtai=request.getParameter("pingtai");
		CRUDUtil.update("bisai", "yaoqiu=?,jiangli=?,pingtai=?", "id=?", new Object[]{yaoqiu,jiangli,pingtai,id});
		jo.put("code", 0);
		BaseUtil.returnJSONObject(request, response, jo);
    }
    //删除列表
    public void saishiMana2() throws Exception{
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		JSONArray ja=new JSONArray();
		String k=request.getParameter("key");
		String sql="select * from bisai where del=? and name like ?";
		Object params[]={1,"%"+k+"%"};
		DBUtil db=new DBUtil();
		db.doPstm(sql, params);
		ResultSet rs=db.getRs();
		while(rs.next()){
			JSONObject jo=new JSONObject();
			jo.put("id", rs.getInt("id"));
			jo.put("name", rs.getString("name"));
			jo.put("time", rs.getString("time"));
			jo.put("content", rs.getString("content")==null?"暂无":rs.getString("content"));
			jo.put("yaoqiu", rs.getString("yaoqiu")==null?"暂无":rs.getString("yaoqiu"));
			jo.put("jiangli", rs.getString("jiangli")==null?"暂无":rs.getString("jiangli"));
			jo.put("pingtai", rs.getString("pingtai")==null?"暂无":rs.getString("pingtai"));
			ja.add(jo);
		}
		rs.close();
		db.closed();
		BaseUtil.returnJSONArray(request, response, ja);
    }
    

}
