package io.github.crucible.omniconfig.gconfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.github.crucible.omniconfig.api.annotation.IAnnotationConfigRegistry;
import io.github.crucible.omniconfig.api.core.IOmniconfig;
import io.github.crucible.omniconfig.core.Omniconfig;

public class AnnotationConfigCore implements IAnnotationConfigRegistry {
    public static final AnnotationConfigCore INSTANCE = new AnnotationConfigCore();
    private final Map<Class<?>, IOmniconfig> annotationConfigMap = new HashMap<>();

    private AnnotationConfigCore() {
        // NO-OP
    }

    public void addAnnotationConfig(Class<?> configClass) {
        if (!this.annotationConfigMap.containsKey(configClass)) {
            this.annotationConfigMap.put(configClass,  new AnnotationConfigReader(configClass).read());
        } else
            throw new IllegalArgumentException("Annotation config class " + configClass + "was already registered!");
    }

    @Override
    public Collection<Class<?>> getRegisteredAnnotationConfigs() {
        return Collections.unmodifiableCollection(this.annotationConfigMap.keySet());
    }

    @Override
    public Optional<IOmniconfig> getAssociatedOmniconfig(Class<?> annotationConfigClass) {
        return Optional.ofNullable(this.annotationConfigMap.get(annotationConfigClass));
    }


}
