package interview;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by shikeyue on 2017/6/22.
 */
public class ReflectPractise {


    public static String test(String a, int b) {
        return "Call in parameter1" + a + ", parameter2" + b;

    }


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IntrospectionException {
        Method method = ReflectPractise.class.getDeclaredMethod("test", String.class, int.class);
        String result = (String) method.invoke(new ReflectPractise(), "fuck", 2);
        System.out.println(result);

        Field[] fields = Node.class.getDeclaredFields();
        for (Field field: fields) {
            System.out.println("================>" + field.getName() + "\t" + field.getType());
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), Node.class);
            System.out.println(propertyDescriptor.getDisplayName());
            System.out.println(propertyDescriptor.getShortDescription());
            System.out.println(propertyDescriptor.getPropertyEditorClass());
            System.out.println(propertyDescriptor.getPropertyType());
            System.out.println(propertyDescriptor.getReadMethod());
            System.out.println(propertyDescriptor.getWriteMethod());
        }
    }

    static class Node {
        private String name;
        private String email;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

}
