package io.github.techtastic.warpdrive_extras.computercraft;

import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import io.github.techtastic.warpdrive_extras.block.entity.StarSystemViewerTileEntity;
import io.github.techtastic.warpdrive_extras.util.LuaConversionMethods;
import net.minecraft.world.World;

public class WDEPeripheralProvider implements IPeripheralProvider {
    public void register() {
        ComputerCraftAPI.registerPeripheralProvider(this);
    }

    @Override
    public IPeripheral getPeripheral(World world, int x, int y, int z, int i3) {
        if (world.getTileEntity(x, y, z) instanceof StarSystemViewerTileEntity)
            return new StarSystemViewerPeripheral();
        return null;
    }

    static class StarSystemViewerPeripheral implements IPeripheral {
        @Override
        public String getType() {
            return "star_system_viewer";
        }

        @Override
        public String[] getMethodNames() {
            return new String[] {"view"};
        }

        @Override
        public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException, InterruptedException {
            if (method == 0)
                return new Object[] { LuaConversionMethods.getAllCelestialObjects() };

            return new Object[0];
        }

        @Override
        public void attach(IComputerAccess iComputerAccess) {}

        @Override
        public void detach(IComputerAccess iComputerAccess) {}

        @Override
        public boolean equals(IPeripheral iPeripheral) {
            return iPeripheral instanceof StarSystemViewerPeripheral peripheral && peripheral.equals((Object) this);
        }
    }
}
