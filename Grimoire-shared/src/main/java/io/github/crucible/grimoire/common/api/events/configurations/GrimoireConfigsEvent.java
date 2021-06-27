package io.github.crucible.grimoire.common.api.events.configurations;

import java.util.Collections;
import java.util.List;

import io.github.crucible.grimoire.common.api.eventbus.ICancelable;
import io.github.crucible.grimoire.common.api.events.GrimoireEvent;
import io.github.crucible.grimoire.common.api.grimmix.lifecycle.LoadingStage;
import io.github.crucible.grimoire.common.api.mixin.ConfigurationType;
import io.github.crucible.grimoire.common.api.mixin.IMixinConfiguration;

public abstract class GrimoireConfigsEvent extends GrimoireEvent {
    protected final ConfigurationType type;
    protected final LoadingStage stage;
    protected final List<IMixinConfiguration> configurations;

    public GrimoireConfigsEvent(List<IMixinConfiguration> configurations, LoadingStage stage) {
        this.configurations = configurations;
        this.stage = stage;
        this.type = stage.getAssociatedConfigurationType();
    }

    public LoadingStage getLoadingStage() {
        return this.stage;
    }

    public ConfigurationType getConfigurationsType() {
        return this.type;
    }

    public static class Pre extends GrimoireConfigsEvent implements ICancelable {

        public Pre(List<IMixinConfiguration> configurations, LoadingStage stage) {
            super(configurations, stage);
        }

        public List<IMixinConfiguration> getPreparedConfigurations() {
            return super.configurations;
        }

        public boolean removeConfiguration(IMixinConfiguration configuration) {
            return super.configurations.remove(configuration);
        }

        public void addConfiguration(IMixinConfiguration configuration) {
            super.configurations.add(configuration);
        }

    }

    public static class Post extends GrimoireConfigsEvent {
        public Post(List<IMixinConfiguration> configurations, LoadingStage stage) {
            super(configurations, stage);
        }

        public List<IMixinConfiguration> getLoadedConfigurations() {
            return Collections.unmodifiableList(super.configurations);
        }
    }

}
