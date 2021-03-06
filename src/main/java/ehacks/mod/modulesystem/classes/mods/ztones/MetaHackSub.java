package ehacks.mod.modulesystem.classes.mods.ztones;

import ehacks.mod.api.ModStatus;
import ehacks.mod.api.Module;
import ehacks.mod.util.InteropUtils;
import ehacks.mod.wrapper.ModuleCategory;
import ehacks.mod.wrapper.Wrapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.play.client.C17PacketCustomPayload;

public class MetaHackSub extends Module {

    public MetaHackSub() {
        super(ModuleCategory.EHACKS);
    }

    @Override
    public String getName() {
        return "MetaHackSub";
    }

    @Override
    public String getDescription() {
        return "Allows you to subtract 1 to metadata of item";
    }

    @Override
    public ModStatus getModStatus() {
        try {
            Class.forName("com.riciJak.Ztones.network.ToggleMetaData");
            return ModStatus.WORKING;
        } catch (Exception e) {
            return ModStatus.NOTWORKING;
        }
    }

    @Override
    public void onModuleEnabled() {
        try {
            Class.forName("com.riciJak.Ztones.network.ToggleMetaData").getConstructor(Boolean.TYPE);
            ByteBuf buf = Unpooled.buffer(0);
            buf.writeInt(0);
            buf.writeBoolean(false);
            C17PacketCustomPayload packet = new C17PacketCustomPayload("Ztones", buf);
            Wrapper.INSTANCE.player().sendQueue.addToSendQueue(packet);
            InteropUtils.log("Meta subtracted", this);
            this.off();
        } catch (Exception ex) {
            this.off();
        }
    }

    @Override
    public String getModName() {
        return "ZTones";
    }
}
