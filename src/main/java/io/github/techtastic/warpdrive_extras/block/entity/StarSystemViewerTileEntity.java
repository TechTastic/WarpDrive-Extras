package io.github.techtastic.warpdrive_extras.block.entity;

import io.github.techtastic.warpdrive_extras.block.StarSystemViewerBlock;
import li.cil.oc.api.Network;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.Environment;
import li.cil.oc.api.network.Message;
import li.cil.oc.api.network.Node;
import li.cil.oc.api.network.Visibility;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class StarSystemViewerTileEntity extends TileEntity implements Environment {
    Node node;
    boolean addedToNetwork = false;

    public StarSystemViewerTileEntity() {
        this.node = Network.newNode(this, Visibility.Network)
            .withComponent("star_system_viewer")
            .create();
    }

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
    public void updateEntity() {
        if (!addedToNetwork) {
            addedToNetwork = true;
            Network.joinOrCreateNetwork(this);
        }
    }

    @Override
    public void onChunkUnload() {
        super.onChunkUnload();

        if (node != null)
            node.remove();
    }

    @Override
    public void invalidate() {
        super.invalidate();

        if (node != null)
            node.remove();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if (node != null && node.host() == this)
            node.load(compound.getCompoundTag("oc:node"));
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        if (node != null && node.host() == this) {
            final NBTTagCompound nodeNbt = new NBTTagCompound();
            node.save(nodeNbt);
            compound.setTag("oc:node", nodeNbt);
        }
    }

    @Callback
    public Object[] view(final Context context, final Arguments arguments) {
        return new Object[]{StarSystemViewerBlock.getAllCelestialObjects()};
    }
}
