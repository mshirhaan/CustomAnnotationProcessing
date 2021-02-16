package shirhaan.annotation;

@CustomTypeAnnotation(
		priority = CustomTypeAnnotation.Priority.HIGH,
		createdBy = "mshirhaan",
		tags = { "java", "annotations" }
)
public class AnnotationExample {

	@CustomMethodAnnotation
	String shouldAlwaysBeProcessed() {
		return "This should always be processed";
	}
	
	@CustomMethodAnnotation
	void willThrowExecption() {
		throw new RuntimeException("This will throw an exception");
	}
	
	@CustomMethodAnnotation(enabled = false)
	void shouldNotBeProcessed() {
		throw new RuntimeException("This should never be processed");
	}
}
