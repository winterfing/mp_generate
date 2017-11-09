package generate;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import utils.CreateVcf;
import utils.DateUtil;
import utils.FileUtil;
import utils.ReadConfig;
import cn.binarywang.tools.generator.ChineseMobileNumberGenerator;

public class GenerateMain
{

    private static String personName;

    private static Long currentCount;

    private static Long count;

    private static Long time;

    private static String filePath;

    /**
     * @Title: main
     * @Description: 生成号码并写入文件
     * @author jhb
     * @param args
     * @throws
     */
    public static void main(String[] args)
    {
        Long start = System.currentTimeMillis();
        System.out.println("***********---程序启动——" + start);
        try
        {
            // 读取配置文件信息
            ReadConfig.readConfig();
            personName = ReadConfig.properties.get("ge.persson.name").toString();
            filePath = ReadConfig.properties.get("ge.filePath").toString();
            currentCount = Long.valueOf(ReadConfig.properties.get("ge.current.count").toString());
            count = Long.valueOf(ReadConfig.properties.get("ge.count").toString());
            time = Long.valueOf(ReadConfig.properties.get("ge.time").toString());

            String phone;
            String myPersonName;

            // 获取写入文件
            File file = new File(filePath + DateUtil.now("yyyy-MM-dd") + "_" +currentCount+"_"
                    + count + ".vcf");

            if (file.exists())
            {
                file.createNewFile();
            }

            // 开始生成
            int a = 0;
            time++;
            while (a++ < currentCount)
            {
                phone = ChineseMobileNumberGenerator.getInstance().generate();

                myPersonName = personName + "_" + a;

                // 写入文件
                FileUtil.writeInFileByPW(file,CreateVcf.buildVcf(myPersonName, phone), "utf-8", true);
                System.out.println("***********---成功写出第" + a + "条");
                count++;
            }
            // 更新配置文件数据
            ReadConfig.properties.setProperty("ge.current.count", currentCount.toString());
            ReadConfig.properties.setProperty("ge.count", count.toString());
            ReadConfig.properties.setProperty("ge.time", time.toString());
            ReadConfig.uptateConfig();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("&&&&&&&&&&&&---程序执行异常");
            System.exit(1);
        }
        System.out.println("***********---程序执行结束——耗时"
                + (System.currentTimeMillis() - start) + "毫秒");
    }

}
