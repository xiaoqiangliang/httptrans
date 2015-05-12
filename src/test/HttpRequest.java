package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class HttpRequest {

    public static String sendGet(String url, String Doc, String id, String ModleFile) {
        String result = "";
        BufferedReader in = null;
        try {

            String urlNameString = url + "?" + "id=" + id + "&ModleFile=" + ModleFile + "&Doc=" + Doc;
            System.out.println(urlNameString);
            URL realUrl = new URL(urlNameString);
            
            URLConnection connection = realUrl.openConnection();
            
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            
            connection.connect();
            
            Map<String, List<String>> map = connection.getHeaderFields();
            
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
           
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("Get调用错误" + e);
            e.printStackTrace();
        }
      
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    public static String testsendPost(String url, String id, String Doc, String ModleFile){
    	 PrintWriter out = null;
         BufferedReader in = null;
         String result = "";
         String param = "id=" + id + "&ModleFile=" + ModleFile + "&Doc=" + Doc;
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
        //ʹ��finally�����ر��������������
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
        
    	String url = "http://localhost:8080/rest-spring/jsonfeed.json";
    	String ModleFile ="E:/eclipse ee/rest-spring-test/ModletestBayes2.modle";
    	String Doc = "桃源仙谷自然风景区于2001年9月14日被评为国家AA级风景区，并在《中国旅游报》第一批公布；被北京市政府评为“北京市风景名胜区”还获得了北京荆杯”先　进单位的荣誉称号。桃源仙谷度假山庄可同时接待400余人，并有完善的设施及周到的服务，还可以吃住农家院，体验民俗风情。源仙谷风景名胜区将以优美的旅游环境、优良的旅游秩序、优质的旅游服务迎接广大游客，预祝广大游客高兴而来，满意而归。世外桃源区世外桃源。观潭望瀑金龟奇景以童乐瀑、天书壁画片麻崖、隐形瀑、天书洞、鸳鸯槐、一线天景观拥抱的奇景注：本团队属于自助户外活动，没有购物环节，队员无团队精神，娇气的童鞋请慎重报名";
    	String id = "1234";
    	
    	//GET
       /* String s=HttpRequest.sendGet(url,Doc,id,ModleFile);
        System.out.println(s);*/
        
        //POST 

    	String sr=HttpRequest.testsendPost(url,id,Doc,ModleFile);
        System.out.println(sr);
        
    }
    

}
