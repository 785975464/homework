package periodictable;

//import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
//import org.springframework.jdbc.core.JdbcTemplate;


public class MysqlConnection implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;  
    
    @Override  
    public void setApplicationContext(ApplicationContext context)  
        throws BeansException {  
    	MysqlConnection.applicationContext = context;  
    }  
    public static Object getBean(String name){  
        return applicationContext.getBean(name);  
    }  
	
	public String hello(){
		return "hello1";
	}
	public String test_connection(){
		String result="";
		Object o=MysqlConnection.getBean("myDataSource");
//		JdbcTemplate t =new JdbcTemplate((BasicDataSource)o);
		
//		t.update("insert into onlineStreams (id,name,channel,iccid) values (1,'"
//				+ "111" + "','" + "111" + "','" + "111" + "')");
		return result;
	}
	
	public class MappedRow{
		private int id;
		private String name;
		public MappedRow(int id,String name){
			this.id = id;
			this.name = name;
		}
		public int getId(){
			return id;
		}
		public String getName(){
			return name;
		}
		
	}
}
