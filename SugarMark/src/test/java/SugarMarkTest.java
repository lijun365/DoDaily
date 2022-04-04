import org.freefly.dodaily.sugarmark.SugarMarkApplication;
import org.freefly.dodaily.sugarmark.common.SugarMarkResult;
import org.freefly.dodaily.sugarmark.controller.SugarMarkController;
import org.freefly.dodaily.sugarmark.entity.SugarMark;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = SugarMarkApplication.class)
@RunWith(SpringRunner.class)
public class SugarMarkTest {

    @Autowired
    private SugarMarkController sugarMarkController;

    @Test
    public void test_queryWhenVoNullAndNotFound(){
        SugarMarkResult queryNull = sugarMarkController.query(1, 10, null);
        System.out.println(queryNull);

        SugarMark sugarMark = new SugarMark();
        sugarMark.setId(-1);
        SugarMarkResult queryNotFound1 = sugarMarkController.query(1, 10, sugarMark);
        System.out.println(queryNotFound1);
    }

    @Test
    public void test_queryWhenVoNotNull(){
        SugarMark sugarMark = new SugarMark();

        // Test for id
        sugarMark.setId(1);
        SugarMarkResult queryId = sugarMarkController.query(1, 10, sugarMark);
        System.out.println(queryId);

        // Test for status
        sugarMark.setId(null);
        sugarMark.setStatus(true);
        SugarMarkResult queryStatus = sugarMarkController.query(1, 10, sugarMark);
        System.out.println(queryStatus);

        // Test for days
        sugarMark.setStatus(null);
        sugarMark.setDays(2);
        SugarMarkResult queryDays = sugarMarkController.query(1, 10, sugarMark);
        System.out.println(queryDays);

        // Test for desc
        sugarMark.setDays(null);
        sugarMark.setDesc("ok");
        SugarMarkResult queryDesc = sugarMarkController.query(1, 10, sugarMark);
        System.out.println(queryDesc);

        // Test for create date
        sugarMark.setDesc(null);
        try {
            sugarMark.setCDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-20"));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("CDATE ERROR!");
            return;
        }
        SugarMarkResult queryCDate = sugarMarkController.query(1, 10, sugarMark);
        System.out.println(queryCDate);

        // Test for update date
        sugarMark.setCDate(null);
        try {
            sugarMark.setUDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-20"));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("UDATE ERROR!");
            return;
        }
        SugarMarkResult queryUDate = sugarMarkController.query(1, 10, sugarMark);
        System.out.println(queryUDate);
    }

    @Test
    public void test_insert(){
        List<SugarMark> list = new ArrayList<>();
        int flag = sugarMarkController.insert(list);
        System.out.println(flag); // 0

        list.add(new SugarMark(-1,true,-1,"ok",new Date(), new Date()));
        list.add(new SugarMark(-2,true,-2,"ok",new Date(), new Date()));
        flag = sugarMarkController.insert(list); // 200
        System.out.println(flag);
    }

    @Test
    public void test_update(){
        int flag = sugarMarkController.update(null); // 0
        System.out.println(flag);
        SugarMark sugarMark = new SugarMark(1,true,-1,"ok11",new Date(), new Date());
        flag = sugarMarkController.update(sugarMark); // 200
        System.out.println(flag);
    }

    @Test
    public void test_delete(){
        int flag = sugarMarkController.delete(null); // 0
        System.out.println(flag);

        List<Integer> ids=new ArrayList<>();
        flag=sugarMarkController.delete(ids); // 0
        System.out.println(flag);

        ids.add(125);
        ids.add(126);
        flag=sugarMarkController.delete(ids); // 200
        System.out.println(flag);
    }
}
