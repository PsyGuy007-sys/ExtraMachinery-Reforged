package net.lmor.botanicalextramachinery.blocks.tiles.mechanicalManaInfuser;

import net.lmor.botanicalextramachinery.blocks.pattern.BlockEntityManaInfuserPattern;
import net.lmor.botanicalextramachinery.config.LibXServerConfig.ManaInfuserSettings.ultimateManaInfuser;
import net.lmor.botanicalextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityManaInfuserUltimate extends BlockEntityManaInfuserPattern {


    private static final int UPGRADED_1 = 0;
    private static final int UPGRADED_2 = 1;
    private static final int FIRST_INPUT_SLOT = 2;
    private static final int LAST_INPUT_SLOT = 15;
    private static final int FIRST_OUTPUT_SLOT = 16;
    private static final int LAST_OUTPUT_SLOT = 27;

    public BlockEntityManaInfuserUltimate(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, ultimateManaInfuser.manaStorage, ultimateManaInfuser.countCraft,
                new int[] {
                        FIRST_INPUT_SLOT, LAST_INPUT_SLOT, FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT, UPGRADED_1, UPGRADED_2
                },
                new SettingPattern()
                        .addConfig("craftTime", Integer.toString(ultimateManaInfuser.craftTime)));
    }
}
