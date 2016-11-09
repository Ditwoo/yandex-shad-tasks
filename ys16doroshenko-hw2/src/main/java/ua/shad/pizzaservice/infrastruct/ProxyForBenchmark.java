package ua.shad.pizzaservice.infrastruct;

import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * @author andrii
 */
class ProxyForBenchmark {

    private Object bean;

    public ProxyForBenchmark(Object bean) {
        this.bean = bean;
    }

    public Object createProxy() {
        Method[] methods = bean.getClass().getMethods();
        boolean anotaionFlag = false;

        for (int i = 0; i < methods.length; i++) {
            if (methods[i].isAnnotationPresent(Benchmark.class)){
                anotaionFlag = true;
                break;
            }
        }
        if (!anotaionFlag) {
            return bean;
        }
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                ClassUtils.getAllInterfaces(bean), handler);
    }

    InvocationHandler handler = new InvocationHandler() {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Benchmark annotation = bean.getClass()
                    .getMethod(method.getName(), method.getParameterTypes())
                    .getAnnotation(Benchmark.class);

            if ((annotation != null) && annotation.active()) {
                long start = System.nanoTime();
                Object result = method.invoke(bean, args);
                long end = System.nanoTime();
                System.out.println("method: " + method.getName());
                System.out.println("benchmark for " + method.getName() + ": " + (end - start));
                return result;
            } else {
                return method.invoke(bean, args);
            }
        }

    };

}
