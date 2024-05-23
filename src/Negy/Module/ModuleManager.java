/*
 * Decompiled with CFR 0.152.
 */
package Negy.Module;

import Negy.Module.impl.Render.ESP;
import Negy.Module.impl.combat.*;
import Negy.Module.impl.misc.Disabler;
import Negy.Module.impl.movement.*;
import Negy.Module.impl.player.FastUse;
import Negy.Module.impl.player.Nofall;
import Negy.Module.impl.player.Regen;

import java.util.concurrent.CopyOnWriteArrayList;

public class ModuleManager {
    public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList();

    public static void AddAllModules() {
        modules.add(new KillAura());
        modules.add(new Clip());
        modules.add(new TeleportHit());
        modules.add(new Disabler());
        modules.add(new Reach());
        modules.add(new FastUse());
        modules.add(new Nofall());
        modules.add(new Step());
        modules.add(new LongJump());
        modules.add(new Highjump());
        modules.add(new Fly());
        modules.add(new ESP());
        modules.add(new Velocity());
        modules.add(new Speed());
        modules.add(new Regen());
    }
    public static CopyOnWriteArrayList<Module> getModulesByCategory(Category category) {
        CopyOnWriteArrayList<Module> modulesByCategory = new CopyOnWriteArrayList<>();
        for (Module module : modules) {
            if (module.category == category) {
                modulesByCategory.add(module);
            }
        }
        return modulesByCategory;
    }

    public static Module getModule(String moduleName) {
        String lowerCaseModuleName = moduleName.toLowerCase();

        for (Module module : modules) {
            if (module.getName().toLowerCase().equals(lowerCaseModuleName)) {
                return module;
            }
        }
        return null;
    }

}

