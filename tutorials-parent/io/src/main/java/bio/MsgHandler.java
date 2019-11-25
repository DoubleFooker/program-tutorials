package bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huangkaifu
 * @date 2018/4/18.
 */
public class MsgHandler implements Runnable{
    static Map<String,String> phone;
    static {
        phone=new HashMap<String,String>();
        phone.put("joe","11");
        phone.put("leo","22");
        phone.put("tom","33");
        phone.put("jack","44");
        phone.put("pete","55");
    }
    private Socket socket;
    public MsgHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            String expression;
            String result;
            while(true){
                //通过BufferedReader读取一行
                //如果已经读到输入流尾部，返回null,退出循环
                //如果得到非空值，就尝试计算结果并返回
                //可约定处理消息格式
                if((expression = in.readLine())==null) {
                    break;
                }
                System.out.println("服务器收到客户端消息：" + expression);
                //这里对消息进行处理后返回
                // TODO
                result= phone.get(expression.toLowerCase())==null?"查无此人":phone.get(expression.toLowerCase());
                out.println(result);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //一些必要的清理工作
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if(out != null){
                out.close();
                out = null;
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}