# <img src="../.github_media/grimoire_logo_transparent.png" alt="logo"/>
In front of you lies a general-purpose Mixin loader framework, designed to serve as a way to develop and load mixins on both 1.7.10 and 1.12.2 versions of Minecraft. This is a logical continuation of [Legacy Grimoire](https://github.com/CrucibleMC/Grimoire), which used to be just a tool for loading simple patches in the form of [Grimoire-Mixins](https://github.com/CrucibleMC/Grimoire-Mixins-1.7.10), but have since evolved beyond that, to provide a convenient way of implementing mixins in any project which may require them on legacy versions of Minecraft.

## Features:
- Embedded [Sponge Mixin](https://github.com/SpongePowered/Mixin), which allows you to avoid shading full Mixin implementation into your mod. I certainly hope you are aware that shading it is an **inherently bad practice**;
- Version-independent core. Most of the Grimoire API is completely independent from Minecraft version you are using, and will work in perfectly the same way on both 1.7.10 and 1.12.2;
- Grimmix system, which serves as a way for framework implementers to declare their presence, easily handle important events and programatically communicate with other implementers if such need exists;
- Simple API that provides an option to build mixin configuration at runtime, instead of having to ship it as `.json` within modfile;
- Mixin configurations are divided in two main categories - those that target Minecraft/Forge, and those that target other mods. They need to be loaded at different time, since at the time of coremod loading where Minecraft-targeting configurations are applied no mods are discovered and added to the classpath yet. Grimoire takes care of loading each configuration at proper time, all you need is to use appropriate lifecycle event for registering your configuration, or properly specify `ConfigurationType` if you build it at runtime;
- Version-independent `EventBus` implementation, stripped of unnecessary ASM thingies Forge's bus really needs for some reason, and much more friendly to being extended;
- Included Omniconfig API, which serves as convenient version-independent a way to create config files, either via `IOmniconfigBuilder` or `@AnnotationConfig`;
- Version-dependent [EventHelper](https://github.com/gamerforEA/EventHelper) integration, which allows you to safely use `EventUtils` without rendering your mod utterly incompatible with singleplayer;
- Proper development environment support.

## Workspace Setup:
Here you can find some examples on how to setup Grimoire-dependent mod workspace:
- For 1.7.10: https://github.com/Aizistral-Studios/ForgeWorkspaceSetup/tree/1.7.10-grimmix
- For 1.12.2: https://github.com/Aizistral-Studios/ForgeWorkspaceSetup/tree/1.12.2-grimmix

You can find most details over there, but to praise what was achieved through our hard work, I will mention once more: **Grimoire has proper development environment support!** Starting a client via `runClient` command or IDE launch configuration will have Grimoire, all dependent grimmixes and that one grimmix you might be developing yourself properly loaded. Refmap generation also works perfectly fine, so no need to target production-time obfuscated names and sacrifice compatibility with development environment.

## Changelog:
As of release 3.2.0, you can find global changelog listing all Grimoire changes here: [docs/CHANGELOG.md](https://github.com/Aizistral-Studios/Grimoire/blob/master/docs/CHANGELOG.md)

## Notes on Legacy Support:
Since in version 3.+ Grimoire was rewritten basically from scratch, and system for loading framework implementers is much different now, [Grimoire-Mixins](https://github.com/CrucibleMC/Grimoire-Mixins-1.7.10) remain yet to be adapted to this new system. Current builds of Grimoire-Mixins are not loaded by default; if you need to load them, after starting client/server with Grimoire at least once procceed to `GrimoireAPI.cfg` file located in config folder within your Minecraft directory. In there, find and enable an option for legacy support:
![image](https://user-images.githubusercontent.com/47505981/124995010-ff455b00-e046-11eb-870b-3229967098ea.png)
