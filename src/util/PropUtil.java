
package util;
import java.io.IOException;
import java.util.Properties;


/**
 * 读取jdbc.properties 的配置信息
 * @author admin
 *
 */
public class PropUtil {
	
	static Properties properties = new Properties();
	
	static{
		try {
			//类加载器 可以加载classpath下的文件
			properties.load(PropUtil.class.getResourceAsStream("/dateBase.properties"));

		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key){
		return properties.getProperty(key);
		
	}

}
