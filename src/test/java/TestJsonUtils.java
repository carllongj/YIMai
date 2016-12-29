import com.alibaba.fastjson.JSON;
import com.carl.yimai.web.utils.IDUtils;
import com.carl.yimai.web.utils.Result;
import org.junit.Test;

import java.util.Date;

/** 测试fastjson
 * <p>Title: PACKAGE_NAME TestJsonUtils</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/24 12:49
 * @Version 1.0
 */
public class TestJsonUtils {

    @Test
    public void testFastJson(){
        Result result = new Result();
        result.setStatus(true);
        result.setData(new Date());
        String jsonString = JSON.toJSONString(result);
        System.out.println(jsonString);
    }

    @Test
    public void testId(){
        Integer id = IDUtils.getOrderId();
        System.out.println(id);
    }
}
