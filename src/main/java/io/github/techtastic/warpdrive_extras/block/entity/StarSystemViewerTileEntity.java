package io.github.techtastic.warpdrive_extras.block.entity;

import cpw.mods.fml.common.Loader;
import io.github.techtastic.warpdrive_extras.opencomputers.OCEnvironmentAbstraction;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class StarSystemViewerTileEntity extends TileEntity {
    public OCEnvironmentAbstraction env;

    public StarSystemViewerTileEntity() {
        if (Loader.isModLoaded("OpenComputers"))
            this.env = OCEnvironmentAbstraction.initialize();
    }

    @Override
    public void updateEntity() {
        if (this.env != null)
            this.env.updateEntity(this);
    }

    @Override
    public void onChunkUnload() {
        super.onChunkUnload();

        if (this.env != null)
            this.env.onChunkUnload();
    }

    @Override
    public void invalidate() {
        super.invalidate();

        if (this.env != null)
            this.env.invalidate();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if (this.env != null)
            this.env.load(compound);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        if (this.env != null)
            this.env.save(compound);
    }
}
