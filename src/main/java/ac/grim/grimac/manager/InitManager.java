package ac.grim.grimac.manager;

import ac.grim.grimac.manager.init.Initable;
import ac.grim.grimac.manager.init.load.PacketEventsInit;
import ac.grim.grimac.manager.init.load.PaletteInit;
import ac.grim.grimac.manager.init.start.EventManager;
import ac.grim.grimac.manager.init.start.PacketManager;
import ac.grim.grimac.manager.init.start.TickRunner;
import ac.grim.grimac.manager.init.start.ViaBackwardsManager;
import ac.grim.grimac.manager.init.stop.TerminatePacketEvents;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.ImmutableClassToInstanceMap;

public class InitManager {
    ClassToInstanceMap<Initable> initializersOnLoad;
    ClassToInstanceMap<Initable> initializersOnStart;
    ClassToInstanceMap<Initable> initializersOnStop;

    public InitManager() {
        initializersOnLoad = new ImmutableClassToInstanceMap.Builder<Initable>()
                .put(PaletteInit.class, new PaletteInit())
                .put(PacketEventsInit.class, new PacketEventsInit())
                .build();

        initializersOnStart = new ImmutableClassToInstanceMap.Builder<Initable>()
                .put(EventManager.class, new EventManager())
                .put(PacketManager.class, new PacketManager())
                .put(ViaBackwardsManager.class, new ViaBackwardsManager())
                .put(TickRunner.class, new TickRunner())
                .build();

        initializersOnStop = new ImmutableClassToInstanceMap.Builder<Initable>()
                .put(TerminatePacketEvents.class, new TerminatePacketEvents())
                .build();
    }

    public void load() {
        for (Initable initable : initializersOnLoad.values()) {
            initable.start();
        }
    }

    public void start() {
        for (Initable initable : initializersOnStart.values()) {
            initable.start();
        }
    }

    public void stop() {
        for (Initable initable : initializersOnStop.values()) {
            initable.start();
        }
    }
}