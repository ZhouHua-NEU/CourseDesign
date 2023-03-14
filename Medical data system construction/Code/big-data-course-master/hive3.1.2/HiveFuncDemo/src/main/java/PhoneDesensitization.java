import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

// 第一步：继承org.apache.hadoop.hive.ql.exec.UDF类
public class PhoneDesensitization extends UDF{

    // 第二步：实现evaluate()函数
    public String evaluate(Text text) {
        String result = "wrong phone number";
        // 1.检查手机号码是否合法
        if (text != null && text.getLength() == 11){
            String phoneNumber = text.toString();
            // 2. 取出手机号的前3位以及后四位，中间由*填充至11位
            StringBuffer sb = new StringBuffer();
            sb.append(phoneNumber.substring(0, 3));
            sb.append("****");
            sb.append(phoneNumber.substring(7));
            result = sb.toString();
        }
        return result;
    }
}
