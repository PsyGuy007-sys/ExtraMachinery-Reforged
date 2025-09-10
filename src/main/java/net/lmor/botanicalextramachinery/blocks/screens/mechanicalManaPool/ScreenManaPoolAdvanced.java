package net.lmor.botanicalextramachinery.blocks.screens.mechanicalManaPool;

import net.lmor.botanicalextramachinery.ModItems;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalManaPool.ContainerManaPoolAdvanced;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenAddInventory;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenDrawLabelText;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenInventory;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolAdvanced;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.lmor.botanicalextramachinery.gui.AllBars;
import net.lmor.botanicalextramachinery.gui.Bars;
import net.lmor.botanicalextramachinery.gui.SlotInfo;
import net.lmor.botanicalextramachinery.util.GhostItemRenderer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class ScreenManaPoolAdvanced extends ExtraScreenBase<ContainerManaPoolAdvanced> {

    BlockEntityManaPoolAdvanced blockEntity;
    ScreenAddInventory screenAddInventory = new ScreenAddInventory(ScreenInventory.ADVANCED);
    Bars bars;
    SlotInfo slotInfo;

    public ScreenManaPoolAdvanced(ContainerManaPoolAdvanced menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        bars = new Bars(this);
        slotInfo = new SlotInfo(this);

        this.imageWidth = ContainerManaPoolAdvanced.WIDTH_GUI;
        this.imageHeight = ContainerManaPoolAdvanced.HEIGHT_GUI;

        bars.setBar(AllBars.MANA);
        bars.setDrawCoord(33, 102);

        blockEntity = this.menu.getBlockEntity();

        Map<Integer, int[]> slots = new HashMap<>();
        slots.put(0, new int[] {98, 72});
        slots.put(1, new int[] {98, 36});

        slotInfo.setCoord(slots);
        slotInfo.setTranslatableText(new String[] { "botanicalextramachinery.tooltip.screen.catalyst_slot", "botanicalextramachinery.tooltip.screen.upgrade_slot"});
    }

    @Override
    protected ScreenAddInventory getAddInventory() {
        return screenAddInventory;
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(guiGraphics, LibResources.ADVANCED_MECHANICAL_MANA_POOL_GUI, screenAddInventory,
                new int[] {blockEntity.getCurrentMana()}, new int[] {blockEntity.getMaxMana()}, bars, slotInfo);

        ScreenDrawLabelText.drawLabelText(guiGraphics, this.font, "block.botanicalextramachinery.advanced_mana_pool",
                new int[] {this.leftPos, this.topPos}, new int[] {this.imageWidth, this.imageHeight}, 6);

        if (blockEntity.getInventory().getStackInSlot(0).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(blockEntity.getCatalysts().stream().map(ItemStack::new).toList(), guiGraphics, this.leftPos + 98, this.topPos + 72);
        }

        if (blockEntity.getInventory().getStackInSlot(1).isEmpty() && this.minecraft != null){
            GhostItemRenderer.renderGhostItem(new ItemStack(ModItems.catalystManaInfinity), guiGraphics, this.leftPos + 98, this.topPos + 36);
        }

        slotInfo.renderHoveredToolTip(guiGraphics, mouseX, mouseY, blockEntity.getInventory());
        bars.renderHoveredToolTip(guiGraphics, mouseX, mouseY, blockEntity.getCurrentMana(), blockEntity.getMaxMana(), 0);
    }
}
