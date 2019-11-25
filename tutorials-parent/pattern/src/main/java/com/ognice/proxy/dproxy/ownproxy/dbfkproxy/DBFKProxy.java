package com.ognice.proxy.dproxy.ownproxy.dbfkproxy;

import com.ognice.proxy.dproxy.jdkproxy.JdkProxyTargetInterface;
import com.sun.tools.javac.util.Assert;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

public class DBFKProxy {
    private String lineChars = System.lineSeparator();
    protected DBFKInvocationHandler dbfkInvocationHandler;

    public Object newProxyInstance(DBFKClassLoader classLoader, Class<?>[] interfaces, DBFKInvocationHandler invocationHandler) {
        this.dbfkInvocationHandler = invocationHandler;
        checkInterfaces(interfaces);
        //1.动态生成java代码
        String javaCode = generateJavaSrc(interfaces);

        //2.java文件输出到磁盘

        String filePath = DBFKProxy.class.getResource("").getPath();
        File file = new File(filePath + "$DBFKProxy0.java");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(javaCode);
            fileWriter.flush();
            fileWriter.close();
            //3.java文件编译成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(null, null, Charset.forName("UTF-8"));
            Iterable iterator = standardFileManager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = compiler.getTask(null, standardFileManager, null, null, null, iterator);
            task.call();
            standardFileManager.close();
            //4.加载class文件 到jvm
            Class proxyClass = classLoader.findClass("$DBFKProxy0");
            Constructor constructor = proxyClass.getConstructor(DBFKInvocationHandler.class);
            //5.字节码重组，生成新代理对象
            return constructor.newInstance(dbfkInvocationHandler);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            file.delete();
        }
        return null;
    }

    public DBFKProxy() {
    }

    public DBFKProxy(DBFKInvocationHandler dbfkInvocationHandler) {
        this.dbfkInvocationHandler = dbfkInvocationHandler;
    }

    //检查是否为接口
    public void checkInterfaces(Class<?>[] interfaces) {
        for (Class<?> clazz : interfaces) {
            Assert.check(clazz.isInterface());
        }

    }

    private String generateJavaSrc(Class<?>[] interfaces) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("package com.ognice.proxy.dproxy.ownproxy.dbfkproxy;" + lineChars)
                .append("public final class $DBFKProxy0  extends DBFKProxy implements ").append(interfaces[0].getName()).append("{" + lineChars)
                .append("   public $DBFKProxy0(DBFKInvocationHandler dbfkInvocationHandler) {" + lineChars)
                .append("       super(dbfkInvocationHandler);" + lineChars)
                .append("   }" + lineChars);
        for (Method m : interfaces[0].getDeclaredMethods()) {
            StringBuilder parametersInitString = new StringBuilder();
            StringBuilder parametersTransString = new StringBuilder();
            StringBuilder parametersString2 = new StringBuilder();
            for (int i = 0; i < m.getParameters().length; i++) {
                parametersInitString.append(m.getParameters()[i].getType().getName() + " " + m.getParameters()[i].getName());
                parametersTransString.append(m.getParameters()[i].getName());
                parametersString2.append("Class.forName(\"" + m.getParameters()[i].getType().getClass().getName() + "\")");
                if (i != m.getParameterCount() - 1) {
                    parametersTransString.append(",");
                    parametersInitString.append(",");
                    parametersString2.append(",");
                }
            }

            stringBuilder.append("public " + m.getReturnType() + " " + m.getName() + "(").append(parametersInitString).append("){" + lineChars)
                    .append("try{")
                    .append("   super.dbfkInvocationHandler.invoke(this, " + lineChars)
                    .append("   Class.forName(\"" + m.getDeclaringClass().getName() + "\").getMethod(\"" + m.getName());
            if (m.getParameters().length > 0) {
                stringBuilder.append("\",");
                stringBuilder.append(parametersString2);
            } else {
                stringBuilder.append("\"");
            }
            stringBuilder.append(")," + lineChars)
                    .append("   new Object[] {").append(parametersTransString).append("});" + lineChars)
                    .append("}catch(Throwable e){            e.printStackTrace();" + lineChars)
                    .append("}" + lineChars)
                    .append("}" + lineChars);
        }
        stringBuilder.append("}" + lineChars);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new DBFKProxy().generateJavaSrc(new Class[]{JdkProxyTargetInterface.class}));
    }
}
