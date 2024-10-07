package Controller;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Random;
import java.lang.reflect.*;

// Аннотация 
@Retention(RetentionPolicy.RUNTIME)
@interface Repeat {
    int count() default 0;  
}

// Класс с методами для аннотации
class MyClass {
    @Repeat(count = 3) 
    private void privateMethod1(double a, double b) {
        System.out.println("Private Method 1 (sum): " + (a + b));
    }
    
    @Repeat(count = 2) 
    private void privateMethod2(String str) {
        System.out.println("Private Method 2 (print string): " + str);
    }
    
    @Repeat(count = 2) 
    protected void protectedMethod1(int a, int b) {
        System.out.println("Protected Method 1 (sum): " + (a + b));
    }
    
    protected void protectedMethod2(String str) {
        System.out.println("Protected Method 2 (print string): " + str);
    }
    
    public void publicMethod1(int a, int b) {
        System.out.println("Public Method 1 (sum): " + (a + b));
    }
    
    public void publicMethod2(String str) {
        System.out.println("Public Method 2 (print string): " + str);
    }
}

// Новый класс для выполнения методов
class MethodExecutor {

    public void executeAnnotatedMethods(Object obj, int n1, double n2, String str) {
        Method[] methods = obj.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (Modifier.isPrivate(method.getModifiers()) || Modifier.isProtected(method.getModifiers())) {
                if (method.isAnnotationPresent(Repeat.class)) {
                    Repeat repeatAnnotation = method.getAnnotation(Repeat.class);
                    int repeatCount = repeatAnnotation.count();

                    method.setAccessible(true);

                    Class<?>[] paramTypes = method.getParameterTypes();
                    Object[] arguments = getArgumentsForMethod(paramTypes, n1, n2, str);

                    for (int i = 0; i < repeatCount; i++) {
                        try {
                            method.invoke(obj, arguments);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private Object[] getArgumentsForMethod(Class<?>[] paramTypes, int n1, double n2, String str) {
        Object[] arguments = new Object[paramTypes.length];
        Random random = new Random();

        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> paramType = paramTypes[i];

            if (paramType == int.class || paramType == Integer.class) {
                arguments[i] = n1;
            } else if (paramType == double.class || paramType == Double.class) {
                arguments[i] = n2;
            } else if (paramType == long.class || paramType == Long.class) {
                arguments[i] = (long) n1;
            } else if (paramType == boolean.class || paramType == Boolean.class) {
                arguments[i] = random.nextBoolean();
            } else if (paramType == String.class) {
                arguments[i] = str;
            } else if (paramType.isArray()) {
                arguments[i] = Array.newInstance(paramType.getComponentType(), 5);
            } else {
                try {
                    arguments[i] = paramType.getConstructor().newInstance();
                } catch (Exception e) {
                    arguments[i] = null;
                }
            }
        }

        return arguments;
    }
}

public class Task2 {
    public static void main(String[] args) {
        
        MyClass obj = new MyClass();
        MethodExecutor executor = new MethodExecutor(); 

        // Выполняем аннотированные методы объекта MyClass
        executor.executeAnnotatedMethods(obj, 3, 0.235, "HEEEEEY");
    }
}


