package io.github.techtastic.warpdrive_extras.opencomputers;

import io.github.techtastic.warpdrive_extras.block.entity.StarSystemViewerTileEntity;
import li.cil.oc.api.API;
import li.cil.oc.api.driver.SidedBlock;
import li.cil.oc.api.network.ManagedEnvironment;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class OCEnvironmentProvider implements SidedBlock {
    public void register() {
        API.driver.add(this);
    }

    @Override
    public boolean worksWith(World world, int x, int y, int z, ForgeDirection forgeDirection) {
        return world.getTileEntity(x, y, z) instanceof StarSystemViewerTileEntity;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, int x, int y, int z, ForgeDirection forgeDirection) {
        if (world.getTileEntity(x, y, z) instanceof StarSystemViewerTileEntity te)
            return te.env;
        return null;
    }
}
