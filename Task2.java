import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

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



public class Task2 {
    public static void main(String[] args) {
        
        MyClass obj = new MyClass();
        
        // Получаем все методы класса MyClass
        Method[] methods = MyClass.class.getDeclaredMethods();
        
        for(Method method : methods) 
        {
            if (Modifier.isPrivate(method.getModifiers()) || Modifier.isProtected(method.getModifiers())) 
            {
                if (method.isAnnotationPresent(Repeat.class))
                {
                    Repeat repeat_annotation = method.getAnnotation(Repeat.class);
                    int repeat_count = repeat_annotation.count();
                    
                    // Делаем приватный или защищенный метод доступным
                    method.setAccessible(true);
                    
                    // Получаем параметры метода
                    Class<?>[] param_types = method.getParameterTypes();
                    
                    // Устанавливаем аргументы для метода 
                    Object[] arguments = getArgumentsForMethod(param_types);
    
                    for (int i = 0; i < repeat_count; ++i)
                    {
                        try 
                        {
                            // Вызываем метод с параметрами
                            method.invoke(obj, arguments);
                        } 
                        catch (IllegalAccessException | InvocationTargetException e) 
                        {
                            // Обрабатываем исключение
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private static Object[] getArgumentsForMethod(Class<?>[] param_types) {
        Object[] arguments = new Object[param_types.length];
        Random random = new Random();
        
        for (int i = 0; i < param_types.length; ++i) 
        {
            Class<?> param_type = param_types[i];

            if (param_type == int.class || param_type == Integer.class)
            {
                arguments[i] = random.nextInt(10);
            }
            else if (param_type == double.class || param_type == Double.class)
            {
                arguments[i] = random.nextDouble();
            }
            else if (param_type == long.class || param_type == Long.class)
            {
                arguments[i] = random.nextLong();
            }
            else if (param_type == boolean.class || param_type == Boolean.class)
            {
                arguments[i] = random.nextBoolean();
            }
            else if (param_type == String.class)
            {
                arguments[i] = "TEST_STRING";
            }
            else if (param_type.isArray())
            {
                arguments[i] = Array.newInstance(param_type.getComponentType(), 5);
            }
            else 
            {
                try 
                {
                    arguments[i] = param_type.getConstructor().newInstance();
                }
                catch (Exception e)
                {
                    arguments[i] = null;
                }
            }
        }
        
        return arguments;
    }

}


// Output:
// Protected Method 1 (sum): 4
// Protected Method 1 (sum): 4
// Private Method 1 (sum): 0.8180849127488063
// Private Method 1 (sum): 0.8180849127488063
// Private Method 1 (sum): 0.8180849127488063
// Private Method 2 (print string): TEST_STRING
// Private Method 2 (print string): TEST_STRING