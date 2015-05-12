package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;



public class httptrianned {
	

	public static String sendPost(String url, String ModleFile, String sourceFile){
   	 PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        String param = "ModleFile=" + ModleFile + "&sourceFile=" + sourceFile;
        try {
            URL realUrl = new URL(url);
            
            URLConnection conn = realUrl.openConnection();
            
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            out = new PrintWriter(conn.getOutputStream());
            
            out.print(param);
            
            out.flush();
            
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("POST调用错误"+e);
            e.printStackTrace();
        }
       
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
}
	
	//演示
	public static void main(String[] args) {
		String url = "http://localhost:8080/rest-spring/trainned.json";
		String ModleFile = "E:/eclipse ee/rest-spring-test/ModletestBayes512.modle";
		String sourceFile = "E:/eclipse ee/rest-spring-test/Training748";
		
		String sr = httptrianned.sendPost(url,ModleFile,sourceFile);
		System.out.println(sr);
		
	}
		

}
