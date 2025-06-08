package io.github.techtastic.warpdrive_extras.computercraft;

import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import io.github.techtastic.warpdrive_extras.block.entity.StarSystemViewerTileEntity;
import net.minecraft.world.World;

public class WDEPeripheralProvider implements IPeripheralProvider {
    public void register() {
        ComputerCraftAPI.registerPeripheralProvider(this);
    }

    @Override
    public IPeripheral getPeripheral(World world, int x, int y, int z, int i3) {
        if (world.getTileEntity(x, y, z) instanceof StarSystemViewerTileEntity te)
            return te;
        return null;
    }
}
