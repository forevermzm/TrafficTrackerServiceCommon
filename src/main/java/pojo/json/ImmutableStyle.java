package pojo.json;

import org.immutables.value.Value;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS) // Make it class retention for incremental compilation
@Value.Style(
        get = {"is*", "get*"}, // Detect 'get' and 'is' prefixes in accessor methods
        init = "with*", // Builder initialization methods will have 'set' prefix
        visibility = Value.Style.ImplementationVisibility.PUBLIC, // Generated class will be always public
        strictBuilder = true // Each attribute can only be set once in builder.
)
public @interface ImmutableStyle {}