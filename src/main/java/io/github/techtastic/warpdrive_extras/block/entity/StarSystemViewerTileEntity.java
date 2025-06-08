package io.github.techtastic.warpdrive_extras.block.entity;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
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

@Optional.InterfaceList({
    @Optional.Interface(iface = "li.cil.oc.api.network.Environment", modid = "OpenComputers"),
    @Optional.Interface(iface = "dan200.computercraft.api.peripheral.IPeripheral", modid = "ComputerCraft")
})
public class StarSystemViewerTileEntity extends TileEntity implements Environment, IPeripheral {
    Node node;
    boolean addedToNetwork = false;

    public StarSystemViewerTileEntity() {

        if (Loader.isModLoaded("OpenComputers"))
            this.node = Network.newNode(this, Visibility.Network)
                .withComponent("star_system_viewer")
                .create();
    }

    @Override
    @Optional.Method(modid = "OpenComputers")
    public Node node() {
        return this.node;
    }

    @Override
    @Optional.Method(modid = "OpenComputers")
    public void onConnect(Node node) {}

    @Override
    @Optional.Method(modid = "OpenComputers")
    public void onDisconnect(Node node) {}

    @Override
    @Optional.Method(modid = "OpenComputers")
    public void onMessage(Message message) {}

    @Override
    public void updateEntity() {
        if (Loader.isModLoaded("OpenComputers") && !addedToNetwork) {
            addedToNetwork = true;
            Network.joinOrCreateNetwork(this);
        }
    }

    @Override
    public void onChunkUnload() {
        super.onChunkUnload();

        if (Loader.isModLoaded("OpenComputers") && node != null)
            node.remove();
    }

    @Override
    public void invalidate() {
        super.invalidate();

        if (Loader.isModLoaded("OpenComputers") && node != null)
            node.remove();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if (Loader.isModLoaded("OpenComputers") && node != null && node.host() == this)
            node.load(compound.getCompoundTag("oc:node"));
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        if (Loader.isModLoaded("OpenComputers") && node != null && node.host() == this) {
            final NBTTagCompound nodeNbt = new NBTTagCompound();
            node.save(nodeNbt);
            compound.setTag("oc:node", nodeNbt);
        }
    }

    @Callback
    @Optional.Method(modid = "OpenComputers")
    public Object[] view(final Context context, final Arguments arguments) {
        return new Object[]{StarSystemViewerBlock.getAllCelestialObjects()};
    }

    @Override
    @Optional.Method(modid = "ComputerCraft")
    public String getType() {
        return "star_system_viewer";
    }

    @Override
    @Optional.Method(modid = "ComputerCraft")
    public String[] getMethodNames() {
        return new String[] {"view"};
    }

    @Override
    @Optional.Method(modid = "ComputerCraft")
    public Object[] callMethod(IComputerAccess iComputerAccess, ILuaContext iLuaContext, int method, Object[] objects) throws LuaException, InterruptedException {
        if (method == 0)
            return new Object[] { StarSystemViewerBlock.getAllCelestialObjects() };
        return new Object[0];
    }

    @Override
    @Optional.Method(modid = "ComputerCraft")
    public void attach(IComputerAccess iComputerAccess) {}

    @Override
    @Optional.Method(modid = "ComputerCraft")
    public void detach(IComputerAccess iComputerAccess) {}

    @Override
    @Optional.Method(modid = "ComputerCraft")
    public boolean equals(IPeripheral iPeripheral) {
        return iPeripheral instanceof StarSystemViewerTileEntity te && te.equals((Object) this);
    }
}
