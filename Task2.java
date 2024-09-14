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
    private void privateMethod1(int a, int b) {
        System.out.println("Private Method 1 (sum): " + (a + b));
    }
    
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
    
    @Repeat(count = 2) 
    public void publicMethod2(String str) {
        System.out.println("Public Method 2 (print string): " + str);
    }
}



public class Task2 {
    public static void main(String[] args) throws Exception {
        
        MyClass obj = new MyClass();
        
        // Получаем все методы класса MyClass
        Method[] methods = MyClass.class.getDeclaredMethods();
        
        for(Method method : methods) 
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

    private static Object[] getArgumentsForMethod(Class<?>[] param_types) {
        Object[] arguments = new Object[param_types.length];
        
        for (int i = 0; i < param_types.length; ++i) 
        {
            if (param_types[i] == int.class)
            {
                arguments[i] = 2;
            }
            else if (param_types[i] == String.class)
            {
                arguments[i] = "This method was called";
            }
        }
        
        return arguments;
    }
}


// Output:
// Protected Method 1 (sum): 4
// Protected Method 1 (sum): 4
// Private Method 1 (sum): 4
// Private Method 1 (sum): 4
// Private Method 1 (sum): 4
// Public Method 2 (print string): This method was called
// Public Method 2 (print string): This method was called