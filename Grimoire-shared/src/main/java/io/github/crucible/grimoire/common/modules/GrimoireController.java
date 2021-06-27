package io.github.crucible.grimoire.common.modules;

import io.github.crucible.grimoire.common.api.grimmix.Grimmix;
import io.github.crucible.grimoire.common.api.grimmix.GrimmixController;
import io.github.crucible.grimoire.common.api.grimmix.lifecycle.IConfigBuildingEvent;

/**
 * Purposeless for now, but let's keep it around for
 * the sake of having its ID reserved.
 *
 * @author Aizistral
 */

@Grimmix(id = "Grimoire", name = "Grimoire", version = "1.0.0")
public class GrimoireController extends GrimmixController {

    public GrimoireController() {
        super();
    }

    @Override
    public void buildMixinConfigs(IConfigBuildingEvent event) {
        /*
        event.createBuilder("omg/randommixins.json")
        .mixinPackage("io.github.crucible.grimoire.common.test.mixins")
        .configurationType(ConfigurationType.CORE)
        .clientMixins("client.*")
        .mixinPriority(1001)
        .required(true)
        .build();
         */
    }

}
