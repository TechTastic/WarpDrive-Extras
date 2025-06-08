package io.github.techtastic.warpdrive_extras.opencomputers;

import io.github.techtastic.warpdrive_extras.util.LuaConversionMethods;
import li.cil.oc.api.Network;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class OCEnvironmentAbstraction implements ManagedEnvironment {
    Node node;
    boolean addedToNetwork = false;

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public void update() {}

    @Override
    public Node node() {
        return this.node;
    }

    @Override
    public void onConnect(Node node) {}

    @Override
    public void onDisconnect(Node node) {}

    @Override
    public void onMessage(Message message) {}

    @Override
    public void load(NBTTagCompound compound) {
        if (node != null && node.host() == this)
            node.load(compound.getCompoundTag("oc:node"));
    }

    @Override
    public void save(NBTTagCompound compound) {
        if (node != null && node.host() == this) {
            final NBTTagCompound nodeNbt = new NBTTagCompound();
            node.save(nodeNbt);
            compound.setTag("oc:node", nodeNbt);
        }
    }

    public static OCEnvironmentAbstraction initialize() {
        OCEnvironmentAbstraction abs = new OCEnvironmentAbstraction();

        abs.node = Network.newNode(abs, Visibility.Network)
            .withComponent("star_system_viewer")
            .create();

        return abs;
    }

    public void updateEntity(TileEntity te) {
        if (!addedToNetwork) {
            addedToNetwork = true;
            Network.joinOrCreateNetwork(te);
        }
    }

    public void onChunkUnload() {
        if (node != null)
            node.remove();
    }

    public void invalidate() {
        if (node != null)
            node.remove();
    }

    @Callback(direct = true)
    public Object[] view(final Context context, final Arguments arguments) {
        return new Object[] { LuaConversionMethods.getAllCelestialObjects() };
    }
}
