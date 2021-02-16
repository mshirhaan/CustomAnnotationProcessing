package shirhaan.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationTest {

	public static void main(String[] args) {
		
		System.out.println("Processing...");
		
		int success = 0, failed = 0, total = 0, disabled = 0;
		
		Class<AnnotationExample> obj = AnnotationExample.class;
		
		// Process @CustomTypeAnnotation
		if (obj.isAnnotationPresent(CustomTypeAnnotation.class)) {
			
			Annotation annotation = obj.getAnnotation(CustomTypeAnnotation.class);
			CustomTypeAnnotation custom = (CustomTypeAnnotation) annotation;
			
			System.out.printf("%nPriority :%s", custom.priority());
			System.out.printf("%nCreatedBy :%s", custom.createdBy());
			System.out.printf("%nTags :");
			
			int tagLength = custom.tags().length;
			
			for(String tag : custom.tags()) {
				
				if (tagLength > 1) {
					System.out.println(tag + ", ");
				} else {
					System.out.println(tag);
				}
				tagLength--;
			}
			System.out.printf("%nLastModified :%s%n%n", custom.lastModified());
		}
		
		// Process @CustomMethodAnnotation
		for (Method method : obj.getDeclaredMethods()) {
			
			// if method is annotated with @CustomMethodAnnotation
			if (method.isAnnotationPresent(CustomMethodAnnotation.class)) {
				
				Annotation annotation = method.getAnnotation(CustomMethodAnnotation.class);
				CustomMethodAnnotation customMethod = (CustomMethodAnnotation) annotation;
				// if enabled = true (default)
				if (customMethod.enabled()) {
					String result = "n/a";
					
					try {
						result = (String) method.invoke(obj.newInstance());
						System.out.printf("%s - customMethod '%s' - processed %n - result: %n",
								++total,
								method.getName(), 
								result);
						success++;
					} catch (Throwable ex) {
						System.out.printf("%s - customMethod '%s' - didn't process: %s %n",
								++total,
								method.getName(),
								ex.getCause());
						failed++;
					}
				
				} else {
					System.out.printf("%s - customMethod '%s' - didn't process%n",
							++total,
							method.getName());
					disabled++;
				} // else
			} // if
		} // for
		System.out.printf("%nResult : Total : %d, Successful: %d, Failed %d, Disabled %d%n",
				total,
				success,
				failed,
				disabled);
	} // main
}
