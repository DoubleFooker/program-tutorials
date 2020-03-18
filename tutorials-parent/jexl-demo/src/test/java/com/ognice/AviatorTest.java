package com.ognice;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.ognice.foo.DemoObject;
import org.apache.commons.jexl3.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kaifuhuang
 * @date 2023/2/10
 **/
public class AviatorTest {
    @Test
    public void stringTest() {
        Map<String,Object> params1=new HashMap<>();
        params1.put("input",1.0);
        Expression compile1 = AviatorEvaluator.compile("input == 1", true);
        Object result1 = compile1.execute(params1);
        System.out.println(result1);


        Map<String,Object> params2=new HashMap<>();
        params2.put("input","1");
        Expression compile2 = AviatorEvaluator.compile("long(input) == 1", true);
        Object result2 = compile2.execute(params2);
        System.out.println(result2);
    }

    @Test
    public void objectTest() {
        DemoObject value = new DemoObject();
        value.setTestColumn("test2");



        Map<String,Object> params2=new HashMap<>();
        params2.put("demo",value);
        Expression compile2 = AviatorEvaluator.compile("demo.testColumn", true);
        Object result2 = compile2.execute(params2);
        System.out.println(result2);
    }


}
