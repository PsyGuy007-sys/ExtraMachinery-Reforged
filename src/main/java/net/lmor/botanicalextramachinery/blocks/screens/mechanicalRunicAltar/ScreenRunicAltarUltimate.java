package net.lmor.botanicalextramachinery.blocks.screens.mechanicalRunicAltar;

import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalRunicAltar.ContainerRunicAltarUltimate;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenAddInventory;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenDrawLabelText;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenInventory;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalRunicAltar.BlockEntityRunicAltarUltimate;
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
import vazkii.botania.common.block.BotaniaBlocks;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class ScreenRunicAltarUltimate extends ExtraScreenBase<ContainerRunicAltarUltimate> {
    BlockEntityRunicAltarUltimate blockEntity;
    ScreenAddInventory screenAddInventory = new ScreenAddInventory(ScreenInventory.ULTIMATE);
    Bars bars;
    SlotInfo slotInfo;

    public ScreenRunicAltarUltimate(ContainerRunicAltarUltimate menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        bars = new Bars(this);
        slotInfo = new SlotInfo(this);

        this.imageWidth = ContainerRunicAltarUltimate.WIDTH_GUI;
        this.imageHeight = ContainerRunicAltarUltimate.HEIGHT_GUI;

        bars.setBar(AllBars.MANA);
        bars.setDrawCoord(43, 121);

        blockEntity = this.menu.getBlockEntity();

        Map<Integer, int[]> slots = new HashMap<>();
        slots.put(0, new int[] {81, 100});
        slots.put(1, new int[] {100, 100});
        slots.put(2, new int[] {119, 100});
        slots.put(3, new int[] {45, 100});
        slots.put(4, new int[] {155, 100});

        slotInfo.setCoord(slots);
        slotInfo.setTranslatableText(new String[] {
                "botanicalextramachinery.tooltip.screen.livingrock_slot",
                "botanicalextramachinery.tooltip.screen.livingrock_slot",
                "botanicalextramachinery.tooltip.screen.livingrock_slot",
                "botanicalextramachinery.tooltip.screen.upgrade_slot",
                "botanicalextramachinery.tooltip.screen.upgrade_slot"
        });
    }

    @Override
    protected ScreenAddInventory getAddInventory() {
        return screenAddInventory;
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(guiGraphics, LibResources.ULTIMATE_MECHANICAL_RUNIC_ALTAR_GUI, screenAddInventory,
                new int[] {blockEntity.getCurrentMana()}, new int[] {blockEntity.getMaxMana()}, bars, slotInfo);

        ScreenDrawLabelText.drawLabelText(guiGraphics, this.font, "block.botanicalextramachinery.ultimate_runic_altar",
                new int[] {this.leftPos, this.topPos}, new int[] {this.imageWidth, this.imageHeight}, 6);

        for (int i = 0; i < 3; i++){
            if (blockEntity.getInventory().getStackInSlot(i).isEmpty() && this.minecraft != null) {
                GhostItemRenderer.renderGhostItem(new ItemStack(BotaniaBlocks.livingrock), guiGraphics, this.leftPos + 81 + i * 18 + i, this.topPos + 100);
            }
        }

        if (blockEntity.getInventory().getStackInSlot(3).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(blockEntity.getUpgrades(), guiGraphics, this.leftPos + 45, this.topPos + 100);
        }

        if (blockEntity.getInventory().getStackInSlot(4).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(blockEntity.getUpgrades(), guiGraphics, this.leftPos + 155, this.topPos + 100);
        }

        if (blockEntity.getProgress() > 0) {
            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            guiGraphics.blit(LibResources.ULTIMATE_MECHANICAL_RUNIC_ALTAR_GUI, this.leftPos + 102, this.topPos + 41, this.imageWidth, 0, Math.round(11.0F * pct), 37);
        }

        slotInfo.renderHoveredToolTip(guiGraphics, mouseX, mouseY, blockEntity.getInventory());
        bars.renderHoveredToolTip(guiGraphics, mouseX, mouseY, blockEntity.getCurrentMana(), blockEntity.getMaxMana(), 0);
    }
}
