package net.lmor.botanicalextramachinery.blocks.containers.mechanicalManaInfuser;

import de.melanx.botanicalmachinery.helper.UnrestrictedOutputSlot;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenAddInventory;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenInventory;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalManaInfuser.BlockEntityManaInfuserUpgraded;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

public class ContainerManaInfuserUpgraded extends BlockEntityMenu<BlockEntityManaInfuserUpgraded> {
    public final static int WIDTH_GUI = 196;
    public final static int HEIGHT_GUI = 131;

    public ContainerManaInfuserUpgraded(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 5, 11);
        IItemHandlerModifiable inventory = this.blockEntity.getInventory();


        int index = this.addSlotBox(inventory, 0, 54, 39, 5, 18, 1, 18);
        this.addSlotBox(inventory, index, 45, 75, 6, 18, 1, 18, UnrestrictedOutputSlot::new);

        int[] x_y = ScreenAddInventory.getCoordInventorySlot(ScreenInventory.UPGRADE, WIDTH_GUI, HEIGHT_GUI);
        this.layoutPlayerInventorySlots(x_y[0], x_y[1]);
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        if (level != null && level.getBlockState(pos).isAir()) {
            player.closeContainer();
            return false;
        }
        return super.stillValid(player);
    }
}
