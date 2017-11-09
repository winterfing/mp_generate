package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
* @ClassName: FileUtil
* @Description: java 文件读取写入操作工具
* @author jhb
* @date 2017-8-7 下午3:51:28
*
 */
public class FileUtil
{
    private FileUtil(){
        super();
    }

    /**
     * 按照字节读取文件内容
     * @Title: readFileByByte
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author Dangzhang
     * @return 返回所读取内容
     * @throws
     */
    public static synchronized String readFileByByte(){
        String s="";
        File f=new File("E:\\Java\\test.txt");
        InputStream in=null;
        try{
            in=new FileInputStream(f);
            int tempByte;
            while((tempByte=in.read())!=-1){
                System.out.println(tempByte);
                s+=tempByte;
            }
            in.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("content:"+s);
        return s;
    }    
    
    
    /**
     * 按照字符读取文件内容
     * @Title: readFileByChar
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author Dangzhang
     * @return
     * @throws
    */
    public static synchronized String readFileByChar(){
        String s="";
        File f=new File("E:\\Java\\test.txt");
        Reader rdr=null;
        try{
            rdr=new InputStreamReader(new FileInputStream(f));
            int temp;
            while((temp=rdr.read())!=-1){
                //对于window下，\r\n这两个字符在一起时，表示一个换行。   
                //但是如果这两个字符分开显示时，会换两行。
                //因此，屏蔽掉\r，或者屏蔽掉\n。否则，将会出现很多空行
                if(((char)temp)!='\r'){
                    s+=(char)temp;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                rdr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(s);
        return s;
    }
    
    
    /**
     * @Title: readFileByLine
     * @Description: 按照行读取文件
     * @author winterfing
     * @param file 目标文件
     * @return 返回list
     * @throws
     */
    public static synchronized List<String> readFileByLine(File file ,String code){
        List<String> list = null;
        BufferedReader br = null;
        try{
            System.out.println("按照行读取文件内容");
            list = new ArrayList<String>();
            br=new BufferedReader(new InputStreamReader(new FileInputStream(file), code));
            String temp;
            while((temp=br.readLine())!=null){
                list.add(temp);
                System.out.println(temp);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    
  //随机行读取文件
    public static synchronized String readFileByRand(){
        String s="";
        File f=new File("E:\\Java\\jmoa\\TestDiff\\src\\test\\resource\\test_fb.txt");
        RandomAccessFile raf=null;
        try{
            //打开一个文件流， 按只读方式
            raf=new RandomAccessFile(f.getName(), "r");
            //文件长度，字节数
            long fileLength=raf.length();
            //读文件的起始位置
            int beginIndex=(fileLength>4)?4:0;
            //将读文件的开始位置移到beginIndex位置
            raf.seek(beginIndex);
            byte[] bytes=new byte[10];
            int byteread=0;
            //一次读10个字节，如果文件内容不足10个字节，则读剩下的文字。
            //将一次读取的字节数赋给byteread
            while((byteread=raf.read(bytes))!=-1){
                System.out.write(bytes,0,byteread);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(raf!=null){
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //System.out.println("文件内容："+s);
        return s;
    }
    
    
    /**
     * 显示输入流中还剩的字节数
     */
    @SuppressWarnings("unused")
    private static synchronized void showAvailableBytes(InputStream in) {
        try {
            System.out.println("当前字节输入流中的字节数为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
//    // 在线打开方式 下载
//    public void downLoad(String filePath, HttpServletResponse response,String fileNewName) throws Exception {
//        File f = new File(filePath);
//        OutputStream out = response.getOutputStream();
//        if (!f.exists()) {
//            response.setCharacterEncoding("UTF-8");
//            String notFileHtml=getNotFileHtml(filePath,"文件找不到");
//            out.write(notFileHtml.getBytes("UTF-8"));
//            out.flush();
//            return;
//        }
//        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
//        byte[] buf = new byte[1024];
//        int len = 0;
//
//        response.reset(); // 非常重要
//        response.setContentType("application/x-msdownload");
//        response.setHeader("Content-Disposition", "attachment; filename=" + fileNewName);
//
//        while ((len = br.read(buf)) > 0)
//            out.write(buf, 0, len);
//        br.close();
//        out.close();
//    }
    
    
    
    /**   文件的写入      **/
    
    /**
     * 
    * @Title: writeInFileByfb
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @author winterfing
    * @param file 目标文件
    * @param content 写入内容
    * @param code 编码
    * @param append 是否追加
    * @throws
     */
    public static synchronized void writeInFileByPW(File file , String content , String code , boolean append) {
        
        PrintWriter pw=null;
        try{
            if(!file.exists()){
                file.createNewFile();
            }
             pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file , append),code)));
             pw.write(content);
             pw.flush();
             pw.close();
        }catch(Exception e){
           e.printStackTrace();
        }
        
    }
    
    /**
     * 
    * @Title: writeInFileByfi
    * @Description: 文字内容写入文件
    * @author winterfing
    * @param f 目标文件File
    * @param append 是否追加 true为追加
    * @param content 写入内容
    * @throws
     */
    public static synchronized void writeInFileByfi(File f , String content ,boolean append){
        FileOutputStream fos=null;
        try {
            if(!f.exists()){
                f.createNewFile();
            }
            fos=new FileOutputStream(f , append);
            fos.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    
    public static synchronized void writeInFileByRdA(){
        String content="randowAccessFile";
        try{
              // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile("E:\\Java\\jmoa\\TestDiff\\src\\test\\resource\\test_fb.txt", "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            //将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * @Title: main
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author Dangzhang
     * @param args
     * @throws
     */
//    public static void main(String[] args)
//    {
//        File file = new File("E://test.txt");
//        String content = "1234\r\n";
//        String code = "UTF-8";
//        
//        writeInFileByPW(file, content, code, true);
//        
////        readFileByLine(file , "UTF-8");
//    }

}
