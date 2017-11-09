package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;

public class ReadConfig
{

    private ReadConfig()
    {
    }

    public static Properties properties;

    /**
     * @Title: readConfig
     * @Description: 读取配置文件
     * @author jhb
     * @throws
     */
    public static void readConfig()
    {
        properties = new Properties();
        InputStreamReader in = null;
        try
        {
            in = new InputStreamReader(
                    ClassLoader.getSystemResourceAsStream("config.properties"));
            properties.load(in);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
    }

    /**
     * @Title: uptateConfig
     * @Description: 更新配置文件
     * @author jhb
     * @throws
     */
    public static void uptateConfig()
    {
        URL url = ReadConfig.class.getClassLoader().getResource(
                "config.properties");
        OutputStream fos = null;
        try
        {
            fos = new FileOutputStream(url.getPath());
            System.out.println(url.getPath());
            properties.store(fos, "");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (fos != null)
                {
                    fos.close();
                }
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
    }

}
