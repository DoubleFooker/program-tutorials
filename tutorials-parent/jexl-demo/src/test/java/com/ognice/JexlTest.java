package com.ognice;

import com.ognice.foo.Contact;
import com.ognice.foo.DemoObject;
import com.ognice.foo.Pricing;
import com.ognice.foo.RatedRecord;
import org.apache.commons.jexl3.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kaifuhuang
 * @date 2023/2/10
 **/
public class JexlTest {
    @Test
    public void stringTest() {
        // Create or retrieve an engine
        JexlEngine jexl = new JexlBuilder().create();
        // Create an expression
        String jexlExp = "string == 'test'";
        JexlExpression e = jexl.createExpression(jexlExp);
        // Create a context and add data
        JexlContext jc = new MapContext();
        jc.set("string", "test");
        // Now evaluate the expression, getting the result
        Object o = e.evaluate(jc);
        assert o.equals(true);

    }

    @Test
    public void intTest() {
        // Create or retrieve an engine
        JexlEngine jexl = new JexlBuilder().create();
        // Create an expression
        String jexlExp = "string == 1";
        JexlExpression e = jexl.createExpression(jexlExp);
        // Create a context and add data
        JexlContext jc = new MapContext();
        jc.set("string", "1");
        // Now evaluate the expression, getting the result
        Object o = e.evaluate(jc);
        assert o.equals(true);

    }

    @Test
    public void doubleTest() {
        // 精度
        JexlEngine jexl1 = new JexlBuilder().create();
        String jexlExp1 = "input == 1.0";
        JexlExpression e1 = jexl1.createExpression(jexlExp1);
        JexlContext jc1 = new MapContext();
        jc1.set("input", "1.00");
        Object o1 = e1.evaluate(jc1);
        assert o1.equals(true);


        // 不同对象类型
        JexlEngine jexl2 = new JexlBuilder().create();
        String jexlExp2 = "input == 1.0";
        JexlExpression e2 = jexl2.createExpression(jexlExp2);
        JexlContext jc2 = new MapContext();
        jc2.set("input", 1L);
        Object o2 = e2.evaluate(jc2);
        assert o2.equals(true);


        // 不同对象类型
        JexlEngine jexl3 = new JexlBuilder().create();
        String jexlExp3 = "input == 1.0";
        JexlExpression e3 = jexl3.createExpression(jexlExp3);
        JexlContext jc3 = new MapContext();
        jc3.set("input", 1);
        Object o3 = e3.evaluate(jc3);
        assert o3.equals(true);
    }

    @Test
    public void objectTest() {

        // 不同对象类型
        JexlEngine jexl3 = new JexlBuilder().create();
        String jexlExp3 = "rand(totalAsset*rate/365,2)";
        JexlExpression e3 = jexl3.createExpression(jexlExp3);
        JexlContext jc3 = new MapContext();
        DemoObject o = new DemoObject();
        o.setTestColumn("demo2");
        jc3.set("demo", o);
        Object o3 = e3.evaluate(jc3);
        System.out.println(o3);
    }

    @Test
    public void bigDecimalTest() {
        BigDecimal bigDecimal = BigDecimal.valueOf(0.11115).setScale(4, RoundingMode.HALF_UP);// 不同对象类型
        JexlEngine jexl3 = new JexlBuilder().create();

        String expression = "result=BigDecimal.valueOf(cashRate).setScale(4,RoundingMode.HALF_UP)";
        JexlExpression e3 = jexl3.createExpression(expression);
        JexlContext jc3 = new MapContext();
        jc3.set("Math", Math.class);
        jc3.set("BigDecimal", BigDecimal.class);
        jc3.set("cashRate", "0.11115");
        BigDecimal result = BigDecimal.ONE;
        jc3.set("result", result);
        Object o3 = e3.evaluate(jc3);
        System.out.println(o3);
        System.out.println(result);
    }

    public static Object evaluateExpression(
            Map<String, Object> map, String[] elementArr, String expression, int margin
    ) throws Exception {

        JexlEngine jexl = new JexlBuilder().create();
        JexlOptions.setDefaultFlags();
        JexlContext jexlContext = new MapContext();
        //需要set源代码Math，否则报错

        jexlContext.set("Math", Math.class);
        //需要set源代码 BigDecimal，否则报错
        jexlContext.set("BigDecimal", BigDecimal.class);

        for (String key : elementArr) {
            Object p = map.get(key);
            Double val = 0.0;
            if (null != p) {
                BigDecimal temp = (BigDecimal) p;
                val = temp.doubleValue();
            }
            jexlContext.set(key, val);
        }
        JexlScript script = jexl.createScript(expression);
        Object obj = script.callable(jexlContext).call();
        return obj;//result.setScale ( margin, BigDecimal.ROUND_HALF_UP );
    }

    @Test
    public void test() throws Exception {
        Map<String, Object> individualIncomeTaxMap = new HashMap<>(16);
        BigDecimal value = BigDecimal.valueOf(5334.11115);
        individualIncomeTaxMap.put("cashRate", value);//.setScale(4,RoundingMode.HALF_UP));
        individualIncomeTaxMap.put("yearPremium", BigDecimal.valueOf(200000));
        String[] te = {"cashRate", "yearPremium"};
        System.out.println(evaluateExpression(individualIncomeTaxMap, te, "cashRate=0.0;var t = 20; var s = function(x, y) {x + y + t}; t = 54; s(15, 7)", 4));
        System.out.println(value);

    }

    @Test
    public void testWc() {
        String expression = """
                if(discount_record.acc_fee>500){
                     return 0;
                 } else {
                     if(charge_record.charge_amount>(500-discount_record.acc_fee)){
                     return 500-discount_record.acc_fee;
                 
                     }
                     else {
                     return charge_record.charge_amount;
                     }
                 }""";
        JexlEngine jexl3 = new JexlBuilder().create();
        JexlScript e3 = jexl3.createScript(expression);

//        JexlExpression e3 = jexl3.createExpression("contract.lastOperationEventType=='GIVE_BEGIN'");

        RatedRecord ratedRecord = new RatedRecord();
        ratedRecord.setLastCent4(BigDecimal.valueOf(2.0));
        Pricing pricing = new Pricing();
        pricing.setFixedFee(BigDecimal.valueOf(1.0));
        pricing.setEndRefValue("100");
        Contact contact = new Contact();
        contact.setServiceDay(365);
        contact.setLastOperationEventType("GIVE_BEGIN");
        JexlContext jc3 = new MapContext();
        jc3.set("ratedRecord", ratedRecord);
        jc3.set("contract", contact);
        jc3.set("pricing", pricing);
        jc3.set("x", pricing);
        Object o3 = e3.execute(jc3);
        System.out.println(o3);
    }

    @Test
    public void objectExpTest() {
        // 精度
        JexlEngine jexl1 = new JexlBuilder().create();
        String jexlExp1 = "input.test == 1.0";
        JexlExpression e1 = jexl1.createExpression(jexlExp1);
        HashMap<String, Object> m= new HashMap<>();
        m.put("x","x");
        JexlContext jc1 = new MapContext(m);
        jc1.set("input.test", null);
        Object o1 = e1.evaluate(jc1);
        System.out.println(o1);
        assert o1.equals(true);
    }


    @Test
    public void objectMathExpTest() {
        // 精度
        JexlEngine jexl1 = new JexlBuilder().create();
        String jexlExp1 = "Math.max(23.0,input.test)";
        JexlExpression e1 = jexl1.createExpression(jexlExp1);
        HashMap<String, Object> m= new HashMap<>();
        m.put("x","x");
        JexlContext jc1 = new MapContext(m);
        jc1.set("input.test", 1);
        jc1.set("Math", Math.class);
        Object o1 = e1.evaluate(jc1);
        System.out.println(o1);
        assert o1.equals(23.0);
    }


    @Test
    public void bigDecimalExpTest() {
        // 精度
        JexlEngine jexl1 = new JexlBuilder().create();
        String jexlExp1 = "new (\"java.math.BigDecimal\",input.test)";
        JexlScript e1 = jexl1.createScript(jexlExp1);
        HashMap<String, Object> m= new HashMap<>();
        m.put("x","x");
        JexlContext jc1 = new MapContext(m);
        jc1.set("input.test", 1);
        jc1.set("BigDecimal", BigDecimal.class);
        Object o1 = e1.execute(jc1);
        System.out.println(o1);
        assert o1.equals(23.0);
    }



}
