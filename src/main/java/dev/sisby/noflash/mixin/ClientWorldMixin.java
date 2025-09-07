package dev.sisby.noflash.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {
	@ModifyReturnValue(method = "getLightningTicksLeft", at = @At("RETURN"))
	private int noFlash(int original) {
		return 0;
	}

	@WrapWithCondition(method = "playSoundClient(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZ)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;playSound(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZJ)V"))
	private boolean noThunder(ClientWorld instance, double x, double y, double z, SoundEvent event, SoundCategory category, float volume, float pitch, boolean useDistance, long seed) {
		return !event.equals(SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT) && !event.equals(SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER);
	}
}
