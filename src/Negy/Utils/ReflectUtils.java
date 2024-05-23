package Negy.Utils;

import java.lang.reflect.Method;

public class ReflectUtils {

    public static Object CallMethod(Class<?> getmethodcls,Object obj,String Methodname,Class<?>... args){
        try{
            Method method = getmethodcls.getDeclaredMethod(Methodname,args);
            method.setAccessible(true);
           return (Object)method.invoke(obj,args);
        }catch (Exception e){
            e.printStackTrace();
            return (Object) null;
        }
    }
    public void CallMethodByObject(Object entity,String MethodName,Class<?>... args){
        try{
            Method method = entity.getClass().getMethod(MethodName,args);
            method.setAccessible(true);
            method.invoke(entity,args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
