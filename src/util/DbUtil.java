package util;



import java.lang.reflect.Method;
import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 数据库jdbc操作
 * @author admin
 *
 */
public class DbUtil {

	private static DbUtil conn;
	 
	 /**
	  * 获取连接池实例
	  * 单例模式的设计保证内存中只有一份
	  * @return
	  */
	 public static DbUtil getInstance(){
		 if(conn == null){
			 conn = new DbUtil();
		 }
		 
		 return conn;
	 }
 
	// 初始化连接池
	private DbUtil(){ }
	
	/**
	 * 获取连接
	 */
	public Connection getConnection (){
		Connection conn = null;
		try {
			// 加载驱动
			Class.forName(PropUtil.getProperty("driver"));
			// 获取一个连接
			conn = DriverManager.getConnection(PropUtil.getProperty("url"), PropUtil.getProperty("userName"), PropUtil.getProperty("passWord"));
		} catch (Exception e) {
			System.out.println("获取连接异常！message:"+e.getMessage());
		}
		
		return conn;
	}
	 
	/**
	 * 释放连接
	 * @param con
	 */
	public void freeConnection(Connection con) {
		try {
			if(con != null){
				con.close();
				con = null;
			}
		} catch (Exception e) {
			System.out.println("释放连接异常！message:"+e.getMessage());
		}
	}
		
	/**
	 * 释放Statement资源
	 * @param statement
	 */
	public void freeStatement(Statement statement){
        try {
        	if(statement != null){
                statement.close();
                statement = null;
        	}
        } catch(Exception e) {
        	
        	System.out.println(e.getMessage());
        }
	}
		
	/**
	 * 释放查询结果
	 * @param rs
	 */
	public void freeResultSet(ResultSet rs){
        try {
              if(rs != null){
                rs.close();
                rs = null;
              }
        } catch(Exception e) {
        	
        	System.out.println(e.getMessage());
        }
	}
	
	/**
	 * 设置sql中的参数，对应SQL语句中的问号
	 */
	private void setParam(PreparedStatement pstmt, List<Object> param)throws SQLException {
		int seq = 1;
		if (param != null) {
			for (Object elem : param) {
				if (elem instanceof String) {
					pstmt.setString(seq, (String) elem);
				} else if (elem instanceof Long) {
					pstmt.setLong(seq, (Long) elem);
				} else if (elem instanceof Double) {
					pstmt.setDouble(seq, (Double) elem);
				} else if (elem instanceof Float) {
					pstmt.setFloat(seq, (Float) elem);
				} else if (elem instanceof Integer) {
					pstmt.setInt(seq, (Integer) elem);
				} else if (elem instanceof Timestamp) {
					pstmt.setTimestamp(seq, (Timestamp) elem);
				} else if (elem instanceof java.util.Date) {
					java.util.Date tmp = (java.util.Date) elem;
					pstmt.setDate(seq, new Date(tmp.getTime()));
				} else if (elem instanceof Date) {
					pstmt.setDate(seq, (Date) elem);
				} else {
					pstmt.setObject(seq, elem);
				}
				seq++;
			}
		}
	}
	
    /**
     * 根据结果集中的数据类型 转换成相应String 类型
     * @throws SQLException 
     */
    private String parseResultSet(ResultSet rs, ResultSetMetaData rsmd,
			int type, int i) throws SQLException {
		String resStr = "";
		switch (type) {
			case Types.VARCHAR:
				resStr = rs.getString(i + 1);
				break;
			case Types.NUMERIC:
				NumberFormat nf = NumberFormat.getInstance();
				nf.setGroupingUsed(false);
				//获取小数位数
				int scale = rsmd.getScale(i + 1);
				if (scale == 0) {
					resStr = nf.format(rs.getLong(i + 1));
				} else {
					// 浮点型数据保留两位小数
					nf.setMinimumFractionDigits(2);
					resStr = nf.format(rs.getDouble(i + 1));
				}
				break;
			case Types.CHAR:
				resStr = rs.getString(i + 1);
				break;
			case Types.FLOAT:
				resStr = String.valueOf(rs.getFloat(i + 1));
				break;
			case Types.DOUBLE:
				resStr = String.valueOf(rs.getDouble(i + 1));
				break;
			case Types.DATE:
				resStr = DateUtil.getStringDate(rs.getDate(i + 1));
				break;
			case Types.TIMESTAMP:
				resStr = DateUtil.getStringTimestamp(rs.getTimestamp(i + 1));
				break;
			default:
				resStr = rs.getString(i + 1);
		}
		return resStr;
	}

	/**
	 * 用于单个动作 的 修改，删除，添加 使用，返回影响数据库的条数
	 *
	 * @param sql
	 * @param param
	 * @return
	 */
	public int execute(String sql, List<Object> param) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 获取一个连接
			conn = this.getConnection();
			// 执行预备语句
			ps = conn.prepareStatement(sql);
			// 设置sql 语句中的参数
			setParam(ps,param);
			// 执行操作
			result = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("SQL执行异常！message:"+e.getMessage());
		} finally {
			this.freeStatement(ps);
			this.freeConnection(conn);
		}
		return result;
	}
	
	/**
	 * 用于事务控制 的 修改，删除，添加 使用，返回影响数据库的条数
	 * @param conn
	 * @param sql
	 * @param param
	 * @return
	 */
	public int execute(Connection conn, String sql, List<Object> param) {
		int result = 0;
		PreparedStatement ps = null;
		try {
			// 执行预备语句
			ps = conn.prepareStatement(sql);
			// 设置sql 语句中的参数
			setParam(ps,param);
			// 执行操作
			result = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("SQL执行异常！message:"+e.getMessage());
		} finally {
			this.freeStatement(ps);
		}
		return result;
	}

	/**
	 * 根据 sql 返回单个对象，sql 中查询的字段需要和bean中的一致，不一致 需要使用 as 增加别名  id as userid
	 * @param sql eg:select id,name from tb_demo where name=? and pass =?
	 * @param param 参数列表，需要和sql 中的问号一一对应
	 * @param clazz 保存数据库字段的javabean
	 * @return
	 */
	public <T> T queryObject(String sql, List<Object> param,Class<T> clazz) {
		T obj = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			//加载驱动   并建立链接
			conn = getConnection();
			
			// 创建prepareStatement 对象
			pstmt = conn.prepareStatement(sql);
			
			// 参数置入
			this.setParam(pstmt, param);
			
			//执行查询处理
			rs = pstmt.executeQuery();
			
			// 获取结果集对象
			rsmd = rs.getMetaData();
			//获取结果集对象的中的列数
			int cols = rsmd.getColumnCount();
			
			//反射技术  获取参数CLASS类型对应类中所有的方法
			Method[] methods = clazz.getDeclaredMethods();
			
			//参数CLASS类型对应类中的所有set方法  放置容器指定
			Map<String, Method> meth = new HashMap<String, Method>();
			// set开头的方法防治到Map中
			for (int k = 0; k < methods.length; k++) {
				
				// 反射技术获取方法名
				String methodName = methods[k].getName().toLowerCase();
				
				//把以set开头的方法放置入map集合
				if (methodName.startsWith("set")) {
					meth.put(methodName, methods[k]);
				}
			}
			//移动指针找数据
			if(rs.next()) {
				
				// 反射技术创建参数类的实例对象
				obj = clazz.newInstance();
				
				//循环所有取得列数
				for (int j = 0; j < cols; j++) {
					
					//获取结果集中的列名 
					String colName = "set" + rsmd.getColumnLabel(j + 1);
					//通过结果集中的列明获取方法名
					Method m_tem = meth.get(colName.toLowerCase());
					
					//如果能通过结果集中的列明获取 参数类中的方法名  就指定对应的结果集中的值 设置入参数类中的set方法
					if (m_tem != null) {
						
						//获取结果集中列的数据类型
						int type = rsmd.getColumnType(j + 1);
						
						//把结果集中的数据类型全都转换成String类型来处理  并获取对应列中的值
						String val = parseResultSet(rs,rsmd,type,j);
						
						if (val != null) {
							//利用反射技术调用set方法赋值 (obj:参数类对象,val:是结果集中的数据值)
							m_tem.invoke(obj, val);
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("queryObject 执行异常！message:"+e.getMessage());
		} finally {
			
			//资源关闭
			this.freeResultSet(rs);
			this.freeStatement(pstmt);
			this.freeConnection(conn);
		}
		return obj;
	}
	
	/**
	 * 根据 sql 返回对象列表，sql 中查询的字段需要和bean中的一致，不一致 需要使用 as 增加别名  id as userid
	 * @param sql eg:select id,name from tb_demo where name=? and pass =?
	 * @param param 参数列表，需要和sql 中的问号一一对应
	 * @param clazz 保存数据库字段的javabean
	 * @return
	 */
	public <T> List<T> queryListObject(String sql, List<Object> param,Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			this.setParam(pstmt, param);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			Method[] methods = clazz.getDeclaredMethods();
			Map<String, Method> meth = new HashMap<String, Method>();
			for (int k = 0; k < methods.length; k++) {
				String methodName = methods[k].getName().toLowerCase();
				if (methodName.startsWith("set")) {
					meth.put(methodName, methods[k]);
				}
			}
			while (rs.next()) {
				// 构造bean对象，
				T obj = clazz.newInstance();
				for (int j = 0; j < cols; j++) {
					String colName = "set" + rsmd.getColumnLabel(j + 1);
					Method m_tem = meth.get(colName.toLowerCase());
					if (m_tem != null) {
						int type = rsmd.getColumnType(j + 1);
						String val = parseResultSet(rs,rsmd,type,j);
						if (val == null) {
							val = "";
						}
						m_tem.invoke(obj, val);
					}
				}
				list.add(obj);
			}

		} catch (Exception e) {
			System.out.println("queryListObject 执行异常！message:"+e.getMessage());
		} finally {
			this.freeResultSet(rs);
			this.freeStatement(pstmt);
			this.freeConnection(conn);
		}
		return list;
	}	
	
	/**
	 * 查询表中的记录数，返回数量  
	 * sql 文  要求用count()查询
	 * @param sql sql
	 * @param param ?号对应的集合
	 * @return 表中的记录数
	 */
	public int count(String sql, List<Object> param) {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = this.getConnection();
			ps = conn.prepareStatement(sql);
			this.setParam(ps, param);
			rs = ps.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("数据库 count 执行异常！message:"+e.getMessage());
		} finally {
			this.freeResultSet(rs);
			this.freeStatement(ps);
			this.freeConnection(conn);
		}
		return result;
	}
	
	
	/**
	 * 返回数组格式
	 * @param sql
	 * @param param
	 * @return
	 */
	public List<String[]> queryForArray(String sql, List<Object> param) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		List<String[]> list = new ArrayList<String[]>();
		try {
			// 获取一个连接
			conn = this.getConnection();
			// 执行预备语句
			ps = conn.prepareStatement(sql);
			// 设置sql 语句中的参数
			setParam(ps,param);
			
			// 查询操作
			rs = ps.executeQuery();
			
			// 获取结果集列信息
			rsmd = rs.getMetaData();
			// 获取总列数
			int columCount = rsmd.getColumnCount();
			
			while (rs.next()) {
				
				//声明数组  放一条信息 数组长度为所取列数
				String[] resStr = new String[columCount];
				
				for (int i = 0; i < columCount; i++) {
					int type = rsmd.getColumnType(i + 1);
					
					resStr[i] = parseResultSet(rs,rsmd,type,i);
				}
				list.add(resStr);
			}

		} catch (Exception e) {
			System.out.println("queryForArray执行异常！message:"+e.getMessage());
		} finally {
			// 关闭连接 释放资源
			this.freeResultSet(rs);
			this.freeStatement(ps);
			this.freeConnection(conn);
		}

		return list;
	}
	
	/**
	 * 根据 sql 返回列表Map格式数据 key 为column 名字，sql 中查询的字段需要和bean中的一致，不一致 需要使用 as 增加别名  id as userid
	 * @param sql eg:select id,name from tb_demo where name=? and pass =?
	 * @param param 参数列表，需要和sql 中的问号一一对应
	 *
	 * @return
	 */
	public List<Map<String, Object>> queryListMap(String sql, List<Object> param) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			this.setParam(ps, param);
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int j = 0; j < cols; j++) {
					String colName = rsmd.getColumnLabel(j + 1);
					int type = rsmd.getColumnType(j + 1);
					String val = parseResultSet(rs,rsmd,type,j);
					map.put(colName.toLowerCase(), val);
				}
				list.add(map);
			}
		} catch (Exception e) {
			System.out.println("queryListMap执行异常！message:"+e.getMessage());
		} finally {
			this.freeResultSet(rs);
			this.freeStatement(ps);
			this.freeConnection(conn);
		}
		return list;
	}
	
	
}
